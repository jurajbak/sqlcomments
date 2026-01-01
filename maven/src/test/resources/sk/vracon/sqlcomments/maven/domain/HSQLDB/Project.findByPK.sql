--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Project", resultClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Project", configClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.sqlcomments.ProjectPKConfig")
SELECT * FROM PROJ WHERE
	PROJID = :projid 