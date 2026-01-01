package sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.LongType;
import sk.vracon.sqlcomments.core.types.BinaryType;
import java.sql.Array;
import sk.vracon.sqlcomments.core.types.ArrayType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;
import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
import sk.vracon.sqlcomments.core.types.IntegerType;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.domain.PostgreSQL.Datatypes;

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
    private Type<byte[]> bitType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> bitVaryingType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> boolType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> boolean_Type = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> boxType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> byteaType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Array> charArrayType = (Type<Array>) ArrayType.getInstance(); 
    private Type<String> char_Type = (Type<String>) StringType.getInstance(); 
    private Type<String> characterType = (Type<String>) StringType.getInstance(); 
    private Type<String> characterVaryingType = (Type<String>) StringType.getInstance(); 
    private Type<byte[]> cidType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> cidrType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> circleType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Date> dateType = (Type<Date>) DateType.getInstance(); 
    private Type<BigDecimal> decimalType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Double> doublePrecisionType = (Type<Double>) DoubleType.getInstance(); 
    private Type<String> enum_Type = (Type<String>) StringType.getInstance(); 
    private Type<Float> float4Type = (Type<Float>) FloatType.getInstance(); 
    private Type<Double> float8Type = (Type<Double>) DoubleType.getInstance(); 
    private Type<byte[]> inetType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Integer> int2Type = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> int4Type = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Long> int8Type = (Type<Long>) LongType.getInstance(); 
    private Type<Array> intArrayType = (Type<Array>) ArrayType.getInstance(); 
    private Type<Integer> int_Type = (Type<Integer>) IntegerType.getInstance(); 
    private Type<Integer> integerType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<byte[]> intervalType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> lineType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> lsegType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> macaddrType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Double> moneyType = (Type<Double>) DoubleType.getInstance(); 
    private Type<BigDecimal> numericType = (Type<BigDecimal>) NumericType.getInstance(); 
    private Type<Long> oidType = (Type<Long>) LongType.getInstance(); 
    private Type<byte[]> pathType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> pointType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> polygonType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Float> realType = (Type<Float>) FloatType.getInstance(); 
    private Type<byte[]> regClassType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> regOperType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> regOperatorType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> regProcType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> regProcedureType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> regTypeType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<Integer> smallIntType = (Type<Integer>) IntegerType.getInstance(); 
    private Type<String> textType = (Type<String>) StringType.getInstance(); 
    private Type<Date> timeType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timeTzType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timeWotzType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timeWtzType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timestampType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timestampTzType = (Type<Date>) DateType.getInstance(); 
    private Type<Date> timestampWtzType = (Type<Date>) DateType.getInstance(); 
    private Type<byte[]> tsQueryType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> tsVectorType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> txidSnapshotType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> uuidType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<byte[]> varBitType = (Type<byte[]>) BinaryType.getInstance(); 
    private Type<String> varcharType = (Type<String>) StringType.getInstance(); 
    private Type<Array> varcharArrayType = (Type<Array>) ArrayType.getInstance(); 
    private Type<String> xmlType = (Type<String>) StringType.getInstance(); 

    /**
     * Reads one database statement result row and transforms it to instance of {@link Datatypes}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link Datatypes} filled by data from database result
     */
    public Datatypes transform(ResultSet resultSet) throws SQLException {
        Datatypes result = new Datatypes();
        
        result.setBigInt(bigIntType.readValue(resultSet, "BIG_INT"));
        result.setBit(bitType.readValue(resultSet, "BIT_"));
        result.setBitVarying(bitVaryingType.readValue(resultSet, "BIT_VARYING"));
        result.setBool(boolType.readValue(resultSet, "BOOL_"));
        result.setBoolean_(boolean_Type.readValue(resultSet, "BOOLEAN_"));
        result.setBox(boxType.readValue(resultSet, "BOX_"));
        result.setBytea(byteaType.readValue(resultSet, "BYTEA_"));
        result.setCharArray(charArrayType.readValue(resultSet, "CHAR_ARRAY"));
        result.setChar_(char_Type.readValue(resultSet, "CHAR_"));
        result.setCharacter(characterType.readValue(resultSet, "CHARACTER_"));
        result.setCharacterVarying(characterVaryingType.readValue(resultSet, "CHARACTER_VARYING"));
        result.setCid(cidType.readValue(resultSet, "CID_"));
        result.setCidr(cidrType.readValue(resultSet, "CIDR_"));
        result.setCircle(circleType.readValue(resultSet, "CIRCLE_"));
        result.setDate(dateType.readValue(resultSet, "DATE_"));
        result.setDecimal(decimalType.readValue(resultSet, "DECIMAL_"));
        result.setDoublePrecision(doublePrecisionType.readValue(resultSet, "DOUBLE_PRECISION"));
        result.setEnum_(enum_Type.readValue(resultSet, "ENUM_"));
        result.setFloat4(float4Type.readValue(resultSet, "FLOAT_4"));
        result.setFloat8(float8Type.readValue(resultSet, "FLOAT_8"));
        result.setInet(inetType.readValue(resultSet, "INET_"));
        result.setInt2(int2Type.readValue(resultSet, "INT_2"));
        result.setInt4(int4Type.readValue(resultSet, "INT_4"));
        result.setInt8(int8Type.readValue(resultSet, "INT_8"));
        result.setIntArray(intArrayType.readValue(resultSet, "INT_ARRAY"));
        result.setInt_(int_Type.readValue(resultSet, "INT_"));
        result.setInteger(integerType.readValue(resultSet, "INTEGER_"));
        result.setInterval(intervalType.readValue(resultSet, "INTERVAL_"));
        result.setLine(lineType.readValue(resultSet, "LINE_"));
        result.setLseg(lsegType.readValue(resultSet, "LSEG_"));
        result.setMacaddr(macaddrType.readValue(resultSet, "MACADDR_"));
        result.setMoney(moneyType.readValue(resultSet, "MONEY_"));
        result.setNumeric(numericType.readValue(resultSet, "NUMERIC_"));
        result.setOid(oidType.readValue(resultSet, "OID_"));
        result.setPath(pathType.readValue(resultSet, "PATH_"));
        result.setPoint(pointType.readValue(resultSet, "POINT_"));
        result.setPolygon(polygonType.readValue(resultSet, "POLYGON_"));
        result.setReal(realType.readValue(resultSet, "REAL_"));
        result.setRegClass(regClassType.readValue(resultSet, "REG_CLASS"));
        result.setRegOper(regOperType.readValue(resultSet, "REG_OPER"));
        result.setRegOperator(regOperatorType.readValue(resultSet, "REG_OPERATOR"));
        result.setRegProc(regProcType.readValue(resultSet, "REG_PROC"));
        result.setRegProcedure(regProcedureType.readValue(resultSet, "REG_PROCEDURE"));
        result.setRegType(regTypeType.readValue(resultSet, "REG_TYPE"));
        result.setSmallInt(smallIntType.readValue(resultSet, "SMALL_INT"));
        result.setText(textType.readValue(resultSet, "TEXT_"));
        result.setTime(timeType.readValue(resultSet, "TIME_"));
        result.setTimeTz(timeTzType.readValue(resultSet, "TIME_TZ"));
        result.setTimeWotz(timeWotzType.readValue(resultSet, "TIME_WOTZ"));
        result.setTimeWtz(timeWtzType.readValue(resultSet, "TIME_WTZ"));
        result.setTimestamp(timestampType.readValue(resultSet, "TIMESTAMP_"));
        result.setTimestampTz(timestampTzType.readValue(resultSet, "TIMESTAMP_TZ"));
        result.setTimestampWtz(timestampWtzType.readValue(resultSet, "TIMESTAMP_WTZ"));
        result.setTsQuery(tsQueryType.readValue(resultSet, "TS_QUERY"));
        result.setTsVector(tsVectorType.readValue(resultSet, "TS_VECTOR"));
        result.setTxidSnapshot(txidSnapshotType.readValue(resultSet, "TXID_SNAPSHOT_"));
        result.setUuid(uuidType.readValue(resultSet, "UUID_"));
        result.setVarBit(varBitType.readValue(resultSet, "VAR_BIT"));
        result.setVarchar(varcharType.readValue(resultSet, "VARCHAR_"));
        result.setVarcharArray(varcharArrayType.readValue(resultSet, "VARCHAR_ARRAY"));
        result.setXml(xmlType.readValue(resultSet, "XML_"));
        
        return result;
    }
}