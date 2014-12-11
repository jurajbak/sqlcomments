-- 
-- This is simple update.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleUpdate", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleUpdateConfig")
UPDATE Companies 
SET name = email,
	country = :country
WHERE id > 5