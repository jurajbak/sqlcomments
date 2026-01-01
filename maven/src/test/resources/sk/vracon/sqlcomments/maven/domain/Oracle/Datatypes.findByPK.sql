--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.Oracle.Datatypes", resultClass="sk.vracon.sqlcomments.maven.domain.Oracle.Datatypes", configClass="sk.vracon.sqlcomments.maven.domain.Oracle.sqlcomments.DatatypesPKConfig")
SELECT * FROM datatypes WHERE
	INT_ = :int_ 