package sk.vracon.sqlcomments.maven.domain.HSQLDB.sqlcomments;

import java.sql.Array;
import sk.vracon.sqlcomments.core.types.ArrayType;
import sk.vracon.sqlcomments.core.types.LongType;
import sk.vracon.sqlcomments.core.types.BinaryType;
import java.sql.Blob;
import sk.vracon.sqlcomments.core.types.BlobType;
import sk.vracon.sqlcomments.core.types.BooleanType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;
import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.IntegerType;
import java.time.OffsetDateTime;
import sk.vracon.sqlcomments.core.types.OffsetDateTimeType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.HSQLDB.Datatypes;

/**
 * SQLComments result mapper class for statement findByPK.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class DatatypesMapper implements ResultMapper<Datatypes> {

    /**
     * Static instance of DatatypesMapper class.
     */
    public static final DatatypesMapper INSTANCE = new DatatypesMapper();

    private Type<Array> arrayType = (Type<Array>) ArrayType.getInstance(); 
    private Type<Long> bigintType = (Type<Long>) LongType.getInstance(); 
    private Type<byte[]> binaryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Blob> blobType = (Type<Blob>) BlobType.getInstance(); 
    private Type<Boolean> boolean_Type = (Type<Boolean>) BooleanType.getInstance(); 
    private Type<String> char_Type = (Type<String>) StringType.getInstance(); 
    private Type<String> clobType = (Type<String>) StringType.getInstance(); 
    private Type<Date> dateType = (Type<Date>) DateType.getInstance(); 
    private Type<BigDecimal> decimalType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Double> double_Type = (Type<Double>) DoubleType.getInstance(); 
    private Type<Double> float_Type = (Type<Double>) DoubleType.getInstance(); 
    private Type<Integer> integerType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> intervalDsType = (Type<String>) StringType.getInstance(); 
    private Type<String> intervalYmType = (Type<String>) StringType.getInstance(); 
    private Type<BigDecimal> numericType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Double> realType = (Type<Double>) DoubleType.getInstance(); 
    private Type<Integer> smallintType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Date> timeType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timestampType = (Type<Date>) DateType.getInstance(); 
    private Type<OffsetDateTime> timestampTzType = (Type<OffsetDateTime>) OffsetDateTimeType.getInstance(); 
    private Type<Integer> tinyintType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<byte[]> uuidType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> varbinaryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<String> varcharType = (Type<String>) StringType.getInstance(); 
    private Type<String> varcharIgnorecaseType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Datatypes}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Datatypes} filled by data from database result
     */
    public Datatypes transform(ResultSet resultSet) throws SQLException {
        Datatypes result = new Datatypes();
        
        result.setArray(arrayType.readValue(resultSet, "ARRAY_"));
        result.setBigint(bigintType.readValue(resultSet, "BIGINT_"));
        result.setBinary(binaryType.readValue(resultSet, "BINARY_"));
        result.setBlob(blobType.readValue(resultSet, "BLOB_"));
        result.setBoolean_(boolean_Type.readValue(resultSet, "BOOLEAN_"));
        result.setChar_(char_Type.readValue(resultSet, "CHAR_"));
        result.setClob(clobType.readValue(resultSet, "CLOB_"));
        result.setDate(dateType.readValue(resultSet, "DATE_"));
        result.setDecimal(decimalType.readValue(resultSet, "DECIMAL_"));
        result.setDouble_(double_Type.readValue(resultSet, "DOUBLE_"));
        result.setFloat_(float_Type.readValue(resultSet, "FLOAT_"));
        result.setInteger(integerType.readValue(resultSet, "INTEGER_"));
        result.setIntervalDs(intervalDsType.readValue(resultSet, "INTERVAL_DS_"));
        result.setIntervalYm(intervalYmType.readValue(resultSet, "INTERVAL_YM_"));
        result.setNumeric(numericType.readValue(resultSet, "NUMERIC_"));
        result.setReal(realType.readValue(resultSet, "REAL_"));
        result.setSmallint(smallintType.readValue(resultSet, "SMALLINT_"));
        result.setTime(timeType.readValue(resultSet, "TIME_"));
        result.setTimestamp(timestampType.readValue(resultSet, "TIMESTAMP_"));
        result.setTimestampTz(timestampTzType.readValue(resultSet, "TIMESTAMP_TZ_"));
        result.setTinyint(tinyintType.readValue(resultSet, "TINYINT_"));
        result.setUuid(uuidType.readValue(resultSet, "UUID_"));
        result.setVarbinary(varbinaryType.readValue(resultSet, "VARBINARY_"));
        result.setVarchar(varcharType.readValue(resultSet, "VARCHAR_"));
        result.setVarcharIgnorecase(varcharIgnorecaseType.readValue(resultSet, "VARCHAR_IGNORECASE_"));
        
        return result;
    }
}