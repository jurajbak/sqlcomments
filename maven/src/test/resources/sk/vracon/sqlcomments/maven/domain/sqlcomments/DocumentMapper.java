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
		
		result.setData((Blob) resultSet.getObject("DATA"));
		result.setDescription((String) resultSet.getObject("DESCRIPTION"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setName((String) resultSet.getObject("NAME"));
		result.setUserid((Integer) resultSet.getObject("USERID"));
		
		return result;
	}
}