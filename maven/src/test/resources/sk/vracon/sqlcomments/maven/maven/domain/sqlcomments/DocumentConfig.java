package sk.vracon.sqlcomments.maven.domain.sqlcomments;

import java.sql.Blob;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class DocumentConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = sk.vracon.sqlcomments.maven.domain.Document.class;
	
	public static final DBColumnMetadata COLUMN_DATA = new DBColumnMetadata("DOCUMENTS", "DATA", 2004, "BLOB", 1073741824, 0, true);
	
	public static final DBColumnMetadata COLUMN_DESCRIPTION = new DBColumnMetadata("DOCUMENTS", "DESCRIPTION", 12, "VARCHAR", 500, 0, true);
	
	public static final DBColumnMetadata COLUMN_ID = new DBColumnMetadata("DOCUMENTS", "ID", 4, "INTEGER", 32, 0, false);
	
	public static final DBColumnMetadata COLUMN_NAME = new DBColumnMetadata("DOCUMENTS", "NAME", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_USERID = new DBColumnMetadata("DOCUMENTS", "USERID", 4, "INTEGER", 32, 0, true);
	
	public static final String[] PRIMARY_KEY = new String[] {"ID"};	

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;
	
	private Long limit;
    private Long offset;

	private String statementName;
	

	
	public DocumentConfig(String operationName) {
		statementName = operationName;

	}

	public DocumentConfig(String operationName, sk.vracon.sqlcomments.maven.domain.Document domain) {
		this(operationName);
		
		__sqlParameters = new HashMap<String, Object>();
		__sqlParameters.put("data", domain.getData());
		__sqlParameters.put("description", domain.getDescription());
		__sqlParameters.put("id", domain.getId());
		__sqlParameters.put("name", domain.getName());
		__sqlParameters.put("userid", domain.getUserid());
	}

	public void setData(Blob value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("data", value);
	}
	
	public void setDescription(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("description", value);
	}
	
	public void setId(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("id", value);
	}
	
	public void setName(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("name", value);
	}
	
	public void setUserid(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("userid", value);
	}
	
	public void acceptNullInData() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("data");
	}
	
	public void acceptNullInDescription() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("description");
	}
	
	public void acceptNullInId() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("id");
	}
	
	public void acceptNullInName() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("name");
	}
	
	public void acceptNullInUserid() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("userid");
	}
	
	public String statementName() {
		return statementName;
	}
	
	public Class<?> baseClass() {
		return BASE_CLASS;
	}

	public Map<String, Object> generateParameterMap() {
		return __sqlParameters;
	}
	
	public Set<String> generateParametersAcceptingNull() {
		return __acceptNullParameters;
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
}