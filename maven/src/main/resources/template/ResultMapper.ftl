package <#if packageName?has_content>${packageName}.</#if>sqlcomments;

<#assign classImports = []>
<#list selectContext.columns as column>
    <#if !column.type.javaClass.name?starts_with("java.lang.") && !column.type.javaClass.name?contains("[") && !classImports?seq_contains(column.type.javaClass.name)>
        <#assign classImports = classImports + [column.type.javaClass.name]>
import ${column.type.javaClass.name};
	</#if>
	<#assign typeClassName = templateUtils.getTypeClass(column).name>
	<#if !typeClassName?starts_with("java.lang.") && !typeClassName?contains("[") && !classImports?seq_contains(typeClassName)>
		<#assign classImports = classImports + [typeClassName]>
import ${typeClassName};
    </#if>
</#list>

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Type;
import <#if packageName?has_content>${packageName}.</#if>${simpleClassName};

/**
 * SQLComments result mapper class for statement ${statementDeclaration.name}.
 * <p>
 * Implementation is thread-safe, use {@link #INSTANCE}.
 */
public class ${simpleClassName}Mapper implements ResultMapper<${simpleClassName}> {

    /**
     * Static instance of ${simpleClassName}Mapper class.
     */
    public static final ${simpleClassName}Mapper INSTANCE = new ${simpleClassName}Mapper();

<#list selectContext.columns as column>
    <#assign typeClassName = templateUtils.getTypeClass(column).simpleName>
    private Type<${column.type.javaClass.simpleName}> ${column.javaIdentifier}Type = (Type<${column.type.javaClass.simpleName}>) ${typeClassName}.getInstance(${templateUtils.getTypeInitParamsString(column)}); 
</#list>

    /**
     * Reads one database statement result row and transforms it to instance of {@link ${simpleClassName}}.
     *
     * @param resultSet select statement {@link ResultSet} pointing to row to be red
     * @return new instance of {@link ${simpleClassName}} filled by data from database result
     */
    public ${simpleClassName} transform(ResultSet resultSet) throws SQLException {
        ${simpleClassName} result = new ${simpleClassName}();
        
    <#list selectContext.columns as column>
        result.set${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}(${column.javaIdentifier}Type.readValue(resultSet, "${column.columnName}"));
    </#list>
        
        return result;
    }
}