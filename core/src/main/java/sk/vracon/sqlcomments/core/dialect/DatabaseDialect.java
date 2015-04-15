package sk.vracon.sqlcomments.core.dialect;

/**
 * Database dialect interface.
 * 
 */
public interface DatabaseDialect {

    /**
     * Returns database product name.
     * 
     * @return database product name
     */
    public String getDatabaseProductName();

    /**
     * Generates SQL with appropriate offset and limit settings.
     * 
     * @param sql
     *            SQL statement
     * @param offset
     *            offset or <code>null</code>
     * @param limit
     *            limit or <code>null</code>
     * @return SQL statement
     */
    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit);
}
