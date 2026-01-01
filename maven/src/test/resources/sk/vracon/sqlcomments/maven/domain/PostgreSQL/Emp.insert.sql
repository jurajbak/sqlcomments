--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Emp")
INSERT INTO emp ( COMM, DEPTNO, EMPNO, ENAME, HIREDATE, JOB, MGR, SAL)
VALUES ( :comm , :deptno , :empno , :ename , :hiredate , :job , :mgr , :sal )