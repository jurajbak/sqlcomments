package sk.vracon.sqlcomments.core.dialect;

/**
 * HSQL database dialect.
 * 
 */
public class HSQLDialect extends AbstractDatabaseDialect {

    /**
     * Returns common database name 'hsql'.
     */
    public String getDatabaseProductName() {
        return "hsql";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        StringBuilder result = new StringBuilder(sql);

        if (offset != null) {
            result.append(" offset ");
            result.append(offset);
        }
        if (limit != null) {
            result.append(" limit ");
            result.append(limit);
        }

        return result.toString();
    }
}
