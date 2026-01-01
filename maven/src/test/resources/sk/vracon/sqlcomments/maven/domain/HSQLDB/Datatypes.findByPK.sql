--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Datatypes", resultClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.Datatypes", configClass="sk.vracon.sqlcomments.maven.domain.HSQLDB.sqlcomments.DatatypesPKConfig")
SELECT * FROM DATATYPES WHERE
	INTEGER_ = :integer 