package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.BlobMappings;

public class BlobMappingsMapper implements ResultMapper<BlobMappings> {


	public BlobMappingsMapper() {
	}

	public BlobMappings transform(ResultSet resultSet) throws SQLException {
		BlobMappings result = new BlobMappings();
		
		result.setData(resultSet.getBlob("DATA"));
		result.setDescription(resultSet.getString("DESCRIPTION"));
		result.setId(resultSet.getInt("ID"));
		result.setName(resultSet.getString("NAME"));
		result.setUserid(resultSet.getInt("USERID"));
		
		return result;
	}
}