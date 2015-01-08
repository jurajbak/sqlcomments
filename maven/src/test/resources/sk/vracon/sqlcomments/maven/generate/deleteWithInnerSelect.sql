-- 
-- Delete with inner select.
-- Testing basic functionality.
--
-- @SQLComment(name="deleteWithInnerSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.DeleteWithInnerSelectConfig")
DELETE FROM Companies WHERE id NOT IN (SELECT companyId FROM users)