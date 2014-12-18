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
		
		result.setData(resultSet.getBlob("DATA"));
		result.setDescription(resultSet.getString("DESCRIPTION"));
		result.setId(resultSet.getInt("ID"));
		result.setName(resultSet.getString("NAME"));
		result.setUserid(resultSet.getInt("USERID"));
		
		return result;
	}
}