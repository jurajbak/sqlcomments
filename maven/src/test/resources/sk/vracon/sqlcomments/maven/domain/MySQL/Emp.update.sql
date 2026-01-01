--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Emp")
UPDATE emp SET
	COMM = :comm,
	DEPTNO = :deptno,
	ENAME = :ename,
	HIREDATE = :hiredate,
	JOB = :job,
	MGR = :mgr,
	SAL = :sal
WHERE
	EMPNO = :empno 
