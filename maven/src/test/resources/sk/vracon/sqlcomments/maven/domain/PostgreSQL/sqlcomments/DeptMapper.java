package sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.maven.DepartmenTypesEnum;
import sk.vracon.sqlcomments.core.types.EnumType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept;

/**
 * SQLComments result mapper class for statement findByPK.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class DeptMapper implements ResultMapper<Dept> {

    /**
     * Static instance of DeptMapper class.
     */
    public static final DeptMapper INSTANCE = new DeptMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<DepartmenTypesEnum> dnameType = (Type<DepartmenTypesEnum>) EnumType.getInstance("sk.vracon.sqlcomments.maven.DepartmenTypesEnum"); 
    private Type<String> locType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Dept}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Dept} filled by data from database result
     */
    public Dept transform(ResultSet resultSet) throws SQLException {
        Dept result = new Dept();
        
        result.setDeptno(deptnoType.readValue(resultSet, "DEPTNO"));
        result.setDname(dnameType.readValue(resultSet, "DNAME"));
        result.setLoc(locType.readValue(resultSet, "LOC"));
        
        return result;
    }
}