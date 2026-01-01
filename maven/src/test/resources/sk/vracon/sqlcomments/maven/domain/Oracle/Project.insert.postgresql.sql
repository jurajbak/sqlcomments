--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Project")
INSERT INTO proj ( EMPNO, ENDDATE, PROJID, STARTDATE)
VALUES ( :empno , :enddate , nextval('documents_seq') , :startdate )