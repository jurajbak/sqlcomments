package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.FullFeatured1;

public class FullFeatured1Mapper implements ResultMapper<FullFeatured1> {

	public static final FullFeatured1Mapper INSTANCE = new FullFeatured1Mapper();


	public FullFeatured1Mapper() {
	}

	public FullFeatured1 transform(ResultSet resultSet) throws SQLException {
		FullFeatured1 result = new FullFeatured1();
		
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