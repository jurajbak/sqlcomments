--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Emp", resultClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Emp", configClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.sqlcomments.EmpPKConfig")
SELECT * FROM EMP WHERE
	EMPNO = :empno 