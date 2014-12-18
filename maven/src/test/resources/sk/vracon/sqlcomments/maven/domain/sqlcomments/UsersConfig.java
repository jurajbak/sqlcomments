package sk.vracon.sqlcomments.maven.domain.sqlcomments;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class UsersConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = sk.vracon.sqlcomments.maven.domain.Users.class;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;
	
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
}