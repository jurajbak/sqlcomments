--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Emp")
DELETE FROM emp WHERE
	EMPNO = :empno 
