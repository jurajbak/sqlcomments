--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.Users")
INSERT INTO USERS ( COMPANYID, COUNTRY, EMAIL, FIRST_NAME, ID, LAST_NAME )
VALUES ( :companyid , :country , :email , :firstName , :id , :lastName )