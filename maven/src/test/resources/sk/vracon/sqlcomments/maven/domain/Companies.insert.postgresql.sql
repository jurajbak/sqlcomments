--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.Companies")
INSERT INTO COMPANIES ( CITY, COUNTRY, EMAIL, ID, IP_ADDRESS, NAME )
VALUES ( :city , :country , :email , nextval('companies_seq') , :ipAddress , :name )