package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SimpleAsterix;

public class SimpleAsterixMapper implements ResultMapper<SimpleAsterix> {

	public static final SimpleAsterixMapper INSTANCE = new SimpleAsterixMapper();

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public SimpleAsterixMapper() {
		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public SimpleAsterix transform(ResultSet resultSet) throws SQLException {
		SimpleAsterix result = new SimpleAsterix();
		
		String cityValue = (String) resultSet.getString("CITY");
		if(resultSet.wasNull()) {
			cityValue = null;
		}
		result.setCity(cityValue);
		String countryValue = (String) resultSet.getString("COUNTRY");
		if(resultSet.wasNull()) {
			countryValue = null;
		}
		result.setCountry((ExampleEnum) countryColumnMapper.convertToJava(countryValue));
		String emailValue = (String) resultSet.getString("EMAIL");
		if(resultSet.wasNull()) {
			emailValue = null;
		}
		result.setEmail(emailValue);
		Integer idValue = (Integer) resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String ipAddressValue = (String) resultSet.getString("IP_ADDRESS");
		if(resultSet.wasNull()) {
			ipAddressValue = null;
		}
		result.setIpAddress(ipAddressValue);
		String nameValue = (String) resultSet.getString("NAME");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		
		return result;
	}
}