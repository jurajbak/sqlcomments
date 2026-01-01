-- 
-- Test select with one replacement parameter.
-- Replacement parameter should have an java.lang.String type
--
-- @SQLComment(name="replacementParameter", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.ReplacementParameterConfig")
select e.empno, e.ename from emp e
order by ${replacementParam}
