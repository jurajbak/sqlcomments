package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInsideInClause;

/**
 * SQLComments result mapper class for statement selectWithPlaceholder.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class PlaceholderInsideInClauseMapper implements ResultMapper<PlaceholderInsideInClause> {

    /**
     * Static instance of PlaceholderInsideInClauseMapper class.
     */
    public static final PlaceholderInsideInClauseMapper INSTANCE = new PlaceholderInsideInClauseMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link PlaceholderInsideInClause}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link PlaceholderInsideInClause} filled by data from database result
     */
    public PlaceholderInsideInClause transform(ResultSet resultSet) throws SQLException {
        PlaceholderInsideInClause result = new PlaceholderInsideInClause();
        
        result.setDeptno(deptnoType.readValue(resultSet, "deptno"));
        result.setDname(dnameType.readValue(resultSet, "dname"));
        
        return result;
    }
}