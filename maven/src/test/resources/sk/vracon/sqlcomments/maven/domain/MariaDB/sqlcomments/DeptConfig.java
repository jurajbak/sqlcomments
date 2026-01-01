package sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.maven.DepartmenTypesEnum;
import sk.vracon.sqlcomments.core.types.EnumType;
import sk.vracon.sqlcomments.core.types.StringType;

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table dept.
 */
public class DeptConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of("DEPTNO");    

    private static final Set<String> __acceptNullParameters = Set.of("deptno", "dname", "loc");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("deptno", IntegerType.getInstance()),
                Map.entry("dname", EnumType.getInstance("sk.vracon.sqlcomments.maven.DepartmenTypesEnum")),
                Map.entry("loc", StringType.getInstance()));
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement Dept.
     *
     * @param operation CRUD operation name
     */
    public DeptConfig(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement Dept.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public DeptConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.MariaDB.Dept domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("deptno", domain.getDeptno());
        __sqlParameters.put("dname", domain.getDname());
        __sqlParameters.put("loc", domain.getLoc());
    }

    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to dept.DEPTNO.
     */
    public void setDeptno(Integer value) {
        __sqlParameters.put("deptno", value);
    }

    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to dept.DEPTNO.
     */
    public DeptConfig withDeptno(Integer value) {
        setDeptno(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder dname.
     * <p>
     * Placeholder is mapped to dept.DNAME.
     */
    public void setDname(DepartmenTypesEnum value) {
        __sqlParameters.put("dname", value);
    }

    /**
     * Setter for placeholder dname.
     * <p>
     * Placeholder is mapped to dept.DNAME.
     */
    public DeptConfig withDname(DepartmenTypesEnum value) {
        setDname(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder loc.
     * <p>
     * Placeholder is mapped to dept.LOC.
     */
    public void setLoc(String value) {
        __sqlParameters.put("loc", value);
    }

    /**
     * Setter for placeholder loc.
     * <p>
     * Placeholder is mapped to dept.LOC.
     */
    public DeptConfig withLoc(String value) {
        setLoc(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.MariaDB.Dept.class;
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
        builder.append("DeptConfig [statementName=");
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