package sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments;

import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
import java.sql.Blob;
import sk.vracon.sqlcomments.core.types.BlobType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;
import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import java.time.Duration;
import sk.vracon.sqlcomments.core.types.DurationType;
import java.time.Period;
import sk.vracon.sqlcomments.core.types.PeriodType;
import sk.vracon.sqlcomments.core.types.BinaryType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.Oracle.Datatypes;

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

    private Type<Double> bDoubleType = (Type<Double>) DoubleType.getInstance(); 
    private Type<Float> bFloatType = (Type<Float>) FloatType.getInstance(); 
    private Type<Blob> bfileType = (Type<Blob>) BlobType.getInstance(); 
    private Type<Blob> blobType = (Type<Blob>) BlobType.getInstance(); 
    private Type<String> charVarType = (Type<String>) StringType.getInstance(); 
    private Type<String> char_Type = (Type<String>) StringType.getInstance(); 
    private Type<String> characterType = (Type<String>) StringType.getInstance(); 
    private Type<String> characterVarType = (Type<String>) StringType.getInstance(); 
    private Type<String> clobType = (Type<String>) StringType.getInstance(); 
    private Type<Float> dPrecisionType = (Type<Float>) FloatType.getInstance(); 
    private Type<Date> dateType = (Type<Date>) DateType.getInstance(); 
    private Type<BigDecimal> decType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<BigDecimal> decimalType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Float> float_Type = (Type<Float>) FloatType.getInstance(); 
    private Type<BigDecimal> int_Type = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<BigDecimal> integerType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Duration> intervalDayType = (Type<Duration>) DurationType.getInstance(); 
    private Type<Period> intervalYearType = (Type<Period>) PeriodType.getInstance(); 
    private Type<String> long_Type = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalCharType = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalCharVarType = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalCharacterType = (Type<String>) StringType.getInstance(); 
    private Type<String> nationalCharacterVaryingType = (Type<String>) StringType.getInstance(); 
    private Type<String> ncharType = (Type<String>) StringType.getInstance(); 
    private Type<String> ncharVaryType = (Type<String>) StringType.getInstance(); 
    private Type<String> nclobType = (Type<String>) StringType.getInstance(); 
    private Type<BigDecimal> numberType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<BigDecimal> numericType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<byte[]> rawType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Float> realType = (Type<Float>) FloatType.getInstance(); 
    private Type<String> rowidType = (Type<String>) StringType.getInstance(); 
    private Type<BigDecimal> smallIntType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Date> timestampType = (Type<Date>) DateType.getInstance(); 
    private Type<String> urowidType = (Type<String>) StringType.getInstance(); 
    private Type<String> varChar2Type = (Type<String>) StringType.getInstance(); 
    private Type<String> varcharType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Datatypes}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Datatypes} filled by data from database result
     */
    public Datatypes transform(ResultSet resultSet) throws SQLException {
        Datatypes result = new Datatypes();
        
        result.setBDouble(bDoubleType.readValue(resultSet, "B_DOUBLE"));
        result.setBFloat(bFloatType.readValue(resultSet, "B_FLOAT"));
        result.setBfile(bfileType.readValue(resultSet, "BFILE_"));
        result.setBlob(blobType.readValue(resultSet, "BLOB_"));
        result.setCharVar(charVarType.readValue(resultSet, "CHAR_VAR"));
        result.setChar_(char_Type.readValue(resultSet, "CHAR_"));
        result.setCharacter(characterType.readValue(resultSet, "CHARACTER_"));
        result.setCharacterVar(characterVarType.readValue(resultSet, "CHARACTER_VAR"));
        result.setClob(clobType.readValue(resultSet, "CLOB_"));
        result.setDPrecision(dPrecisionType.readValue(resultSet, "D_PRECISION"));
        result.setDate(dateType.readValue(resultSet, "DATE_"));
        result.setDec(decType.readValue(resultSet, "DEC_"));
        result.setDecimal(decimalType.readValue(resultSet, "DECIMAL_"));
        result.setFloat_(float_Type.readValue(resultSet, "FLOAT_"));
        result.setInt_(int_Type.readValue(resultSet, "INT_"));
        result.setInteger(integerType.readValue(resultSet, "INTEGER_"));
        result.setIntervalDay(intervalDayType.readValue(resultSet, "INTERVAL_DAY"));
        result.setIntervalYear(intervalYearType.readValue(resultSet, "INTERVAL_YEAR"));
        result.setLong_(long_Type.readValue(resultSet, "LONG_"));
        result.setNationalChar(nationalCharType.readValue(resultSet, "NATIONAL_CHAR"));
        result.setNationalCharVar(nationalCharVarType.readValue(resultSet, "NATIONAL_CHAR_VAR"));
        result.setNationalCharacter(nationalCharacterType.readValue(resultSet, "NATIONAL_CHARACTER"));
        result.setNationalCharacterVarying(nationalCharacterVaryingType.readValue(resultSet, "NATIONAL_CHARACTER_VARYING"));
        result.setNchar(ncharType.readValue(resultSet, "NCHAR_"));
        result.setNcharVary(ncharVaryType.readValue(resultSet, "NCHAR_VARY"));
        result.setNclob(nclobType.readValue(resultSet, "NCLOB_"));
        result.setNumber(numberType.readValue(resultSet, "NUMBER_"));
        result.setNumeric(numericType.readValue(resultSet, "NUMERIC_"));
        result.setRaw(rawType.readValue(resultSet, "RAW_"));
        result.setReal(realType.readValue(resultSet, "REAL_"));
        result.setRowid(rowidType.readValue(resultSet, "ROWID_"));
        result.setSmallInt(smallIntType.readValue(resultSet, "SMALL_INT"));
        result.setTimestamp(timestampType.readValue(resultSet, "TIMESTAMP_"));
        result.setUrowid(urowidType.readValue(resultSet, "UROWID_"));
        result.setVarChar2(varChar2Type.readValue(resultSet, "VAR_CHAR2"));
        result.setVarchar(varcharType.readValue(resultSet, "VARCHAR_"));
        
        return result;
    }
}