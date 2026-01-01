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
package sk.vracon.sqlcomments.core;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;

/**
 * Basic JDBC repository with implemented CRUD operations.
 * <p>
 * CRUD methods and {@link #findByPK(StatementConfiguration, ResultMapper) findByPK} are expecting that some configuration classes and statement files already exists. Use SQLComments Maven plugin with
 * goal <i>domain</i> to generate appropriate classes and files:
 * <ul>
 * <li>domain configuration class - &lt;T&gt;Config</li>
 * <li>primary key configuration class - &lt;T&gt;PKConfig</li>
 * <li>domain mapper class - &lt;T&gt;Mapper</li>
 * <li>insert statement file - &lt;T&gt;.insert.sql</li>
 * <li>update statement file - &lt;T&gt;.update.sql</li>
 * <li>delete statement file - &lt;T&gt;.delete.sql</li>
 * <li>findByPK statement file - &lt;T&gt;.findByPK.sql</li>
 * </ul>
 */
public class BasicSQLCommentsRepository {

	private StatementContainer statementContainer;

	private StatementGenerator statementGenerator = new StatementGenerator();

	private Connection connection;

	/**
	 * Constructs repository instance with predefined {@link StatementGenerator}.
	 * 
	 * @param connection         database connection
	 * @param statementContainer statement container with cached statements
	 */
	public BasicSQLCommentsRepository(Connection connection, StatementContainer statementContainer) {
		this.statementContainer = statementContainer;
		this.connection = connection;
	}

	/**
	 * Constructs repository instance.
	 * 
	 * @param connection         database connection
	 * @param statementContainer statement container with cached statements
	 * @param statementGenerator statement generator
	 */
	public BasicSQLCommentsRepository(Connection connection, StatementContainer statementContainer, StatementGenerator statementGenerator) {
		this.statementContainer = statementContainer;
		this.statementGenerator = statementGenerator;
		this.connection = connection;
	}

	/**
	 * Loads instance from database by primary key.
	 * <p>
	 * Configuration class is using SQL statement called <i>findByPK</i> which is generated for each domain class.
	 * </p>
	 * 
	 * @param <T>    instance class
	 * @param config statement configuration (usually class generated as a &lt;T&gt;PKConfig)
	 * @param mapper class mapper
	 * @return domain class instance filled by data or null if no result
	 * @throws SQLException       thrown if SQL statement execution failed
	 * @throws StatementException thrown if JDBC call or transformation failed or non-unique result
	 */
	public <T> T findByPK(StatementConfiguration config, ResultMapper<T> mapper) throws StatementException, SQLException {

		Class<?> baseClass = config.baseClass();
		if (baseClass == null) {
			throw new IllegalArgumentException("Base class must be set");
		}

		return singleResult(config, mapper);
	}

	/**
	 * Inserts instance to database.
	 * <p>
	 * Configuration class is using SQL statement called <i>&lt;T&gt;.insert.sql</i> which is generated for each domain class.
	 * </p>
	 * 
	 * @param <T>    instance class
	 * @param domain domain class to be stored in DB
	 * 
	 * @return the same instance with primary key filled
	 * @throws SQLException thrown if SQL statement execution failed
	 */
	public <T> T insert(T domain) throws SQLException {
		executeCRUDOperation("insert", true, domain);
		return domain;
	}

	/**
	 * Updates record in database.
	 * <p>
	 * Configuration class is using SQL statement called <i>&lt;T&gt;.update.sql</i> which is generated for each domain class.
	 * </p>
	 * 
	 * @param <T>    instance class
	 * @param domain domain class to be stored in DB
	 * @throws SQLException thrown if SQL statement execution failed
	 */
	public <T> void update(T domain) throws SQLException {
		executeCRUDOperation("update", false, domain);
	}

	/**
	 * Deletes record from database.
	 * <p>
	 * Configuration class is using SQL statement called <i>&lt;T&gt;.delete.sql</i> which is generated for each domain class.
	 * </p>
	 * 
	 * @param <T>    instance class
	 * @param domain domain to be removed from DB
	 * @throws SQLException thrown if SQL statement execution failed
	 */
	public <T> void delete(T domain) throws SQLException {
		executeCRUDOperation("delete", false, domain);
	}

	/**
	 * Deletes record from database.
	 * <p>
	 * Configuration class is using SQL statement called <i>&lt;T&gt;.delete.sql</i> which is generated for each domain class.
	 * </p>
	 * 
	 * @param configuration statement configuration (usually &lt;T&gt;PKConfig)
	 * @throws SQLException thrown if SQL statement execution failed
	 */
	public void delete(final StatementConfiguration configuration) throws SQLException {
		try (PreparedStatement ps = createPreparedStatement(configuration, false)) {
			ps.executeUpdate();
		}
	}

	private <T> void executeCRUDOperation(String crudType, final boolean replaceKeys, final T domain) throws SQLException {

		final StatementConfiguration configuration = createConfiguration(crudType, domain);

		try (PreparedStatement ps = createPreparedStatement(configuration, replaceKeys)) {

			ps.executeUpdate();

			if (replaceKeys) {
				replaceKeys(domain, ps);
			}
		}
	}

	private <T> void replaceKeys(T domain, PreparedStatement stmt) throws SQLException {

		ResultSet generatedKeys = stmt.getGeneratedKeys();

		ResultSetMetaData metaData = generatedKeys.getMetaData();
		while (generatedKeys.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				try {
					// Call setter
					String attributeName = Utils.transformToJavaIdentifier(metaData.getColumnLabel(i), true);
					String setter = "set" + attributeName;

					java.beans.Statement beanStatement = new java.beans.Statement(domain, setter, new Object[] { generatedKeys.getObject(i) });
					beanStatement.execute();
				} catch (Exception e) {
					throw new StatementException("Unable to set primary key to domain object: " + e.getMessage(), e);
				}
			}
		}
	}

	private <T> StatementConfiguration createConfiguration(String operation, T domain) {
		Class<? extends Object> domainClass = domain.getClass();
		String configClassName = domainClass.getPackage().getName() + ".sqlcomments." + domainClass.getSimpleName() + "Config";
		try {
			Class<?> configClass = Class.forName(configClassName, true, domainClass.getClassLoader());

			Constructor<?> constructor = configClass.getConstructor(String.class, domainClass);

			StatementConfiguration configuration = (StatementConfiguration) constructor.newInstance(operation, domain);

			return configuration;
		} catch (Exception e) {
			throw new StatementException("Unable to instantiate configuration class " + configClassName + " cause: " + e.getMessage(), e);
		}
	}

	/**
	 * Returns list of transformed query results.
	 * 
	 * @param <T>           result class
	 * @param configuration statement configuration
	 * @param mapper        result mapper
	 * @return list of transformed results
	 * @throws StatementException thrown if JDBC call or transformation failed
	 * @throws SQLException       thrown if SQL statement execution failed
	 */
	protected <T> List<T> list(final StatementConfiguration configuration, final ResultMapper<T> mapper) throws StatementException, SQLException {
		try (PreparedStatement ps = createPreparedStatement(configuration)) {
			try (ResultSet rs = ps.executeQuery()) {
				List<T> results = new LinkedList<T>();
				while (rs.next()) {
					T transformed = mapper.transform(rs);
					results.add(transformed);
				}

				return results;
			}
		}
	}

	/**
	 * Returns single row transformed into result object.
	 * 
	 * @param <T>           result class
	 * @param configuration statement configuration
	 * @param mapper        result mapper
	 * @return transformed result object or null if no result
	 * @throws StatementException thrown if JDBC call or transformation failed or non-unique result
	 * @throws SQLException       thrown if SQL statement execution failed
	 */
	public <T> T singleResult(final StatementConfiguration configuration, final ResultMapper<T> mapper) throws StatementException, SQLException {
		try (PreparedStatement ps = createPreparedStatement(configuration)) {
			try (ResultSet rs = ps.executeQuery()) {
				T result = null;
				if (rs.next()) {
					result = mapper.transform(rs);
				} else {
					return null;
				}
				if (rs.next()) {
					throw new StatementException("Non unique result.");
				}
				return result;
			}
		}
	}

	/**
	 * Creates prepared statement.
	 * <p>
	 * As a base class is used <i>this</i>.
	 * 
	 * @param configuration statement configuration
	 * @return prepared statement
	 * @throws StatementException thrown if statement creation failed
	 */
	public PreparedStatement createPreparedStatement(StatementConfiguration configuration) {
		return createPreparedStatement(configuration, false);
	}

	private PreparedStatement createPreparedStatement(StatementConfiguration configuration, boolean useGeneratedKeys) {
		if (configuration == null) {
			throw new IllegalArgumentException("Configuration must be set.");
		}

		if (configuration.statementName() == null) {
			throw new IllegalArgumentException("Statement name must be set.");
		}

		Statement statement = statementContainer.getStatement(configuration.baseClass(), configuration.statementName());

		return statementGenerator.createPreparedStatement(connection, statement, configuration, useGeneratedKeys);
	}

	/**
	 * Creates callable statement.
	 * <p>
	 * As a base class is used <i>this</i>.
	 * 
	 * @param connection    JDBC connection
	 * @param statementName statement simple name
	 * @param parameters    parameters
	 * @return created {@link CallableStatement}
	 * @throws StatementException thrown if statement creation failed
	 */
	public CallableStatement createCallableStatement(Connection connection, String statementName, Map<String, Object> parameters) throws StatementException {
		return createCallableStatement(connection, this.getClass(), statementName, parameters, null);
	}

	/**
	 * Creates callable statement.
	 * <p>
	 * As a base class is used <i>this</i>.
	 * 
	 * @param connection           JDBC connection
	 * @param statementName        statement simple name
	 * @param parameters           parameters
	 * @param acceptNullParameters parameter names accepting null values
	 * @return created {@link CallableStatement}
	 * @throws StatementException thrown if statement creation failed
	 */
	public CallableStatement createCallableStatement(Connection connection, String statementName, Map<String, Object> parameters, Set<String> acceptNullParameters) throws StatementException {
		return createCallableStatement(connection, this.getClass(), statementName, parameters, acceptNullParameters);
	}

	private CallableStatement createCallableStatement(Connection connection, Class<?> baseClass, String statementName, Map<String, Object> parameters, Set<String> acceptNullParameters) {

		if (statementName == null) {
			throw new IllegalArgumentException("Statement name must be set.");
		}

		Statement statement = statementContainer.getStatement(baseClass, statementName);

		return statementGenerator.createPreparedCall(connection, statement, parameters, acceptNullParameters);
	}

	/**
	 * Executes given statement.
	 * <p>
	 * Method is intended for use with update and delete SQL statements.
	 * 
	 * @see PreparedStatement#execute()
	 * 
	 * @param config statement configuration
	 * @throws SQLException thrown if SQL statement execution failed
	 */
	public void executeStatement(final StatementConfiguration config) throws SQLException {
		try (PreparedStatement ps = createPreparedStatement(config)) {
			ps.execute();
		}
	}

	/**
	 * Gets statement container.
	 * 
	 * @return statement container
	 */
	public StatementContainer getStatementContainer() {
		return statementContainer;
	}

	/**
	 * Sets statement container.
	 * 
	 * @param statementContainer statement container
	 */
	public void setStatementContainer(StatementContainer statementContainer) {
		this.statementContainer = statementContainer;
	}

	/**
	 * Sets database dialect to use.
	 * 
	 * @param dialect database dialect
	 */
	public void setDialect(DatabaseDialect dialect) {
		statementGenerator.setDialect(dialect);
	}
}