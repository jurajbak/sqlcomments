package sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments;

import java.math.BigDecimal;
import sk.vracon.sqlcomments.core.types.NumericType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for statement findByPK.
 */
public class EmpPKConfig implements StatementConfiguration {

    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("empno", NumericType.getInstance()));

    private Map<String, Object> __sqlParameters;
    private Set<String> __acceptNullParameters;
    private Long limit;
    private Long offset;

    /**
     * Creates new instance of SQLComments configuration class for statement findByPK.
     */
    public EmpPKConfig() {
    }

    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to emp.EMPNO.
     */
    public void setEmpno(BigDecimal value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("empno", value);
    }

    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to emp.EMPNO.
     */
    public EmpPKConfig withEmpno(BigDecimal value) {
        setEmpno(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for empno placeholder and do not remove rows from statement where this placeholder is used.
     */
    public EmpPKConfig acceptNullInEmpno() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("empno");
        
        return this;
    }
    
    public String statementName() {
        return "findByPK";
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.Oracle.Emp.class;
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
        builder.append("EmpPKConfig [statementName=");
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