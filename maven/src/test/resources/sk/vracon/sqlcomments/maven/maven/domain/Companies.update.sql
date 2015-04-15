--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.Companies")
UPDATE COMPANIES SET
	CITY = :city,
	COUNTRY = :country,
	EMAIL = :email,
	IP_ADDRESS = :ipAddress,
	NAME = :name
WHERE
	ID = :id 
