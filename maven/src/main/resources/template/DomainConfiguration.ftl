<#if packageName?has_content>
package ${packageName};
</#if>

<#assign classImports = []>
<#list placeholders as placeholder>
    <#if !placeholder.type.javaClass.name?starts_with("java.lang.") && !placeholder.type.javaClass.name?contains("[") && !classImports?seq_contains(placeholder.type.javaClass.name)>
        <#assign classImports = classImports + [placeholder.type.javaClass.name]>
import ${placeholder.type.javaClass.name};
    </#if>
    <#assign typeClassName = templateUtils.getTypeClass(placeholder).name>
	<#if !typeClassName?starts_with("java.lang.") && !typeClassName?contains("[") && !classImports?seq_contains(typeClassName)>
		<#assign classImports = classImports + [typeClassName]>
import ${typeClassName};
    </#if>
<#if placeholder.collection && !classImports?seq_contains('java.util.Collection')>
    <#assign classImports = classImports + ['java.util.Collection']>
import java.util.Collection;
</#if>
</#list>

import sk.vracon.sqlcomments.core.CRUDType;
<#if placeholders?has_content>
import java.util.HashMap;
</#if>
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * SQLComments configuration class for CRUD operations over table <#if tableColumns?has_content>${tableColumns[0].tableName}</#if>.
 */
public class ${simpleClassName} implements StatementConfiguration {

    private static final Set<String> __primaryKey = Set.of(<#list primaryKeys as key>"${key}"<#sep>, </#list>);    

    private static final Set<String> __acceptNullParameters = Set.of(<#list placeholders as placeholder>"${placeholder.name}"<#sep>, </#list>);
    
    private static final Map<String, Type<?>> __parameterTypes = Map.ofEntries(<#list placeholders as placeholder>
                Map.entry("${placeholder.name}", ${templateUtils.getTypeClass(placeholder).simpleName}.getInstance(${templateUtils.getTypeInitParamsString(placeholder.type)}))<#sep>,</#list>);
    
    private Map<String, Object> __sqlParameters = new HashMap<String, Object>();
    
    private Long limit;
    private Long offset;

    private final String statementName;

    /**
     * Creates new instance of SQLComments configuration class for statement ${statementDeclaration.name}.
     *
     * @param operation CRUD operation name
     */
    public ${simpleClassName}(CRUDType operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Attribute operation must be set.");
        }
    
        statementName = operation.name().toLowerCase();
    }

    /**
     * Creates new instance of SQLComments configuration class for statement ${statementDeclaration.name}.
     *
     * @param operation CRUD operation name
     * @param domain domain instance from which will be filled statement configuration
     */
    public ${simpleClassName}(CRUDType operation, ${statementDeclaration.baseClassName} domain) {
        this(operation);

        if (domain == null) {
            throw new IllegalArgumentException("Attribute domain must be set.");
        }
        
        <#list placeholders as placeholder>
        __sqlParameters.put("${placeholder.name}", domain.get${placeholder.name[0]?upper_case}${placeholder.name[1..]}());
        </#list>
    }

<#list placeholders as placeholder>
    /**
     * Setter for placeholder ${placeholder.name}.
     <#if placeholder.dbColumns?has_content>
     <#list placeholder.dbColumns>
     * <p>
     * Placeholder is mapped to <#items as dbColumn>${dbColumn.tableName!}.${dbColumn.columnName}<#sep>, </#sep></#items>.
     </#list>
     </#if>
     */
    public void set${placeholder.name[0]?upper_case}${placeholder.name[1..]}(<#if placeholder.collection>Collection<${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}><#else>${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}</#if> value) {
        __sqlParameters.put("${placeholder.name}", value);
    }

    /**
     * Setter for placeholder ${placeholder.name}.
     <#if placeholder.dbColumns?has_content>
     <#list placeholder.dbColumns>
     * <p>
     * Placeholder is mapped to <#items as dbColumn>${dbColumn.tableName!}.${dbColumn.columnName}<#sep>, </#sep></#items>.
     </#list>
     </#if>
     */
    public ${simpleClassName} with${placeholder.name[0]?upper_case}${placeholder.name[1..]}(<#if placeholder.collection>Collection<${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}><#else>${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}</#if> value) {
        set${placeholder.name[0]?upper_case}${placeholder.name[1..]}(value);
        
        return this;
    }
    
</#list>
    public String statementName() {
        return statementName;
    }
    
    public Class<?> baseClass() {
        return <#if statementDeclaration.baseClassName?has_content>${statementDeclaration.baseClassName}.class<#else>null</#if>;
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
        builder.append("${simpleClassName} [statementName=");
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