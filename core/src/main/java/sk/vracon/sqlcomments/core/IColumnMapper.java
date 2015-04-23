package sk.vracon.sqlcomments.core;

/**
 * Column converter interface.
 * <p>
 * Column mapper is responsible for converting data returned by JDBC driver to custom Java type.
 * </p>
 * 
 * @param <T>
 *            Java type
 */
public interface IColumnMapper<T> {

    /**
     * Sets expected java class to converter.
     * <p>
     * SQL Comments library calls this method only once before first conversion call.
     * </p>
     * 
     * @param expectedJavaClass
     *            java class
     */
    public void setJavaType(Class<? extends T> expectedJavaClass);

    /**
     * Converts value returned by JDBC driver to custom java object.
     * 
     * @param dbValue
     *            value read from database, can be {code null}
     * @return converted value
     */
    public T convertToJava(Object dbValue);

    /**
     * Converts java value to value sent to JDBC driver.
     * 
     * @param javaValue
     *            java value, can be null
     * @return converted value
     */
    public Object convertToDatabase(T javaValue);
}
