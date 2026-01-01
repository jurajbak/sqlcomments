package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.ObjectOrUndefinedType;
import sk.vracon.sqlcomments.core.types.StringType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for statement fullFeatured1.
 */
public class FullFeatured1Config implements StatementConfiguration {

    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("deptno", IntegerType.getInstance()),
                Map.entry("jsVar", ObjectOrUndefinedType.getInstance()),
                Map.entry("orderBy", StringType.getInstance()),
                Map.entry("userName", StringType.getInstance()));

    private Map<String, Object> __sqlParameters;
    private Set<String> __acceptNullParameters;
    private Long limit;
    private Long offset;

    /**
     * Creates new instance of SQLComments configuration class for statement fullFeatured1.
     */
    public FullFeatured1Config() {
    }

    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to DEPT.DEPTNO.
     */
    public void setDeptno(Integer value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("deptno", value);
    }

    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to DEPT.DEPTNO.
     */
    public FullFeatured1Config withDeptno(Integer value) {
        setDeptno(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for deptno placeholder and do not remove rows from statement where this placeholder is used.
     */
    public FullFeatured1Config acceptNullInDeptno() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("deptno");
        
        return this;
    }
    
    /**
     * Setter for placeholder jsVar.
     */
    public void setJsVar(Object value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("jsVar", value);
    }

    /**
     * Setter for placeholder jsVar.
     */
    public FullFeatured1Config withJsVar(Object value) {
        setJsVar(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for jsVar placeholder and do not remove rows from statement where this placeholder is used.
     */
    public FullFeatured1Config acceptNullInJsVar() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("jsVar");
        
        return this;
    }
    
    /**
     * Setter for placeholder orderBy.
     */
    public void setOrderBy(String value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("orderBy", value);
    }

    /**
     * Setter for placeholder orderBy.
     */
    public FullFeatured1Config withOrderBy(String value) {
        setOrderBy(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for orderBy placeholder and do not remove rows from statement where this placeholder is used.
     */
    public FullFeatured1Config acceptNullInOrderBy() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("orderBy");
        
        return this;
    }
    
    /**
     * Setter for placeholder userName.
     */
    public void setUserName(String value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("userName", value);
    }

    /**
     * Setter for placeholder userName.
     */
    public FullFeatured1Config withUserName(String value) {
        setUserName(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for userName placeholder and do not remove rows from statement where this placeholder is used.
     */
    public FullFeatured1Config acceptNullInUserName() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("userName");
        
        return this;
    }
    
    public String statementName() {
        return "fullFeatured1";
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
        builder.append("FullFeatured1Config [statementName=");
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