--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Project")
DELETE FROM proj WHERE
	PROJID = :projid 
