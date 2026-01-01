package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.SimpleSelect;

/**
 * SQLComments result mapper class for statement simpleSelect.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class SimpleSelectMapper implements ResultMapper<SimpleSelect> {

    /**
     * Static instance of SimpleSelectMapper class.
     */
    public static final SimpleSelectMapper INSTANCE = new SimpleSelectMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link SimpleSelect}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link SimpleSelect} filled by data from database result
     */
    public SimpleSelect transform(ResultSet resultSet) throws SQLException {
        SimpleSelect result = new SimpleSelect();
        
        result.setDeptno(deptnoType.readValue(resultSet, "deptno"));
        result.setDname(dnameType.readValue(resultSet, "dname"));
        
        return result;
    }
}