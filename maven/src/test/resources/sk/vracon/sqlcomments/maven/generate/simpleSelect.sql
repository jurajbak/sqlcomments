-- 
-- This is simple select with table prefixes.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleSelect", baseClass="sk.vracon.sqlcomments.maven.generate.SimpleSelect", resultClass="sk.vracon.sqlcomments.maven.generate.SimpleSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleSelectConfig")
SELECT d.deptno, d.dname
FROM dept d
WHERE d.deptno > 5 