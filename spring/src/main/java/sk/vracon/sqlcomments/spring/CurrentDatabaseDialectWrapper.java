package sk.vracon.sqlcomments.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;
import sk.vracon.sqlcomments.core.dialect.HSQLDialect;
import sk.vracon.sqlcomments.core.dialect.MariaDBDialect;
import sk.vracon.sqlcomments.core.dialect.MySQLDialect;
import sk.vracon.sqlcomments.core.dialect.OracleDialect;
import sk.vracon.sqlcomments.core.dialect.PostgreSQLDialect;

/**
 * {@link DatabaseDialect} wrapper bean.
 * <p>
 * Class is trying to select correct database dialect according to used database.
 */
public class CurrentDatabaseDialectWrapper implements DatabaseDialect, InitializingBean {

    private DatabaseDialect dialect;
	private DataSource dataSource;

	@Override
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
        } else if (database.equalsIgnoreCase("MariaDB")) {
            dialect = new MariaDBDialect();
        } else if (database.equalsIgnoreCase("PostgreSQL")) {
            dialect = new PostgreSQLDialect();
        } else if (database.equalsIgnoreCase("Oracle")) {
            dialect = new OracleDialect();
        } else {
            throw new IllegalStateException("No dialect for database type: " + database);
        }
    }

	@Override
    public String getDatabaseProductName() {
        return dialect.getDatabaseProductName();
    }

	@Override
    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {
        return dialect.generateSQLWithOffsetAndLimit(sql, offset, limit);
    }

	@Override
    public Type<?> getMostGenericType(Set<Type<?>> classes) {
		return dialect.getMostGenericType(classes);
	}

	@Override
	public Type<?> getType(DBColumnMetadata columnMetadata) {
		return dialect.getType(columnMetadata);
	}

	@Override
	public void addCustomTypeMapping(DBColumnMetadata column, Type<?> type) {
		dialect.addCustomTypeMapping(column, type);
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