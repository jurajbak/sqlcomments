package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.FullFeatured1;

/**
 * SQLComments result mapper class for statement fullFeatured1.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class FullFeatured1Mapper implements ResultMapper<FullFeatured1> {

    /**
     * Static instance of FullFeatured1Mapper class.
     */
    public static final FullFeatured1Mapper INSTANCE = new FullFeatured1Mapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 
    private Type<String> employeenameType = (Type<String>) StringType.getInstance(); 
    private Type<Integer> empnoType = (Type<Integer>) IntegerType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link FullFeatured1}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link FullFeatured1} filled by data from database result
     */
    public FullFeatured1 transform(ResultSet resultSet) throws SQLException {
        FullFeatured1 result = new FullFeatured1();
        
        result.setDeptno(deptnoType.readValue(resultSet, "deptno"));
        result.setDname(dnameType.readValue(resultSet, "dname"));
        result.setEmployeename(employeenameType.readValue(resultSet, "EmployeeName"));
        result.setEmpno(empnoType.readValue(resultSet, "empno"));
        
        return result;
    }
}