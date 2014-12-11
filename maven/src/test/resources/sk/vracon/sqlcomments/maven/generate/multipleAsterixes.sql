-- 
-- This is simple select with asterix instead of columns.
-- Testing basic functionality.
--
-- @SQLComment(name="multipleAsterixes", resultClass="sk.vracon.sqlcomments.maven.generate.MultipleAsterixes", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.MultipleAsterixesConfig")
SELECT comp.*, user.first_name
FROM Companies comp, Users user
WHERE comp.id = user.companyId