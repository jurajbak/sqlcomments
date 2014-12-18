package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class SimpleDeleteConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;


	public SimpleDeleteConfig() {
	}

	public void setCompanyId(Integer value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("companyId", value);
	}
	
	public void acceptNullInCompanyId() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("companyId");
	}
	
	public String statementName() {
		return "simpleDelete";
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