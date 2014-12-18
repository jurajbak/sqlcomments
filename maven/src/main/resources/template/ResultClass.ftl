<#if packageName?has_content>
package ${packageName};
</#if>

<#assign classImports = []>
<#list selectContext.columns as column>
<#if !column.javaClass.name?starts_with("java.lang.") && !classImports?seq_contains(column.javaClass.name)>
	<#assign classImports = classImports + [column.javaClass.name]>
import ${column.javaClass.name};
</#if>
</#list>

public class ${simpleClassName} {

<#list selectContext.columns as column>
	private ${column.mappedClass! column.javaClass.simpleName} ${column.javaIdentifier};
</#list>

<#list selectContext.columns as column>
	public ${column.mappedClass! column.javaClass.simpleName} get${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}() {
		return ${column.javaIdentifier};
	}

	public void set${column.javaIdentifier[0]?upper_case}${column.javaIdentifier[1..]}(${column.mappedClass! column.javaClass.simpleName} newValue) {
		this.${column.javaIdentifier} = newValue;
	}
	
</#list>
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("${simpleClassName} [${selectContext.columns[0].javaIdentifier}=");
        builder.append(${selectContext.columns[0].javaIdentifier});
	<#list selectContext.columns[1..] as column>
        builder.append(", ${column.javaIdentifier}=");
        builder.append(${column.javaIdentifier});
	</#list>
        builder.append("]");
        return builder.toString();
    }
}