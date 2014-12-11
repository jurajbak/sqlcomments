--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Documents", resultClass="sk.vracon.sqlcomments.maven.domain.Documents", configClass="sk.vracon.sqlcomments.maven.domain.sqlcomments.DocumentsPKConfig")
SELECT * FROM DOCUMENTS WHERE
	ID = :id 
