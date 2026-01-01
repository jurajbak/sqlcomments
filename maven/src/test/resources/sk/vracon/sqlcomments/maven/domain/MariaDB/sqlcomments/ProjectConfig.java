package sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table proj.
 */
public class ProjectConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of("PROJID");    

    private static final Set<String> __acceptNullParameters = Set.of("empno", "enddate", "projid", "startdate");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("empno", IntegerType.getInstance()),
                Map.entry("enddate", DateType.getInstance()),
                Map.entry("projid", IntegerType.getInstance()),
                Map.entry("startdate", DateType.getInstance()));
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement Project.
     *
     * @param operation CRUD operation name
     */
    public ProjectConfig(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement Project.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public ProjectConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.MariaDB.Project domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("empno", domain.getEmpno());
        __sqlParameters.put("enddate", domain.getEnddate());
        __sqlParameters.put("projid", domain.getProjid());
        __sqlParameters.put("startdate", domain.getStartdate());
    }

    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to proj.EMPNO.
     */
    public void setEmpno(Integer value) {
        __sqlParameters.put("empno", value);
    }

    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to proj.EMPNO.
     */
    public ProjectConfig withEmpno(Integer value) {
        setEmpno(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder enddate.
     * <p>
     * Placeholder is mapped to proj.ENDDATE.
     */
    public void setEnddate(Date value) {
        __sqlParameters.put("enddate", value);
    }

    /**
     * Setter for placeholder enddate.
     * <p>
     * Placeholder is mapped to proj.ENDDATE.
     */
    public ProjectConfig withEnddate(Date value) {
        setEnddate(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder projid.
     * <p>
     * Placeholder is mapped to proj.PROJID.
     */
    public void setProjid(Integer value) {
        __sqlParameters.put("projid", value);
    }

    /**
     * Setter for placeholder projid.
     * <p>
     * Placeholder is mapped to proj.PROJID.
     */
    public ProjectConfig withProjid(Integer value) {
        setProjid(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder startdate.
     * <p>
     * Placeholder is mapped to proj.STARTDATE.
     */
    public void setStartdate(Date value) {
        __sqlParameters.put("startdate", value);
    }

    /**
     * Setter for placeholder startdate.
     * <p>
     * Placeholder is mapped to proj.STARTDATE.
     */
    public ProjectConfig withStartdate(Date value) {
        setStartdate(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.MariaDB.Project.class;
    }

    public Map<String, Object> parameterMap() {
        return __sqlParameters;
    }
    
    public Map<String, Type<?>> typeMap() {
        return __parameterTypes;
    }
    
    public Set<String> parametersAcceptingNull() {
        return __acceptNullParameters;
    }
    
    public Set<String> primaryKey() {
    	return __primaryKey;
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
        builder.append("ProjectConfig [statementName=");
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