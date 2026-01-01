-- 
-- This is simple select with asterix instead of columns.
-- Testing basic functionality.
--
-- @SQLComment(name="multipleAsterixes", resultClass="sk.vracon.sqlcomments.maven.generate.MultipleAsterixes", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.MultipleAsterixesConfig")
SELECT d.*, e.ename
FROM dept d, emp e
WHERE d.deptno = e.deptno