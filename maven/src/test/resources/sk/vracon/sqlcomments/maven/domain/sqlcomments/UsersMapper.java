package sk.vracon.sqlcomments.maven.domain.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Users;

public class UsersMapper implements ResultMapper<Users> {

	public static final UsersMapper INSTANCE = new UsersMapper();


	public UsersMapper() {
	}

	public Users transform(ResultSet resultSet) throws SQLException {
		Users result = new Users();
		
		Integer companyidValue = (Integer) resultSet.getInt("COMPANYID");
		if(resultSet.wasNull()) {
			companyidValue = null;
		}
		result.setCompanyid(companyidValue);
		String countryValue = (String) resultSet.getString("COUNTRY");
		if(resultSet.wasNull()) {
			countryValue = null;
		}
		result.setCountry(countryValue);
		String emailValue = (String) resultSet.getString("EMAIL");
		if(resultSet.wasNull()) {
			emailValue = null;
		}
		result.setEmail(emailValue);
		String firstNameValue = (String) resultSet.getString("FIRST_NAME");
		if(resultSet.wasNull()) {
			firstNameValue = null;
		}
		result.setFirstName(firstNameValue);
		Integer idValue = (Integer) resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String lastNameValue = (String) resultSet.getString("LAST_NAME");
		if(resultSet.wasNull()) {
			lastNameValue = null;
		}
		result.setLastName(lastNameValue);
		
		return result;
	}
}