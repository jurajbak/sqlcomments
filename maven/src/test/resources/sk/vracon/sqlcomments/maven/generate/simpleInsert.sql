-- 
-- This is simple insert.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleInsert", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleInsertConfig")
INSERT INTO Companies (id, name, email) 
VALUES (:id, :name, :email)