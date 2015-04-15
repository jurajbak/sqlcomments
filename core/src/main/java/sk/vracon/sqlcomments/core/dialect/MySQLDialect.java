package sk.vracon.sqlcomments.core.dialect;

/**
 * MySQL database dialect.
 * 
 */
public class MySQLDialect implements DatabaseDialect {

    /**
     * Returns common database name 'mysql'.
     */
    public String getDatabaseProductName() {
        return "mysql";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        StringBuilder result = new StringBuilder(sql);

        result.append(" limit ");
        if (offset != null) {
            result.append(offset);
            result.append(", ");
        }
        if (limit != null) {
            result.append(limit);
        } else {
            result.append("2147483647");
        }

        return result.toString();
    }
}
