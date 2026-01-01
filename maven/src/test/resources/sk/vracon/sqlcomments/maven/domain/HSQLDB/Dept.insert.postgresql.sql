--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Dept")
INSERT INTO DEPT ( DEPTNO, DNAME, LOC)
VALUES ( nextval('companies_seq') , :dname , :loc )