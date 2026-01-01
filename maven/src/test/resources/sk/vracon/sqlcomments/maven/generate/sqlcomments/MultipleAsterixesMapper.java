package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.MultipleAsterixes;

/**
 * SQLComments result mapper class for statement multipleAsterixes.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class MultipleAsterixesMapper implements ResultMapper<MultipleAsterixes> {

    /**
     * Static instance of MultipleAsterixesMapper class.
     */
    public static final MultipleAsterixesMapper INSTANCE = new MultipleAsterixesMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 
    private Type<String> enameType = (Type<String>) StringType.getInstance(); 
    private Type<String> locType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link MultipleAsterixes}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link MultipleAsterixes} filled by data from database result
     */
    public MultipleAsterixes transform(ResultSet resultSet) throws SQLException {
        MultipleAsterixes result = new MultipleAsterixes();
        
        result.setDeptno(deptnoType.readValue(resultSet, "DEPTNO"));
        result.setDname(dnameType.readValue(resultSet, "DNAME"));
        result.setEname(enameType.readValue(resultSet, "ename"));
        result.setLoc(locType.readValue(resultSet, "LOC"));
        
        return result;
    }
}