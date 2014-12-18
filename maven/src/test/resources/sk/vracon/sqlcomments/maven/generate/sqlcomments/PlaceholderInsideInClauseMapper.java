package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInsideInClause;

public class PlaceholderInsideInClauseMapper implements ResultMapper<PlaceholderInsideInClause> {


	public PlaceholderInsideInClauseMapper() {
	}

	public PlaceholderInsideInClause transform(ResultSet resultSet) throws SQLException {
		PlaceholderInsideInClause result = new PlaceholderInsideInClause();
		
		result.setId(resultSet.getInt("id"));
		result.setName(resultSet.getString("name"));
		
		return result;
	}
}