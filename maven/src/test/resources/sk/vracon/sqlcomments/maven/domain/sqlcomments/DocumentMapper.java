package sk.vracon.sqlcomments.maven.domain.sqlcomments;

import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Document;

public class DocumentMapper implements ResultMapper<Document> {


	public DocumentMapper() {
	}

	public Document transform(ResultSet resultSet) throws SQLException {
		Document result = new Document();
		
		Blob dataValue = resultSet.getBlob("DATA");
		if(resultSet.wasNull()) {
			dataValue = null;
		}
		result.setData(dataValue);
		String descriptionValue = resultSet.getString("DESCRIPTION");
		if(resultSet.wasNull()) {
			descriptionValue = null;
		}
		result.setDescription(descriptionValue);
		Integer idValue = resultSet.getInt("ID");
		if(resultSet.wasNull()) {
			idValue = null;
		}
		result.setId(idValue);
		String nameValue = resultSet.getString("NAME");
		if(resultSet.wasNull()) {
			nameValue = null;
		}
		result.setName(nameValue);
		Integer useridValue = resultSet.getInt("USERID");
		if(resultSet.wasNull()) {
			useridValue = null;
		}
		result.setUserid(useridValue);
		
		return result;
	}
}