package sk.vracon.sqlcomments.maven.generate.sqlcomments;

import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.generate.BlobMappings;

public class BlobMappingsMapper implements ResultMapper<BlobMappings> {

	public BlobMappings transform(ResultSet resultSet) throws SQLException {
		BlobMappings result = new BlobMappings();
		
		result.setData((Blob) resultSet.getObject("DATA"));
		result.setDescription((String) resultSet.getObject("DESCRIPTION"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setName((String) resultSet.getObject("NAME"));
		result.setUserid((Integer) resultSet.getObject("USERID"));
		
		return result;
	}
}