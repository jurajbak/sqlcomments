package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import sk.vracon.sqlcomments.maven.ExampleEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SimpleAsterix;

public class SimpleAsterixMapper implements ResultMapper<SimpleAsterix> {

	private sk.vracon.sqlcomments.core.mappers.EnumMapper countryColumnMapper = new sk.vracon.sqlcomments.core.mappers.EnumMapper(); 

	public SimpleAsterixMapper() {
		countryColumnMapper.setJavaType(ExampleEnum.class); 
	}

	public SimpleAsterix transform(ResultSet resultSet) throws SQLException {
		SimpleAsterix result = new SimpleAsterix();
		
		result.setCity(resultSet.getString("CITY"));
		result.setCountry((ExampleEnum) countryColumnMapper.convertToJava(resultSet.getString("COUNTRY")));
		result.setEmail(resultSet.getString("EMAIL"));
		result.setId(resultSet.getInt("ID"));
		result.setIpAddress(resultSet.getString("IP_ADDRESS"));
		result.setName(resultSet.getString("NAME"));
		
		return result;
	}
}