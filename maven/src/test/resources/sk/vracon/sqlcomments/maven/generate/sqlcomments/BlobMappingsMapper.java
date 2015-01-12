package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.BlobMappings;

public class BlobMappingsMapper implements ResultMapper<BlobMappings> {

	public static final BlobMappingsMapper INSTANCE = new BlobMappingsMapper();


	public BlobMappingsMapper() {
	}

	public BlobMappings transform(ResultSet resultSet) throws SQLException {
		BlobMappings result = new BlobMappings();
		
		Blob dataValue = (Blob) resultSet.getBlob("DATA");
		if(resultSet.wasNull()) {
			dataValue = null;
		}
		result.setData(dataValue);
		String descriptionValue = (String) resultSet.getString("DESCRIPTION");
		if(resultSet.wasNull()) {
			descriptionValue = null;
		}
		result.setDescription(descriptionValue);
		Integer idValue = (Integer) resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String nameValue = (String) resultSet.getString("NAME");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		Integer useridValue = (Integer) resultSet.getInt("USERID");
		if(resultSet.wasNull()) {
			useridValue = null;
		}
		result.setUserid(useridValue);
		
		return result;
	}
}