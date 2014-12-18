package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SimpleSelect;

public class SimpleSelectMapper implements ResultMapper<SimpleSelect> {


	public SimpleSelectMapper() {
	}

	public SimpleSelect transform(ResultSet resultSet) throws SQLException {
		SimpleSelect result = new SimpleSelect();
		
		result.setId(resultSet.getInt("id"));
		result.setName(resultSet.getString("name"));
		
		return result;
	}
}