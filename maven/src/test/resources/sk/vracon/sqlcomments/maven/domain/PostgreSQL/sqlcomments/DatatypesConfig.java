package sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.LongType;
import sk.vracon.sqlcomments.core.types.BinaryType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.sql.Array;
import sk.vracon.sqlcomments.core.types.ArrayType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;
import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
import sk.vracon.sqlcomments.core.types.IntegerType;

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

    private static final Set<String> __primaryKey = Set.of("INTEGER_");    

    private static final Set<String> __acceptNullParameters = Set.of("bigInt", "bit", "bitVarying", "boolean_", "bool", "box", "bytea", "character", "characterVarying", "char_", "charArray", "cidr", "cid", "circle", "date", "decimal", "doublePrecision", "enum_", "float4", "float8", "inet", "integer", "interval", "int_", "int2", "int4", "int8", "intArray", "line", "lseg", "macaddr", "money", "numeric", "oid", "path", "point", "polygon", "real", "regClass", "regOper", "regOperator", "regProc", "regProcedure", "regType", "smallInt", "text", "timestamp", "timestampTz", "timestampWtz", "time", "timeTz", "timeWotz", "timeWtz", "tsQuery", "tsVector", "txidSnapshot", "uuid", "varchar", "varcharArray", "varBit", "xml");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("bigInt", LongType.getInstance()),
                Map.entry("bit", BinaryType.getInstance()),
                Map.entry("bitVarying", BinaryType.getInstance()),
                Map.entry("boolean_", BinaryType.getInstance()),
                Map.entry("bool", BinaryType.getInstance()),
                Map.entry("box", BinaryType.getInstance()),
                Map.entry("bytea", BinaryType.getInstance()),
                Map.entry("character", StringType.getInstance()),
                Map.entry("characterVarying", StringType.getInstance()),
                Map.entry("char_", StringType.getInstance()),
                Map.entry("charArray", ArrayType.getInstance()),
                Map.entry("cidr", BinaryType.getInstance()),
                Map.entry("cid", BinaryType.getInstance()),
                Map.entry("circle", BinaryType.getInstance()),
                Map.entry("date", DateType.getInstance()),
                Map.entry("decimal", NumericType.getInstance()),
                Map.entry("doublePrecision", DoubleType.getInstance()),
                Map.entry("enum_", StringType.getInstance()),
                Map.entry("float4", FloatType.getInstance()),
                Map.entry("float8", DoubleType.getInstance()),
                Map.entry("inet", BinaryType.getInstance()),
                Map.entry("integer", IntegerType.getInstance()),
                Map.entry("interval", BinaryType.getInstance()),
                Map.entry("int_", IntegerType.getInstance()),
                Map.entry("int2", IntegerType.getInstance()),
                Map.entry("int4", IntegerType.getInstance()),
                Map.entry("int8", LongType.getInstance()),
                Map.entry("intArray", ArrayType.getInstance()),
                Map.entry("line", BinaryType.getInstance()),
                Map.entry("lseg", BinaryType.getInstance()),
                Map.entry("macaddr", BinaryType.getInstance()),
                Map.entry("money", DoubleType.getInstance()),
                Map.entry("numeric", NumericType.getInstance()),
                Map.entry("oid", LongType.getInstance()),
                Map.entry("path", BinaryType.getInstance()),
                Map.entry("point", BinaryType.getInstance()),
                Map.entry("polygon", BinaryType.getInstance()),
                Map.entry("real", FloatType.getInstance()),
                Map.entry("regClass", BinaryType.getInstance()),
                Map.entry("regOper", BinaryType.getInstance()),
                Map.entry("regOperator", BinaryType.getInstance()),
                Map.entry("regProc", BinaryType.getInstance()),
                Map.entry("regProcedure", BinaryType.getInstance()),
                Map.entry("regType", BinaryType.getInstance()),
                Map.entry("smallInt", IntegerType.getInstance()),
                Map.entry("text", StringType.getInstance()),
                Map.entry("timestamp", DateType.getInstance()),
                Map.entry("timestampTz", DateType.getInstance()),
                Map.entry("timestampWtz", DateType.getInstance()),
                Map.entry("time", DateType.getInstance()),
                Map.entry("timeTz", DateType.getInstance()),
                Map.entry("timeWotz", DateType.getInstance()),
                Map.entry("timeWtz", DateType.getInstance()),
                Map.entry("tsQuery", BinaryType.getInstance()),
                Map.entry("tsVector", BinaryType.getInstance()),
                Map.entry("txidSnapshot", BinaryType.getInstance()),
                Map.entry("uuid", BinaryType.getInstance()),
                Map.entry("varchar", StringType.getInstance()),
                Map.entry("varcharArray", ArrayType.getInstance()),
                Map.entry("varBit", BinaryType.getInstance()),
                Map.entry("xml", StringType.getInstance()));
    
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
    public DatatypesConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.PostgreSQL.Datatypes domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("bigInt", domain.getBigInt());
        __sqlParameters.put("bit", domain.getBit());
        __sqlParameters.put("bitVarying", domain.getBitVarying());
        __sqlParameters.put("boolean_", domain.getBoolean_());
        __sqlParameters.put("bool", domain.getBool());
        __sqlParameters.put("box", domain.getBox());
        __sqlParameters.put("bytea", domain.getBytea());
        __sqlParameters.put("character", domain.getCharacter());
        __sqlParameters.put("characterVarying", domain.getCharacterVarying());
        __sqlParameters.put("char_", domain.getChar_());
        __sqlParameters.put("charArray", domain.getCharArray());
        __sqlParameters.put("cidr", domain.getCidr());
        __sqlParameters.put("cid", domain.getCid());
        __sqlParameters.put("circle", domain.getCircle());
        __sqlParameters.put("date", domain.getDate());
        __sqlParameters.put("decimal", domain.getDecimal());
        __sqlParameters.put("doublePrecision", domain.getDoublePrecision());
        __sqlParameters.put("enum_", domain.getEnum_());
        __sqlParameters.put("float4", domain.getFloat4());
        __sqlParameters.put("float8", domain.getFloat8());
        __sqlParameters.put("inet", domain.getInet());
        __sqlParameters.put("integer", domain.getInteger());
        __sqlParameters.put("interval", domain.getInterval());
        __sqlParameters.put("int_", domain.getInt_());
        __sqlParameters.put("int2", domain.getInt2());
        __sqlParameters.put("int4", domain.getInt4());
        __sqlParameters.put("int8", domain.getInt8());
        __sqlParameters.put("intArray", domain.getIntArray());
        __sqlParameters.put("line", domain.getLine());
        __sqlParameters.put("lseg", domain.getLseg());
        __sqlParameters.put("macaddr", domain.getMacaddr());
        __sqlParameters.put("money", domain.getMoney());
        __sqlParameters.put("numeric", domain.getNumeric());
        __sqlParameters.put("oid", domain.getOid());
        __sqlParameters.put("path", domain.getPath());
        __sqlParameters.put("point", domain.getPoint());
        __sqlParameters.put("polygon", domain.getPolygon());
        __sqlParameters.put("real", domain.getReal());
        __sqlParameters.put("regClass", domain.getRegClass());
        __sqlParameters.put("regOper", domain.getRegOper());
        __sqlParameters.put("regOperator", domain.getRegOperator());
        __sqlParameters.put("regProc", domain.getRegProc());
        __sqlParameters.put("regProcedure", domain.getRegProcedure());
        __sqlParameters.put("regType", domain.getRegType());
        __sqlParameters.put("smallInt", domain.getSmallInt());
        __sqlParameters.put("text", domain.getText());
        __sqlParameters.put("timestamp", domain.getTimestamp());
        __sqlParameters.put("timestampTz", domain.getTimestampTz());
        __sqlParameters.put("timestampWtz", domain.getTimestampWtz());
        __sqlParameters.put("time", domain.getTime());
        __sqlParameters.put("timeTz", domain.getTimeTz());
        __sqlParameters.put("timeWotz", domain.getTimeWotz());
        __sqlParameters.put("timeWtz", domain.getTimeWtz());
        __sqlParameters.put("tsQuery", domain.getTsQuery());
        __sqlParameters.put("tsVector", domain.getTsVector());
        __sqlParameters.put("txidSnapshot", domain.getTxidSnapshot());
        __sqlParameters.put("uuid", domain.getUuid());
        __sqlParameters.put("varchar", domain.getVarchar());
        __sqlParameters.put("varcharArray", domain.getVarcharArray());
        __sqlParameters.put("varBit", domain.getVarBit());
        __sqlParameters.put("xml", domain.getXml());
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
     * Setter for placeholder bitVarying.
     * <p>
     * Placeholder is mapped to datatypes.BIT_VARYING.
     */
    public void setBitVarying(byte[] value) {
        __sqlParameters.put("bitVarying", value);
    }

    /**
     * Setter for placeholder bitVarying.
     * <p>
     * Placeholder is mapped to datatypes.BIT_VARYING.
     */
    public DatatypesConfig withBitVarying(byte[] value) {
        setBitVarying(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to datatypes.BOOLEAN_.
     */
    public void setBoolean_(byte[] value) {
        __sqlParameters.put("boolean_", value);
    }

    /**
     * Setter for placeholder boolean_.
     * <p>
     * Placeholder is mapped to datatypes.BOOLEAN_.
     */
    public DatatypesConfig withBoolean_(byte[] value) {
        setBoolean_(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bool.
     * <p>
     * Placeholder is mapped to datatypes.BOOL_.
     */
    public void setBool(byte[] value) {
        __sqlParameters.put("bool", value);
    }

    /**
     * Setter for placeholder bool.
     * <p>
     * Placeholder is mapped to datatypes.BOOL_.
     */
    public DatatypesConfig withBool(byte[] value) {
        setBool(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder box.
     * <p>
     * Placeholder is mapped to datatypes.BOX_.
     */
    public void setBox(byte[] value) {
        __sqlParameters.put("box", value);
    }

    /**
     * Setter for placeholder box.
     * <p>
     * Placeholder is mapped to datatypes.BOX_.
     */
    public DatatypesConfig withBox(byte[] value) {
        setBox(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder bytea.
     * <p>
     * Placeholder is mapped to datatypes.BYTEA_.
     */
    public void setBytea(byte[] value) {
        __sqlParameters.put("bytea", value);
    }

    /**
     * Setter for placeholder bytea.
     * <p>
     * Placeholder is mapped to datatypes.BYTEA_.
     */
    public DatatypesConfig withBytea(byte[] value) {
        setBytea(value);
        
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
     * Setter for placeholder characterVarying.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_VARYING.
     */
    public void setCharacterVarying(String value) {
        __sqlParameters.put("characterVarying", value);
    }

    /**
     * Setter for placeholder characterVarying.
     * <p>
     * Placeholder is mapped to datatypes.CHARACTER_VARYING.
     */
    public DatatypesConfig withCharacterVarying(String value) {
        setCharacterVarying(value);
        
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
     * Setter for placeholder charArray.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_ARRAY.
     */
    public void setCharArray(Array value) {
        __sqlParameters.put("charArray", value);
    }

    /**
     * Setter for placeholder charArray.
     * <p>
     * Placeholder is mapped to datatypes.CHAR_ARRAY.
     */
    public DatatypesConfig withCharArray(Array value) {
        setCharArray(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder cidr.
     * <p>
     * Placeholder is mapped to datatypes.CIDR_.
     */
    public void setCidr(byte[] value) {
        __sqlParameters.put("cidr", value);
    }

    /**
     * Setter for placeholder cidr.
     * <p>
     * Placeholder is mapped to datatypes.CIDR_.
     */
    public DatatypesConfig withCidr(byte[] value) {
        setCidr(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder cid.
     * <p>
     * Placeholder is mapped to datatypes.CID_.
     */
    public void setCid(byte[] value) {
        __sqlParameters.put("cid", value);
    }

    /**
     * Setter for placeholder cid.
     * <p>
     * Placeholder is mapped to datatypes.CID_.
     */
    public DatatypesConfig withCid(byte[] value) {
        setCid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder circle.
     * <p>
     * Placeholder is mapped to datatypes.CIRCLE_.
     */
    public void setCircle(byte[] value) {
        __sqlParameters.put("circle", value);
    }

    /**
     * Setter for placeholder circle.
     * <p>
     * Placeholder is mapped to datatypes.CIRCLE_.
     */
    public DatatypesConfig withCircle(byte[] value) {
        setCircle(value);
        
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
     * Setter for placeholder float4.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_4.
     */
    public void setFloat4(Float value) {
        __sqlParameters.put("float4", value);
    }

    /**
     * Setter for placeholder float4.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_4.
     */
    public DatatypesConfig withFloat4(Float value) {
        setFloat4(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder float8.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_8.
     */
    public void setFloat8(Double value) {
        __sqlParameters.put("float8", value);
    }

    /**
     * Setter for placeholder float8.
     * <p>
     * Placeholder is mapped to datatypes.FLOAT_8.
     */
    public DatatypesConfig withFloat8(Double value) {
        setFloat8(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder inet.
     * <p>
     * Placeholder is mapped to datatypes.INET_.
     */
    public void setInet(byte[] value) {
        __sqlParameters.put("inet", value);
    }

    /**
     * Setter for placeholder inet.
     * <p>
     * Placeholder is mapped to datatypes.INET_.
     */
    public DatatypesConfig withInet(byte[] value) {
        setInet(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to datatypes.INTEGER_.
     */
    public void setInteger(Integer value) {
        __sqlParameters.put("integer", value);
    }

    /**
     * Setter for placeholder integer.
     * <p>
     * Placeholder is mapped to datatypes.INTEGER_.
     */
    public DatatypesConfig withInteger(Integer value) {
        setInteger(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder interval.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_.
     */
    public void setInterval(byte[] value) {
        __sqlParameters.put("interval", value);
    }

    /**
     * Setter for placeholder interval.
     * <p>
     * Placeholder is mapped to datatypes.INTERVAL_.
     */
    public DatatypesConfig withInterval(byte[] value) {
        setInterval(value);
        
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
     * Setter for placeholder int2.
     * <p>
     * Placeholder is mapped to datatypes.INT_2.
     */
    public void setInt2(Integer value) {
        __sqlParameters.put("int2", value);
    }

    /**
     * Setter for placeholder int2.
     * <p>
     * Placeholder is mapped to datatypes.INT_2.
     */
    public DatatypesConfig withInt2(Integer value) {
        setInt2(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder int4.
     * <p>
     * Placeholder is mapped to datatypes.INT_4.
     */
    public void setInt4(Integer value) {
        __sqlParameters.put("int4", value);
    }

    /**
     * Setter for placeholder int4.
     * <p>
     * Placeholder is mapped to datatypes.INT_4.
     */
    public DatatypesConfig withInt4(Integer value) {
        setInt4(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder int8.
     * <p>
     * Placeholder is mapped to datatypes.INT_8.
     */
    public void setInt8(Long value) {
        __sqlParameters.put("int8", value);
    }

    /**
     * Setter for placeholder int8.
     * <p>
     * Placeholder is mapped to datatypes.INT_8.
     */
    public DatatypesConfig withInt8(Long value) {
        setInt8(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder intArray.
     * <p>
     * Placeholder is mapped to datatypes.INT_ARRAY.
     */
    public void setIntArray(Array value) {
        __sqlParameters.put("intArray", value);
    }

    /**
     * Setter for placeholder intArray.
     * <p>
     * Placeholder is mapped to datatypes.INT_ARRAY.
     */
    public DatatypesConfig withIntArray(Array value) {
        setIntArray(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder line.
     * <p>
     * Placeholder is mapped to datatypes.LINE_.
     */
    public void setLine(byte[] value) {
        __sqlParameters.put("line", value);
    }

    /**
     * Setter for placeholder line.
     * <p>
     * Placeholder is mapped to datatypes.LINE_.
     */
    public DatatypesConfig withLine(byte[] value) {
        setLine(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder lseg.
     * <p>
     * Placeholder is mapped to datatypes.LSEG_.
     */
    public void setLseg(byte[] value) {
        __sqlParameters.put("lseg", value);
    }

    /**
     * Setter for placeholder lseg.
     * <p>
     * Placeholder is mapped to datatypes.LSEG_.
     */
    public DatatypesConfig withLseg(byte[] value) {
        setLseg(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder macaddr.
     * <p>
     * Placeholder is mapped to datatypes.MACADDR_.
     */
    public void setMacaddr(byte[] value) {
        __sqlParameters.put("macaddr", value);
    }

    /**
     * Setter for placeholder macaddr.
     * <p>
     * Placeholder is mapped to datatypes.MACADDR_.
     */
    public DatatypesConfig withMacaddr(byte[] value) {
        setMacaddr(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder money.
     * <p>
     * Placeholder is mapped to datatypes.MONEY_.
     */
    public void setMoney(Double value) {
        __sqlParameters.put("money", value);
    }

    /**
     * Setter for placeholder money.
     * <p>
     * Placeholder is mapped to datatypes.MONEY_.
     */
    public DatatypesConfig withMoney(Double value) {
        setMoney(value);
        
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
     * Setter for placeholder oid.
     * <p>
     * Placeholder is mapped to datatypes.OID_.
     */
    public void setOid(Long value) {
        __sqlParameters.put("oid", value);
    }

    /**
     * Setter for placeholder oid.
     * <p>
     * Placeholder is mapped to datatypes.OID_.
     */
    public DatatypesConfig withOid(Long value) {
        setOid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder path.
     * <p>
     * Placeholder is mapped to datatypes.PATH_.
     */
    public void setPath(byte[] value) {
        __sqlParameters.put("path", value);
    }

    /**
     * Setter for placeholder path.
     * <p>
     * Placeholder is mapped to datatypes.PATH_.
     */
    public DatatypesConfig withPath(byte[] value) {
        setPath(value);
        
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
     * Setter for placeholder regClass.
     * <p>
     * Placeholder is mapped to datatypes.REG_CLASS.
     */
    public void setRegClass(byte[] value) {
        __sqlParameters.put("regClass", value);
    }

    /**
     * Setter for placeholder regClass.
     * <p>
     * Placeholder is mapped to datatypes.REG_CLASS.
     */
    public DatatypesConfig withRegClass(byte[] value) {
        setRegClass(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder regOper.
     * <p>
     * Placeholder is mapped to datatypes.REG_OPER.
     */
    public void setRegOper(byte[] value) {
        __sqlParameters.put("regOper", value);
    }

    /**
     * Setter for placeholder regOper.
     * <p>
     * Placeholder is mapped to datatypes.REG_OPER.
     */
    public DatatypesConfig withRegOper(byte[] value) {
        setRegOper(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder regOperator.
     * <p>
     * Placeholder is mapped to datatypes.REG_OPERATOR.
     */
    public void setRegOperator(byte[] value) {
        __sqlParameters.put("regOperator", value);
    }

    /**
     * Setter for placeholder regOperator.
     * <p>
     * Placeholder is mapped to datatypes.REG_OPERATOR.
     */
    public DatatypesConfig withRegOperator(byte[] value) {
        setRegOperator(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder regProc.
     * <p>
     * Placeholder is mapped to datatypes.REG_PROC.
     */
    public void setRegProc(byte[] value) {
        __sqlParameters.put("regProc", value);
    }

    /**
     * Setter for placeholder regProc.
     * <p>
     * Placeholder is mapped to datatypes.REG_PROC.
     */
    public DatatypesConfig withRegProc(byte[] value) {
        setRegProc(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder regProcedure.
     * <p>
     * Placeholder is mapped to datatypes.REG_PROCEDURE.
     */
    public void setRegProcedure(byte[] value) {
        __sqlParameters.put("regProcedure", value);
    }

    /**
     * Setter for placeholder regProcedure.
     * <p>
     * Placeholder is mapped to datatypes.REG_PROCEDURE.
     */
    public DatatypesConfig withRegProcedure(byte[] value) {
        setRegProcedure(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder regType.
     * <p>
     * Placeholder is mapped to datatypes.REG_TYPE.
     */
    public void setRegType(byte[] value) {
        __sqlParameters.put("regType", value);
    }

    /**
     * Setter for placeholder regType.
     * <p>
     * Placeholder is mapped to datatypes.REG_TYPE.
     */
    public DatatypesConfig withRegType(byte[] value) {
        setRegType(value);
        
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
     * Setter for placeholder timestampTz.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_TZ.
     */
    public void setTimestampTz(Date value) {
        __sqlParameters.put("timestampTz", value);
    }

    /**
     * Setter for placeholder timestampTz.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_TZ.
     */
    public DatatypesConfig withTimestampTz(Date value) {
        setTimestampTz(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timestampWtz.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_WTZ.
     */
    public void setTimestampWtz(Date value) {
        __sqlParameters.put("timestampWtz", value);
    }

    /**
     * Setter for placeholder timestampWtz.
     * <p>
     * Placeholder is mapped to datatypes.TIMESTAMP_WTZ.
     */
    public DatatypesConfig withTimestampWtz(Date value) {
        setTimestampWtz(value);
        
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
     * Setter for placeholder timeTz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_TZ.
     */
    public void setTimeTz(Date value) {
        __sqlParameters.put("timeTz", value);
    }

    /**
     * Setter for placeholder timeTz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_TZ.
     */
    public DatatypesConfig withTimeTz(Date value) {
        setTimeTz(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timeWotz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_WOTZ.
     */
    public void setTimeWotz(Date value) {
        __sqlParameters.put("timeWotz", value);
    }

    /**
     * Setter for placeholder timeWotz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_WOTZ.
     */
    public DatatypesConfig withTimeWotz(Date value) {
        setTimeWotz(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder timeWtz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_WTZ.
     */
    public void setTimeWtz(Date value) {
        __sqlParameters.put("timeWtz", value);
    }

    /**
     * Setter for placeholder timeWtz.
     * <p>
     * Placeholder is mapped to datatypes.TIME_WTZ.
     */
    public DatatypesConfig withTimeWtz(Date value) {
        setTimeWtz(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tsQuery.
     * <p>
     * Placeholder is mapped to datatypes.TS_QUERY.
     */
    public void setTsQuery(byte[] value) {
        __sqlParameters.put("tsQuery", value);
    }

    /**
     * Setter for placeholder tsQuery.
     * <p>
     * Placeholder is mapped to datatypes.TS_QUERY.
     */
    public DatatypesConfig withTsQuery(byte[] value) {
        setTsQuery(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder tsVector.
     * <p>
     * Placeholder is mapped to datatypes.TS_VECTOR.
     */
    public void setTsVector(byte[] value) {
        __sqlParameters.put("tsVector", value);
    }

    /**
     * Setter for placeholder tsVector.
     * <p>
     * Placeholder is mapped to datatypes.TS_VECTOR.
     */
    public DatatypesConfig withTsVector(byte[] value) {
        setTsVector(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder txidSnapshot.
     * <p>
     * Placeholder is mapped to datatypes.TXID_SNAPSHOT_.
     */
    public void setTxidSnapshot(byte[] value) {
        __sqlParameters.put("txidSnapshot", value);
    }

    /**
     * Setter for placeholder txidSnapshot.
     * <p>
     * Placeholder is mapped to datatypes.TXID_SNAPSHOT_.
     */
    public DatatypesConfig withTxidSnapshot(byte[] value) {
        setTxidSnapshot(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder uuid.
     * <p>
     * Placeholder is mapped to datatypes.UUID_.
     */
    public void setUuid(byte[] value) {
        __sqlParameters.put("uuid", value);
    }

    /**
     * Setter for placeholder uuid.
     * <p>
     * Placeholder is mapped to datatypes.UUID_.
     */
    public DatatypesConfig withUuid(byte[] value) {
        setUuid(value);
        
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
     * Setter for placeholder varcharArray.
     * <p>
     * Placeholder is mapped to datatypes.VARCHAR_ARRAY.
     */
    public void setVarcharArray(Array value) {
        __sqlParameters.put("varcharArray", value);
    }

    /**
     * Setter for placeholder varcharArray.
     * <p>
     * Placeholder is mapped to datatypes.VARCHAR_ARRAY.
     */
    public DatatypesConfig withVarcharArray(Array value) {
        setVarcharArray(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder varBit.
     * <p>
     * Placeholder is mapped to datatypes.VAR_BIT.
     */
    public void setVarBit(byte[] value) {
        __sqlParameters.put("varBit", value);
    }

    /**
     * Setter for placeholder varBit.
     * <p>
     * Placeholder is mapped to datatypes.VAR_BIT.
     */
    public DatatypesConfig withVarBit(byte[] value) {
        setVarBit(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder xml.
     * <p>
     * Placeholder is mapped to datatypes.XML_.
     */
    public void setXml(String value) {
        __sqlParameters.put("xml", value);
    }

    /**
     * Setter for placeholder xml.
     * <p>
     * Placeholder is mapped to datatypes.XML_.
     */
    public DatatypesConfig withXml(String value) {
        setXml(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.PostgreSQL.Datatypes.class;
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