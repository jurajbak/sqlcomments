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
import java.lang.reflect.Method;
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
import java.util.Map.Entry;
import java.util.Properties;
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
import org.codehaus.plexus.util.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import freemarker.template.TemplateModelException;
import sk.vracon.sqlcomments.core.Constants;
import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.RowInfo;
import sk.vracon.sqlcomments.core.Statement;
import sk.vracon.sqlcomments.core.StatementContainer;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.core.dialect.AbstractDatabaseDialect;
import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;
import sk.vracon.sqlcomments.core.dialect.HSQLDialect;
import sk.vracon.sqlcomments.core.types.ObjectOrUndefinedType;
import sk.vracon.sqlcomments.core.types.StringType;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptLexer;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser.ProgramContext;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.InsertContext;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
import sk.vracon.sqlcomments.maven.generate.ResultColumnInfo;
import sk.vracon.sqlcomments.maven.generate.SelectContext;
import sk.vracon.sqlcomments.maven.generate.TableColumnIdentifier;
import sk.vracon.sqlcomments.maven.generate.TableInfo;
import sk.vracon.sqlcomments.maven.sql.SQLLexer;
import sk.vracon.sqlcomments.maven.sql.SQLParser;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Column_referenceContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Row_value_predicandContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.SqlContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Update_targetContext;

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
	 * Root of custom column type properties - .type.
	 * <p>
	 * Configuration of column type can be done by concatenation of column name, this root value and property defined by setter in type implementation. e.g.
	 * <code>myColumn.type.enumClass=sk.vracon.sqlcomments.TestEnum</code>. Error will be thrown if no appropriate setter will be found.
	 */
	public static final String TABLE_PROP_COLUMN_TYPE = ".type";

	/**
	 * Boolean property to exclude column from generating in domain classes - .exclude.
	 * <p>
	 * E.g. <code>myColumn.exclude=true</code>
	 */
	public static final String TABLE_PROP_COLUMN_EXCLUDED = ".excluded";

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
	 * The default value is {@link AbstractDatabaseDialect}.
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
	 * Script engine name.
	 */
	@Parameter(required = false, defaultValue = "javascript")
	protected String scriptEngine = "javascript";

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
	 * Map consisting of pairs &lt;table name&gt; - &lt;table properties&gt;. Properties are in form {@link Properties}. E.g.
	 * <pre>
	 *     &lt;USERS /&gt;
	 *     &lt;COMPANIES&gt;
	 *         id.type=sk.vracon.sqlcomments.types.LongType
	 *         country.type=sk.vracon.sqlcomments.types.EnumType(sk.vracon.sqlcomments.maven.ExampleEnum)
	 *     &lt;/COMPANIES&gt;
	 *     &lt;DOCUMENTS /&gt;
	 * </pre>
	 * <p>
	 * Currently supported properties:
	 * <ul>
	 * <li>pkGenerator - sequence call or other statement to use in generated insert statements instead of PK column</li>
	 * <li>className - canonical domain class name used instead of generated name</li>
	 * <li>[column name].type - class name of a {@link Type} which implements mapping between database and java values</li>
	 * <li>[column name].excluded - optional boolean parameter, false by default. If set true, this column will be not generated (useful for columns with values generated in database).
	 * </ul>
	 */
	@Parameter(required = false)
	protected Map<String, String> tables;

	/**
	 * Table metadata configuration files to use.
	 * <p>
	 * Can contain ant-style wildcards and double wildcards.
	 * <p>
	 * Mapping file is a simple XML with root element {code}&lt;sqlcomments&gt;{/code}. Content of root element is the same as parameter tables in pom.xml. Elements are table names and content
	 * represents table metadata in {@link Properties} format. E.g.
	 * <pre>
	 * &lt;sqlcomments&gt;
	 *     &lt;USERS /&gt;
	 *     &lt;COMPANIES&gt;
	 *         id.type=sk.vracon.sqlcomments.types.LongType
	 *         country.type=sk.vracon.sqlcomments.types.EnumType(sk.vracon.sqlcomments.maven.ExampleEnum)
	 *     &lt;/COMPANIES&gt;
	 *     &lt;DOCUMENTS /&gt;
	 * &lt;/sqlcomments>
	 * </pre>
	 */
	@Parameter(required = false)
	protected String[] mappingFiles;

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
	 * Key is a composition of &lt;table_name&gt;.&lt;column.name&gt; .
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
			dialect = new HSQLDialect();
		} else {
			try {
				Class<?> dialectClass = Class.forName(databaseDialect);
				dialect = (DatabaseDialect) dialectClass.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new MojoExecutionException("Unable to create instance of dialect class " + databaseDialect + " cause: " + e.getMessage(), e);
			}
		}

		// Load database metadata
		loadDatabaseMetadata();

		// Create template processor
		try {
			templateProcessor = new TemplateProcessor(getLog());
		} catch (TemplateModelException e) {
			throw new MojoExecutionException("Failed creating FreeMarker configuration: " + e.getMessage(), e);
		}

		// Parse table properties
		loadTableProperties();
	}

	private void loadTableProperties() throws MojoExecutionException {
		tableProperties = new TreeMap<String, Properties>(String.CASE_INSENSITIVE_ORDER);

		if (mappingFiles != null) {
			try {
				for (int i = 0; i < mappingFiles.length; i++) {

					PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
					Resource[] resources = resolver.getResources(mappingFiles[i]);

					for (int j = 0; j < resources.length; j++) {
						if (!resources[j].exists()) {
							continue;
						}

						MappingFileParser.loadMappingFile(tableProperties, resources[j].getInputStream());
					}
				}
			} catch (Exception e) {
				throw new MojoExecutionException("Unable to read mapping files: " + e.getMessage(), e);
			}
		}

		if (tables != null) {
			String tablePropertiesString = null;
			for (String table : tables.keySet()) {
				try {
					Properties props = tableProperties.get(table);
					if (props == null) {
						props = new Properties();
						tableProperties.put(table, props);
					}

					tablePropertiesString = tables.get(table);
					if (tablePropertiesString != null) {
						props.load(new StringReader(tablePropertiesString));

					}
				} catch (IOException e) {
					throw new MojoExecutionException("Configuration error for table " + table + " cause: " + e.getMessage(), e);
				}
			}
		}

		// Set custom types to dialect
		for (String table : tableProperties.keySet()) {
			Properties props = tableProperties.get(table);

			for (Object propName : props.keySet()) {
				String propNameString = (String) propName;
				if (propNameString.endsWith(TABLE_PROP_COLUMN_TYPE)) {
					String columnName = propNameString.substring(0, propNameString.length() - TABLE_PROP_COLUMN_TYPE.length());

					Type<?> type = instantiateColumnCustomType(columnName, props);
					
					DBColumnMetadata column = new DBColumnMetadata();
					column.setTableName(table);
					column.setColumnName(columnName);
					
					dialect.addCustomTypeMapping(column, type);
				}
			}
		}
	}

	/**
	 * Parses configuration for DB column and returns appropriate {@link Type}.
	 * 
	 * @param columnName database column
	 * @param properties table configuration
	 * @return {@link Type} instantiated according to custom configuration or null if no custom type for column is defined
	 * @throws MojoExecutionException error while instantiating custom type class
	 */
	protected Type<?> instantiateColumnCustomType(String columnName, Properties properties) throws MojoExecutionException {
		String typeClassName = ColumnUtils.findColumnProperty(properties, columnName + TABLE_PROP_COLUMN_TYPE);
		if (typeClassName == null) {
			return null;
		}

		List<Object> params = new ArrayList<Object>();
		int paramsPosition = typeClassName.indexOf('(');
		if (paramsPosition > 0) {
			// Definition contains parameters
			String paramString = typeClassName.substring(paramsPosition+1);
			typeClassName = typeClassName.substring(0,paramsPosition).trim();
			
			// Remove trailing end bracket
			paramString = paramString.substring(0, paramString.lastIndexOf(')'));
			// Parse parameters
			
			boolean escaping = false;
			boolean inString = false;
			boolean mustBeString = false;
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < paramString.length(); i++) {
				int c = paramString.codePointAt(i);

				if(c == '\\') {
					escaping = true;
					continue;
				}
				
				if (c == '"' && !escaping) {
					inString = !inString;
					mustBeString = true;
					continue;
				}
				
				if (c == ',' && !escaping && !inString) {
					String stringValue = buffer.toString().trim();
					addParamToList(params, mustBeString, stringValue);
					buffer = new StringBuffer();
					mustBeString = false;
					continue;
				}

				escaping = false;
				buffer.append((char) c);
			}
			addParamToList(params, mustBeString, paramString);
		}
		
		// Instantiate custom type
		try {
			Object[] paramsArr = params.toArray();

			Class<?> typeClass = Class.forName(typeClassName);
			Method method = typeClass.getMethod("getInstance", Object[].class);
			
			Type<?> type = (Type<?>) method.invoke(null, new Object[] {paramsArr});
			if (paramsArr.length > 0) {
				return new CustomTypeWraper<>(type, paramsArr);
			} else {
				return type;
			}
		} catch (Exception e) {
			throw new MojoExecutionException("Unable to create instance of custom type class " + typeClassName + " cause: " + e.getMessage(), e);
		}
	}

	private void addParamToList(List<Object> params, boolean mustBeString, String stringValue) {
		if (mustBeString) {
			params.add(stringValue);
		} else {
			if (stringValue.indexOf('.') > 0) {
				try {
					double doubleValue = Double.parseDouble(stringValue);
					params.add(doubleValue);
				} catch (NumberFormatException e) {
					params.add(stringValue);
				}
			} else {
				try {
					long longValue = Long.parseLong(stringValue);
					params.add(longValue);
				} catch (NumberFormatException e) {
					params.add(stringValue);
				}
			}
		}
	}

	
	/**
	 * Processes SQL file and generates result class, result mapper class and statement configuration class.
	 * 
	 * @param sourceDirectory source directory
	 * @param resource        SQL file
	 */
	protected void processFile(File sourceDirectory, final String resource) {
		processFile(sourceDirectory, resource, new HashMap<String, Object>());
	}

	/**
	 * Processes SQL file and generates result class, result mapper class and statement configuration class.
	 * 
	 * @param sourceDirectory source directory
	 * @param resource        SQL file
	 * @param extraTemplateModel template data model
	 */
	protected void processFile(File sourceDirectory, final String resource, Map<String, Object> extraTemplateModel) {

		File file = new File(sourceDirectory, resource);
		getLog().debug("Processing file: " + file.getAbsolutePath());

		try {
			// Load whole file into string
			String fileContent = FileUtils.fileRead(file);

			// Open reader to read declaration first
			LineNumberReader reader = new LineNumberReader(new StringReader(fileContent));

			StatementDeclaration declaration = null;
			for (String line = null; (line = reader.readLine()) != null;) {
				if (line.contains(Constants.SQLCOMMENT)) {
					try {
						String declarationText = line.substring(line.indexOf(Constants.SQLCOMMENT));
						declaration = StatementDeclarationParser.parseStatementDeclaration(declarationText);
					} catch (SyntaxErrorException e) {
						getLog().error("Declaration error in file " + resource + " line " + reader.getLineNumber() + ": " + e.getMessage());
						hasErrors = true;
					}
					break;
				}
			}

			// Try to parse statement
			// Check if runtime engine will be able to parse it and compile scripts
			StatementContainer container = new StatementContainer(scriptEngine);
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
			if (declaration.isDefaultConfigurationClass() || (declaration.getConfigurationClassName() != null && declaration.getConfigurationClassName().trim().length() > 0)) {
				try {
					generateConfigurationClass(extractor, declaration, statement);
				} catch (SyntaxErrorException e) {
					throw new SyntaxErrorException("Parsing file " + declaration.getBaseClassName() + " failed. SQL: " + declaration.getName() + " Cause: " + e.getMessage(), e);
				}
			}

		} catch (FileNotFoundException e) {
			getLog().error("Unable to open file: " + file.getAbsolutePath(), e);
			hasErrors = true;
		} catch (IOException e) {
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
			boolean successfullyReplaced = false; 

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
							columnReplacement.setType(dialect.getType(dbColumn));
							columnReplacement.getReferences().add(new TableColumnIdentifier(identifier.getTableAlias(), dbColumn.getColumnName()));

							columnList.add(columnReplacement);
							successfullyReplaced = true;
						}
					}
				} else {
					// Get columns from subquery
					for (SelectContext subquery : table.getSubqueries()) {
						columnList.addAll(subquery.getColumns());
						successfullyReplaced = true;
					}
				}
			}
			
			if (!successfullyReplaced) {
				getLog().debug("Asterix identifier: " + identifier);
				throw new SyntaxErrorException("Unable to replace asterix with appropriate columns. Please check syntax or if database tables are reachable.");
			}

			iterator.remove();
		}

		selectContext.getColumns().addAll(columnList);
	}

	private void generateConfigurationClass(ColumnExtractorSQLQueryListener extractor, StatementDeclaration declaration, Statement statement) throws IOException {

		Map<String, Set<Type<?>>> placeholderToTypesMapping = new HashMap<String, Set<Type<?>>>();
		Map<String, PlaceholderInfo> placeholders = new HashMap<String, PlaceholderInfo>();

		// Collect all replacements
		for (RowInfo row : statement.getRows()) {
			if (row.getReplacementParameters() != null) {
				for (String replacementName : row.getReplacementParameters()) {
					if (!placeholderToTypesMapping.containsKey(replacementName)) {
						Set<Type<?>> classes = new HashSet<Type<?>>();
						// Replacements are at least String - always
						classes.add(StringType.getInstance());

						placeholderToTypesMapping.put(replacementName, classes);

						PlaceholderInfo finalPlaceholder = new PlaceholderInfo();
						finalPlaceholder.setName(replacementName);
						placeholders.put(replacementName, finalPlaceholder);
					}
				}
			}
		}

		// Get all possible types of placeholder value
		for (PlaceholderInfo placeholder : extractor.getPlaceholders()) {

			Set<Type<?>> types = placeholderToTypesMapping.get(placeholder.getName());
			if (types == null) {
				types = new HashSet<Type<?>>();
				placeholderToTypesMapping.put(placeholder.getName(), types);

				placeholders.put(placeholder.getName(), placeholder);
			}

			if (placeholder.isCollection()) {
				placeholders.get(placeholder.getName()).setCollection(true);
			}

			if (placeholder.getType() != null) {
				// Type of placeholder was clear while parsing
				types.add(placeholder.getType());
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

					Set<TableColumnIdentifier> identifiers = Collections.singleton(columnIdentifier);
					placeholder.setTableColumnIdentifiers(identifiers);
					placeholder.setDbColumns(ColumnUtils.findDBColumnMetadataForColumnIdentifier(databaseColumns, placeholder.getContext(), columnIdentifier));

					// Find java class by identifier
					Type<?> type = ColumnUtils.findTypeForColumnIdentifier(dialect, databaseColumns, placeholder.getContext(), columnIdentifier);

					if (type != null) {
						types.add(type);
					}
				}
			} else {
				// Placeholders in queries inside select, update and delete

				placeholder.setDbColumns(new HashSet<>());

				// Find most close column to find out java type
				Set<TableColumnIdentifier> identifiers = null;
				ParserRuleContext baseContext = placeholder.getSqlContext();
				while (baseContext != null && (identifiers == null || identifiers.size() == 0)) {

					identifiers = extractColumnIdentifiers(baseContext);

					baseContext = baseContext.getParent();
				}

				if (identifiers != null) {
					for (TableColumnIdentifier identifier : identifiers) {
						Type<?> type = ColumnUtils.findTypeForColumnIdentifier(dialect, databaseColumns, placeholder.getContext(), identifier);

						if (type != null) {
							types.add(type);
						}

						placeholder.getDbColumns().addAll(ColumnUtils.findDBColumnMetadataForColumnIdentifier(databaseColumns, placeholder.getContext(), identifier));
					}

					placeholder.setTableColumnIdentifiers(identifiers);
				}
			}
		}

		// Extract script parameters
		Set<String> jsVariables = extractJavaScriptVariables(statement);
		for (String variable : jsVariables) {
			// Add only those JS variables, which are not placeholders nor replacements
			if (!placeholderToTypesMapping.containsKey(variable)) {
				Set<Type<?>> types = new HashSet<Type<?>>();
				// JavaScript variables are always Object - don't know how to decide type
				types.add(ObjectOrUndefinedType.getInstance());

				placeholderToTypesMapping.put(variable, types);

				PlaceholderInfo finalPlaceholder = new PlaceholderInfo();
				finalPlaceholder.setName(variable);
				placeholders.put(variable, finalPlaceholder);
			}
		}

		// Decide the most generic class from all possibilities
		for (Entry<String, Set<Type<?>>> entry : placeholderToTypesMapping.entrySet()) {
			PlaceholderInfo placeholder = placeholders.get(entry.getKey());
			placeholder.setName(entry.getKey());

			Set<Type<?>> types = entry.getValue();
			if (types.size() == 0) {
				getLog().warn("Unable to detect type of placeholder " + entry.getKey());
				placeholder.setType(ObjectOrUndefinedType.getInstance());
			} else {
				Type<?> type = dialect.getMostGenericType(types);
				placeholder.setType(type);
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
			} else if (child instanceof Update_targetContext) {
				Update_targetContext ctx = (Update_targetContext) child;

				TableColumnIdentifier identifier = new TableColumnIdentifier();
				identifier.setColumnName(ctx.identifier().getText());

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

			if (column.getType() != null) {
				continue;
			}

			Type<?> type = ColumnUtils.findTypeForColumn(dialect, databaseColumns, selectContext, column);

			if (type == null) {
				throw new SyntaxErrorException("No java type found for column " + column);
			}

			column.setType(type);
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
	 * 
	 * @throws MojoExecutionException when error reading database metadata
	 * 
	 * @see #extractDatabaseMetaData(DatabaseMetaData)
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
				getLog().info("Registering JDBC driver " + driverClass.getName());

				Driver driver = (Driver) driverClass.newInstance();

				DriverManager.registerDriver(driver);
			}
		} catch (ClassNotFoundException e) {
			hasErrors = true;
			throw new MojoExecutionException("Unable to load JDBC driver: " + e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new MojoExecutionException("Unable to instantiate JDBC driver: " + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new MojoExecutionException("Unable to instantiate JDBC driver: " + e.getMessage(), e);
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			throw new MojoExecutionException("Unable to load database metadata: " + e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					getLog().error("Error while closing JDBC connection: " + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Extract appropriate metadata from database.
	 * 
	 * @param databaseMetaData database metadata
	 * @throws SQLException when error reading database metadata
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
	 * @param columnsRS result set of column metadata
	 * @return column metadata
	 * @throws SQLException when error reading database metadata 
	 */
	protected DBColumnMetadata mapDBColumnDefinition(ResultSet columnsRS) throws SQLException {
		DBColumnMetadata column = new DBColumnMetadata();

	    // 1. TABLE_CAT String => table catalog (may be null)
		//    - no mapping, ignored
		
	    // 2. TABLE_SCHEM String => table schema (may be null)
		column.setSchema(columnsRS.getString("TABLE_SCHEM"));

		// 3. TABLE_NAME String => table name
		column.setTableName(columnsRS.getString("TABLE_NAME"));

		// 4. COLUMN_NAME String => column name
		column.setColumnName(columnsRS.getString("COLUMN_NAME"));

		// 5. DATA_TYPE int => SQL type from java.sql.Types
		column.setSqlType(columnsRS.getInt("DATA_TYPE"));

		// 6. TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
		column.setSqlTypeName(columnsRS.getString("TYPE_NAME"));

		// 7. COLUMN_SIZE int => column size.
		column.setColumnSize(columnsRS.getInt("COLUMN_SIZE"));

		// 8. BUFFER_LENGTH is not used.

		// 9. DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
		column.setDecimalDigits(columnsRS.getInt("DECIMAL_DIGITS"));

		// 10. NUM_PREC_RADIX int => Radix (typically either 10 or 2)
		column.setRadix(columnsRS.getInt("NUM_PREC_RADIX"));
	    
		// 11. NULLABLE int => is NULL allowed.
	    //       columnNoNulls - might not allow NULL values
	    //       columnNullable - definitely allows NULL values
	    //       columnNullableUnknown - nullability unknown 
	    
		// 12. REMARKS String => comment describing column (may be null)
		column.setDescription(columnsRS.getString("REMARKS"));
		
	    // 13. COLUMN_DEF String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
		column.setDefaultValue(columnsRS.getString("COLUMN_DEF"));
		
	    // 14. SQL_DATA_TYPE int => unused
	    // 15. SQL_DATETIME_SUB int => unused
		
	    // 16. CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
		column.setCharOctetLength(columnsRS.getInt("CHAR_OCTET_LENGTH"));
		
	    // 17. ORDINAL_POSITION int => index of column in table (starting at 1)
		column.setOrdinalPosition(columnsRS.getInt("ORDINAL_POSITION"));
		
	    // 18. IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
	    //       YES --- if the column can include NULLs
	    //       NO --- if the column cannot include NULLs
	    //       empty string --- if the nullability for the column is unknown 
		column.setNullable(parseYesNoBoolean(columnsRS.getString("IS_NULLABLE")));
		
	    // 19. SCOPE_CATALOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
	    // 20. SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	    // 21. SCOPE_TABLE String => table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	    // 22. SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
		
	    // 23. IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
	    //       YES --- if the column is auto incremented
	    //       NO --- if the column is not auto incremented
	    //       empty string --- if it cannot be determined whether the column is auto incremented
		column.setAutoincrement(parseYesNoBoolean(columnsRS.getString("IS_AUTOINCREMENT")));
		
	    // 24. IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
	    //       YES --- if this a generated column
	    //       NO --- if this not a generated column
	    //       empty string --- if it cannot be determined whether this is a generated column 
		column.setGeneratedColumn(parseYesNoBoolean(columnsRS.getString("IS_GENERATEDCOLUMN")));
		
		return column;
	}
	
	private Boolean parseYesNoBoolean(String value) {
		if (value == null || value.trim().length() < 2) {
			return null;
		}
		if ("YES".equals(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
