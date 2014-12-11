package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SimpleSelect;

public class SimpleSelectMapper implements ResultMapper<SimpleSelect> {

	public SimpleSelect transform(ResultSet resultSet) throws SQLException {
		SimpleSelect result = new SimpleSelect();
		
		result.setId((Integer) resultSet.getObject("id"));
		result.setName((String) resultSet.getObject("name"));
		
		return result;
	}
}