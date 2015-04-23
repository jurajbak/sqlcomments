package sk.vracon.sqlcomments.maven;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Map;

public class TemplateUtils {

    private static final Map<String, String> RESULT_SET_METHODS = new Hashtable<String, String>();

    static {
        RESULT_SET_METHODS.put(Array.class.getCanonicalName(), "getArray");
        RESULT_SET_METHODS.put(BigDecimal.class.getCanonicalName(), "getBigDecimal");
        RESULT_SET_METHODS.put(InputStream.class.getCanonicalName(), "getBinaryStream");
        RESULT_SET_METHODS.put(Blob.class.getCanonicalName(), "getBlob");
        RESULT_SET_METHODS.put(Boolean.class.getCanonicalName(), "getBoolean");
        RESULT_SET_METHODS.put(Byte.class.getCanonicalName(), "getByte");
        RESULT_SET_METHODS.put(byte[].class.getCanonicalName(), "getBytes");
        RESULT_SET_METHODS.put("[B", "getBytes");
        RESULT_SET_METHODS.put(Reader.class.getCanonicalName(), "getCharacterStream");
        RESULT_SET_METHODS.put(Clob.class.getCanonicalName(), "getClob");
        RESULT_SET_METHODS.put(java.sql.Date.class.getCanonicalName(), "getDate");
        //RESULT_SET_METHODS.put(java.util.Date.class.getCanonicalName(), "getDate");
        RESULT_SET_METHODS.put(Double.class.getCanonicalName(), "getDouble");
        RESULT_SET_METHODS.put(Float.class.getCanonicalName(), "getFloat");
        RESULT_SET_METHODS.put(Integer.class.getCanonicalName(), "getInt");
        RESULT_SET_METHODS.put(Long.class.getCanonicalName(), "getLong");
        RESULT_SET_METHODS.put(Short.class.getCanonicalName(), "getShort");
        RESULT_SET_METHODS.put(String.class.getCanonicalName(), "getString");
        RESULT_SET_METHODS.put(Time.class.getCanonicalName(), "getTime");
        RESULT_SET_METHODS.put(Timestamp.class.getCanonicalName(), "getTimestamp");
        RESULT_SET_METHODS.put(URL.class.getCanonicalName(), "getURL");
    }

    public String getSimpleClassName(String className) {
        if (className == null) {
            return null;
        }

        int lastDot = className.lastIndexOf('.');

        if (lastDot < 0) {
            return className;
        } else {
            return className.substring(lastDot + 1);
        }
    }

    public String getResultSetValueGetterName(String className) {
        String method = RESULT_SET_METHODS.get(className);
        if (method != null) {
            return method;
        }

        return "getObject";
    }
}
