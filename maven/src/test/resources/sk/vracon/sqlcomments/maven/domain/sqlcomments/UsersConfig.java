package sk.vracon.sqlcomments.maven.domain.sqlcomments;


import sk.vracon.sqlcomments.core.DBColumnMetadata;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class UsersConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = sk.vracon.sqlcomments.maven.domain.Users.class;
	
	public static final DBColumnMetadata COLUMN_COMPANYID = new DBColumnMetadata("USERS", "COMPANYID", 4, "INTEGER", 32, 0, true);
	
	public static final DBColumnMetadata COLUMN_COUNTRY = new DBColumnMetadata("USERS", "COUNTRY", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_EMAIL = new DBColumnMetadata("USERS", "EMAIL", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_FIRSTNAME = new DBColumnMetadata("USERS", "FIRST_NAME", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_ID = new DBColumnMetadata("USERS", "ID", 4, "INTEGER", 32, 0, false);
	
	public static final DBColumnMetadata COLUMN_LASTNAME = new DBColumnMetadata("USERS", "LAST_NAME", 12, "VARCHAR", 50, 0, true);
	
	public static final String[] PRIMARY_KEY = new String[] {"ID"};	

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;
	
	private Long limit;
    private Long offset;

	private String statementName;

	
	public UsersConfig(String operationName) {
		statementName = operationName;

	}

	public UsersConfig(String operationName, sk.vracon.sqlcomments.maven.domain.Users domain) {
		this(operationName);
		
		__sqlParameters = new HashMap<String, Object>();
		__sqlParameters.put("companyid", domain.getCompanyid());
		__sqlParameters.put("country", domain.getCountry());
		__sqlParameters.put("email", domain.getEmail());
		__sqlParameters.put("firstName", domain.getFirstName());
		__sqlParameters.put("id", domain.getId());
		__sqlParameters.put("lastName", domain.getLastName());
	}

	public void setCompanyid(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("companyid", value);
	}
	
	public void setCountry(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("country", value);
	}
	
	public void setEmail(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("email", value);
	}
	
	public void setFirstName(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("firstName", value);
	}
	
	public void setId(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("id", value);
	}
	
	public void setLastName(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("lastName", value);
	}
	
	public void acceptNullInCompanyid() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("companyid");
	}
	
	public void acceptNullInCountry() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("country");
	}
	
	public void acceptNullInEmail() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("email");
	}
	
	public void acceptNullInFirstName() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("firstName");
	}
	
	public void acceptNullInId() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("id");
	}
	
	public void acceptNullInLastName() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("lastName");
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