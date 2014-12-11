--
-- @SQLComment(name="update", baseClass="${statementDeclaration.baseClassName}")
UPDATE ${table} SET
<#list columns as column>
	<#if !primaryKeys?seq_contains(column.columnName)>
	${column.columnName} = :${placeholders[column_index].name}<#if column_has_next>,</#if>
	</#if>
</#list>
WHERE
<#list primaryKeys as key>
	${key} = <#list columns as column><#if column.columnName = key>:${placeholders[column_index].name}</#if></#list> <#if key_has_next>AND</#if>
</#list>
