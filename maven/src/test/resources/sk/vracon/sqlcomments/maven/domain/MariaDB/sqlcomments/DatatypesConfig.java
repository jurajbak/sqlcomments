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

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table datatypes.
 */
public class DatatypesConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of("INT_");    

    private static final Set<String> __acceptNullParameters = Set.of("bigInt", "binary", "bit", "boolean_", "bool", "char_", "date", "dateTime", "decimal", "dec", "double_", "doublePrecision", "enum_", "fixed", "float_", "geometry", "geometryCollection", "int_", "lineString", "longBlob", "longText", "mediumBlob", "mediumInt", "mediumText", "multiLineString", "multiPoint", "multiPolygon", "nationalChar", "nationalVarchar", "numeric", "nChar", "nVarChar", "point", "polygon", "real", "set", "smallInt", "text", "time", "timeStamp", "tinyBlob", "tinyInt", "tinyText", "varBinary", "varChar", "year4");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("bigInt", LongType.getInstance()),
                Map.entry("binary", BinaryType.getInstance()),
                Map.entry("bit", BinaryType.getInstance()),
                Map.entry("boolean_", BooleanType.getInstance()),
                Map.entry("bool", BooleanType.getInstance()),
                Map.entry("char_", StringType.getInstance()),
                Map.entry("date", DateType.getInstance()),
                Map.entry("dateTime", DateType.getInstance()),
                Map.entry("decimal", NumericType.getInstance()),
                Map.entry("dec", NumericType.getInstance()),
                Map.entry("double_", DoubleType.getInstance()),
                Map.entry("doublePrecision", DoubleType.getInstance()),
                Map.entry("enum_", StringType.getInstance()),
                Map.entry("fixed", NumericType.getInstance()),
                Map.entry("float_", FloatType.getInstance()),
                Map.entry("geometry", BinaryType.getInstance()),
                Map.entry("geometryCollection", BinaryType.getInstance()),
                Map.entry("int_", IntegerType.getInstance()),
                Map.entry("lineString", BinaryType.getInstance()),
                Map.entry("longBlob", BlobType.getInstance()),
                Map.entry("longText", StringType.getInstance()),
                Map.entry("mediumBlob", BlobType.getInstance()),
                Map.entry("mediumInt", IntegerType.getInstance()),
                Map.entry("mediumText", StringType.getInstance()),
                Map.entry("multiLineString", BinaryType.getInstance()),
                Map.entry("multiPoint", BinaryType.getInstance()),
                Map.entry("multiPolygon", BinaryType.getInstance()),
                Map.entry("nationalChar", StringType.getInstance()),
                Map.entry("nationalVarchar", StringType.getInstance()),
                Map.entry("numeric", NumericType.getInstance()),
                Map.entry("nChar", StringType.getInstance()),
                Map.entry("nVarChar", StringType.getInstance()),
                Map.entry("point", BinaryType.getInstance()),
                Map.entry("polygon", BinaryType.getInstance()),
                Map.entry("real", DoubleType.getInstance()),
                Map.entry("set", StringType.getInstance()),
                Map.entry("smallInt", IntegerType.getInstance()),
                Map.entry("text", StringType.getInstance()),
                Map.entry("time", DateType.getInstance()),
                Map.entry("timeStamp", DateType.getInstance()),
                Map.entry("tinyBlob", BinaryType.getInstance()),
                Map.entry("tinyInt", IntegerType.getInstance()),
                Map.entry("tinyText", StringType.getInstance()),
                Map.entry("varBinary", BinaryType.getInstance()),
                Map.entry("varChar", StringType.getInstance()),
                Map.entry("year4", DateType.getInstance()));
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement Datatypes.
     *
     * @param operation CRUD operation name
     */
    public DatatypesConfig(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement Datatypes.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public DatatypesConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.MariaDB.Datatypes domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("bigInt", domain.getBigInt());
        __sqlParameters.put("binary", domain.getBinary());
        __sqlParameters.put("bit", domain.getBit());
        __sqlParameters.put("boolean_", domain.getBoolean_());
        __sqlParameters.put("bool", domain.getBool());
        __sqlParameters.put("char_", domain.getChar_());
        __sqlParameters.put("date", domain.getDate());
        __sqlParameters.put("dateTime", domain.getDateTime());
        __sqlParameters.put("decimal", domain.getDecimal());
        __sqlParameters.put("dec", domain.getDec());
        __sqlParameters.put("double_", domain.getDouble_());
        __sqlParameters.put("doublePrecision", domain.getDoublePrecision());
        __sqlParameters.put("enum_", domain.getEnum_());
        __sqlParameters.put("fixed", domain.getFixed());
        __sqlParameters.put("float_", domain.getFloat_());
        __sqlParameters.put("geometry", domain.getGeometry());
        __sqlParameters.put("geometryCollection", domain.getGeometryCollection());
        __sqlParameters.put("int_", domain.getInt_());
        __sqlParameters.put("lineString", domain.getLineString());
        __sqlParameters.put("longBlob", domain.getLongBlob());
        __sqlParameters.put("longText", domain.getLongText());
        __sqlParameters.put("mediumBlob", domain.getMediumBlob());
        __sqlParameters.put("mediumInt", domain.getMediumInt());
        __sqlParameters.put("mediumText", domain.getMediumText());
        __sqlParameters.put("multiLineString", domain.getMultiLineString());
        __sqlParameters.put("multiPoint", domain.getMultiPoint());
        __sqlParameters.put("multiPolygon", domain.getMultiPolygon());
        __sqlParameters.put("nationalChar", domain.getNationalChar());
        __sqlParameters.put("nationalVarchar", domain.getNationalVarchar());
        __sqlParameters.put("numeric", domain.getNumeric());
        __sqlParameters.put("nChar", domain.getNChar());
        __sqlParameters.put("nVarChar", domain.getNVarChar());
        __sqlParameters.put("point", domain.getPoint());
        __sqlParameters.put("polygon", domain.getPolygon());
        __sqlParameters.put("real", domain.getReal());
        __sqlParameters.put("set", domain.getSet());
        __sqlParameters.put("smallInt", domain.getSmallInt());
        __sqlParameters.put("text", domain.getText());
        __sqlParameters.put("time", domain.getTime());
        __sqlParameters.put("timeStamp", domain.getTimeStamp());
        __sqlParameters.put("tinyBlob", domain.getTinyBlob());
        __sqlParameters.put("tinyInt", domain.getTinyInt());
        __sqlParameters.put("tinyText", domain.getTinyText());
        __sqlParameters.put("varBinary", domain.getVarBinary());
        __sqlParameters.put("varChar", domain.getVarChar());
        __sqlParameters.put("year4", domain.getYear4());
    }

    /**
     * Setter for placeholder bigInt.
     * <p>
     * Placeholder is mapped to datatypes.BIG_INT.
     */
    public void setBigInt(Long value) {
        __sqlParameters.put("bigInt", value);
    }

    /**
     * Setter for placeholder bigInt.
     * <p>
     * Placeholder is mapped to datatypes.BIG_INT.
     */
    public DatatypesConfig withBigInt(Long value) {
        setBigInt(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder binary.
     * <p>
     * Placeholder is mapped to datatypes.BINARY_.
     */
    public void setBinary(byte[] value) {
        __sqlParameters.put("binary", value);
    }

    /**
     * Setter for placeholder binary.
     * <p>
     * Placeholder is mapped to datatypes.BINARY_.
     */
    public DatatypesConfig withBinary(byte[] value) {
        setBinary(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bit.
     * <p>
     * Placeholder is mapped to datatypes.BIT_.
     */
    public void setBit(byte[] value) {
        __sqlParameters.put("bit", value);
    }

    /**
     * Setter for placeholder bit.
     * <p>
     * Placeholder is mapped to datatypes.BIT_.
     */
    public DatatypesConfig withBit(byte[] value) {
        setBit(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to datatypes.BOOLEAN_.
     */
    public void setBoolean_(Boolean value) {
        __sqlParameters.put("boolean_", value);
    }

    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to datatypes.BOOLEAN_.
     */
    public DatatypesConfig withBoolean_(Boolean value) {
        setBoolean_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bool.
     * <p>
     * Placeholder is mapped to datatypes.BOOL_.
     */
    public void setBool(Boolean value) {
        __sqlParameters.put("bool", value);
    }

    /**
     * Setter for placeholder bool.
     * <p>
     * Placeholder is mapped to datatypes.BOOL_.
     */
    public DatatypesConfig withBool(Boolean value) {
        setBool(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder char_.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_.
     */
    public void setChar_(String value) {
        __sqlParameters.put("char_", value);
    }

    /**
     * Setter for placeholder char_.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_.
     */
    public DatatypesConfig withChar_(String value) {
        setChar_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder date.
     * <p>
     * Placeholder is mapped to datatypes.DATE_.
     */
    public void setDate(Date value) {
        __sqlParameters.put("date", value);
    }

    /**
     * Setter for placeholder date.
     * <p>
     * Placeholder is mapped to datatypes.DATE_.
     */
    public DatatypesConfig withDate(Date value) {
        setDate(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder dateTime.
     * <p>
     * Placeholder is mapped to datatypes.DATE_TIME.
     */
    public void setDateTime(Date value) {
        __sqlParameters.put("dateTime", value);
    }

    /**
     * Setter for placeholder dateTime.
     * <p>
     * Placeholder is mapped to datatypes.DATE_TIME.
     */
    public DatatypesConfig withDateTime(Date value) {
        setDateTime(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder decimal.
     * <p>
     * Placeholder is mapped to datatypes.DECIMAL_.
     */
    public void setDecimal(BigDecimal value) {
        __sqlParameters.put("decimal", value);
    }

    /**
     * Setter for placeholder decimal.
     * <p>
     * Placeholder is mapped to datatypes.DECIMAL_.
     */
    public DatatypesConfig withDecimal(BigDecimal value) {
        setDecimal(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder dec.
     * <p>
     * Placeholder is mapped to datatypes.DEC_.
     */
    public void setDec(BigDecimal value) {
        __sqlParameters.put("dec", value);
    }

    /**
     * Setter for placeholder dec.
     * <p>
     * Placeholder is mapped to datatypes.DEC_.
     */
    public DatatypesConfig withDec(BigDecimal value) {
        setDec(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder double_.
     * <p>
     * Placeholder is mapped to datatypes.DOUBLE_.
     */
    public void setDouble_(Double value) {
        __sqlParameters.put("double_", value);
    }

    /**
     * Setter for placeholder double_.
     * <p>
     * Placeholder is mapped to datatypes.DOUBLE_.
     */
    public DatatypesConfig withDouble_(Double value) {
        setDouble_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder doublePrecision.
     * <p>
     * Placeholder is mapped to datatypes.DOUBLE_PRECISION.
     */
    public void setDoublePrecision(Double value) {
        __sqlParameters.put("doublePrecision", value);
    }

    /**
     * Setter for placeholder doublePrecision.
     * <p>
     * Placeholder is mapped to datatypes.DOUBLE_PRECISION.
     */
    public DatatypesConfig withDoublePrecision(Double value) {
        setDoublePrecision(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder enum_.
     * <p>
     * Placeholder is mapped to datatypes.ENUM_.
     */
    public void setEnum_(String value) {
        __sqlParameters.put("enum_", value);
    }

    /**
     * Setter for placeholder enum_.
     * <p>
     * Placeholder is mapped to datatypes.ENUM_.
     */
    public DatatypesConfig withEnum_(String value) {
        setEnum_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder fixed.
     * <p>
     * Placeholder is mapped to datatypes.FIXED_.
     */
    public void setFixed(BigDecimal value) {
        __sqlParameters.put("fixed", value);
    }

    /**
     * Setter for placeholder fixed.
     * <p>
     * Placeholder is mapped to datatypes.FIXED_.
     */
    public DatatypesConfig withFixed(BigDecimal value) {
        setFixed(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder float_.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_.
     */
    public void setFloat_(Float value) {
        __sqlParameters.put("float_", value);
    }

    /**
     * Setter for placeholder float_.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_.
     */
    public DatatypesConfig withFloat_(Float value) {
        setFloat_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder geometry.
     * <p>
     * Placeholder is mapped to datatypes.GEOMETRY_.
     */
    public void setGeometry(byte[] value) {
        __sqlParameters.put("geometry", value);
    }

    /**
     * Setter for placeholder geometry.
     * <p>
     * Placeholder is mapped to datatypes.GEOMETRY_.
     */
    public DatatypesConfig withGeometry(byte[] value) {
        setGeometry(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder geometryCollection.
     * <p>
     * Placeholder is mapped to datatypes.GEOMETRY_COLLECTION.
     */
    public void setGeometryCollection(byte[] value) {
        __sqlParameters.put("geometryCollection", value);
    }

    /**
     * Setter for placeholder geometryCollection.
     * <p>
     * Placeholder is mapped to datatypes.GEOMETRY_COLLECTION.
     */
    public DatatypesConfig withGeometryCollection(byte[] value) {
        setGeometryCollection(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder int_.
     * <p>
     * Placeholder is mapped to datatypes.INT_.
     */
    public void setInt_(Integer value) {
        __sqlParameters.put("int_", value);
    }

    /**
     * Setter for placeholder int_.
     * <p>
     * Placeholder is mapped to datatypes.INT_.
     */
    public DatatypesConfig withInt_(Integer value) {
        setInt_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder lineString.
     * <p>
     * Placeholder is mapped to datatypes.LINE_STRING.
     */
    public void setLineString(byte[] value) {
        __sqlParameters.put("lineString", value);
    }

    /**
     * Setter for placeholder lineString.
     * <p>
     * Placeholder is mapped to datatypes.LINE_STRING.
     */
    public DatatypesConfig withLineString(byte[] value) {
        setLineString(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder longBlob.
     * <p>
     * Placeholder is mapped to datatypes.LONG_BLOB.
     */
    public void setLongBlob(Blob value) {
        __sqlParameters.put("longBlob", value);
    }

    /**
     * Setter for placeholder longBlob.
     * <p>
     * Placeholder is mapped to datatypes.LONG_BLOB.
     */
    public DatatypesConfig withLongBlob(Blob value) {
        setLongBlob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder longText.
     * <p>
     * Placeholder is mapped to datatypes.LONG_TEXT.
     */
    public void setLongText(String value) {
        __sqlParameters.put("longText", value);
    }

    /**
     * Setter for placeholder longText.
     * <p>
     * Placeholder is mapped to datatypes.LONG_TEXT.
     */
    public DatatypesConfig withLongText(String value) {
        setLongText(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder mediumBlob.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_BLOB.
     */
    public void setMediumBlob(Blob value) {
        __sqlParameters.put("mediumBlob", value);
    }

    /**
     * Setter for placeholder mediumBlob.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_BLOB.
     */
    public DatatypesConfig withMediumBlob(Blob value) {
        setMediumBlob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder mediumInt.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_INT.
     */
    public void setMediumInt(Integer value) {
        __sqlParameters.put("mediumInt", value);
    }

    /**
     * Setter for placeholder mediumInt.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_INT.
     */
    public DatatypesConfig withMediumInt(Integer value) {
        setMediumInt(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder mediumText.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_TEXT.
     */
    public void setMediumText(String value) {
        __sqlParameters.put("mediumText", value);
    }

    /**
     * Setter for placeholder mediumText.
     * <p>
     * Placeholder is mapped to datatypes.MEDIUM_TEXT.
     */
    public DatatypesConfig withMediumText(String value) {
        setMediumText(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder multiLineString.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_LINE_STRING.
     */
    public void setMultiLineString(byte[] value) {
        __sqlParameters.put("multiLineString", value);
    }

    /**
     * Setter for placeholder multiLineString.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_LINE_STRING.
     */
    public DatatypesConfig withMultiLineString(byte[] value) {
        setMultiLineString(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder multiPoint.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_POINT.
     */
    public void setMultiPoint(byte[] value) {
        __sqlParameters.put("multiPoint", value);
    }

    /**
     * Setter for placeholder multiPoint.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_POINT.
     */
    public DatatypesConfig withMultiPoint(byte[] value) {
        setMultiPoint(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder multiPolygon.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_POLYGON.
     */
    public void setMultiPolygon(byte[] value) {
        __sqlParameters.put("multiPolygon", value);
    }

    /**
     * Setter for placeholder multiPolygon.
     * <p>
     * Placeholder is mapped to datatypes.MULTI_POLYGON.
     */
    public DatatypesConfig withMultiPolygon(byte[] value) {
        setMultiPolygon(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nationalChar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHAR.
     */
    public void setNationalChar(String value) {
        __sqlParameters.put("nationalChar", value);
    }

    /**
     * Setter for placeholder nationalChar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHAR.
     */
    public DatatypesConfig withNationalChar(String value) {
        setNationalChar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nationalVarchar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_VARCHAR.
     */
    public void setNationalVarchar(String value) {
        __sqlParameters.put("nationalVarchar", value);
    }

    /**
     * Setter for placeholder nationalVarchar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_VARCHAR.
     */
    public DatatypesConfig withNationalVarchar(String value) {
        setNationalVarchar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder numeric.
     * <p>
     * Placeholder is mapped to datatypes.NUMERIC_.
     */
    public void setNumeric(BigDecimal value) {
        __sqlParameters.put("numeric", value);
    }

    /**
     * Setter for placeholder numeric.
     * <p>
     * Placeholder is mapped to datatypes.NUMERIC_.
     */
    public DatatypesConfig withNumeric(BigDecimal value) {
        setNumeric(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nChar.
     * <p>
     * Placeholder is mapped to datatypes.N_CHAR.
     */
    public void setNChar(String value) {
        __sqlParameters.put("nChar", value);
    }

    /**
     * Setter for placeholder nChar.
     * <p>
     * Placeholder is mapped to datatypes.N_CHAR.
     */
    public DatatypesConfig withNChar(String value) {
        setNChar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nVarChar.
     * <p>
     * Placeholder is mapped to datatypes.N_VAR_CHAR.
     */
    public void setNVarChar(String value) {
        __sqlParameters.put("nVarChar", value);
    }

    /**
     * Setter for placeholder nVarChar.
     * <p>
     * Placeholder is mapped to datatypes.N_VAR_CHAR.
     */
    public DatatypesConfig withNVarChar(String value) {
        setNVarChar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder point.
     * <p>
     * Placeholder is mapped to datatypes.POINT_.
     */
    public void setPoint(byte[] value) {
        __sqlParameters.put("point", value);
    }

    /**
     * Setter for placeholder point.
     * <p>
     * Placeholder is mapped to datatypes.POINT_.
     */
    public DatatypesConfig withPoint(byte[] value) {
        setPoint(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder polygon.
     * <p>
     * Placeholder is mapped to datatypes.POLYGON_.
     */
    public void setPolygon(byte[] value) {
        __sqlParameters.put("polygon", value);
    }

    /**
     * Setter for placeholder polygon.
     * <p>
     * Placeholder is mapped to datatypes.POLYGON_.
     */
    public DatatypesConfig withPolygon(byte[] value) {
        setPolygon(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to datatypes.REAL_.
     */
    public void setReal(Double value) {
        __sqlParameters.put("real", value);
    }

    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to datatypes.REAL_.
     */
    public DatatypesConfig withReal(Double value) {
        setReal(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder set.
     * <p>
     * Placeholder is mapped to datatypes.SET_.
     */
    public void setSet(String value) {
        __sqlParameters.put("set", value);
    }

    /**
     * Setter for placeholder set.
     * <p>
     * Placeholder is mapped to datatypes.SET_.
     */
    public DatatypesConfig withSet(String value) {
        setSet(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder smallInt.
     * <p>
     * Placeholder is mapped to datatypes.SMALL_INT.
     */
    public void setSmallInt(Integer value) {
        __sqlParameters.put("smallInt", value);
    }

    /**
     * Setter for placeholder smallInt.
     * <p>
     * Placeholder is mapped to datatypes.SMALL_INT.
     */
    public DatatypesConfig withSmallInt(Integer value) {
        setSmallInt(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder text.
     * <p>
     * Placeholder is mapped to datatypes.TEXT_.
     */
    public void setText(String value) {
        __sqlParameters.put("text", value);
    }

    /**
     * Setter for placeholder text.
     * <p>
     * Placeholder is mapped to datatypes.TEXT_.
     */
    public DatatypesConfig withText(String value) {
        setText(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder time.
     * <p>
     * Placeholder is mapped to datatypes.TIME_.
     */
    public void setTime(Date value) {
        __sqlParameters.put("time", value);
    }

    /**
     * Setter for placeholder time.
     * <p>
     * Placeholder is mapped to datatypes.TIME_.
     */
    public DatatypesConfig withTime(Date value) {
        setTime(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timeStamp.
     * <p>
     * Placeholder is mapped to datatypes.TIME_STAMP.
     */
    public void setTimeStamp(Date value) {
        __sqlParameters.put("timeStamp", value);
    }

    /**
     * Setter for placeholder timeStamp.
     * <p>
     * Placeholder is mapped to datatypes.TIME_STAMP.
     */
    public DatatypesConfig withTimeStamp(Date value) {
        setTimeStamp(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tinyBlob.
     * <p>
     * Placeholder is mapped to datatypes.TINY_BLOB.
     */
    public void setTinyBlob(byte[] value) {
        __sqlParameters.put("tinyBlob", value);
    }

    /**
     * Setter for placeholder tinyBlob.
     * <p>
     * Placeholder is mapped to datatypes.TINY_BLOB.
     */
    public DatatypesConfig withTinyBlob(byte[] value) {
        setTinyBlob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tinyInt.
     * <p>
     * Placeholder is mapped to datatypes.TINY_INT.
     */
    public void setTinyInt(Integer value) {
        __sqlParameters.put("tinyInt", value);
    }

    /**
     * Setter for placeholder tinyInt.
     * <p>
     * Placeholder is mapped to datatypes.TINY_INT.
     */
    public DatatypesConfig withTinyInt(Integer value) {
        setTinyInt(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tinyText.
     * <p>
     * Placeholder is mapped to datatypes.TINY_TEXT.
     */
    public void setTinyText(String value) {
        __sqlParameters.put("tinyText", value);
    }

    /**
     * Setter for placeholder tinyText.
     * <p>
     * Placeholder is mapped to datatypes.TINY_TEXT.
     */
    public DatatypesConfig withTinyText(String value) {
        setTinyText(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varBinary.
     * <p>
     * Placeholder is mapped to datatypes.VAR_BINARY.
     */
    public void setVarBinary(byte[] value) {
        __sqlParameters.put("varBinary", value);
    }

    /**
     * Setter for placeholder varBinary.
     * <p>
     * Placeholder is mapped to datatypes.VAR_BINARY.
     */
    public DatatypesConfig withVarBinary(byte[] value) {
        setVarBinary(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varChar.
     * <p>
     * Placeholder is mapped to datatypes.VAR_CHAR.
     */
    public void setVarChar(String value) {
        __sqlParameters.put("varChar", value);
    }

    /**
     * Setter for placeholder varChar.
     * <p>
     * Placeholder is mapped to datatypes.VAR_CHAR.
     */
    public DatatypesConfig withVarChar(String value) {
        setVarChar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder year4.
     * <p>
     * Placeholder is mapped to datatypes.YEAR_4.
     */
    public void setYear4(Date value) {
        __sqlParameters.put("year4", value);
    }

    /**
     * Setter for placeholder year4.
     * <p>
     * Placeholder is mapped to datatypes.YEAR_4.
     */
    public DatatypesConfig withYear4(Date value) {
        setYear4(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.MariaDB.Datatypes.class;
    }

    public Map<String, Object> parameterMap() {
        return __sqlParameters;
    }
    
    public Map<String, Type<?>> typeMap() {
        return __parameterTypes;
    }
    
    public Set<String> parametersAcceptingNull() {
        return __acceptNullParameters;
    }
    
    public Set<String> primaryKey() {
    	return __primaryKey;
    }
    
    public Long limit() {
        return limit;
    }

    public void limit(Long limit) {
        this.limit = limit;
    }

    public Long offset() {
        return offset;
    }

    public void offset(Long offset) {
        this.offset = offset;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DatatypesConfig [statementName=");
        builder.append(statementName());
        builder.append(", baseClass=");
        builder.append(baseClass());
        builder.append(", sqlParameters=");
        builder.append(parameterMap());
        builder.append(", acceptNullParameters=");
        builder.append(parametersAcceptingNull());
        builder.append(", limit=");
        builder.append(limit());
        builder.append(", offset=");
        builder.append(offset());
        builder.append("]");
        return builder.toString();
    }
}