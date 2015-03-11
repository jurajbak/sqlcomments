package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;
import java.util.Collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;

public class SelectWithCollectionAndMapperConfig implements StatementConfiguration {

	private static final Class<?> BASE_CLASS = null;

	private Map<String, Object> __sqlParameters;
	
	private Set<String> __acceptNullParameters;

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countriesColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public SelectWithCollectionAndMapperConfig() {
		countriesColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public void setCountries(Collection<ExampleEnum> value) {
		if(__sqlParameters == null) {
			__sqlParameters = new HashMap<String, Object>();
		}
		
		if(value == null) {
			__sqlParameters.put("countries", countriesColumnMapper.convertToDatabase(null));
		} else {
			Set converted = new HashSet(value.size()); 
			for (ExampleEnum item : value) {
			    converted.add(countriesColumnMapper.convertToDatabase(item));
	        }
			__sqlParameters.put("countries", converted);
		}
	}
	
	public void acceptNullInCountries() {
		if(__acceptNullParameters == null) {
			__acceptNullParameters = new HashSet<String>();
		}
		
		__acceptNullParameters.add("countries");
	}
	
	public String statementName() {
		return "selectWithCollectionAndMapper";
	}
	
	public Class<?> baseClass() {
		return BASE_CLASS;
	}

	public Map<String, Object> generateParameterMap() {
		return __sqlParameters;
	}
	
	public Set<String> generateParametersAcceptingNull() {
		return __acceptNullParameters;
	}
}