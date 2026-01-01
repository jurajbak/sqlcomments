package sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.MariaDB.Emp;

/**
 * SQLComments result mapper class for statement findByPK.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class EmpMapper implements ResultMapper<Emp> {

    /**
     * Static instance of EmpMapper class.
     */
    public static final EmpMapper INSTANCE = new EmpMapper();

    private Type<Integer> commType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> empnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> enameType = (Type<String>) StringType.getInstance(); 
    private Type<Date> hiredateType = (Type<Date>) DateType.getInstance(); 
    private Type<String> jobType = (Type<String>) StringType.getInstance(); 
    private Type<Integer> mgrType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> salType = (Type<Integer>) IntegerType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Emp}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Emp} filled by data from database result
     */
    public Emp transform(ResultSet resultSet) throws SQLException {
        Emp result = new Emp();
        
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