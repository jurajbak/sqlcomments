--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Users", resultClass="sk.vracon.sqlcomments.maven.domain.Users", configClass="sk.vracon.sqlcomments.maven.domain.sqlcomments.UsersPKConfig")
SELECT * FROM USERS WHERE
	ID = :id 
