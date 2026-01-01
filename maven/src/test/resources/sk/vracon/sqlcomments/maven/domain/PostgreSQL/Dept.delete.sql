--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept")
DELETE FROM dept WHERE
	DEPTNO = :deptno 
