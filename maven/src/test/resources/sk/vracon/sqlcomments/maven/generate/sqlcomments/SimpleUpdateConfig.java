package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class SimpleUpdateConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;


	public SimpleUpdateConfig() {
	}

	public void setCountry(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("country", value);
	}
	
	public void acceptNullInCountry() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("country");
	}
	
	public String statementName() {
		return "simpleUpdate";
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