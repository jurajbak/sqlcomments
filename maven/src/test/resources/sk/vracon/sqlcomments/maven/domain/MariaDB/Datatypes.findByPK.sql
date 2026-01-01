--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Datatypes", resultClass="sk.vracon.sqlcomments.maven.domain.MariaDB.Datatypes", configClass="sk.vracon.sqlcomments.maven.domain.MariaDB.sqlcomments.DatatypesPKConfig")
SELECT * FROM datatypes WHERE
	INT_ = :int_ 