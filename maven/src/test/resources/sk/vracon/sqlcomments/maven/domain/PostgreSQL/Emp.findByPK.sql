--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Emp", resultClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Emp", configClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments.EmpPKConfig")
SELECT * FROM emp WHERE
	EMPNO = :empno 