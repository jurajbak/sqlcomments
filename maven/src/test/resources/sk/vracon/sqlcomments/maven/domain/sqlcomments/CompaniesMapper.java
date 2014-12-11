package sk.vracon.sqlcomments.maven.domain.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Companies;

public class CompaniesMapper implements ResultMapper<Companies> {

	public Companies transform(ResultSet resultSet) throws SQLException {
		Companies result = new Companies();
		
		result.setCity((String) resultSet.getObject("CITY"));
		result.setCountry((String) resultSet.getObject("COUNTRY"));
		result.setEmail((String) resultSet.getObject("EMAIL"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setIpAddress((String) resultSet.getObject("IP_ADDRESS"));
		result.setName((String) resultSet.getObject("NAME"));
		
		return result;
	}
}