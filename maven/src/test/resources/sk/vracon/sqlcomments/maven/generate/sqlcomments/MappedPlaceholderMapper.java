package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.MappedPlaceholder;

public class MappedPlaceholderMapper implements ResultMapper<MappedPlaceholder> {

	public static final MappedPlaceholderMapper INSTANCE = new MappedPlaceholderMapper();


	public MappedPlaceholderMapper() {
	}

	public MappedPlaceholder transform(ResultSet resultSet) throws SQLException {
		MappedPlaceholder result = new MappedPlaceholder();
		
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