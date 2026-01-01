--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Project")
UPDATE PROJ SET
	EMPNO = :empno,
	ENDDATE = :enddate,
	STARTDATE = :startdate
WHERE
	PROJID = :projid 
