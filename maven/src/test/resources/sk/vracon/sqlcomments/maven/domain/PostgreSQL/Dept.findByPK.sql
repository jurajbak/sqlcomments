--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept", resultClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Dept", configClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments.DeptPKConfig")
SELECT * FROM dept WHERE
	DEPTNO = :deptno 