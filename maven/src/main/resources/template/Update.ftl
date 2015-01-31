--
-- @SQLComment(name="update", baseClass="${statementDeclaration.baseClassName}")
UPDATE ${table} SET
<#assign columnsToUpdate = []>
<#assign placeholdersToUpdate = []>
<#list columns as column>
	<#if !primaryKeys?seq_contains(column.columnName)>
		<#assign columnsToUpdate = columnsToUpdate + [column]>
		<#assign placeholdersToUpdate = placeholdersToUpdate + [placeholders[column_index]]>
	</#if>
</#list>
<#list columnsToUpdate as column>
	${column.columnName} = :${placeholdersToUpdate[column_index].name}<#if column_has_next>,</#if>
</#list>
WHERE
<#list primaryKeys as key>
	${key} = <#list columns as column><#if column.columnName = key>:${placeholders[column_index].name}</#if></#list> <#if key_has_next>AND</#if>
</#list>
