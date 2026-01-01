package sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.PostgreSQL.Managers;

/**
 * SQLComments result mapper class for statement selectAll.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class ManagersMapper implements ResultMapper<Managers> {

    /**
     * Static instance of ManagersMapper class.
     */
    public static final ManagersMapper INSTANCE = new ManagersMapper();

    private Type<String> employeeType = (Type<String>) StringType.getInstance(); 
    private Type<String> managerType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Managers}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Managers} filled by data from database result
     */
    public Managers transform(ResultSet resultSet) throws SQLException {
        Managers result = new Managers();
        
        result.setEmployee(employeeType.readValue(resultSet, "Employee"));
        result.setManager(managerType.readValue(resultSet, "Manager"));
        
        return result;
    }
}