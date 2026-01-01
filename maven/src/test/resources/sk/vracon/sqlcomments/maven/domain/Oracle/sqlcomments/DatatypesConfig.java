package sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments;

import java.sql.Blob;
import sk.vracon.sqlcomments.core.types.BlobType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
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

    private static final Set<String> __acceptNullParameters = Set.of("bfile", "blob", "bDouble", "bFloat", "character", "characterVar", "char_", "charVar", "clob", "date", "decimal", "dec", "dPrecision", "float_", "integer", "intervalDay", "intervalYear", "int_", "long_", "nationalChar", "nationalCharacter", "nationalCharacterVarying", "nationalCharVar", "nchar", "ncharVary", "nclob", "number", "numeric", "raw", "real", "rowid", "smallInt", "timestamp", "urowid", "varchar", "varChar2");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("bfile", BlobType.getInstance()),
                Map.entry("blob", BlobType.getInstance()),
                Map.entry("bDouble", DoubleType.getInstance()),
                Map.entry("bFloat", FloatType.getInstance()),
                Map.entry("character", StringType.getInstance()),
                Map.entry("characterVar", StringType.getInstance()),
                Map.entry("char_", StringType.getInstance()),
                Map.entry("charVar", StringType.getInstance()),
                Map.entry("clob", StringType.getInstance()),
                Map.entry("date", DateType.getInstance()),
                Map.entry("decimal", NumericType.getInstance()),
                Map.entry("dec", NumericType.getInstance()),
                Map.entry("dPrecision", FloatType.getInstance()),
                Map.entry("float_", FloatType.getInstance()),
                Map.entry("integer", NumericType.getInstance()),
                Map.entry("intervalDay", DurationType.getInstance()),
                Map.entry("intervalYear", PeriodType.getInstance()),
                Map.entry("int_", NumericType.getInstance()),
                Map.entry("long_", StringType.getInstance()),
                Map.entry("nationalChar", StringType.getInstance()),
                Map.entry("nationalCharacter", StringType.getInstance()),
                Map.entry("nationalCharacterVarying", StringType.getInstance()),
                Map.entry("nationalCharVar", StringType.getInstance()),
                Map.entry("nchar", StringType.getInstance()),
                Map.entry("ncharVary", StringType.getInstance()),
                Map.entry("nclob", StringType.getInstance()),
                Map.entry("number", NumericType.getInstance()),
                Map.entry("numeric", NumericType.getInstance()),
                Map.entry("raw", BinaryType.getInstance()),
                Map.entry("real", FloatType.getInstance()),
                Map.entry("rowid", StringType.getInstance()),
                Map.entry("smallInt", NumericType.getInstance()),
                Map.entry("timestamp", DateType.getInstance()),
                Map.entry("urowid", StringType.getInstance()),
                Map.entry("varchar", StringType.getInstance()),
                Map.entry("varChar2", StringType.getInstance()));
    
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
    public DatatypesConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.Oracle.Datatypes domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("bfile", domain.getBfile());
        __sqlParameters.put("blob", domain.getBlob());
        __sqlParameters.put("bDouble", domain.getBDouble());
        __sqlParameters.put("bFloat", domain.getBFloat());
        __sqlParameters.put("character", domain.getCharacter());
        __sqlParameters.put("characterVar", domain.getCharacterVar());
        __sqlParameters.put("char_", domain.getChar_());
        __sqlParameters.put("charVar", domain.getCharVar());
        __sqlParameters.put("clob", domain.getClob());
        __sqlParameters.put("date", domain.getDate());
        __sqlParameters.put("decimal", domain.getDecimal());
        __sqlParameters.put("dec", domain.getDec());
        __sqlParameters.put("dPrecision", domain.getDPrecision());
        __sqlParameters.put("float_", domain.getFloat_());
        __sqlParameters.put("integer", domain.getInteger());
        __sqlParameters.put("intervalDay", domain.getIntervalDay());
        __sqlParameters.put("intervalYear", domain.getIntervalYear());
        __sqlParameters.put("int_", domain.getInt_());
        __sqlParameters.put("long_", domain.getLong_());
        __sqlParameters.put("nationalChar", domain.getNationalChar());
        __sqlParameters.put("nationalCharacter", domain.getNationalCharacter());
        __sqlParameters.put("nationalCharacterVarying", domain.getNationalCharacterVarying());
        __sqlParameters.put("nationalCharVar", domain.getNationalCharVar());
        __sqlParameters.put("nchar", domain.getNchar());
        __sqlParameters.put("ncharVary", domain.getNcharVary());
        __sqlParameters.put("nclob", domain.getNclob());
        __sqlParameters.put("number", domain.getNumber());
        __sqlParameters.put("numeric", domain.getNumeric());
        __sqlParameters.put("raw", domain.getRaw());
        __sqlParameters.put("real", domain.getReal());
        __sqlParameters.put("rowid", domain.getRowid());
        __sqlParameters.put("smallInt", domain.getSmallInt());
        __sqlParameters.put("timestamp", domain.getTimestamp());
        __sqlParameters.put("urowid", domain.getUrowid());
        __sqlParameters.put("varchar", domain.getVarchar());
        __sqlParameters.put("varChar2", domain.getVarChar2());
    }

    /**
     * Setter for placeholder bfile.
     * <p>
     * Placeholder is mapped to datatypes.BFILE_.
     */
    public void setBfile(Blob value) {
        __sqlParameters.put("bfile", value);
    }

    /**
     * Setter for placeholder bfile.
     * <p>
     * Placeholder is mapped to datatypes.BFILE_.
     */
    public DatatypesConfig withBfile(Blob value) {
        setBfile(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder blob.
     * <p>
     * Placeholder is mapped to datatypes.BLOB_.
     */
    public void setBlob(Blob value) {
        __sqlParameters.put("blob", value);
    }

    /**
     * Setter for placeholder blob.
     * <p>
     * Placeholder is mapped to datatypes.BLOB_.
     */
    public DatatypesConfig withBlob(Blob value) {
        setBlob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bDouble.
     * <p>
     * Placeholder is mapped to datatypes.B_DOUBLE.
     */
    public void setBDouble(Double value) {
        __sqlParameters.put("bDouble", value);
    }

    /**
     * Setter for placeholder bDouble.
     * <p>
     * Placeholder is mapped to datatypes.B_DOUBLE.
     */
    public DatatypesConfig withBDouble(Double value) {
        setBDouble(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bFloat.
     * <p>
     * Placeholder is mapped to datatypes.B_FLOAT.
     */
    public void setBFloat(Float value) {
        __sqlParameters.put("bFloat", value);
    }

    /**
     * Setter for placeholder bFloat.
     * <p>
     * Placeholder is mapped to datatypes.B_FLOAT.
     */
    public DatatypesConfig withBFloat(Float value) {
        setBFloat(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder character.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_.
     */
    public void setCharacter(String value) {
        __sqlParameters.put("character", value);
    }

    /**
     * Setter for placeholder character.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_.
     */
    public DatatypesConfig withCharacter(String value) {
        setCharacter(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder characterVar.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_VAR.
     */
    public void setCharacterVar(String value) {
        __sqlParameters.put("characterVar", value);
    }

    /**
     * Setter for placeholder characterVar.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_VAR.
     */
    public DatatypesConfig withCharacterVar(String value) {
        setCharacterVar(value);
        
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
     * Setter for placeholder charVar.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_VAR.
     */
    public void setCharVar(String value) {
        __sqlParameters.put("charVar", value);
    }

    /**
     * Setter for placeholder charVar.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_VAR.
     */
    public DatatypesConfig withCharVar(String value) {
        setCharVar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder clob.
     * <p>
     * Placeholder is mapped to datatypes.CLOB_.
     */
    public void setClob(String value) {
        __sqlParameters.put("clob", value);
    }

    /**
     * Setter for placeholder clob.
     * <p>
     * Placeholder is mapped to datatypes.CLOB_.
     */
    public DatatypesConfig withClob(String value) {
        setClob(value);
        
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
     * Setter for placeholder dPrecision.
     * <p>
     * Placeholder is mapped to datatypes.D_PRECISION.
     */
    public void setDPrecision(Float value) {
        __sqlParameters.put("dPrecision", value);
    }

    /**
     * Setter for placeholder dPrecision.
     * <p>
     * Placeholder is mapped to datatypes.D_PRECISION.
     */
    public DatatypesConfig withDPrecision(Float value) {
        setDPrecision(value);
        
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
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to datatypes.INTEGER_.
     */
    public void setInteger(BigDecimal value) {
        __sqlParameters.put("integer", value);
    }

    /**
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to datatypes.INTEGER_.
     */
    public DatatypesConfig withInteger(BigDecimal value) {
        setInteger(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder intervalDay.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_DAY.
     */
    public void setIntervalDay(Duration value) {
        __sqlParameters.put("intervalDay", value);
    }

    /**
     * Setter for placeholder intervalDay.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_DAY.
     */
    public DatatypesConfig withIntervalDay(Duration value) {
        setIntervalDay(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder intervalYear.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_YEAR.
     */
    public void setIntervalYear(Period value) {
        __sqlParameters.put("intervalYear", value);
    }

    /**
     * Setter for placeholder intervalYear.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_YEAR.
     */
    public DatatypesConfig withIntervalYear(Period value) {
        setIntervalYear(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder int_.
     * <p>
     * Placeholder is mapped to datatypes.INT_.
     */
    public void setInt_(BigDecimal value) {
        __sqlParameters.put("int_", value);
    }

    /**
     * Setter for placeholder int_.
     * <p>
     * Placeholder is mapped to datatypes.INT_.
     */
    public DatatypesConfig withInt_(BigDecimal value) {
        setInt_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder long_.
     * <p>
     * Placeholder is mapped to datatypes.LONG_.
     */
    public void setLong_(String value) {
        __sqlParameters.put("long_", value);
    }

    /**
     * Setter for placeholder long_.
     * <p>
     * Placeholder is mapped to datatypes.LONG_.
     */
    public DatatypesConfig withLong_(String value) {
        setLong_(value);
        
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
     * Setter for placeholder nationalCharacter.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHARACTER.
     */
    public void setNationalCharacter(String value) {
        __sqlParameters.put("nationalCharacter", value);
    }

    /**
     * Setter for placeholder nationalCharacter.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHARACTER.
     */
    public DatatypesConfig withNationalCharacter(String value) {
        setNationalCharacter(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nationalCharacterVarying.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHARACTER_VARYING.
     */
    public void setNationalCharacterVarying(String value) {
        __sqlParameters.put("nationalCharacterVarying", value);
    }

    /**
     * Setter for placeholder nationalCharacterVarying.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHARACTER_VARYING.
     */
    public DatatypesConfig withNationalCharacterVarying(String value) {
        setNationalCharacterVarying(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nationalCharVar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHAR_VAR.
     */
    public void setNationalCharVar(String value) {
        __sqlParameters.put("nationalCharVar", value);
    }

    /**
     * Setter for placeholder nationalCharVar.
     * <p>
     * Placeholder is mapped to datatypes.NATIONAL_CHAR_VAR.
     */
    public DatatypesConfig withNationalCharVar(String value) {
        setNationalCharVar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nchar.
     * <p>
     * Placeholder is mapped to datatypes.NCHAR_.
     */
    public void setNchar(String value) {
        __sqlParameters.put("nchar", value);
    }

    /**
     * Setter for placeholder nchar.
     * <p>
     * Placeholder is mapped to datatypes.NCHAR_.
     */
    public DatatypesConfig withNchar(String value) {
        setNchar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder ncharVary.
     * <p>
     * Placeholder is mapped to datatypes.NCHAR_VARY.
     */
    public void setNcharVary(String value) {
        __sqlParameters.put("ncharVary", value);
    }

    /**
     * Setter for placeholder ncharVary.
     * <p>
     * Placeholder is mapped to datatypes.NCHAR_VARY.
     */
    public DatatypesConfig withNcharVary(String value) {
        setNcharVary(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder nclob.
     * <p>
     * Placeholder is mapped to datatypes.NCLOB_.
     */
    public void setNclob(String value) {
        __sqlParameters.put("nclob", value);
    }

    /**
     * Setter for placeholder nclob.
     * <p>
     * Placeholder is mapped to datatypes.NCLOB_.
     */
    public DatatypesConfig withNclob(String value) {
        setNclob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder number.
     * <p>
     * Placeholder is mapped to datatypes.NUMBER_.
     */
    public void setNumber(BigDecimal value) {
        __sqlParameters.put("number", value);
    }

    /**
     * Setter for placeholder number.
     * <p>
     * Placeholder is mapped to datatypes.NUMBER_.
     */
    public DatatypesConfig withNumber(BigDecimal value) {
        setNumber(value);
        
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
     * Setter for placeholder raw.
     * <p>
     * Placeholder is mapped to datatypes.RAW_.
     */
    public void setRaw(byte[] value) {
        __sqlParameters.put("raw", value);
    }

    /**
     * Setter for placeholder raw.
     * <p>
     * Placeholder is mapped to datatypes.RAW_.
     */
    public DatatypesConfig withRaw(byte[] value) {
        setRaw(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to datatypes.REAL_.
     */
    public void setReal(Float value) {
        __sqlParameters.put("real", value);
    }

    /**
     * Setter for placeholder real.
     * <p>
     * Placeholder is mapped to datatypes.REAL_.
     */
    public DatatypesConfig withReal(Float value) {
        setReal(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder rowid.
     * <p>
     * Placeholder is mapped to datatypes.ROWID_.
     */
    public void setRowid(String value) {
        __sqlParameters.put("rowid", value);
    }

    /**
     * Setter for placeholder rowid.
     * <p>
     * Placeholder is mapped to datatypes.ROWID_.
     */
    public DatatypesConfig withRowid(String value) {
        setRowid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder smallInt.
     * <p>
     * Placeholder is mapped to datatypes.SMALL_INT.
     */
    public void setSmallInt(BigDecimal value) {
        __sqlParameters.put("smallInt", value);
    }

    /**
     * Setter for placeholder smallInt.
     * <p>
     * Placeholder is mapped to datatypes.SMALL_INT.
     */
    public DatatypesConfig withSmallInt(BigDecimal value) {
        setSmallInt(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timestamp.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_.
     */
    public void setTimestamp(Date value) {
        __sqlParameters.put("timestamp", value);
    }

    /**
     * Setter for placeholder timestamp.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_.
     */
    public DatatypesConfig withTimestamp(Date value) {
        setTimestamp(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder urowid.
     * <p>
     * Placeholder is mapped to datatypes.UROWID_.
     */
    public void setUrowid(String value) {
        __sqlParameters.put("urowid", value);
    }

    /**
     * Setter for placeholder urowid.
     * <p>
     * Placeholder is mapped to datatypes.UROWID_.
     */
    public DatatypesConfig withUrowid(String value) {
        setUrowid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varchar.
     * <p>
     * Placeholder is mapped to datatypes.VARCHAR_.
     */
    public void setVarchar(String value) {
        __sqlParameters.put("varchar", value);
    }

    /**
     * Setter for placeholder varchar.
     * <p>
     * Placeholder is mapped to datatypes.VARCHAR_.
     */
    public DatatypesConfig withVarchar(String value) {
        setVarchar(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varChar2.
     * <p>
     * Placeholder is mapped to datatypes.VAR_CHAR2.
     */
    public void setVarChar2(String value) {
        __sqlParameters.put("varChar2", value);
    }

    /**
     * Setter for placeholder varChar2.
     * <p>
     * Placeholder is mapped to datatypes.VAR_CHAR2.
     */
    public DatatypesConfig withVarChar2(String value) {
        setVarChar2(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.Oracle.Datatypes.class;
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