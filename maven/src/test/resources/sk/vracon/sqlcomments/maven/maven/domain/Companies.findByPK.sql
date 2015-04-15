--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Companies", resultClass="sk.vracon.sqlcomments.maven.domain.Companies", configClass="sk.vracon.sqlcomments.maven.domain.sqlcomments.CompaniesPKConfig")
SELECT * FROM COMPANIES WHERE
	ID = :id 
