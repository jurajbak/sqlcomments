package sk.vracon.sqlcomments.maven.domain.MySQL.sqlcomments;

import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.StringType;
import java.util.Date;
import sk.vracon.sqlcomments.core.types.DateType;

import sk.vracon.sqlcomments.core.CRUDType;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table emp.
 */
public class EmpConfig implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of("EMPNO");    

    private static final Set<String> __acceptNullParameters = Set.of("comm", "deptno", "empno", "ename", "hiredate", "job", "mgr", "sal");
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(
                Map.entry("comm", IntegerType.getInstance()),
                Map.entry("deptno", IntegerType.getInstance()),
                Map.entry("empno", IntegerType.getInstance()),
                Map.entry("ename", StringType.getInstance()),
                Map.entry("hiredate", DateType.getInstance()),
                Map.entry("job", StringType.getInstance()),
                Map.entry("mgr", IntegerType.getInstance()),
                Map.entry("sal", IntegerType.getInstance()));
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement Emp.
     *
     * @param operation CRUD operation name
     */
    public EmpConfig(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement Emp.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public EmpConfig(CRUDType operation, sk.vracon.sqlcomments.maven.domain.MySQL.Emp domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        __sqlParameters.put("comm", domain.getComm());
        __sqlParameters.put("deptno", domain.getDeptno());
        __sqlParameters.put("empno", domain.getEmpno());
        __sqlParameters.put("ename", domain.getEname());
        __sqlParameters.put("hiredate", domain.getHiredate());
        __sqlParameters.put("job", domain.getJob());
        __sqlParameters.put("mgr", domain.getMgr());
        __sqlParameters.put("sal", domain.getSal());
    }

    /**
     * Setter for placeholder comm.
     * <p>
     * Placeholder is mapped to emp.COMM.
     */
    public void setComm(Integer value) {
        __sqlParameters.put("comm", value);
    }

    /**
     * Setter for placeholder comm.
     * <p>
     * Placeholder is mapped to emp.COMM.
     */
    public EmpConfig withComm(Integer value) {
        setComm(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to emp.DEPTNO.
     */
    public void setDeptno(Integer value) {
        __sqlParameters.put("deptno", value);
    }

    /**
     * Setter for placeholder deptno.
     * <p>
     * Placeholder is mapped to emp.DEPTNO.
     */
    public EmpConfig withDeptno(Integer value) {
        setDeptno(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to emp.EMPNO.
     */
    public void setEmpno(Integer value) {
        __sqlParameters.put("empno", value);
    }

    /**
     * Setter for placeholder empno.
     * <p>
     * Placeholder is mapped to emp.EMPNO.
     */
    public EmpConfig withEmpno(Integer value) {
        setEmpno(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder ename.
     * <p>
     * Placeholder is mapped to emp.ENAME.
     */
    public void setEname(String value) {
        __sqlParameters.put("ename", value);
    }

    /**
     * Setter for placeholder ename.
     * <p>
     * Placeholder is mapped to emp.ENAME.
     */
    public EmpConfig withEname(String value) {
        setEname(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder hiredate.
     * <p>
     * Placeholder is mapped to emp.HIREDATE.
     */
    public void setHiredate(Date value) {
        __sqlParameters.put("hiredate", value);
    }

    /**
     * Setter for placeholder hiredate.
     * <p>
     * Placeholder is mapped to emp.HIREDATE.
     */
    public EmpConfig withHiredate(Date value) {
        setHiredate(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder job.
     * <p>
     * Placeholder is mapped to emp.JOB.
     */
    public void setJob(String value) {
        __sqlParameters.put("job", value);
    }

    /**
     * Setter for placeholder job.
     * <p>
     * Placeholder is mapped to emp.JOB.
     */
    public EmpConfig withJob(String value) {
        setJob(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder mgr.
     * <p>
     * Placeholder is mapped to emp.MGR.
     */
    public void setMgr(Integer value) {
        __sqlParameters.put("mgr", value);
    }

    /**
     * Setter for placeholder mgr.
     * <p>
     * Placeholder is mapped to emp.MGR.
     */
    public EmpConfig withMgr(Integer value) {
        setMgr(value);
        
        return this;
    }
    
    /**
     * Setter for placeholder sal.
     * <p>
     * Placeholder is mapped to emp.SAL.
     */
    public void setSal(Integer value) {
        __sqlParameters.put("sal", value);
    }

    /**
     * Setter for placeholder sal.
     * <p>
     * Placeholder is mapped to emp.SAL.
     */
    public EmpConfig withSal(Integer value) {
        setSal(value);
        
        return this;
    }
    
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return sk.vracon.sqlcomments.maven.domain.MySQL.Emp.class;
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
        builder.append("EmpConfig [statementName=");
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