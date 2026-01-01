--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Project")
UPDATE proj SET
	EMPNO = :empno,
	ENDDATE = :enddate,
	STARTDATE = :startdate
WHERE
	PROJID = :projid 
