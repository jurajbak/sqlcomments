--
-- @SQLComment(name="findByPK", baseClass="${statementDeclaration.baseClassName}", resultClass="${statementDeclaration.baseClassName}", configClass="${statementDeclaration.configurationClassName}")
SELECT * FROM ${table} WHERE
<#list primaryKeys as key>
	${key} = <#list columns as column><#if column.columnName = key>:${placeholders[column_index].name}</#if></#list> <#sep>AND
</#list>
