--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Dept")
UPDATE DEPT SET
	DNAME = :dname,
	LOC = :loc
WHERE
	DEPTNO = :deptno 
