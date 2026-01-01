--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept")
UPDATE dept SET
	DNAME = :dname,
	LOC = :loc
WHERE
	DEPTNO = :deptno 
