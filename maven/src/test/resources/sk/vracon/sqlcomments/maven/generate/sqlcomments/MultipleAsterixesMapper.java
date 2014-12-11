package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.MultipleAsterixes;

public class MultipleAsterixesMapper implements ResultMapper<MultipleAsterixes> {

	public MultipleAsterixes transform(ResultSet resultSet) throws SQLException {
		MultipleAsterixes result = new MultipleAsterixes();
		
		result.setCity((String) resultSet.getObject("CITY"));
		result.setCountry((String) resultSet.getObject("COUNTRY"));
		result.setEmail((String) resultSet.getObject("EMAIL"));
		result.setFirstName((String) resultSet.getObject("first_name"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setIpAddress((String) resultSet.getObject("IP_ADDRESS"));
		result.setName((String) resultSet.getObject("NAME"));
		
		return result;
	}
}