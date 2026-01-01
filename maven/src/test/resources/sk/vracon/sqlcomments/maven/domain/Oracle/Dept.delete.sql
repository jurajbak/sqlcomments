--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Dept")
DELETE FROM dept WHERE
	DEPTNO = :deptno 
