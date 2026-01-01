package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.SimpleAsterix;

/**
 * SQLComments result mapper class for statement simpleAsterix.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class SimpleAsterixMapper implements ResultMapper<SimpleAsterix> {

    /**
     * Static instance of SimpleAsterixMapper class.
     */
    public static final SimpleAsterixMapper INSTANCE = new SimpleAsterixMapper();

    private Type<Integer> commType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> empnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> enameType = (Type<String>) StringType.getInstance(); 
    private Type<Date> hiredateType = (Type<Date>) DateType.getInstance(); 
    private Type<String> jobType = (Type<String>) StringType.getInstance(); 
    private Type<Integer> mgrType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> salType = (Type<Integer>) IntegerType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link SimpleAsterix}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link SimpleAsterix} filled by data from database result
     */
    public SimpleAsterix transform(ResultSet resultSet) throws SQLException {
        SimpleAsterix result = new SimpleAsterix();
        
        result.setComm(commType.readValue(resultSet, "COMM"));
        result.setDeptno(deptnoType.readValue(resultSet, "DEPTNO"));
        result.setEmpno(empnoType.readValue(resultSet, "EMPNO"));
        result.setEname(enameType.readValue(resultSet, "ENAME"));
        result.setHiredate(hiredateType.readValue(resultSet, "HIREDATE"));
        result.setJob(jobType.readValue(resultSet, "JOB"));
        result.setMgr(mgrType.readValue(resultSet, "MGR"));
        result.setSal(salType.readValue(resultSet, "SAL"));
        
        return result;
    }
}