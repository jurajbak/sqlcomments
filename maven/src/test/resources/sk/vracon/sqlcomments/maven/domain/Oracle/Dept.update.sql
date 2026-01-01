--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Dept")
UPDATE dept SET
	DNAME = :dname,
	LOC = :loc
WHERE
	DEPTNO = :deptno 
