package sk.vracon.sqlcomments.spring;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;
import sk.vracon.sqlcomments.core.dialect.HSQLDialect;
import sk.vracon.sqlcomments.core.dialect.MySQLDialect;
import sk.vracon.sqlcomments.core.dialect.PostgreSQLDialect;

/**
 * {@link DatabaseDialect} wrapper bean.
 * <p>
 * Bean is trying to select correct database dialect according to used database.
 * </p>
 */
public class CurrentDatabaseDialectWrapper implements DatabaseDialect, InitializingBean {

    private DatabaseDialect dialect;
    private DataSource dataSource;

    public void afterPropertiesSet() throws Exception {

        // Get database name
        JdbcTemplate template = new JdbcTemplate(dataSource);
        String database = template.execute(new ConnectionCallback<String>() {
            public String doInConnection(Connection con) throws SQLException, DataAccessException {
                return con.getMetaData().getDatabaseProductName().toLowerCase();
            }
        });

        if (database.equalsIgnoreCase("HSQL Database Engine")) {
            dialect = new HSQLDialect();
        } else if (database.equalsIgnoreCase("MySQL")) {
            dialect = new MySQLDialect();
        } else if (database.equalsIgnoreCase("PostgreSQL")) {
            dialect = new PostgreSQLDialect();
        } else {
            throw new IllegalStateException("No dialect for database type: " + database);
        }
    }

    /**
     * @return
     * @see sk.vracon.sqlcomments.core.dialect.DatabaseDialect#getDatabaseProductName()
     */
    public String getDatabaseProductName() {
        return dialect.getDatabaseProductName();
    }

    /**
     * @param sql
     * @param offset
     * @param limit
     * @return
     * @see sk.vracon.sqlcomments.core.dialect.DatabaseDialect#generateSQLWithOffsetAndLimit(java.lang.String,
     *      java.lang.Long, java.lang.Long)
     */
    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {
        return dialect.generateSQLWithOffsetAndLimit(sql, offset, limit);
    }

    /**
     * Sets data source.
     * 
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
