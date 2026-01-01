package sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments;

import sk.vracon.sqlcomments.core.types.LongType;
import sk.vracon.sqlcomments.core.types.BinaryType;
import sk.vracon.sqlcomments.core.types.BooleanType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;
import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
import sk.vracon.sqlcomments.core.types.IntegerType;
import java.sql.Blob;
import sk.vracon.sqlcomments.core.types.BlobType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.MariaDB.Datatypes;

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

    private Type<Long> bigIntType = (Type<Long>) LongType.getInstance(); 
    private Type<byte[]> binaryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> bitType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Boolean> boolType = (Type<Boolean>) BooleanType.getInstance(); 
    private Type<Boolean> boolean_Type = (Type<Boolean>) BooleanType.getInstance(); 
    private Type<String> char_Type = (Type<String>) StringType.getInstance(); 
    private Type<Date> dateType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> dateTimeType = (Type<Date>) DateType.getInstance(); 
    private Type<BigDecimal> decType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<BigDecimal> decimalType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Double> doublePrecisionType = (Type<Double>) DoubleType.getInstance(); 
    private Type<Double> double_Type = (Type<Double>) DoubleType.getInstance(); 
    private Type<String> enum_Type = (Type<String>) StringType.getInstance(); 
    private Type<BigDecimal> fixedType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Float> float_Type = (Type<Float>) FloatType.getInstance(); 
    private Type<byte[]> geometryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> geometryCollectionType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Integer> int_Type = (Type<Integer>) IntegerType.getInstance(); 
    private Type<byte[]> lineStringType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Blob> longBlobType = (Type<Blob>) BlobType.getInstance(); 
    private Type<String> longTextType = (Type<String>) StringType.getInstance(); 
    private Type<Blob> mediumBlobType = (Type<Blob>) BlobType.getInstance(); 
    private Type<Integer> mediumIntType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> mediumTextType = (Type<String>) StringType.getInstance(); 
    private Type<byte[]> multiLineStringType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> multiPointType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> multiPolygonType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<String> nCharType = (Type<String>) StringType.getInstance(); 
    private Type<String> nVarCharType = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalCharType = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalVarcharType = (Type<String>) StringType.getInstance(); 
    private Type<BigDecimal> numericType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<byte[]> pointType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> polygonType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Double> realType = (Type<Double>) DoubleType.getInstance(); 
    private Type<String> setType = (Type<String>) StringType.getInstance(); 
    private Type<Integer> smallIntType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> textType = (Type<String>) StringType.getInstance(); 
    private Type<Date> timeType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timeStampType = (Type<Date>) DateType.getInstance(); 
    private Type<byte[]> tinyBlobType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Integer> tinyIntType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> tinyTextType = (Type<String>) StringType.getInstance(); 
    private Type<byte[]> varBinaryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<String> varCharType = (Type<String>) StringType.getInstance(); 
    private Type<Date> year4Type = (Type<Date>) DateType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Datatypes}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Datatypes} filled by data from database result
     */
    public Datatypes transform(ResultSet resultSet) throws SQLException {
        Datatypes result = new Datatypes();
        
        result.setBigInt(bigIntType.readValue(resultSet, "BIG_INT"));
        result.setBinary(binaryType.readValue(resultSet, "BINARY_"));
        result.setBit(bitType.readValue(resultSet, "BIT_"));
        result.setBool(boolType.readValue(resultSet, "BOOL_"));
        result.setBoolean_(boolean_Type.readValue(resultSet, "BOOLEAN_"));
        result.setChar_(char_Type.readValue(resultSet, "CHAR_"));
        result.setDate(dateType.readValue(resultSet, "DATE_"));
        result.setDateTime(dateTimeType.readValue(resultSet, "DATE_TIME"));
        result.setDec(decType.readValue(resultSet, "DEC_"));
        result.setDecimal(decimalType.readValue(resultSet, "DECIMAL_"));
        result.setDoublePrecision(doublePrecisionType.readValue(resultSet, "DOUBLE_PRECISION"));
        result.setDouble_(double_Type.readValue(resultSet, "DOUBLE_"));
        result.setEnum_(enum_Type.readValue(resultSet, "ENUM_"));
        result.setFixed(fixedType.readValue(resultSet, "FIXED_"));
        result.setFloat_(float_Type.readValue(resultSet, "FLOAT_"));
        result.setGeometry(geometryType.readValue(resultSet, "GEOMETRY_"));
        result.setGeometryCollection(geometryCollectionType.readValue(resultSet, "GEOMETRY_COLLECTION"));
        result.setInt_(int_Type.readValue(resultSet, "INT_"));
        result.setLineString(lineStringType.readValue(resultSet, "LINE_STRING"));
        result.setLongBlob(longBlobType.readValue(resultSet, "LONG_BLOB"));
        result.setLongText(longTextType.readValue(resultSet, "LONG_TEXT"));
        result.setMediumBlob(mediumBlobType.readValue(resultSet, "MEDIUM_BLOB"));
        result.setMediumInt(mediumIntType.readValue(resultSet, "MEDIUM_INT"));
        result.setMediumText(mediumTextType.readValue(resultSet, "MEDIUM_TEXT"));
        result.setMultiLineString(multiLineStringType.readValue(resultSet, "MULTI_LINE_STRING"));
        result.setMultiPoint(multiPointType.readValue(resultSet, "MULTI_POINT"));
        result.setMultiPolygon(multiPolygonType.readValue(resultSet, "MULTI_POLYGON"));
        result.setNChar(nCharType.readValue(resultSet, "N_CHAR"));
        result.setNVarChar(nVarCharType.readValue(resultSet, "N_VAR_CHAR"));
        result.setNationalChar(nationalCharType.readValue(resultSet, "NATIONAL_CHAR"));
        result.setNationalVarchar(nationalVarcharType.readValue(resultSet, "NATIONAL_VARCHAR"));
        result.setNumeric(numericType.readValue(resultSet, "NUMERIC_"));
        result.setPoint(pointType.readValue(resultSet, "POINT_"));
        result.setPolygon(polygonType.readValue(resultSet, "POLYGON_"));
        result.setReal(realType.readValue(resultSet, "REAL_"));
        result.setSet(setType.readValue(resultSet, "SET_"));
        result.setSmallInt(smallIntType.readValue(resultSet, "SMALL_INT"));
        result.setText(textType.readValue(resultSet, "TEXT_"));
        result.setTime(timeType.readValue(resultSet, "TIME_"));
        result.setTimeStamp(timeStampType.readValue(resultSet, "TIME_STAMP"));
        result.setTinyBlob(tinyBlobType.readValue(resultSet, "TINY_BLOB"));
        result.setTinyInt(tinyIntType.readValue(resultSet, "TINY_INT"));
        result.setTinyText(tinyTextType.readValue(resultSet, "TINY_TEXT"));
        result.setVarBinary(varBinaryType.readValue(resultSet, "VAR_BINARY"));
        result.setVarChar(varCharType.readValue(resultSet, "VAR_CHAR"));
        result.setYear4(year4Type.readValue(resultSet, "YEAR_4"));
        
        return result;
    }
}