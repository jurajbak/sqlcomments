--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Dept", resultClass="sk.vracon.sqlcomments.maven.domain.MySQL.Dept", configClass="sk.vracon.sqlcomments.maven.domain.MySQL.sqlcomments.DeptPKConfig")
SELECT * FROM dept WHERE
	DEPTNO = :deptno 