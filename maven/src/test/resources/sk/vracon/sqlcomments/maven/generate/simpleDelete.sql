-- 
-- This is simple delete.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleDelete", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleDeleteConfig")
DELETE FROM emp 
WHERE empno > :employeeNo