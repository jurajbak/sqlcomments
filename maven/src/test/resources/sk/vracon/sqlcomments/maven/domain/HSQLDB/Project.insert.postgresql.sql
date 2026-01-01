--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Project")
INSERT INTO PROJ ( EMPNO, ENDDATE, PROJID, STARTDATE)
VALUES ( :empno , :enddate , nextval('documents_seq') , :startdate )