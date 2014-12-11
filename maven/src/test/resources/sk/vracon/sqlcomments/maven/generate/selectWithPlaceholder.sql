-- 
-- Test simple select with one placeholder.
-- Placeholder should have an Integer type
--
-- @SQLComment(name="selectWithPlaceholder", resultClass="sk.vracon.sqlcomments.maven.generate.SelectWithPlaceholder", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SelectWithPlaceholderConfig")
          select comp.id, comp.name from companies comp
          where comp.id = :companyId
