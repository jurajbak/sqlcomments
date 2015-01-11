package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInsideInClause;

public class PlaceholderInsideInClauseMapper implements ResultMapper<PlaceholderInsideInClause> {

	public static final PlaceholderInsideInClauseMapper INSTANCE = new PlaceholderInsideInClauseMapper();


	public PlaceholderInsideInClauseMapper() {
	}

	public PlaceholderInsideInClause transform(ResultSet resultSet) throws SQLException {
		PlaceholderInsideInClause result = new PlaceholderInsideInClause();
		
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