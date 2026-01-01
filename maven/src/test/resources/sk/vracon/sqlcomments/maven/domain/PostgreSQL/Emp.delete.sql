--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Emp")
DELETE FROM emp WHERE
	EMPNO = :empno 
