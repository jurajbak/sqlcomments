--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.Users")
UPDATE USERS SET
	COMPANYID = :companyid,
	COUNTRY = :country,
	EMAIL = :email,
	FIRST_NAME = :firstName,
	LAST_NAME = :lastName
WHERE
	ID = :id 
