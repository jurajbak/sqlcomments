--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Emp", resultClass="sk.vracon.sqlcomments.maven.domain.Oracle.Emp", configClass="sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments.EmpPKConfig")
SELECT * FROM emp WHERE
	EMPNO = :empno 