--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Project", resultClass="sk.vracon.sqlcomments.maven.domain.Oracle.Project", configClass="sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments.ProjectPKConfig")
SELECT * FROM proj WHERE
	PROJID = :projid 