--
-- @SQLComment(name="insert", baseClass="${statementDeclaration.baseClassName}")
<@compress single_line=true>
INSERT INTO ${table} (
<#list columns as column>
	${column.columnName}<#if column_has_next>,</#if>
</#list>
)
</@compress>

<@compress single_line=true>
 VALUES (
<#list columns as column>
	<#if primaryKeys?seq_contains(column.columnName) && tableProperties['pkGenerator']?has_content>
	${tableProperties['pkGenerator']}
	<#else>
	:${placeholders[column_index].name}
	</#if>
	<#if column_has_next>,</#if>
</#list>
)
</@compress>
