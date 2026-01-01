--
-- @SQLComment(name="findByPK", baseClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Datatypes", resultClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.Datatypes", configClass="sk.vracon.sqlcomments.maven.domain.PostgreSQL.sqlcomments.DatatypesPKConfig")
SELECT * FROM datatypes WHERE
	INTEGER_ = :integer 