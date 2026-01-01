--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Dept")
DELETE FROM dept WHERE
	DEPTNO = :deptno 
