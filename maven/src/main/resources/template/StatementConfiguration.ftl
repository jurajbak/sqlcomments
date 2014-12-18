<#if packageName?has_content>
package ${packageName};
</#if>

<#assign classImports = []>
<#list placeholders as placeholder>
<#if placeholder.mappedClass?has_content>
	<#if !placeholder.mappedClass?starts_with("java.lang.") && !classImports?seq_contains(placeholder.mappedClass)>
		<#assign classImports = classImports + [placeholder.mappedClass]>
import ${placeholder.mappedClass};
	</#if>
<#else>
	<#if !placeholder.javaClass.name?starts_with("java.lang.") && !classImports?seq_contains(placeholder.javaClass.name)>
		<#assign classImports = classImports + [placeholder.javaClass.name]>
import ${placeholder.javaClass.name};
	</#if>
</#if>
<#if placeholder.collection && !classImports?seq_contains('java.util.Collection')>
	<#assign classImports = classImports + ['java.util.Collection']>
import java.util.Collection;
</#if>
</#list>

<#if placeholders?has_content>
import java.util.HashMap;
import java.util.HashSet;
</#if>
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class ${simpleClassName} implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = <#if statementDeclaration.baseClassName?has_content>${statementDeclaration.baseClassName}.class<#else>null</#if>;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;

<#list placeholders as placeholder>
	<#if placeholder.mapperClass?has_content>
	private ${placeholder.mapperClass} ${placeholder.name}ColumnMapper = new ${placeholder.mapperClass}(); 
	</#if> 
</#list>

	public ${simpleClassName}() {
	<#list placeholders as placeholder>
		<#if placeholder.mapperClass?has_content>
		${placeholder.name}ColumnMapper.setJavaType(${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}.class); 
		</#if> 
	</#list>
	}

<#list placeholders as placeholder>
	public void set${placeholder.name[0]?upper_case}${placeholder.name[1..]}(<#if placeholder.collection>Collection<${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}><#else>${templateUtils.getSimpleClassName(placeholder.mappedClass)! placeholder.javaClass.simpleName}</#if> value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		<#if placeholder.mapperClass?has_content>
		__sqlParameters.put("${placeholder.name}", ${placeholder.name}ColumnMapper.convertToDatabase(value));
		<#else> 
		__sqlParameters.put("${placeholder.name}", value);
		</#if> 
	}
	
</#list>
<#list placeholders as placeholder>
	public void acceptNullIn${placeholder.name[0]?upper_case}${placeholder.name[1..]}() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("${placeholder.name}");
	}
	
</#list>
	public String statementName() {
		return "${statementDeclaration.name}";
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
}