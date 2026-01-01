--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Project")
DELETE FROM proj WHERE
	PROJID = :projid 
