package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.SelectWithCollectionAndMapper;

/**
 * SQLComments result mapper class for statement selectWithCollectionAndMapper.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class SelectWithCollectionAndMapperMapper implements ResultMapper<SelectWithCollectionAndMapper> {

    /**
     * Static instance of SelectWithCollectionAndMapperMapper class.
     */
    public static final SelectWithCollectionAndMapperMapper INSTANCE = new SelectWithCollectionAndMapperMapper();

    private Type<Integer> deptnoType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> dnameType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link SelectWithCollectionAndMapper}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link SelectWithCollectionAndMapper} filled by data from database result
     */
    public SelectWithCollectionAndMapper transform(ResultSet resultSet) throws SQLException {
        SelectWithCollectionAndMapper result = new SelectWithCollectionAndMapper();
        
        result.setDeptno(deptnoType.readValue(resultSet, "deptno"));
        result.setDname(dnameType.readValue(resultSet, "dname"));
        
        return result;
    }
}