package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.MappedPlaceholder;

/**
 * SQLComments result mapper class for statement mappedPlaceholder.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class MappedPlaceholderMapper implements ResultMapper<MappedPlaceholder> {

    /**
     * Static instance of MappedPlaceholderMapper class.
     */
    public static final MappedPlaceholderMapper INSTANCE = new MappedPlaceholderMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link MappedPlaceholder}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link MappedPlaceholder} filled by data from database result
     */
    public MappedPlaceholder transform(ResultSet resultSet) throws SQLException {
        MappedPlaceholder result = new MappedPlaceholder();
        
        result.setDeptno(deptnoType.readValue(resultSet, "deptno"));
        result.setDname(dnameType.readValue(resultSet, "dname"));
        
        return result;
    }
}