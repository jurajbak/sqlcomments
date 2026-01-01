--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Project", resultClass="sk.vracon.sqlcomments.maven.domain.MySQL.Project", configClass="sk.vracon.sqlcomments.maven.domain.MySQL.sqlcomments.ProjectPKConfig")
SELECT * FROM proj WHERE
	PROJID = :projid 