package sk.vracon.sqlcomments.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import sk.vracon.sqlcomments.core.StatementContainer;

/**
 * Helper bean to lazy configure database to {@link StatementContainer}s.
 * <p>
 * Bean requires live data source to read database product name.
 * </p>
 * <p>
 * Usage:
 * 
 * <pre>
 * &lt;bean class="sk.vracon.sqlcomments.spring.DatabaseProductNameConfigurer"&gt;
 *     &lt;property name="dataSource" ref="dataSource"/&gt;
 *     &lt;property name="container" ref="statementContainer" /&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * </p>
 * 
 * @see #setDataSource(javax.sql.DataSource)
 * @see #setContainer(StatementContainer)
 * @see DatabaseProductNameConfigurer#setContainers(List)
 */
public class DatabaseProductNameConfigurer extends JdbcDaoSupport {

    private List<StatementContainer> containers;

    @Override
    protected void initDao() throws Exception {
        super.initDao();

        if (containers == null) {
            // Nothing to do
            return;
        }

        // Get database name
        String database = getJdbcTemplate().execute(new ConnectionCallback<String>() {
            public String doInConnection(Connection con) throws SQLException, DataAccessException {
                return con.getMetaData().getDatabaseProductName().toLowerCase();
            }
        });

        // Set property to container
        for (StatementContainer container : containers) {
            container.setDatabaseProductName(database);
        }
    }

    /**
     * Sets container to initialize.
     * 
     * @param container
     *            statement container to initialize.
     */
    public void setContainer(StatementContainer container) {
        if (containers == null) {
            containers = new ArrayList<StatementContainer>();
        }
        containers.add(container);
    }

    /**
     * Sets containers to initialize.
     * 
     * @param containers
     *            statement containers to initialize.
     */
    public void setContainers(List<StatementContainer> containers) {
        this.containers = containers;
    }
}
