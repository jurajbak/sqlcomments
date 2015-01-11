package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SelectWithPlaceholder;

public class SelectWithPlaceholderMapper implements ResultMapper<SelectWithPlaceholder> {


	public SelectWithPlaceholderMapper() {
	}

	public SelectWithPlaceholder transform(ResultSet resultSet) throws SQLException {
		SelectWithPlaceholder result = new SelectWithPlaceholder();
		
		Integer idValue = resultSet.getInt("id");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String nameValue = resultSet.getString("name");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		
		return result;
	}
}