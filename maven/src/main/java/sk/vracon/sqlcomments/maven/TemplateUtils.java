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

import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
import sk.vracon.sqlcomments.maven.generate.ResultColumnInfo;

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
		// RESULT_SET_METHODS.put(java.util.Date.class.getCanonicalName(), "getDate");
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

	public Class getTypeClass(ResultColumnInfo column) {
		if (column == null || column.getType() == null) {
			return null;
		}

		if (column.getType() instanceof CustomTypeWraper) {
			return ((CustomTypeWraper) column.getType()).getParent().getClass();
		}

		return column.getType().getClass();
	}

	public Class getTypeClass(PlaceholderInfo placeholder) {
		if (placeholder == null || placeholder.getType() == null) {
			return null;
		}

		if (placeholder.getType() instanceof CustomTypeWraper) {
			return ((CustomTypeWraper) placeholder.getType()).getParent().getClass();
		}

		return placeholder.getType().getClass();
	}
	
	public String getTypeInitParamsString(ResultColumnInfo column) {
		if (column == null || column.getType() == null) {
			return "";
		}

		return getTypeInitParamsString(column.getType());
	}
		
	public String getTypeInitParamsString(Type<?> type) {
		if (!(type instanceof CustomTypeWraper)) {
			return "";
		}

		Object[] params = ((CustomTypeWraper<?>) type).getParams();
		if (params == null || params.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			Object param = params[i];

			if (i != 0) {
				sb.append(", ");
			}

			if (param instanceof String) {
				sb.append("\"" + ((String) param).replace("\\", "\\\\").replace("\"", "\\\"") + "\"");
			} else if (param instanceof Double) {
				sb.append(((Double) param).toString() + "d");
			} else if (param instanceof String) {
				sb.append(((Long) param).toString() + "l");
			}
		}

		return sb.toString();
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
