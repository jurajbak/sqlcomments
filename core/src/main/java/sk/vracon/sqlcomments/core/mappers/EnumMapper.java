package sk.vracon.sqlcomments.core.mappers;

import sk.vracon.sqlcomments.core.IColumnMapper;

/**
 * Mapper implementation for Enums.
 * <p>
 * Implementation expects String as a database type.
 */
public class EnumMapper implements IColumnMapper<Enum<?>> {

    @SuppressWarnings("rawtypes")
    private Class enumClass;

    public void setJavaType(Class<? extends Enum<?>> expectedJavaClass) {
        this.enumClass = expectedJavaClass;
    }

    @SuppressWarnings("unchecked")
    public Enum<?> convertToJava(Object dbValue) {
        if (dbValue == null) {
            return null;
        } else {
            return Enum.valueOf(enumClass, dbValue.toString());
        }
    }

    public Object convertToDatabase(Enum<?> javaValue) {
        if (javaValue == null) {
            return null;
        } else {
            return javaValue.toString();
        }
    }
}
