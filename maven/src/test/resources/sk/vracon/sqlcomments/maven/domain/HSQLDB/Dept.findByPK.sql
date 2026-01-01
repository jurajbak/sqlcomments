--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Dept", resultClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Dept", configClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.sqlcomments.DeptPKConfig")
SELECT * FROM DEPT WHERE
	DEPTNO = :deptno 