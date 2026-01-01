--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Dept")
UPDATE dept SET
	DNAME = :dname,
	LOC = :loc
WHERE
	DEPTNO = :deptno 
