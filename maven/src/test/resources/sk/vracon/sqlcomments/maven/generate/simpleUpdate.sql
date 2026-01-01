-- 
-- This is simple update.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleUpdate", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleUpdateConfig")
UPDATE dept 
SET dname = loc,
	loc = :country
WHERE deptno > 5