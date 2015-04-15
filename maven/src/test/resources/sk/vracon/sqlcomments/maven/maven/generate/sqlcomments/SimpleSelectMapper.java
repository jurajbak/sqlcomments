package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SimpleSelect;

public class SimpleSelectMapper implements ResultMapper<SimpleSelect> {

	public static final SimpleSelectMapper INSTANCE = new SimpleSelectMapper();


	public SimpleSelectMapper() {
	}

	public SimpleSelect transform(ResultSet resultSet) throws SQLException {
		SimpleSelect result = new SimpleSelect();
		
		Integer idValue = (Integer) resultSet.getInt("id");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String nameValue = (String) resultSet.getString("name");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		
		return result;
	}
}