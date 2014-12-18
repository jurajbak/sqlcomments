package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class BlobMappingsConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;


	public BlobMappingsConfig() {
	}

	public String statementName() {
		return "blobMappings";
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