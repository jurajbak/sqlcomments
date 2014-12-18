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
		
		result.setCompanyid(resultSet.getInt("COMPANYID"));
		result.setCountry(resultSet.getString("COUNTRY"));
		result.setEmail(resultSet.getString("EMAIL"));
		result.setFirstName(resultSet.getString("FIRST_NAME"));
		result.setId(resultSet.getInt("ID"));
		result.setLastName(resultSet.getString("LAST_NAME"));
		
		return result;
	}
}