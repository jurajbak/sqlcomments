package sk.vracon.sqlcomments.maven.generate.sqlcomments;


import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.SelectWithCollectionAndMapper;

public class SelectWithCollectionAndMapperMapper implements ResultMapper<SelectWithCollectionAndMapper> {

	public static final SelectWithCollectionAndMapperMapper INSTANCE = new SelectWithCollectionAndMapperMapper();


	public SelectWithCollectionAndMapperMapper() {
	}

	public SelectWithCollectionAndMapper transform(ResultSet resultSet) throws SQLException {
		SelectWithCollectionAndMapper result = new SelectWithCollectionAndMapper();
		
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