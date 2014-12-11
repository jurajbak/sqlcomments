package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.DefaultResult;

public class DefaultResultMapper implements ResultMapper<DefaultResult> {

	public DefaultResult transform(ResultSet resultSet) throws SQLException {
		DefaultResult result = new DefaultResult();
		
		result.setCompanyid((Integer) resultSet.getObject("COMPANYID"));
		result.setCountry((String) resultSet.getObject("COUNTRY"));
		result.setEmail((String) resultSet.getObject("EMAIL"));
		result.setFirstName((String) resultSet.getObject("FIRST_NAME"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setLastName((String) resultSet.getObject("LAST_NAME"));
		
		return result;
	}
}