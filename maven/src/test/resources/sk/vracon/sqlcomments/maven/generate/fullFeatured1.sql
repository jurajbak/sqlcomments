-- 
-- Test select with all supported features used.
-- Placeholder :companyId should have an Integer type
-- Placeholder :name should have a String type
-- JavaScript variable jsVar have an Object type
--
-- @SQLComment(name="fullFeatured1", resultClass="sk.vracon.sqlcomments.maven.generate.FullFeatured1", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.FullFeatured1Config")
SELECT DISTINCT comp.id, comp.name
FROM companies comp
	JOIN users user ON company.id = user.companyId 		-- Join only if filtering by user name 	//@ userName != null
WHERE 1 = 1
 	AND comp.id = :companyId
	AND user.name LIKE :userName
	AND comp.id > 5										-- Add condition only if JS passed 		//@ jsVar > 3
ORDER BY ${orderBy}
