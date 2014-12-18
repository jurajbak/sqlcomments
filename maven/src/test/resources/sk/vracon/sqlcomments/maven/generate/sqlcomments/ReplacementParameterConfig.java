package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class ReplacementParameterConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;


	public ReplacementParameterConfig() {
	}

	public void setReplacementParam(String value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		__sqlParameters.put("replacementParam", value);
	}
	
	public void acceptNullInReplacementParam() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("replacementParam");
	}
	
	public String statementName() {
		return "replacementParameter";
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