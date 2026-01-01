package sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments;

import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.Oracle.Project;

/**
 * SQLComments result mapper class for statement findByPK.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class ProjectMapper implements ResultMapper<Project> {

    /**
     * Static instance of ProjectMapper class.
     */
    public static final ProjectMapper INSTANCE = new ProjectMapper();

    private Type<BigDecimal> empnoType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Date> enddateType = (Type<Date>) DateType.getInstance(); 
    private Type<BigDecimal> projidType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Date> startdateType = (Type<Date>) DateType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Project}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Project} filled by data from database result
     */
    public Project transform(ResultSet resultSet) throws SQLException {
        Project result = new Project();
        
        result.setEmpno(empnoType.readValue(resultSet, "EMPNO"));
        result.setEnddate(enddateType.readValue(resultSet, "ENDDATE"));
        result.setProjid(projidType.readValue(resultSet, "PROJID"));
        result.setStartdate(startdateType.readValue(resultSet, "STARTDATE"));
        
        return result;
    }
}