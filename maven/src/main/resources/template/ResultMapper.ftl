<#if packageName?has_content>
package ${packageName}.sqlcomments;
</#if>

<#assign classImports = []>
<#list selectContext.columns as column>
<#if !column.javaClass.name?starts_with("java.lang.") && !classImports?seq_contains(column.javaClass.name)>
	<#assign classImports = classImports + [column.javaClass.name]>
import ${column.javaClass.name};
</#if>
</#list>

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import ${packageName}.${simpleClassName};

public class ${simpleClassName}Mapper implements ResultMapper<${simpleClassName}> {

	public ${simpleClassName} transform(ResultSet resultSet) throws SQLException {
		${simpleClassName} result = new ${simpleClassName}();
		
	<#list selectContext.columns as column>
		result.set${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}((${column.javaClass.simpleName}) resultSet.getObject("${column.columnName}"));
	</#list>
		
		return result;
	}
}