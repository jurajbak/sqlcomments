package sk.vracon.sqlcomments.maven.domain.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Companies;

public class CompaniesMapper implements ResultMapper<Companies> {

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public CompaniesMapper() {
		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public Companies transform(ResultSet resultSet) throws SQLException {
		Companies result = new Companies();
		
		result.setCity((String) resultSet.getObject("CITY"));
		result.setCountry((ExampleEnum) countryColumnMapper.convertToJava(resultSet.getObject("COUNTRY")));
		result.setEmail((String) resultSet.getObject("EMAIL"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setIpAddress((String) resultSet.getObject("IP_ADDRESS"));
		result.setName((String) resultSet.getObject("NAME"));
		
		return result;
	}
}