package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.MappedPlaceholder;

public class MappedPlaceholderMapper implements ResultMapper<MappedPlaceholder> {


	public MappedPlaceholderMapper() {
	}

	public MappedPlaceholder transform(ResultSet resultSet) throws SQLException {
		MappedPlaceholder result = new MappedPlaceholder();
		
		result.setId((Integer) resultSet.getObject("id"));
		result.setName((String) resultSet.getObject("name"));
		
		return result;
	}
}