-- 
-- Test select with one placeholder using IN clause.
-- Placeholder should have an java.util.Collection type
--
-- @SQLComment(name="selectWithPlaceholder", resultClass="sk.vracon.sqlcomments.maven.generate.PlaceholderInsideInClause", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.PlaceholderInsideInClauseConfig")
select d.deptno, d.dname from dept d
where d.deptno IN (:departmentIds)
