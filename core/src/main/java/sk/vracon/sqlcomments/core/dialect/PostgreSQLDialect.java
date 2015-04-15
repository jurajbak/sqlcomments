package sk.vracon.sqlcomments.core.dialect;

/**
 * PostgreSQL database dialect.
 * 
 */
public class PostgreSQLDialect implements DatabaseDialect {

    /**
     * Returns common database name 'postgresql'.
     */
    public String getDatabaseProductName() {
        return "hsql";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        StringBuilder result = new StringBuilder(sql);

        if (limit != null) {
            result.append(" limit ");
            result.append(limit);
        }
        if (offset != null) {
            result.append(" offset ");
            result.append(offset);
        }

        return result.toString();
    }
}
