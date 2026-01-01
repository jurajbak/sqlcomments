package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.DefaultResult;

/**
 * SQLComments result mapper class for statement defaultResult.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class DefaultResultMapper implements ResultMapper<DefaultResult> {

    /**
     * Static instance of DefaultResultMapper class.
     */
    public static final DefaultResultMapper INSTANCE = new DefaultResultMapper();

    private Type<Integer> empnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Date> enddateType = (Type<Date>) DateType.getInstance(); 
    private Type<Integer> projidType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Date> startdateType = (Type<Date>) DateType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link DefaultResult}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link DefaultResult} filled by data from database result
     */
    public DefaultResult transform(ResultSet resultSet) throws SQLException {
        DefaultResult result = new DefaultResult();
        
        result.setEmpno(empnoType.readValue(resultSet, "EMPNO"));
        result.setEnddate(enddateType.readValue(resultSet, "ENDDATE"));
        result.setProjid(projidType.readValue(resultSet, "PROJID"));
        result.setStartdate(startdateType.readValue(resultSet, "STARTDATE"));
        
        return result;
    }
}