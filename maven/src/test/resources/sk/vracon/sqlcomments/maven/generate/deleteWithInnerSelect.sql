-- 
-- Delete with inner select.
-- Testing basic functionality.
--
-- @SQLComment(name="deleteWithInnerSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.DeleteWithInnerSelectConfig")
DELETE FROM dept WHERE deptno NOT IN (SELECT deptno FROM emp)