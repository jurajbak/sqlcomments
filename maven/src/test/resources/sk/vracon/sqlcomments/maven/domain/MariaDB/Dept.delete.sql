--
-- @SQLComment(name="delete", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Dept")
DELETE FROM dept WHERE
	DEPTNO = :deptno 
