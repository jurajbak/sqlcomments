--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Project")
UPDATE proj SET
	EMPNO = :empno,
	ENDDATE = :enddate,
	STARTDATE = :startdate
WHERE
	PROJID = :projid 
