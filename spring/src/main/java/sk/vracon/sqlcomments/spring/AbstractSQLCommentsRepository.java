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
package sk.vracon.sqlcomments.spring;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Statement;
import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.StatementContainer;
import sk.vracon.sqlcomments.core.StatementException;
import sk.vracon.sqlcomments.core.StatementGenerator;
import sk.vracon.sqlcomments.core.Utils;

/**
 * Abstract spring repository with implemented CRUD operations.
 * <p>
 * CRUD methods and {@link #findByPK(StatementConfiguration, ResultMapper) findByPK} are expecting that some
 * configuration classes and statement files already exists. Use sqlcomments maven plugin with goal <i>domain</i> to
 * generate appropriate classes and files:
 * <ul>
 * <li>domain configuration class - &lt;T&gt;Config</li>
 * <li>primary key configuration class - &lt;T&gt;PKConfig</li>
 * <li>domain mapper class - &lt;T&gt;Mapper</li>
 * <li>insert statement file - &lt;T&gt;.insert.sql</li>
 * <li>update statement file - &lt;T&gt;.update.sql</li>
 * <li>delete statement file - &lt;T&gt;.delete.sql</li>
 * <li>findByPK statement file - &lt;T&gt;.findByPK.sql</li>
 * </ul>
 * </p>
 */
public class AbstractSQLCommentsRepository extends JdbcDaoSupport {

    private StatementContainer statementContainer;

    private StatementGenerator statementGenerator = new StatementGenerator();

    /**
     * Loads instance from database by primary key.
     * <p>
     * Configuration class is using SQL statement called <i>findByPK</i> which is generated for each domain class.
     * </p>
     * 
     * @param <T>
     *            instance class
     * @param config
     *            statement configuration (usually class generated as a &lt;T&gt;PKConfig)
     * @param mapper
     *            class mapper
     * @return
     */
    protected <T> T findByPK(StatementConfiguration config, ResultMapper<T> mapper) {

        Class<?> baseClass = config.baseClass();
        if (baseClass == null) {
            throw new IllegalArgumentException("Base class must be set");
        }

        return singleResult(config, mapper);
    }

    /**
     * Inserts instance to database.
     * <p>
     * Configuration class is using SQL statement called <i>&lt;T&gt;.insert.sql</i> which is generated for each domain
     * class.
     * </p>
     * 
     * @param <T>
     *            instance class
     * @param domain
     *            domain class to be stored in DB
     * 
     * @return the same instance with primary key filled
     */
    protected <T> T insert(T domain) {
        executeCRUDOperation("insert", true, domain);
        return domain;
    }

    /**
     * Updates record in database.
     * <p>
     * Configuration class is using SQL statement called <i>&lt;T&gt;.update.sql</i> which is generated for each domain
     * class.
     * </p>
     * 
     * @param <T>
     *            instance class
     * @param domain
     *            domain class to be stored in DB
     */
    protected <T> void update(T domain) {
        executeCRUDOperation("update", false, domain);
    }

    /**
     * Deletes record from database.
     * <p>
     * Configuration class is using SQL statement called <i>&lt;T&gt;.delete.sql</i> which is generated for each domain
     * class.
     * </p>
     * 
     * @param <T>
     *            instance class
     * @param domain
     *            domain to be removed from DB
     */
    protected <T> void delete(T domain) {
        executeCRUDOperation("delete", false, domain);
    }

    /**
     * Deletes record from database.
     * <p>
     * Configuration class is using SQL statement called <i>&lt;T&gt;.delete.sql</i> which is generated for each domain
     * class.
     * </p>
     * 
     * @param configuration
     *            statement configuration (usually &lt;T&gt;PKConfig)
     */
    protected void delete(StatementConfiguration configuration) {

        Set<String> acceptNullParameters = configuration.generateParameterMap().keySet();

        PreparedStatement stmt = createPreparedStatement(configuration.baseClass(), "delete", configuration.generateParameterMap(), acceptNullParameters, null);

        try {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new StatementException("Error while executing statement: " + e.getMessage(), e);
        }
        finally {
            close(stmt);
        }
    }

    private <T> void executeCRUDOperation(String crudType, boolean replaceKeys, T domain) {

        StatementConfiguration configuration = createConfiguration(crudType, domain);

        Set<String> acceptNullParameters = configuration.generateParameterMap().keySet();

        String[] keys = null;
        if(replaceKeys) {
            try {
                keys = (String[]) configuration.getClass().getField("PRIMARY_KEY").get(null);
            }
            catch (Exception e) {
                throw new IllegalStateException(
                        "Unable to get value of static field " + configuration.getClass().getName() + ".PRIMARY_KEY. Cause: " + e.getMessage(), e);
            }
        }
        
        PreparedStatement stmt = createPreparedStatement(configuration.baseClass(), configuration.statementName(), configuration.generateParameterMap(),
                acceptNullParameters, keys);

        try {
            stmt.executeUpdate();

            if (replaceKeys) {
                replaceKeys(domain, stmt);
            }
        }
        catch (SQLException e) {
            throw new StatementException("Error while executing statement: " + e.getMessage(), e);
        }
        finally {
            close(stmt);
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

                    java.beans.Statement beanStatement = new java.beans.Statement(domain, setter, new Object[] {generatedKeys.getObject(i)});
                    beanStatement.execute();
                }
                catch (Exception e) {
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
        }
        catch (Exception e) {
            throw new StatementException("Unable to instantiate configuration class " + configClassName + " cause: " + e.getMessage(), e);
        }
    }

    /**
     * Returns list of transformed query results.
     * 
     * @param <T>
     *            result class
     * @param configuration
     *            statement configuration
     * @param mapper
     *            result mapper
     * @return list of transformed results
     * @throws StatementException
     *             thrown if JDBC call or transformation failed
     */
    @SuppressWarnings("unchecked")
    protected <T> List<T> list(StatementConfiguration configuration, ResultMapper<T> mapper) throws StatementException {
        PreparedStatement stmt = createPreparedStatement(configuration);

        boolean hasResult;
        try {
            hasResult = stmt.execute();

            if (hasResult) {
                ResultSet resultSet = null;
                try {
                    resultSet = stmt.getResultSet();

                    List<T> results = new LinkedList<T>();
                    while (resultSet.next()) {
                        T value = mapper.transform(resultSet);
                        results.add(value);
                    }
                    return results;
                }
                finally {
                    close(resultSet);
                }
            }

            return Collections.EMPTY_LIST;
        }
        catch (SQLException e) {
            throw new StatementException("Error while executing statement: " + e.getMessage(), e);
        }
        finally {
            close(stmt);
        }
    }

    /**
     * Returns single row transformed into result object.
     * 
     * @param <T>
     *            result class
     * @param configuration
     *            statement configuration
     * @param mapper
     *            result mapper
     * @return transformed result object or null if no result
     * @throws StatementException
     *             thrown if JDBC call or transformation failed or non-unique result
     */
    protected <T> T singleResult(StatementConfiguration configuration, ResultMapper<T> mapper) throws StatementException {
        PreparedStatement stmt = createPreparedStatement(configuration);

        boolean hasResult;
        try {
            hasResult = stmt.execute();

            T value = null;

            if (hasResult) {
                ResultSet resultSet = null;
                try {
                    resultSet = stmt.getResultSet();

                    if (resultSet.next()) {
                        value = mapper.transform(resultSet);
                    }
                    if (resultSet.next()) {
                        throw new StatementException("Non unique result.");
                    }
                }
                finally {
                    close(resultSet);
                }
            }

            return value;
        }
        catch (SQLException e) {
            throw new StatementException("Error while executing statement: " + e.getMessage(), e);
        }
        finally {
            close(stmt);
        }
    }

    /**
     * Creates prepared statement.]
     * <p>
     * As a base class is used <i>this</i>.
     * </p>
     * 
     * @param statementName
     *            statement simple name
     * @param parameters
     *            parameters
     * @return prepared statement
     * @throws StatementException
     *             thrown if statement creation failed
     */
    protected PreparedStatement createPreparedStatement(String statementName, Map<String, Object> parameters) throws StatementException {
        return createPreparedStatement(this.getClass(), statementName, parameters, null, null);
    }

    /**
     * Creates prepared statement.
     * <p>
     * As a base class is used <i>this</i>.
     * </p>
     * 
     * @param statementName
     *            statement simple name
     * @param parameters
     *            parameters
     * @param acceptNullParameters
     *            parameter names accepting null values
     * @return prepared statement
     * @throws StatementException
     *             thrown if statement creation failed
     */
    protected PreparedStatement createPreparedStatement(String statementName, Map<String, Object> parameters, Set<String> acceptNullParameters)
            throws StatementException {
        return createPreparedStatement(this.getClass(), statementName, parameters, acceptNullParameters, null);
    }

    /**
     * Creates prepared statement.
     * <p>
     * As a base class is used <i>this</i>.
     * </p>
     * 
     * @param configuration
     *            statement configuration
     * @return prepared statement
     * @throws StatementException
     *             thrown if statement creation failed
     */
    protected PreparedStatement createPreparedStatement(StatementConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Configuration must be set.");
        }

        return createPreparedStatement(configuration.baseClass(), configuration.statementName(), configuration.generateParameterMap(),
                configuration.generateParametersAcceptingNull(), null);
    }

    private PreparedStatement createPreparedStatement(Class<?> baseClass, String statementName, Map<String, Object> parameters,
            Set<String> acceptNullParameters, String[] keys) {

        if (statementName == null) {
            throw new IllegalArgumentException("Statement name must be set.");
        }

        Statement statement = statementContainer.getStatement(baseClass, statementName);

        return statementGenerator.createPreparedStatement(getConnection(), statement, parameters, acceptNullParameters, keys);
    }

    /**
     * Creates callable statement.
     * <p>
     * As a base class is used <i>this</i>.
     * </p>
     * 
     * @param statementName
     *            statement simple name
     * @param parameters
     *            parameters
     * @return callable statement
     * @throws StatementException
     *             thrown if statement creation failed
     */
    protected CallableStatement createCallableStatement(String statementName, Map<String, Object> parameters) throws StatementException {
        return createCallableStatement(this.getClass(), statementName, parameters, null);
    }

    /**
     * Creates callable statement.
     * <p>
     * As a base class is used <i>this</i>.
     * </p>
     * 
     * @param statementName
     *            statement simple name
     * @param parameters
     *            parameters
     * @param acceptNullParameters
     *            parameter names accepting null values
     * @return callable statement
     * @throws StatementException
     *             thrown if statement creation failed
     */
    protected CallableStatement createCallableStatement(String statementName, Map<String, Object> parameters, Set<String> acceptNullParameters)
            throws StatementException {
        return createCallableStatement(this.getClass(), statementName, parameters, acceptNullParameters);
    }

    private CallableStatement createCallableStatement(Class<?> baseClass, String statementName, Map<String, Object> parameters, Set<String> acceptNullParameters) {

        if (statementName == null) {
            throw new IllegalArgumentException("Statement name must be set.");
        }

        Statement statement = statementContainer.getStatement(baseClass, statementName);

        return statementGenerator.createPreparedCall(getConnection(), statement, parameters, acceptNullParameters);
    }

    /**
     * Helper method for closing result set.
     * 
     * @param rs
     *            result set to close
     */
    protected void close(ResultSet rs) {
        try {
            rs.close();
        }
        catch (Exception e) {
            throw new StatementException("Closing ResultSet failed: " + e.getMessage(), e);
        }
    }

    /**
     * Helper method for closing prepared statement.
     * 
     * @param stmt
     *            prepared statement to close
     */
    protected void close(PreparedStatement stmt) {
        try {
            stmt.close();
        }
        catch (Exception e) {
            throw new StatementException("Closing CallableStatement failed: " + e.getMessage(), e);
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
     * @param statementContainer
     *            statement container
     */
    public void setStatementContainer(StatementContainer statementContainer) {
        this.statementContainer = statementContainer;
    }
}
