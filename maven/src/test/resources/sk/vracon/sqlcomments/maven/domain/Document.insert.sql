--
-- @SQLComment(name="insert", baseClass="sk.vracon.sqlcomments.maven.domain.Document")
INSERT INTO DOCUMENTS ( DATA, DESCRIPTION, ID, NAME, USERID )
VALUES ( :data , :description , :id , :name , :userid )