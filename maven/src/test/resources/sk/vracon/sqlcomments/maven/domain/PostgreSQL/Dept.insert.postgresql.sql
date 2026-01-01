--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept")
INSERT INTO dept ( DEPTNO, DNAME, LOC)
VALUES ( nextval('companies_seq') , :dname , :loc )