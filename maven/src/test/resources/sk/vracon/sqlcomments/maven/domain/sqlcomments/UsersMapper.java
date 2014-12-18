package sk.vracon.sqlcomments.maven.domain.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Users;

public class UsersMapper implements ResultMapper<Users> {


	public UsersMapper() {
	}

	public Users transform(ResultSet resultSet) throws SQLException {
		Users result = new Users();
		
		result.setCompanyid((Integer) resultSet.getObject("COMPANYID"));
		result.setCountry((String) resultSet.getObject("COUNTRY"));
		result.setEmail((String) resultSet.getObject("EMAIL"));
		result.setFirstName((String) resultSet.getObject("FIRST_NAME"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setLastName((String) resultSet.getObject("LAST_NAME"));
		
		return result;
	}
}