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
		
		Integer companyidValue = resultSet.getInt("COMPANYID");
		if(resultSet.wasNull()) {
			companyidValue = null;
		}
		result.setCompanyid(companyidValue);
		String countryValue = resultSet.getString("COUNTRY");
		if(resultSet.wasNull()) {
			countryValue = null;
		}
		result.setCountry(countryValue);
		String emailValue = resultSet.getString("EMAIL");
		if(resultSet.wasNull()) {
			emailValue = null;
		}
		result.setEmail(emailValue);
		String firstNameValue = resultSet.getString("FIRST_NAME");
		if(resultSet.wasNull()) {
			firstNameValue = null;
		}
		result.setFirstName(firstNameValue);
		Integer idValue = resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String lastNameValue = resultSet.getString("LAST_NAME");
		if(resultSet.wasNull()) {
			lastNameValue = null;
		}
		result.setLastName(lastNameValue);
		
		return result;
	}
}