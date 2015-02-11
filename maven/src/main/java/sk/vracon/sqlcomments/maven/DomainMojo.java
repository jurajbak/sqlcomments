/*
 * Copyright 2014 Vracon s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.vracon.sqlcomments.maven;

import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultMavenProjectHelper;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;

@Mojo(name = "domain", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresProject = true)
public class DomainMojo extends AbstractSqlCommentsMojo {

    /**
     * A table prefix to be removed when creating domain class name.
     */
    @Parameter(required = false)
    protected String tablePrefix;

    /**
     * Target package name where generate classes.
     */
    @Parameter(required = true)
    protected String packageName;

    /**
     * Database metadata. Informations about column size and type.
     * 
     * Key is a table name.
     */
    private Map<String, Set<DBColumnMetadata>> tableColumns;

    /**
     * Database metadata. Informations about primary keys.
     * 
     * Key is a table name.
     */
    private Map<String, Set<String>> tablePrimaryKeys;

    public void execute() throws MojoExecutionException, MojoFailureException {
        // Do generic stuff first
        super.execute();

        // Process tables
        for (String table : tableColumns.keySet()) {
            processTable(table);
        }

        if (hasErrors) {
            throw new MojoExecutionException("Processing SQL comments failed. See errors above.");
        }

        // Tell Maven that there are some new source files underneath the output directory.
        if (compileWithTestClasses) {
            new DefaultMavenProjectHelper().addTestResource(project, outputDirectory.getPath(), Collections.singletonList("**/*.sql"), null);
            project.addTestCompileSourceRoot(outputDirectory.getPath());
        } else {
            new DefaultMavenProjectHelper().addResource(project, outputDirectory.getPath(), Collections.singletonList("**/*.sql"), null);
            project.addCompileSourceRoot(outputDirectory.getPath());
        }
    }

    private void processTable(String table) {

        getLog().info("Processing table: " + table);

        try {
            Properties processedTableProperties = tableProperties.get(table);
            
            // Construct class names
            String domainPackageName = packageName;
            String domainSimpleClassName;
            String domainClass = processedTableProperties.getProperty(TABLE_PROP_CLASS_NAME);
            if (domainClass == null) {
                // Domain class was not specified - generate from table name
                if (tablePrefix != null && table.toLowerCase().startsWith(tablePrefix.toLowerCase())) {
                    domainSimpleClassName = Utils.transformToJavaIdentifier(table.substring(tablePrefix.length()), true);
                } else {
                    domainSimpleClassName = Utils.transformToJavaIdentifier(table, true);
                }

                domainClass = domainPackageName + "." + domainSimpleClassName;
            } else {
                // Domain class was specified
                // Get simple base class name
                int classNameStart = domainClass.lastIndexOf('.');
                if (classNameStart < 0) {
                    classNameStart = 0;
                }
                domainSimpleClassName = domainClass.substring(classNameStart + 1);

                domainPackageName = domainClass.substring(0, classNameStart);
            }

            String configClass = "sqlcomments." + domainSimpleClassName + "Config";
            if (domainPackageName.length() > 0) {
                configClass = domainPackageName + "." + configClass;
            }

            // Create statement declaration for class generator
            StatementDeclaration declaration = new StatementDeclaration();
            declaration.setBaseClassName(domainClass);
            declaration.setResultClassName(domainClass);
            declaration.setConfigurationClassName(configClass);
            declaration.setName(domainSimpleClassName);

            // Create placeholders from columns
            List<DBColumnMetadata> columns = new ArrayList<DBColumnMetadata>(tableColumns.get(table));
            // Sort columns by name to avoid unnecessary changes in generated classes
            Collections.sort(columns, new Comparator<DBColumnMetadata>() {
                public int compare(DBColumnMetadata o1, DBColumnMetadata o2) {
                    return o1.getColumnName().compareTo(o2.getColumnName());
                }
            });

            List<PlaceholderInfo> placeholders = new ArrayList<PlaceholderInfo>(columns.size());
            for (DBColumnMetadata column : columns) {
                PlaceholderInfo placeholder = new PlaceholderInfo();
                placeholder.setName(Utils.transformToJavaIdentifier(column.getColumnName(), false));
                placeholder.setJavaClass(dialect.getJavaTypeForSQL(column.getSqlType(), column.getSqlTypeName()));

                Properties properties = tableProperties.get(table);
                placeholder.setMappedClass(ColumnUtils.findColumnProperty(properties, column.getColumnName()+TABLE_PROP_COLUMN_JAVA_CLASS));
                placeholder.setMapperClass(ColumnUtils.findColumnProperty(properties, column.getColumnName()+TABLE_PROP_COLUMN_MAPPER));
                
                placeholders.add(placeholder);
            }

            // Generate configuration class
            Map<String, Object> configTemplateData = new HashMap<String, Object>();
            configTemplateData.put("primaryKeys", tablePrimaryKeys.get(table));
            configTemplateData.put("tableColumns", columns);
            
            templateProcessor.populateDomainConfigurationTemplate(outputDirectory, declaration.getConfigurationClassName(), declaration, placeholders, configTemplateData);

            // Generate SQL files
            String fileName = domainClass.replace('.', File.separatorChar);

            Map<String, Object> templateData = templateProcessor.createGenericData(domainPackageName, domainSimpleClassName, declaration);
            templateData.put("primaryKeys", tablePrimaryKeys.get(table));
            templateData.put("table", table);
            templateData.put("columns", columns);
            templateData.put("placeholders", placeholders);
            templateData.put("tableProperties", processedTableProperties);

            templateProcessor.writeInsert(outputDirectory, fileName + ".insert.sql", templateData);

            templateProcessor.writeUpdate(outputDirectory, fileName + ".update.sql", templateData);

            templateProcessor.writeDelete(outputDirectory, fileName + ".delete.sql", templateData);

            String pkConfigClass = "sqlcomments." + domainSimpleClassName + "PKConfig";
            if (domainPackageName != null && domainPackageName.length() > 0) {
                pkConfigClass = domainPackageName + "." + pkConfigClass;
            }
            declaration.setConfigurationClassName(pkConfigClass);
            String findByPKFileName = fileName + ".findByPK.sql";
            templateProcessor.writeFindByPK(outputDirectory, findByPKFileName, templateData);

            // Process created SQL to generate domain class, mapper and PK configuration
            processedTableProperties.put("primaryKeys", tablePrimaryKeys.get(table));
            processFile(outputDirectory, findByPKFileName, (Map) processedTableProperties);

        }
        catch (IOException e) {
            getLog().error("Unable to write file: " + e.getMessage(), e);
            hasErrors = true;
        }
    }

    @Override
    protected void extractDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {
        super.extractDatabaseMetaData(databaseMetaData);

        ResultSet tablesRS = databaseMetaData.getTables(null, null, null, null);

        tableColumns = new HashMap<String, Set<DBColumnMetadata>>();
        tablePrimaryKeys = new HashMap<String, Set<String>>();

        while (tablesRS.next()) {
            String table = tablesRS.getString("TABLE_NAME");

            // Check configuration
            boolean include = false;
            for (String configuredTable : tables.keySet()) {
                if (table.equalsIgnoreCase(configuredTable)) {
                    include = true;
                    break;
                }
            }

            if (!include) {
                // Table is not included
                continue;
            }

            // Read information about all columns
            ResultSet columnsRS = databaseMetaData.getColumns(null, null, table, null);

            Set<DBColumnMetadata> columns = new HashSet<DBColumnMetadata>();
            while (columnsRS.next()) {
                columns.add(mapDBColumnDefinition(columnsRS));
            }
            columnsRS.close();
            tableColumns.put(table, columns);

            ResultSet primaryKeysRS = databaseMetaData.getPrimaryKeys(null, null, table);
            Set<String> keys = new HashSet<String>();
            while (primaryKeysRS.next()) {
                String pkTable = primaryKeysRS.getString("TABLE_NAME");
                // Check table name again - some JDBC drivers (pgjdbc-ng) ignore filter in method getPrimaryKeys
                if (pkTable.equalsIgnoreCase(table)) {
                    keys.add(primaryKeysRS.getString("COLUMN_NAME"));
                }
            }
            primaryKeysRS.close();
            tablePrimaryKeys.put(table, keys);
        }
        tablesRS.close();
    }
}
