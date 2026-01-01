package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for statement simpleInsert.
 */
public class SimpleInsertConfig implements StatementConfiguration {

    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("employeeNo", IntegerType.getInstance()),
                Map.entry("endDate", DateType.getInstance()),
                Map.entry("projectId", IntegerType.getInstance()),
                Map.entry("startDate", DateType.getInstance()));

    private Map<String, Object> __sqlParameters;
    private Set<String> __acceptNullParameters;
    private Long limit;
    private Long offset;

    /**
     * Creates new instance of SQLComments configuration class for statement simpleInsert.
     */
    public SimpleInsertConfig() {
    }

    /**
     * Setter for placeholder employeeNo.
     * <p>
     * Placeholder is mapped to PROJ.EMPNO.
     */
    public void setEmployeeNo(Integer value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("employeeNo", value);
    }

    /**
     * Setter for placeholder employeeNo.
     * <p>
     * Placeholder is mapped to PROJ.EMPNO.
     */
    public SimpleInsertConfig withEmployeeNo(Integer value) {
        setEmployeeNo(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for employeeNo placeholder and do not remove rows from statement where this placeholder is used.
     */
    public SimpleInsertConfig acceptNullInEmployeeNo() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("employeeNo");
        
        return this;
    }
    
    /**
     * Setter for placeholder endDate.
     * <p>
     * Placeholder is mapped to PROJ.ENDDATE.
     */
    public void setEndDate(Date value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("endDate", value);
    }

    /**
     * Setter for placeholder endDate.
     * <p>
     * Placeholder is mapped to PROJ.ENDDATE.
     */
    public SimpleInsertConfig withEndDate(Date value) {
        setEndDate(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for endDate placeholder and do not remove rows from statement where this placeholder is used.
     */
    public SimpleInsertConfig acceptNullInEndDate() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("endDate");
        
        return this;
    }
    
    /**
     * Setter for placeholder projectId.
     * <p>
     * Placeholder is mapped to PROJ.PROJID.
     */
    public void setProjectId(Integer value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("projectId", value);
    }

    /**
     * Setter for placeholder projectId.
     * <p>
     * Placeholder is mapped to PROJ.PROJID.
     */
    public SimpleInsertConfig withProjectId(Integer value) {
        setProjectId(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for projectId placeholder and do not remove rows from statement where this placeholder is used.
     */
    public SimpleInsertConfig acceptNullInProjectId() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("projectId");
        
        return this;
    }
    
    /**
     * Setter for placeholder startDate.
     * <p>
     * Placeholder is mapped to PROJ.STARTDATE.
     */
    public void setStartDate(Date value) {
        if(__sqlParameters == null) {
            __sqlParameters = new HashMap<String, Object>();
        }
        
        __sqlParameters.put("startDate", value);
    }

    /**
     * Setter for placeholder startDate.
     * <p>
     * Placeholder is mapped to PROJ.STARTDATE.
     */
    public SimpleInsertConfig withStartDate(Date value) {
        setStartDate(value);
        
        return this;
    }
    
    /**
     * Accept <code>null</code> value for startDate placeholder and do not remove rows from statement where this placeholder is used.
     */
    public SimpleInsertConfig acceptNullInStartDate() {
        if(__acceptNullParameters == null) {
            __acceptNullParameters = new HashSet<String>();
        }
        
        __acceptNullParameters.add("startDate");
        
        return this;
    }
    
    public String statementName() {
        return "simpleInsert";
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
        builder.append("SimpleInsertConfig [statementName=");
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