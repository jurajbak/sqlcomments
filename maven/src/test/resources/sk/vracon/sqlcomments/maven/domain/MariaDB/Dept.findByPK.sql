--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Dept", resultClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Dept", configClass="sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments.DeptPKConfig")
SELECT * FROM dept WHERE
	DEPTNO = :deptno 