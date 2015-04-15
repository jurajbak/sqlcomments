package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class SimpleAsterixConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;
	
	private Long limit;
    private Long offset;


	public SimpleAsterixConfig() {
	}

	public String statementName() {
		return "simpleAsterix";
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