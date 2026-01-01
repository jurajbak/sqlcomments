--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MySQL.Datatypes", resultClass="sk.vracon.sqlcomments.maven.domain.MySQL.Datatypes", configClass="sk.vracon.sqlcomments.maven.domain.MySQL.sqlcomments.DatatypesPKConfig")
SELECT * FROM datatypes WHERE
	INT_ = :int_ 