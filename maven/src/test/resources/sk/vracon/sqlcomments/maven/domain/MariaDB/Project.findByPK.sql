--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Project", resultClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Project", configClass="sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments.ProjectPKConfig")
SELECT * FROM proj WHERE
	PROJID = :projid 