--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Dept", resultClass="sk.vracon.sqlcomments.maven.domain.Oracle.Dept", configClass="sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments.DeptPKConfig")
SELECT * FROM dept WHERE
	DEPTNO = :deptno 