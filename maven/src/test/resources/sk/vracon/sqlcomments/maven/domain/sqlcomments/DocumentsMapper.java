package sk.vracon.sqlcomments.maven.domain.sqlcomments;

import java.sql.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.maven.domain.Documents;

public class DocumentsMapper implements ResultMapper<Documents> {

	public Documents transform(ResultSet resultSet) throws SQLException {
		Documents result = new Documents();
		
		result.setData((Blob) resultSet.getObject("DATA"));
		result.setDescription((String) resultSet.getObject("DESCRIPTION"));
		result.setId((Integer) resultSet.getObject("ID"));
		result.setName((String) resultSet.getObject("NAME"));
		result.setUserid((Integer) resultSet.getObject("USERID"));
		
		return result;
	}
}