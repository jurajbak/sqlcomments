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
		
		result.setId(resultSet.getInt("id"));
		result.setName(resultSet.getString("name"));
		
		return result;
	}
}