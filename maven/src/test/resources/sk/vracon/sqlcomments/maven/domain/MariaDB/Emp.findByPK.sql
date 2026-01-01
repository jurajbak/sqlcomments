--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Emp", resultClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Emp", configClass="sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments.EmpPKConfig")
SELECT * FROM emp WHERE
	EMPNO = :empno 