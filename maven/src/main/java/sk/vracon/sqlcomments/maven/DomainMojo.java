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
import java.io.StringReader;
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

import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.maven.generate.DBColumnDefinition;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;

@Mojo(name = "domain", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresProject = true)
public class DomainMojo extends AbstractSqlCommentsMojo {

    /**
     * A list of tables to include.
     * 
     * <p>
     * Map consists of pairs &lt;table name&gt; - &lt;table properties&gt;. Properties are in form {@link Properties}.
     * </p>
     * <p>
     * Currently supported properties:
     * <ul>
     * <li>pkGenerator - sequence call or other statement to use in generated insert statements instead of PK column</li>
     * </ul>
     * <p>
     * 
     */
    @Parameter(required = false)
    protected Map<String, String> tables;

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
    private Map<String, Set<DBColumnDefinition>> tableColumns;

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
            String baseClassName;
            if (tablePrefix != null && table.toLowerCase().startsWith(tablePrefix.toLowerCase())) {
                baseClassName = Utils.transformToJavaIdentifier(table.substring(tablePrefix.length()), true);
            } else {
                baseClassName = Utils.transformToJavaIdentifier(table, true);
            }
            String resultClass = packageName + "." + baseClassName;
            String configClass = packageName + ".sqlcomments." + baseClassName + "Config";

            StatementDeclaration declaration = new StatementDeclaration();
            declaration.setBaseClassName(resultClass);
            declaration.setResultClassName(resultClass);
            declaration.setConfigurationClassName(configClass);
            declaration.setName(baseClassName);

            // Create placeholders from columns
            List<DBColumnDefinition> columns = new ArrayList<DBColumnDefinition>(tableColumns.get(table));
            // Sort columns by name to avoid unnecessary changes in generated classes
            Collections.sort(columns, new Comparator<DBColumnDefinition>() {
                public int compare(DBColumnDefinition o1, DBColumnDefinition o2) {
                    return o1.getColumnName().compareTo(o2.getColumnName());
                }
            });

            List<PlaceholderInfo> placeholders = new ArrayList<PlaceholderInfo>(columns.size());
            for (DBColumnDefinition column : columns) {
                PlaceholderInfo placeholder = new PlaceholderInfo();
                placeholder.setName(Utils.transformToJavaIdentifier(column.getColumnName(), false));
                placeholder.setJavaClass(dialect.getJavaTypeForSQL(column.getSqlType(), column.getSqlTypeName()));

                placeholders.add(placeholder);
            }

            Properties tableProperties = new Properties();
            String tablePropertiesString = tables.get(table);
            if (tablePropertiesString != null) {
                tableProperties.load(new StringReader(tablePropertiesString));
            }

            // Create template data
            Map<String, Object> templateData = templateProcessor.createGenericData(packageName, baseClassName, declaration);
            templateData.put("table", table);
            templateData.put("columns", columns);
            templateData.put("placeholders", placeholders);
            templateData.put("primaryKeys", tablePrimaryKeys.get(table));
            templateData.put("tableProperties", tableProperties);

            String fileName = packageName.replace('.', File.separatorChar) + File.separatorChar + baseClassName;

            // Generate configuration class
            templateProcessor.populateDomainConfigurationTemplate(outputDirectory, declaration.getConfigurationClassName(), declaration, placeholders);

            // Generate SQL files
            templateProcessor.writeInsert(outputDirectory, fileName + ".insert.sql", templateData);

            templateProcessor.writeUpdate(outputDirectory, fileName + ".update.sql", templateData);

            templateProcessor.writeDelete(outputDirectory, fileName + ".delete.sql", templateData);

            declaration.setConfigurationClassName(packageName + ".sqlcomments." + baseClassName + "PKConfig");
            String findByPKFileName = fileName + ".findByPK.sql";
            templateProcessor.writeFindByPK(outputDirectory, findByPKFileName, templateData);

            // Process created SQL to generate domain class, mapper and PK configuration
            processFile(outputDirectory, findByPKFileName);

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

        tableColumns = new HashMap<String, Set<DBColumnDefinition>>();
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

            Set<DBColumnDefinition> columns = new HashSet<DBColumnDefinition>();
            while (columnsRS.next()) {
                columns.add(mapDBColumnDefinition(columnsRS));
            }
            columnsRS.close();
            tableColumns.put(table, columns);

            ResultSet primaryKeysRS = databaseMetaData.getPrimaryKeys(null, null, table);
            Set<String> keys = new HashSet<String>();
            while (primaryKeysRS.next()) {
                keys.add(primaryKeysRS.getString("COLUMN_NAME"));
            }
            primaryKeysRS.close();
            tablePrimaryKeys.put(table, keys);
        }
        tablesRS.close();
    }
}
