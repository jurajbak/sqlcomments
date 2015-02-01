<#if packageName?has_content>
package ${packageName};
</#if>

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

public class ${simpleClassName} <#if interfaces?has_content>implements ${interfaces} </#if>{

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
    /**
     * @see java.lang.Object#toString()
     */
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
    
<#if primaryKeys??>
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ${simpleClassName} other = (${simpleClassName}) obj;
		<#list primaryKeys as key>
			<#list selectContext.columns as column><#if column.columnName = key>
		if (${column.javaIdentifier} == null) {
            if (other.${column.javaIdentifier} != null) return false;
        } else if (!${column.javaIdentifier}.equals(other.${column.javaIdentifier})) return false;
			</#if></#list>
		</#list>
        return true;
    }    
    
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
		<#list primaryKeys as key>
			<#list selectContext.columns as column><#if column.columnName = key>
        result = prime * result + ((${column.javaIdentifier} == null) ? 0 : ${column.javaIdentifier}.hashCode());
			</#if></#list>
		</#list>
        return result;
    }
</#if>
}