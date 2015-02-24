-- 
-- Testing update with inner select in IN clause.
--
-- @SQLComment(name="updateInSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.UpdateInSelectConfig")
UPDATE documents SET description = name
WHERE userId IN (SELECT id FROM users WHERE companyId = :companyId)
