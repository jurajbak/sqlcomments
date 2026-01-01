-- 
-- Testing update with inner select in IN clause.
--
-- @SQLComment(name="updateInSelect", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.UpdateInSelectConfig")
UPDATE dept SET dname = loc
WHERE deptno IN (SELECT deptno FROM emp WHERE empno = :empNo)
