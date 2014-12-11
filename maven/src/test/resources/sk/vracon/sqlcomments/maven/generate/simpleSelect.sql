-- 
-- This is simple select with table prefixes.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleSelect", baseClass="sk.vracon.sqlcomments.maven.generate.SimpleSelect", resultClass="sk.vracon.sqlcomments.maven.generate.SimpleSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleSelectConfig")
SELECT comp.id, comp.name
FROM Companies comp
WHERE comp.id > 5 