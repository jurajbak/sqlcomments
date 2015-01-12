package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.MultipleAsterixes;

public class MultipleAsterixesMapper implements ResultMapper<MultipleAsterixes> {

	public static final MultipleAsterixesMapper INSTANCE = new MultipleAsterixesMapper();

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public MultipleAsterixesMapper() {
		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public MultipleAsterixes transform(ResultSet resultSet) throws SQLException {
		MultipleAsterixes result = new MultipleAsterixes();
		
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
		String firstNameValue = (String) resultSet.getString("first_name");
		if(resultSet.wasNull()) {
			firstNameValue = null;
		}
		result.setFirstName(firstNameValue);
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