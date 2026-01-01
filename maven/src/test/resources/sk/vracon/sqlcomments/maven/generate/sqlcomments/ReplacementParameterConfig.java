package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.StringType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for statement replacementParameter.
 */
public class ReplacementParameterConfig implements StatementConfiguration {

    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("replacementParam", StringType.getInstance()));

    private Map<String, Object> __sqlParameters;
    private Set<String> __acceptNullParameters;
    private Long limit;
    private Long offset;

    /**
     * Creates new instance of SQLComments configuration class for statement replacementParameter.
     */
    public ReplacementParameterConfig() {
    }

    /**
     * Setter for placeholder replacementParam.
     */
    public void setReplacementParam(String value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("replacementParam", value);
    }

    /**
     * Setter for placeholder replacementParam.
     */
    public ReplacementParameterConfig withReplacementParam(String value) {
        setReplacementParam(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for replacementParam placeholder and do not remove rows from statement where this placeholder is used.
     */
    public ReplacementParameterConfig acceptNullInReplacementParam() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("replacementParam");
        
        return this;
    }
    
    public String statementName() {
        return "replacementParameter";
    }
    
    public Class<?> baseClass() {
        return null;
    }
    
    public Map<String, Type<?>> typeMap() {
        return __parameterTypes;
    }

    public Map<String, Object> parameterMap() {
        return __sqlParameters;
    }
    
    public Set<String> parametersAcceptingNull() {
        return __acceptNullParameters;
    }
    
    public Set<String> primaryKey() {
        return null;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReplacementParameterConfig [statementName=");
        builder.append(statementName());
        builder.append(", baseClass=");
        builder.append(baseClass());
        builder.append(", sqlParameters=");
        builder.append(parameterMap());
        builder.append(", acceptNullParameters=");
        builder.append(parametersAcceptingNull());
        builder.append(", limit=");
        builder.append(limit());
        builder.append(", offset=");
        builder.append(offset());
        builder.append("]");
        return builder.toString();
    }
}