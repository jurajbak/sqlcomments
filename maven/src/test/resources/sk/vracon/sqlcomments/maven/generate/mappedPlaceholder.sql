-- 
-- Test simple select with mapped placeholder.
-- Placeholder should have an enum type
--
-- @SQLComment(name="mappedPlaceholder", resultClass="sk.vracon.sqlcomments.maven.generate.MappedPlaceholder", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.MappedPlaceholderConfig")
select comp.id, comp.name from companies comp
where comp.country = :country
