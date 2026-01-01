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

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table DATATYPES.
 */
public class DatatypesConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of("INTEGER_");    

    private static final Set<String> __acceptNullParameters = Set.of("array", "bigint", "binary", "blob", "boolean_", "char_", "clob", "date", "decimal", "double_", "float_", "integer", "intervalDs", "intervalYm", "numeric", "real", "smallint", "timestamp", "timestampTz", "time", "tinyint", "uuid", "varbinary", "varchar", "varcharIgnorecase");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("array", ArrayType.getInstance()),
                Map.entry("bigint", LongType.getInstance()),
                Map.entry("binary", BinaryType.getInstance()),
                Map.entry("blob", BlobType.getInstance()),
                Map.entry("boolean_", BooleanType.getInstance()),
                Map.entry("char_", StringType.getInstance()),
                Map.entry("clob", StringType.getInstance()),
                Map.entry("date", DateType.getInstance()),
                Map.entry("decimal", NumericType.getInstance()),
                Map.entry("double_", DoubleType.getInstance()),
                Map.entry("float_", DoubleType.getInstance()),
                Map.entry("integer", IntegerType.getInstance()),
                Map.entry("intervalDs", StringType.getInstance()),
                Map.entry("intervalYm", StringType.getInstance()),
                Map.entry("numeric", NumericType.getInstance()),
                Map.entry("real", DoubleType.getInstance()),
                Map.entry("smallint", IntegerType.getInstance()),
                Map.entry("timestamp", DateType.getInstance()),
                Map.entry("timestampTz", OffsetDateTimeType.getInstance()),
                Map.entry("time", DateType.getInstance()),
                Map.entry("tinyint", IntegerType.getInstance()),
                Map.entry("uuid", BinaryType.getInstance()),
                Map.entry("varbinary", BinaryType.getInstance()),
                Map.entry("varchar", StringType.getInstance()),
                Map.entry("varcharIgnorecase", StringType.getInstance()));
    
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
    public DatatypesConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.HSQLDB.Datatypes domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("array", domain.getArray());
        __sqlParameters.put("bigint", domain.getBigint());
        __sqlParameters.put("binary", domain.getBinary());
        __sqlParameters.put("blob", domain.getBlob());
        __sqlParameters.put("boolean_", domain.getBoolean_());
        __sqlParameters.put("char_", domain.getChar_());
        __sqlParameters.put("clob", domain.getClob());
        __sqlParameters.put("date", domain.getDate());
        __sqlParameters.put("decimal", domain.getDecimal());
        __sqlParameters.put("double_", domain.getDouble_());
        __sqlParameters.put("float_", domain.getFloat_());
        __sqlParameters.put("integer", domain.getInteger());
        __sqlParameters.put("intervalDs", domain.getIntervalDs());
        __sqlParameters.put("intervalYm", domain.getIntervalYm());
        __sqlParameters.put("numeric", domain.getNumeric());
        __sqlParameters.put("real", domain.getReal());
        __sqlParameters.put("smallint", domain.getSmallint());
        __sqlParameters.put("timestamp", domain.getTimestamp());
        __sqlParameters.put("timestampTz", domain.getTimestampTz());
        __sqlParameters.put("time", domain.getTime());
        __sqlParameters.put("tinyint", domain.getTinyint());
        __sqlParameters.put("uuid", domain.getUuid());
        __sqlParameters.put("varbinary", domain.getVarbinary());
        __sqlParameters.put("varchar", domain.getVarchar());
        __sqlParameters.put("varcharIgnorecase", domain.getVarcharIgnorecase());
    }

    /**
     * Setter for placeholder array.
     * <p>
     * Placeholder is mapped to DATATYPES.ARRAY_.
     */
    public void setArray(Array value) {
        __sqlParameters.put("array", value);
    }

    /**
     * Setter for placeholder array.
     * <p>
     * Placeholder is mapped to DATATYPES.ARRAY_.
     */
    public DatatypesConfig withArray(Array value) {
        setArray(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bigint.
     * <p>
     * Placeholder is mapped to DATATYPES.BIGINT_.
     */
    public void setBigint(Long value) {
        __sqlParameters.put("bigint", value);
    }

    /**
     * Setter for placeholder bigint.
     * <p>
     * Placeholder is mapped to DATATYPES.BIGINT_.
     */
    public DatatypesConfig withBigint(Long value) {
        setBigint(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder binary.
     * <p>
     * Placeholder is mapped to DATATYPES.BINARY_.
     */
    public void setBinary(byte[] value) {
        __sqlParameters.put("binary", value);
    }

    /**
     * Setter for placeholder binary.
     * <p>
     * Placeholder is mapped to DATATYPES.BINARY_.
     */
    public DatatypesConfig withBinary(byte[] value) {
        setBinary(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder blob.
     * <p>
     * Placeholder is mapped to DATATYPES.BLOB_.
     */
    public void setBlob(Blob value) {
        __sqlParameters.put("blob", value);
    }

    /**
     * Setter for placeholder blob.
     * <p>
     * Placeholder is mapped to DATATYPES.BLOB_.
     */
    public DatatypesConfig withBlob(Blob value) {
        setBlob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to DATATYPES.BOOLEAN_.
     */
    public void setBoolean_(Boolean value) {
        __sqlParameters.put("boolean_", value);
    }

    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to DATATYPES.BOOLEAN_.
     */
    public DatatypesConfig withBoolean_(Boolean value) {
        setBoolean_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder char_.
     * <p>
     * Placeholder is mapped to DATATYPES.CHAR_.
     */
    public void setChar_(String value) {
        __sqlParameters.put("char_", value);
    }

    /**
     * Setter for placeholder char_.
     * <p>
     * Placeholder is mapped to DATATYPES.CHAR_.
     */
    public DatatypesConfig withChar_(String value) {
        setChar_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder clob.
     * <p>
     * Placeholder is mapped to DATATYPES.CLOB_.
     */
    public void setClob(String value) {
        __sqlParameters.put("clob", value);
    }

    /**
     * Setter for placeholder clob.
     * <p>
     * Placeholder is mapped to DATATYPES.CLOB_.
     */
    public DatatypesConfig withClob(String value) {
        setClob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder date.
     * <p>
     * Placeholder is mapped to DATATYPES.DATE_.
     */
    public void setDate(Date value) {
        __sqlParameters.put("date", value);
    }

    /**
     * Setter for placeholder date.
     * <p>
     * Placeholder is mapped to DATATYPES.DATE_.
     */
    public DatatypesConfig withDate(Date value) {
        setDate(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder decimal.
     * <p>
     * Placeholder is mapped to DATATYPES.DECIMAL_.
     */
    public void setDecimal(BigDecimal value) {
        __sqlParameters.put("decimal", value);
    }

    /**
     * Setter for placeholder decimal.
     * <p>
     * Placeholder is mapped to DATATYPES.DECIMAL_.
     */
    public DatatypesConfig withDecimal(BigDecimal value) {
        setDecimal(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder double_.
     * <p>
     * Placeholder is mapped to DATATYPES.DOUBLE_.
     */
    public void setDouble_(Double value) {
        __sqlParameters.put("double_", value);
    }

    /**
     * Setter for placeholder double_.
     * <p>
     * Placeholder is mapped to DATATYPES.DOUBLE_.
     */
    public DatatypesConfig withDouble_(Double value) {
        setDouble_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder float_.
     * <p>
     * Placeholder is mapped to DATATYPES.FLOAT_.
     */
    public void setFloat_(Double value) {
        __sqlParameters.put("float_", value);
    }

    /**
     * Setter for placeholder float_.
     * <p>
     * Placeholder is mapped to DATATYPES.FLOAT_.
     */
    public DatatypesConfig withFloat_(Double value) {
        setFloat_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to DATATYPES.INTEGER_.
     */
    public void setInteger(Integer value) {
        __sqlParameters.put("integer", value);
    }

    /**
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to DATATYPES.INTEGER_.
     */
    public DatatypesConfig withInteger(Integer value) {
        setInteger(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder intervalDs.
     * <p>
     * Placeholder is mapped to DATATYPES.INTERVAL_DS_.
     */
    public void setIntervalDs(String value) {
        __sqlParameters.put("intervalDs", value);
    }

    /**
     * Setter for placeholder intervalDs.
     * <p>
     * Placeholder is mapped to DATATYPES.INTERVAL_DS_.
     */
    public DatatypesConfig withIntervalDs(String value) {
        setIntervalDs(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder intervalYm.
     * <p>
     * Placeholder is mapped to DATATYPES.INTERVAL_YM_.
     */
    public void setIntervalYm(String value) {
        __sqlParameters.put("intervalYm", value);
    }

    /**
     * Setter for placeholder intervalYm.
     * <p>
     * Placeholder is mapped to DATATYPES.INTERVAL_YM_.
     */
    public DatatypesConfig withIntervalYm(String value) {
        setIntervalYm(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder numeric.
     * <p>
     * Placeholder is mapped to DATATYPES.NUMERIC_.
     */
    public void setNumeric(BigDecimal value) {
        __sqlParameters.put("numeric", value);
    }

    /**
     * Setter for placeholder numeric.
     * <p>
     * Placeholder is mapped to DATATYPES.NUMERIC_.
     */
    public DatatypesConfig withNumeric(BigDecimal value) {
        setNumeric(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to DATATYPES.REAL_.
     */
    public void setReal(Double value) {
        __sqlParameters.put("real", value);
    }

    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to DATATYPES.REAL_.
     */
    public DatatypesConfig withReal(Double value) {
        setReal(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder smallint.
     * <p>
     * Placeholder is mapped to DATATYPES.SMALLINT_.
     */
    public void setSmallint(Integer value) {
        __sqlParameters.put("smallint", value);
    }

    /**
     * Setter for placeholder smallint.
     * <p>
     * Placeholder is mapped to DATATYPES.SMALLINT_.
     */
    public DatatypesConfig withSmallint(Integer value) {
        setSmallint(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timestamp.
     * <p>
     * Placeholder is mapped to DATATYPES.TIMESTAMP_.
     */
    public void setTimestamp(Date value) {
        __sqlParameters.put("timestamp", value);
    }

    /**
     * Setter for placeholder timestamp.
     * <p>
     * Placeholder is mapped to DATATYPES.TIMESTAMP_.
     */
    public DatatypesConfig withTimestamp(Date value) {
        setTimestamp(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timestampTz.
     * <p>
     * Placeholder is mapped to DATATYPES.TIMESTAMP_TZ_.
     */
    public void setTimestampTz(OffsetDateTime value) {
        __sqlParameters.put("timestampTz", value);
    }

    /**
     * Setter for placeholder timestampTz.
     * <p>
     * Placeholder is mapped to DATATYPES.TIMESTAMP_TZ_.
     */
    public DatatypesConfig withTimestampTz(OffsetDateTime value) {
        setTimestampTz(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder time.
     * <p>
     * Placeholder is mapped to DATATYPES.TIME_.
     */
    public void setTime(Date value) {
        __sqlParameters.put("time", value);
    }

    /**
     * Setter for placeholder time.
     * <p>
     * Placeholder is mapped to DATATYPES.TIME_.
     */
    public DatatypesConfig withTime(Date value) {
        setTime(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tinyint.
     * <p>
     * Placeholder is mapped to DATATYPES.TINYINT_.
     */
    public void setTinyint(Integer value) {
        __sqlParameters.put("tinyint", value);
    }

    /**
     * Setter for placeholder tinyint.
     * <p>
     * Placeholder is mapped to DATATYPES.TINYINT_.
     */
    public DatatypesConfig withTinyint(Integer value) {
        setTinyint(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder uuid.
     * <p>
     * Placeholder is mapped to DATATYPES.UUID_.
     */
    public void setUuid(byte[] value) {
        __sqlParameters.put("uuid", value);
    }

    /**
     * Setter for placeholder uuid.
     * <p>
     * Placeholder is mapped to DATATYPES.UUID_.
     */
    public DatatypesConfig withUuid(byte[] value) {
        setUuid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varbinary.
     * <p>
     * Placeholder is mapped to DATATYPES.VARBINARY_.
     */
    public void setVarbinary(byte[] value) {
        __sqlParameters.put("varbinary", value);
    }

    /**
     * Setter for placeholder varbinary.
     * <p>
     * Placeholder is mapped to DATATYPES.VARBINARY_.
     */
    public DatatypesConfig withVarbinary(byte[] value) {
        setVarbinary(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varchar.
     * <p>
     * Placeholder is mapped to DATATYPES.VARCHAR_.
     */
    public void setVarchar(String value) {
        __sqlParameters.put("varchar", value);
    }

    /**
     * Setter for placeholder varchar.
     * <p>
     * Placeholder is mapped to DATATYPES.VARCHAR_.
     */
    public DatatypesConfig withVarchar(String value) {
        setVarchar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varcharIgnorecase.
     * <p>
     * Placeholder is mapped to DATATYPES.VARCHAR_IGNORECASE_.
     */
    public void setVarcharIgnorecase(String value) {
        __sqlParameters.put("varcharIgnorecase", value);
    }

    /**
     * Setter for placeholder varcharIgnorecase.
     * <p>
     * Placeholder is mapped to DATATYPES.VARCHAR_IGNORECASE_.
     */
    public DatatypesConfig withVarcharIgnorecase(String value) {
        setVarcharIgnorecase(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.HSQLDB.Datatypes.class;
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