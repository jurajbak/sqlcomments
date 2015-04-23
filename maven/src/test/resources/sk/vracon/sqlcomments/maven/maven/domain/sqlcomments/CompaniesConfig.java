package sk.vracon.sqlcomments.maven.domain.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class CompaniesConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = sk.vracon.sqlcomments.maven.domain.Companies.class;
	
	public static final DBColumnMetadata COLUMN_CITY = new DBColumnMetadata("COMPANIES", "CITY", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_COUNTRY = new DBColumnMetadata("COMPANIES", "COUNTRY", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_EMAIL = new DBColumnMetadata("COMPANIES", "EMAIL", 12, "VARCHAR", 50, 0, true);
	
	public static final DBColumnMetadata COLUMN_ID = new DBColumnMetadata("COMPANIES", "ID", 4, "INTEGER", 32, 0, false);
	
	public static final DBColumnMetadata COLUMN_IPADDRESS = new DBColumnMetadata("COMPANIES", "IP_ADDRESS", 12, "VARCHAR", 20, 0, true);
	
	public static final DBColumnMetadata COLUMN_NAME = new DBColumnMetadata("COMPANIES", "NAME", 12, "VARCHAR", 50, 0, true);
	
	public static final String[] PRIMARY_KEY = new String[] {"ID"};	

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;
	
	private Long limit;
    private Long offset;

	private String statementName;
	

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 
	
	public CompaniesConfig(String operationName) {
		statementName = operationName;

		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public CompaniesConfig(String operationName, sk.vracon.sqlcomments.maven.domain.Companies domain) {
		this(operationName);
		
		__sqlParameters = new HashMap<String, Object>();
		__sqlParameters.put("city", domain.getCity());
		__sqlParameters.put("country", countryColumnMapper.convertToDatabase(domain.getCountry()));
		__sqlParameters.put("email", domain.getEmail());
		__sqlParameters.put("id", domain.getId());
		__sqlParameters.put("ipAddress", domain.getIpAddress());
		__sqlParameters.put("name", domain.getName());
	}

	public void setCity(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("city", value);
	}
	
	public void setCountry(ExampleEnum value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("country", countryColumnMapper.convertToDatabase(value));
	}
	
	public void setEmail(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("email", value);
	}
	
	public void setId(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("id", value);
	}
	
	public void setIpAddress(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("ipAddress", value);
	}
	
	public void setName(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("name", value);
	}
	
	public void acceptNullInCity() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("city");
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
	
	public void acceptNullInId() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("id");
	}
	
	public void acceptNullInIpAddress() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("ipAddress");
	}
	
	public void acceptNullInName() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("name");
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