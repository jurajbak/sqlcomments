<#if packageName?has_content>
package ${packageName};
</#if>

<#assign classImports = []>
<#list placeholders as placeholder>
<#if !placeholder.javaClass.name?starts_with("java.lang.") && !classImports?seq_contains(placeholder.javaClass.name)>
	<#assign classImports = classImports + [placeholder.javaClass.name]>
import ${placeholder.javaClass.name};
</#if>
</#list>

public class ${simpleClassName} {

<#list placeholders as placeholder>
	private ${placeholder.javaClass.simpleName} ${placeholder.name};
</#list>

<#list placeholders as placeholder>
	public ${placeholder.javaClass.simpleName} get${placeholder.name[0]?upper_case}${placeholder.name[1..]}() {
		return ${placeholder.name};
	}

	public void set${placeholder.name[0]?upper_case}${placeholder.name[1..]}(${placeholder.javaClass.simpleName} newValue) {
		this.${placeholder.name} = newValue;
	}
	
</#list>
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("${simpleClassName} [${placeholders[0].name}=");
        builder.append(${placeholders[0].name});
	<#list placeholders[1..] as placeholder>
        builder.append(", ${placeholder.name}=");
        builder.append(${placeholder.name});
	</#list>
        builder.append("]");
        return builder.toString();
    }
}