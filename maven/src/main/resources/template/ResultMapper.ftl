package <#if packageName?has_content>${packageName}.</#if>sqlcomments;

<#assign classImports = []>
<#list selectContext.columns as column>
<#if column.mappedClass?has_content>
	<#if !column.mappedClass?starts_with("java.lang.") && !column.mappedClass?contains("[") && !classImports?seq_contains(column.mappedClass)>
		<#assign classImports = classImports + [column.mappedClass]>
import ${column.mappedClass};
	</#if>
<#else>
	<#if !column.javaClass.name?starts_with("java.lang.") && !column.javaClass.name?contains("[") && !classImports?seq_contains(column.javaClass.name)>
		<#assign classImports = classImports + [column.javaClass.name]>
import ${column.javaClass.name};
	</#if>
</#if>
</#list>

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import <#if packageName?has_content>${packageName}.</#if>${simpleClassName};

public class ${simpleClassName}Mapper implements ResultMapper<${simpleClassName}> {

	public static final ${simpleClassName}Mapper INSTANCE = new ${simpleClassName}Mapper();

<#list selectContext.columns as column>
	<#if column.mapperClass?has_content>
	private ${column.mapperClass} ${column.javaIdentifier}ColumnMapper = new ${column.mapperClass}(); 
	</#if> 
</#list>

	public ${simpleClassName}Mapper() {
	<#list selectContext.columns as column>
		<#if column.mapperClass?has_content>
		${column.javaIdentifier}ColumnMapper.setJavaType(${templateUtils.getSimpleClassName(column.mappedClass)! column.javaClass.simpleName}.class); 
		</#if> 
	</#list>
	}

	public ${simpleClassName} transform(ResultSet resultSet) throws SQLException {
		${simpleClassName} result = new ${simpleClassName}();
		
	<#list selectContext.columns as column>
		<#assign columnSimpleTypeName = (templateUtils.getSimpleClassName(column.mappedClass)! column.javaClass.simpleName)>
		${column.javaClass.simpleName} ${column.javaIdentifier}Value = resultSet.${templateUtils.getResultSetValueGetterName(column.javaClass.name)}("${column.columnName}");
		if(resultSet.wasNull()) {
			${column.javaIdentifier}Value = null;
		}
		<#if column.mapperClass?has_content>
		result.set${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}((${columnSimpleTypeName}) ${column.javaIdentifier}ColumnMapper.convertToJava(${column.javaIdentifier}Value));
		<#else>
		result.set${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}(${column.javaIdentifier}Value);
		</#if>
	</#list>
		
		return result;
	}
}