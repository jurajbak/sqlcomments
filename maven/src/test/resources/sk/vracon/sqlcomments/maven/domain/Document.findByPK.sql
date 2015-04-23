--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Document", resultClass="sk.vracon.sqlcomments.maven.domain.Document", configClass="sk.vracon.sqlcomments.maven.domain.sqlcomments.DocumentPKConfig")
SELECT * FROM DOCUMENTS WHERE
	ID = :id 
