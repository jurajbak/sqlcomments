-- 
-- Test simple select with one placeholder.
-- Placeholder should have an Integer type
--
-- @SQLComment(name="selectWithCollectionAndMapper", resultClass="sk.vracon.sqlcomments.maven.generate.SelectWithCollectionAndMapper", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SelectWithCollectionAndMapperConfig")
          select d.deptno, d.dname from dept d
          where d.loc IN (:locations)
