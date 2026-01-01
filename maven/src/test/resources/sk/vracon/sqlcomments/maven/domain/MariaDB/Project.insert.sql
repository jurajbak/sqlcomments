--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Project")
INSERT INTO proj ( EMPNO, ENDDATE, PROJID, STARTDATE)
VALUES ( :empno , :enddate , :projid , :startdate )