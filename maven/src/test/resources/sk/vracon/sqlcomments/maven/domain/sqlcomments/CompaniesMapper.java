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
		
		String cityValue = resultSet.getString("CITY");
		if(resultSet.wasNull()) {
			cityValue = null;
		}
		result.setCity(cityValue);
		String countryValue = resultSet.getString("COUNTRY");
		if(resultSet.wasNull()) {
			countryValue = null;
		}
		result.setCountry((ExampleEnum) countryColumnMapper.convertToJava(countryValue));
		String emailValue = resultSet.getString("EMAIL");
		if(resultSet.wasNull()) {
			emailValue = null;
		}
		result.setEmail(emailValue);
		Integer idValue = resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String ipAddressValue = resultSet.getString("IP_ADDRESS");
		if(resultSet.wasNull()) {
			ipAddressValue = null;
		}
		result.setIpAddress(ipAddressValue);
		String nameValue = resultSet.getString("NAME");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		
		return result;
	}
}