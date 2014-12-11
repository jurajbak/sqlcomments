--
-- @SQLComment(name="delete", baseClass="${statementDeclaration.baseClassName}")
DELETE FROM ${table} WHERE
<#list primaryKeys as key>
	${key} = <#list columns as column><#if column.columnName = key>:${placeholders[column_index].name}</#if></#list> <#if key_has_next>AND</#if>
</#list>
