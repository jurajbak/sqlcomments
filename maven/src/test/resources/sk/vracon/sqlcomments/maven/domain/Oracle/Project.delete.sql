--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Project")
DELETE FROM proj WHERE
	PROJID = :projid 
