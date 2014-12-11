package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.FullFeatured1;

public class FullFeatured1Mapper implements ResultMapper<FullFeatured1> {

	public FullFeatured1 transform(ResultSet resultSet) throws SQLException {
		FullFeatured1 result = new FullFeatured1();
		
		result.setId((Integer) resultSet.getObject("id"));
		result.setName((String) resultSet.getObject("name"));
		
		return result;
	}
}