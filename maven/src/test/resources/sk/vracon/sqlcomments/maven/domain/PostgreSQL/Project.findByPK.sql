--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Project", resultClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Project", configClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments.ProjectPKConfig")
SELECT * FROM proj WHERE
	PROJID = :projid 