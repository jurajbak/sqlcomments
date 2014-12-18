package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.DefaultResult;

public class DefaultResultMapper implements ResultMapper<DefaultResult> {


	public DefaultResultMapper() {
	}

	public DefaultResult transform(ResultSet resultSet) throws SQLException {
		DefaultResult result = new DefaultResult();
		
		result.setCompanyid(resultSet.getInt("COMPANYID"));
		result.setCountry(resultSet.getString("COUNTRY"));
		result.setEmail(resultSet.getString("EMAIL"));
		result.setFirstName(resultSet.getString("FIRST_NAME"));
		result.setId(resultSet.getInt("ID"));
		result.setLastName(resultSet.getString("LAST_NAME"));
		
		return result;
	}
}