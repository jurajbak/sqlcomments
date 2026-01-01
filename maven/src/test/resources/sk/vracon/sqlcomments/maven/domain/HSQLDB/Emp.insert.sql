--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Emp")
INSERT INTO EMP ( COMM, DEPTNO, EMPNO, ENAME, HIREDATE, JOB, MGR, SAL)
VALUES ( :comm , :deptno , :empno , :ename , :hiredate , :job , :mgr , :sal )