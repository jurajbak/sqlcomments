--
-- @SQLComment(name="update", baseClass="sk.vracon.sqlcomments.maven.domain.Documents")
UPDATE DOCUMENTS SET
	DATA = :data,
	DESCRIPTION = :description,
	NAME = :name,
	USERID = :userid
WHERE
	ID = :id 
