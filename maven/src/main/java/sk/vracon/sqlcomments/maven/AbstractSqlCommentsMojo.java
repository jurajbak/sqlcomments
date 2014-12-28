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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import sk.vracon.sqlcomments.core.Constants;
import sk.vracon.sqlcomments.core.IColumnMapper;
import sk.vracon.sqlcomments.core.RowInfo;
import sk.vracon.sqlcomments.core.Statement;
import sk.vracon.sqlcomments.core.StatementContainer;
import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.maven.dialect.DefaultDatabaseDialect;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptLexer;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser.ProgramContext;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.TableColumnIdentifier;
import sk.vracon.sqlcomments.maven.generate.ResultColumnInfo;
import sk.vracon.sqlcomments.maven.generate.DBColumnMetadata;
import sk.vracon.sqlcomments.maven.generate.InsertContext;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
import sk.vracon.sqlcomments.maven.generate.SelectContext;
import sk.vracon.sqlcomments.maven.generate.TableInfo;
import sk.vracon.sqlcomments.maven.sql.SQLLexer;
import sk.vracon.sqlcomments.maven.sql.SQLParser;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Column_referenceContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Row_value_predicandContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.SqlContext;

import com.google.common.io.Files;

/**
 * Abstract MOJO implementing generating result and configuration classes.
 * 
 */
public abstract class AbstractSqlCommentsMojo extends AbstractMojo {

    /**
     * Table property name - className.
     */
    public static final String TABLE_PROP_CLASS_NAME = "className";

    /**
     * Table property name - className.
     */
    public static final String TABLE_PROP_INTERFACES = "interfaces";

    /**
     * Table property name - pkGenerator.
     */
    public static final String TABLE_PROP_PK_GENERATOR = "pkGenerator";

    /**
     * Column property suffix - .javaClass.
     */
    public static final String TABLE_PROP_COLUMN_JAVA_CLASS = ".javaClass";

    /**
     * Column property suffix - .mapper.
     */
    public static final String TABLE_PROP_COLUMN_MAPPER = ".mapper";

    /**
     * The output directory where to generate files and java classes.
     * 
     * The default value is ${project.build.directory}/generated-sources/sqlcomments.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/sqlcomments")
    protected File outputDirectory;

    /**
     * Database dialect class to use.
     * 
     * The default value is {@link DefaultDatabaseDialect}.
     */
    @Parameter(required = false)
    protected String databaseDialect;

    /**
     * JDBC driver class name.
     * 
     * Class must be included in plugin dependendcies.
     */
    @Parameter(required = true)
    protected String jdbcDriverClass;

    /**
     * Database URL.
     */
    @Parameter(required = true)
    protected String databaseUrl;

    /**
     * Database credentials - username.
     */
    @Parameter(required = false)
    protected String dbUserName;

    /**
     * Database credentials - password.
     */
    @Parameter(required = false)
    protected String dbPassword;

    /**
     * Indicates whether to use standard or test class path for compilation of generated classes.
     * 
     * The default value is false - using standard class path.
     */
    @Parameter(required = false)
    protected boolean compileWithTestClasses = false;

    /**
     * Table meta data and a list of tables to include.
     * 
     * <p>
     * Map consists of pairs &lt;table name&gt; - &lt;table properties&gt;. Properties are in form {@link Properties}.
     * </p>
     * <p>
     * Currently supported properties:
     * <ul>
     * <li>pkGenerator - sequence call or other statement to use in generated insert statements instead of PK column</li>
     * <li>className - canonical domain class name used instead of generated name</li>
     * <li>[column name].javaClass - java class of a column to which result object should be mapped</li>
     * <li>[column name].mapper - class name of a value mapper used for DB object to java transformation. Mapper class
     * must implement {@link IColumnMapper}.</li>
     * </ul>
     * <p>
     * 
     */
    @Parameter(required = false)
    protected Map<String, String> tables;

    /**
     * The current Maven project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    protected MavenProject project;

    /**
     * Internal state indicates if execution had errors and should be stopped.
     */
    protected boolean hasErrors = false;

    /**
     * Database metadata. Informations about column size and type.
     * 
     * Key is a composition of <table_name>.<column.name> .
     */
    protected Map<String, DBColumnMetadata> databaseColumns;

    /**
     * Freemarker template processor.
     */
    protected TemplateProcessor templateProcessor = null;

    /**
     * Database dialect used to map SQL data types to java classes.
     */
    protected DatabaseDialect dialect;

    /**
     * Parsed table properties.
     * 
     * @see #tables
     */
    protected Map<String, Properties> tableProperties;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (databaseDialect == null) {
            dialect = new DefaultDatabaseDialect();
        } else {
            Class<?> dialectClass;
            try {
                dialectClass = Class.forName(databaseDialect);
                dialect = (DatabaseDialect) dialectClass.newInstance();
            }
            catch (Exception e) {
                throw new MojoExecutionException("Unable to create instance of dialect class " + databaseDialect + " cause: " + e.getMessage(), e);
            }
        }

        // Load database metadata
        loadDatabaseMetadata();

        // Create template processor
        templateProcessor = new TemplateProcessor(getLog());

        // Parse table properties
        tableProperties = new TreeMap<String, Properties>(String.CASE_INSENSITIVE_ORDER);
        if (tables != null) {
            String tablePropertiesString = null;
            for (String table : tables.keySet()) {
                try {
                    Properties props = new Properties();
                    tableProperties.put(table, props);

                    tablePropertiesString = tables.get(table);
                    if (tablePropertiesString != null) {
                        props.load(new StringReader(tablePropertiesString));

                    }
                }
                catch (IOException e) {
                    throw new MojoExecutionException("Configuration error for table " + table + " cause: " + e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Processes SQL file and generates result class, result mapper class and statement configuration class.
     * 
     * @param sourceDirectory
     *            source directory
     * @param resource
     *            SQL file
     */
    protected void processFile(File sourceDirectory, final String resource) {
        processFile(sourceDirectory, resource, new HashMap<String, Object>());
    }

    /**
     * Processes SQL file and generates result class, result mapper class and statement configuration class.
     * 
     * @param sourceDirectory
     *            source directory
     * @param resource
     *            SQL file
     */
    protected void processFile(File sourceDirectory, final String resource, Map<String, Object> extraTemplateModel) {

        File file = new File(sourceDirectory, resource);
        getLog().debug("Processing file: " + file.getAbsolutePath());

        try {
            // Load whole file into string
            String fileContent = Files.toString(file, Charset.defaultCharset());

            // Open reader to read declaration first
            LineNumberReader reader = new LineNumberReader(new StringReader(fileContent));

            StatementDeclaration declaration = null;
            for (String line = null; (line = reader.readLine()) != null;) {
                if (line.contains(Constants.SQLCOMMENT)) {
                    try {
                        String declarationText = line.substring(line.indexOf(Constants.SQLCOMMENT));
                        declaration = StatementDeclarationParser.parseStatementDeclaration(declarationText);
                    }
                    catch (SyntaxErrorException e) {
                        getLog().error("Declaration error in file " + resource + " line " + reader.getLineNumber() + ": " + e.getMessage());
                        hasErrors = true;
                    }
                    break;
                }
            }

            // Try to parse statement
            // Check if runtime engine will be able to parse it and compile scripts
            StatementContainer container = new StatementContainer();
            Statement statement = container.addStatement("Test", fileContent);

            if (declaration == null) {
                // Declaration was not found or there was an error
                // Stop processing
                return;
            }

            // Parse statement
            ColumnExtractorSQLQueryListener extractor = parseSQL(resource, fileContent);

            // Generate result class
            if (extractor.getPrimaryContext() instanceof SelectContext
                    && (declaration.isDefaultResultClass() || (declaration.getResultClassName() != null && declaration.getResultClassName().trim().length() > 0))) {
                generateResultClass(extractor, declaration, extraTemplateModel);
            }

            // Generate configuration class
            if (declaration.isDefaultConfigurationClass()
                    || (declaration.getConfigurationClassName() != null && declaration.getConfigurationClassName().trim().length() > 0)) {
                generateConfigurationClass(extractor, declaration, statement);
            }

        }
        catch (FileNotFoundException e) {
            getLog().error("Unable to open file: " + file.getAbsolutePath(), e);
            hasErrors = true;
        }
        catch (IOException e) {
            getLog().error("Unable to read file: " + file.getAbsolutePath(), e);
            hasErrors = true;
        }
    }

    private ColumnExtractorSQLQueryListener parseSQL(final String resource, String fileContent) {
        SQLLexer lexer = new SQLLexer(new ANTLRInputStream(fileContent));
        final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        final SQLParser parser = new SQLParser(tokenStream);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                getLog().error("Error in file " + resource + " line " + line + ":" + charPositionInLine + " " + msg);
                hasErrors = true;
            }
        });

        SqlContext sql = parser.sql();

        // Extract required informations about columns and tables
        ColumnExtractorSQLQueryListener extractor = new ColumnExtractorSQLQueryListener(getLog());

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(extractor, sql);

        // Replace asterixes with real columns
        replaceAsterixes(extractor.getPrimaryContext());

        if (getLog().isDebugEnabled()) {
            if (extractor.getPrimaryContext() instanceof SelectContext) {
                for (ResultColumnInfo column : ((SelectContext) extractor.getPrimaryContext()).getColumns()) {
                    getLog().debug(column.toString());
                }
            }
        }
        return extractor;
    }

    private Set<String> extractJavaScriptVariables(Statement statement) {
        Set<String> jsVariables = new HashSet<String>();
        // Extract script parameters
        for (RowInfo row : statement.getRows()) {
            if (row.getScriptText() != null) {
                // Script was compiled, there should be no syntax error right now
                ECMAScriptLexer lexer = new ECMAScriptLexer(new ANTLRInputStream(row.getScriptText()));
                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                ECMAScriptParser parser = new ECMAScriptParser(tokenStream);

                ProgramContext program = parser.program();

                // Extract variables
                JavaScriptVariablesExtractor extractor = new JavaScriptVariablesExtractor();

                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(extractor, program);

                jsVariables.addAll(extractor.getVariables());
            }
        }

        return jsVariables;
    }

    private void replaceAsterixes(AbstractStatementContext context) {
        // Check inner queries first
        for (TableInfo table : context.getTables()) {
            for (SelectContext subquery : table.getSubqueries()) {
                replaceAsterixes(subquery);
            }
        }

        if (!(context instanceof SelectContext)) {
            return;
        }

        SelectContext selectContext = (SelectContext) context;

        // Replace columns
        Set<ResultColumnInfo> columnList = new HashSet<ResultColumnInfo>();
        Iterator<ResultColumnInfo> iterator = selectContext.getColumns().iterator();
        for (ResultColumnInfo column = null; iterator.hasNext();) {
            column = iterator.next();
            if (!column.isAsterix()) {
                continue;
            }

            TableColumnIdentifier identifier = column.getReferences().iterator().next();

            for (TableInfo table : selectContext.getTables()) {
                if (identifier.getTableAlias() != null && !identifier.getTableAlias().equalsIgnoreCase(table.getAlias())) {
                    // Skip tables where alias does not match
                    continue;
                }

                if (table.getName() != null) {
                    // Get columns from DB metadata
                    for (DBColumnMetadata dbColumn : databaseColumns.values()) {
                        if (table.getName().equalsIgnoreCase(dbColumn.getTableName())) {
                            ResultColumnInfo columnReplacement = new ResultColumnInfo();
                            columnReplacement.setColumnName(dbColumn.getColumnName());
                            columnReplacement.setJavaIdentifier(Utils.transformToJavaIdentifier(dbColumn.getColumnName(), false));
                            columnReplacement.setJavaClass(dialect.getJavaTypeForSQL(dbColumn.getSqlType(), dbColumn.getSqlTypeName()));
                            columnReplacement.getReferences().add(new TableColumnIdentifier(identifier.getTableAlias(), dbColumn.getColumnName()));

                            columnList.add(columnReplacement);
                        }
                    }
                } else {
                    // Get columns from subquery
                    for (SelectContext subquery : table.getSubqueries()) {
                        columnList.addAll(subquery.getColumns());
                    }
                }
            }

            iterator.remove();
        }

        selectContext.getColumns().addAll(columnList);
    }

    private void generateConfigurationClass(ColumnExtractorSQLQueryListener extractor, StatementDeclaration declaration, Statement statement)
            throws IOException {

        Map<String, Set<Class<?>>> classMapping = new HashMap<String, Set<Class<?>>>();
        Map<String, PlaceholderInfo> placeholders = new HashMap<String, PlaceholderInfo>();

        // Collect all replacements
        for (RowInfo row : statement.getRows()) {
            if (row.getReplacementParameters() != null) {
                for (String replacementName : row.getReplacementParameters()) {
                    if (!classMapping.containsKey(replacementName)) {
                        Set<Class<?>> classes = new HashSet<Class<?>>();
                        // Replacements are at least String - always
                        classes.add(String.class);

                        classMapping.put(replacementName, classes);

                        PlaceholderInfo finalPlaceholder = new PlaceholderInfo();
                        finalPlaceholder.setName(replacementName);
                        placeholders.put(replacementName, finalPlaceholder);
                    }
                }
            }
        }

        // Get all possible types of placeholder value
        for (PlaceholderInfo placeholder : extractor.getPlaceholders()) {

            Set<Class<?>> classes = classMapping.get(placeholder.getName());
            if (classes == null) {
                classes = new HashSet<Class<?>>();
                classMapping.put(placeholder.getName(), classes);

                placeholders.put(placeholder.getName(), placeholder);
            }

            if (placeholder.isCollection()) {
                placeholders.get(placeholder.getName()).setCollection(true);
            }

            if (placeholder.getJavaClass() != null) {
                // Type of placeholder was clear while parsing
                classes.add(placeholder.getJavaClass());
                continue;
            }

            if (placeholder.getContext() instanceof InsertContext) {
                // Placeholders in values part of insert statement
                InsertContext context = (InsertContext) placeholder.getContext();

                if (context.getColumnIdentifiers().size() == 0) {
                    getLog().warn("Unable to define placeholder type for " + placeholder.getName() + " No columns defined in insert statement.");
                    continue;
                }

                // Get placeholder position in VALUES statement part
                ParserRuleContext columnContext = placeholder.getSqlContext();
                while (columnContext != null && !(columnContext instanceof Row_value_predicandContext)) {
                    columnContext = columnContext.getParent();
                }
                if (columnContext instanceof Row_value_predicandContext) {
                    // Found column in VALUES statement part

                    // Get column index
                    Row_value_predicandContext firstColumnCtx = columnContext.getParent().getChild(Row_value_predicandContext.class, 0);
                    int firstColumnIndex = columnContext.getParent().children.indexOf(firstColumnCtx);
                    int columnIndex = columnContext.getParent().children.indexOf(columnContext);
                    // Column definitions in insert are directly under Insert_statementContext
                    // Subtract index of first defined column - it is always even number, odd numbers have comma
                    // terminal nodes
                    columnIndex = (columnIndex - firstColumnIndex) / 2;

                    // Get column identifier by context
                    TableColumnIdentifier columnIdentifier = context.getColumnIdentifiers().get(columnIndex);

                    // Find java class by identifier
                    Class<?> javaClass = ColumnUtils.findJavaClassForColumnIdentifier(dialect, databaseColumns, placeholder.getContext(), columnIdentifier);

                    if (javaClass != null) {
                        classes.add(javaClass);
                    }

                    // Fill mapper and mapped class
                    ColumnUtils.fillMapperToPlaceholder(getLog(), databaseColumns, tableProperties, placeholder, Collections.singleton(columnIdentifier));
                }

            } else {
                // Placeholders in queries inside select, update and delete

                // Find most close column to find out java type
                Set<TableColumnIdentifier> identifiers = null;
                ParserRuleContext baseContext = placeholder.getSqlContext();
                while (baseContext != null && (identifiers == null || identifiers.size() == 0)) {

                    identifiers = extractColumnIdentifiers(baseContext);

                    baseContext = baseContext.getParent();
                }

                if (identifiers != null) {
                    for (TableColumnIdentifier identifier : identifiers) {
                        Class<?> javaClass = ColumnUtils.findJavaClassForColumnIdentifier(dialect, databaseColumns, placeholder.getContext(), identifier);

                        if (javaClass != null) {
                            classes.add(javaClass);
                        }
                    }
                }

                // Fill mapper and mapped class
                ColumnUtils.fillMapperToPlaceholder(getLog(), databaseColumns, tableProperties, placeholder, identifiers);
            }
        }

        // Extract script parameters
        Set<String> jsVariables = extractJavaScriptVariables(statement);
        for (String variable : jsVariables) {
            // Add only those JS variables, which are not placeholders nor replacements
            if (!classMapping.containsKey(variable)) {
                Set<Class<?>> classes = new HashSet<Class<?>>();
                // JavaScript variables are always Object - don't know how to decide type
                classes.add(Object.class);

                classMapping.put(variable, classes);

                PlaceholderInfo finalPlaceholder = new PlaceholderInfo();
                finalPlaceholder.setName(variable);
                placeholders.put(variable, finalPlaceholder);
            }
        }

        // Decide the most generic class from all possibilities
        for (Entry<String, Set<Class<?>>> entry : classMapping.entrySet()) {
            PlaceholderInfo placeholder = placeholders.get(entry.getKey());
            placeholder.setName(entry.getKey());

            Set<Class<?>> classes = entry.getValue();
            if (classes.size() == 0) {
                getLog().warn("Unable to detect type of placeholder " + entry.getKey());
                placeholder.setJavaClass(Object.class);
            } else {
                Class<?> type = dialect.getMostGenericClass(classes);
                placeholder.setJavaClass(type);
            }
        }

        // Sort placeholders by name to avoid unnecessary changes in generated classes
        List<PlaceholderInfo> resultPlaceholders = new ArrayList<PlaceholderInfo>(placeholders.values());
        Collections.sort(resultPlaceholders, new Comparator<PlaceholderInfo>() {
            public int compare(PlaceholderInfo o1, PlaceholderInfo o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // Generate class
        getLog().debug("Placeholders: " + resultPlaceholders);

        String configurationClassName = declaration.getConfigurationClassName();
        if (declaration.isDefaultConfigurationClass()) {
            if (declaration.getBaseClassName() == null) {
                throw new SyntaxErrorException("Base class must be defined to generate default configuration class.");
            }

            configurationClassName = "";
            int classNameIndex = declaration.getBaseClassName().lastIndexOf('.');
            if (classNameIndex > -1) {
                configurationClassName = declaration.getBaseClassName().substring(0, classNameIndex) + ".";
            }
            configurationClassName += "sqlcomments." + declaration.getName().substring(0, 1).toUpperCase() + declaration.getName().substring(1) + "Config";
        }

        templateProcessor.populateGenericConfigurationTemplate(outputDirectory, configurationClassName, declaration, resultPlaceholders);
    }

    private Set<TableColumnIdentifier> extractColumnIdentifiers(ParserRuleContext context) {
        Set<TableColumnIdentifier> identifiers = new HashSet<TableColumnIdentifier>();

        for (ParseTree child : context.children) {
            if (child instanceof TerminalNode) {
                continue;
            }
            if (child instanceof Column_referenceContext) {
                // Found reference
                Column_referenceContext ctx = (Column_referenceContext) child;

                TableColumnIdentifier identifier = new TableColumnIdentifier();
                identifier.setColumnName(ctx.name.getText());
                if (ctx.tb_name != null) {
                    identifier.setTableAlias(ctx.tb_name.getText());
                }

                identifiers.add(identifier);
            } else if (child instanceof ParserRuleContext) {
                Set<TableColumnIdentifier> childIdentifiers = extractColumnIdentifiers((ParserRuleContext) child);

                identifiers.addAll(childIdentifiers);
            }
        }

        return identifiers;
    }

    private void generateResultClass(ColumnExtractorSQLQueryListener extractor, StatementDeclaration declaration, Map<String, Object> extraTemplateModel) throws IOException {

        String resultClassName = declaration.getResultClassName();
        if (declaration.isDefaultResultClass()) {
            if (declaration.getBaseClassName() == null) {
                throw new SyntaxErrorException("Base class must be defined to generate default result class.");
            }

            resultClassName = "";
            int classNameIndex = declaration.getBaseClassName().lastIndexOf('.');
            if (classNameIndex > -1) {
                resultClassName = declaration.getBaseClassName().substring(0, classNameIndex) + ".";
            }
            resultClassName += declaration.getName().substring(0, 1).toUpperCase() + declaration.getName().substring(1);
        }

        // Assign types to columns according to database types
        SelectContext selectContext = (SelectContext) extractor.getPrimaryContext();
        for (ResultColumnInfo column : selectContext.getColumns()) {

            if (column.getJavaClass() != null) {
                continue;
            }

            Class<?> javaClass = ColumnUtils.findJavaClassForColumn(dialect, databaseColumns, selectContext, column);

            if (javaClass == null) {
                throw new SyntaxErrorException("No java type found for column " + column);
            }

            column.setJavaClass(javaClass);
        }

        // Assign mapped class from metadata
        for (ResultColumnInfo column : selectContext.getColumns()) {
            ColumnUtils.fillMapperToColumn(getLog(), databaseColumns, tableProperties, column, selectContext);
        }

        // Sort columns by name to avoid unnecessary changes in generated classes
        Collections.sort(selectContext.getColumns(), new Comparator<ResultColumnInfo>() {
            public int compare(ResultColumnInfo o1, ResultColumnInfo o2) {
                return o1.getJavaIdentifier().compareTo(o2.getJavaIdentifier());
            }
        });

        // Write result class
        templateProcessor.populateResultTemplate(outputDirectory, resultClassName, selectContext, declaration, extraTemplateModel);

        // Write result mapper class
        templateProcessor.populateResultMapperTemplate(outputDirectory, resultClassName, selectContext, declaration, extraTemplateModel);
    }

    /**
     * Loads database metadata.
     * <p>
     * This method just opens connection. Metadata are processed by {@link #extractDatabaseMetaData(DatabaseMetaData)}.
     * </p>
     * 
     * @throws MojoExecutionException
     * 
     * @see {@link #extractDatabaseMetaData(DatabaseMetaData)}
     */
    protected void loadDatabaseMetadata() throws MojoExecutionException {

        // Register JDBC driver
        try {
            Class<?> driverClass = Class.forName(jdbcDriverClass);
            getLog().info("Loaded driver class: " + driverClass.getName());

            boolean driverIsRegistered = false;
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                if (drivers.nextElement().getClass().getName().equals(driverClass.getName())) {
                    driverIsRegistered = true;
                    break;
                }
            }

            if (!driverIsRegistered) {
                getLog().info("Registering JDBC driver.");

                Driver driver = (Driver) driverClass.newInstance();

                DriverManager.registerDriver(driver);
            }
        }
        catch (ClassNotFoundException e) {
            hasErrors = true;
            throw new MojoExecutionException("Unable to load JDBC driver: " + e.getMessage(), e);
        }
        catch (InstantiationException e) {
            throw new MojoExecutionException("Unable to instantiate JDBC driver: " + e.getMessage(), e);
        }
        catch (IllegalAccessException e) {
            throw new MojoExecutionException("Unable to instantiate JDBC driver: " + e.getMessage(), e);
        }
        catch (SQLException e) {
            throw new MojoExecutionException("Unable to instantiate JDBC driver: " + e.getMessage(), e);
        }

        Connection conn = null;
        try {
            // Open a connection
            conn = DriverManager.getConnection(databaseUrl, dbUserName, dbPassword);

            // Get database metadata
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            // Read information about all columns
            extractDatabaseMetaData(databaseMetaData);
        }
        catch (SQLException e) {
            throw new MojoExecutionException("Unable to load database metadata: " + e.getMessage(), e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    getLog().error("Error while closing JDBC connection: " + e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Extract appropriate metadata from database.
     * 
     * @param databaseMetaData
     *            database metadata
     * @throws SQLException
     */
    protected void extractDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {
        ResultSet columnsRS = databaseMetaData.getColumns(null, null, null, null);

        databaseColumns = new HashMap<String, DBColumnMetadata>();
        while (columnsRS.next()) {
            DBColumnMetadata column = mapDBColumnDefinition(columnsRS);

            databaseColumns.put(column.getTableName().toLowerCase() + "." + column.getColumnName().toLowerCase(), column);
        }
        columnsRS.close();
    }

    /**
     * Maps column metadata into {@link DBColumnMetadata}.
     * 
     * @param columnsRS
     * @return
     * @throws SQLException
     */
    protected DBColumnMetadata mapDBColumnDefinition(ResultSet columnsRS) throws SQLException {
        DBColumnMetadata column = new DBColumnMetadata();
        column.setColumnName(columnsRS.getString("COLUMN_NAME"));
        column.setColumnSize(columnsRS.getInt("COLUMN_SIZE"));
        column.setDecimalDigits(columnsRS.getInt("DECIMAL_DIGITS"));
        column.setNullable(columnsRS.getBoolean("NULLABLE"));
        column.setSqlType(columnsRS.getInt("DATA_TYPE"));
        column.setSqlTypeName(columnsRS.getString("TYPE_NAME"));
        column.setTableName(columnsRS.getString("TABLE_NAME"));
        return column;
    }
}
