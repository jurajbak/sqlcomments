package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.MultipleAsterixes;

public class MultipleAsterixesMapper implements ResultMapper<MultipleAsterixes> {

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public MultipleAsterixesMapper() {
		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public MultipleAsterixes transform(ResultSet resultSet) throws SQLException {
		MultipleAsterixes result = new MultipleAsterixes();
		
		result.setCity(resultSet.getString("CITY"));
		result.setCountry((ExampleEnum) countryColumnMapper.convertToJava(resultSet.getString("COUNTRY")));
		result.setEmail(resultSet.getString("EMAIL"));
		result.setFirstName(resultSet.getString("first_name"));
		result.setId(resultSet.getInt("ID"));
		result.setIpAddress(resultSet.getString("IP_ADDRESS"));
		result.setName(resultSet.getString("NAME"));
		
		return result;
	}
}