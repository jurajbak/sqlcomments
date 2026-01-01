package sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.StringType;

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table Managers.
 */
public class ManagersConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of();    

    private static final Set<String> __acceptNullParameters = Set.of("employee", "manager");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("employee", StringType.getInstance()),
                Map.entry("manager", StringType.getInstance()));
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement Managers.
     *
     * @param operation CRUD operation name
     */
    public ManagersConfig(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement Managers.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public ManagersConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.PostgreSQL.Managers domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("employee", domain.getEmployee());
        __sqlParameters.put("manager", domain.getManager());
    }

    /**
     * Setter for placeholder employee.
     * <p>
     * Placeholder is mapped to Managers.Employee.
     */
    public void setEmployee(String value) {
        __sqlParameters.put("employee", value);
    }

    /**
     * Setter for placeholder employee.
     * <p>
     * Placeholder is mapped to Managers.Employee.
     */
    public ManagersConfig withEmployee(String value) {
        setEmployee(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder manager.
     * <p>
     * Placeholder is mapped to Managers.Manager.
     */
    public void setManager(String value) {
        __sqlParameters.put("manager", value);
    }

    /**
     * Setter for placeholder manager.
     * <p>
     * Placeholder is mapped to Managers.Manager.
     */
    public ManagersConfig withManager(String value) {
        setManager(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.PostgreSQL.Managers.class;
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
        builder.append("ManagersConfig [statementName=");
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