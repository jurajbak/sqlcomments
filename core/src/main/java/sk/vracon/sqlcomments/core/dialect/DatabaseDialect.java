package sk.vracon.sqlcomments.core.dialect;

import java.util.Set;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;

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
    
	/**
	 * Returns most generic class from set of classes.
	 * 
	 * @param classes classes
	 * @return most generic class
	 */
	public Type<?> getMostGenericType(Set<Type<?>> classes);

	/**
	 * Returns {@link Type} for specified SQL type.
	 * 
	 * <p>
	 * This method is making mapping between java and SQL types.
	 * </p>
	 * 
	 * @param columnMetadata database column metadata
	 * @return Type corresponding to SQL type
	 */
	public Type<?> getType(DBColumnMetadata columnMetadata);

	
	/**
	 * Sets custom type mapping for database column.
	 * 
	 * @param column column descriptor
	 * @param type custom type
	 */
	public void addCustomTypeMapping(DBColumnMetadata column, Type<?> type);

}
