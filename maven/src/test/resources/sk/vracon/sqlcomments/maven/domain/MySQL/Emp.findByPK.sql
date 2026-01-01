--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Emp", resultClass="sk.vracon.sqlcomments.maven.domain.MySQL.Emp", configClass="sk.vracon.sqlcomments.maven.domain.MySQL.sqlcomments.EmpPKConfig")
SELECT * FROM emp WHERE
	EMPNO = :empno 