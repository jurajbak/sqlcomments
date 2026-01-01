-- 
-- Test select with all supported features used.
-- Placeholder :companyId should have an Integer type
-- Placeholder :name should have a String type
-- JavaScript variable jsVar have an Object type
--
-- @SQLComment(name="fullFeatured1", resultClass="sk.vracon.sqlcomments.maven.generate.FullFeatured1", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.FullFeatured1Config")
SELECT DISTINCT d.deptno, d.dname, e.empno, e.ename "EmployeeName"
FROM Dept d
	JOIN Emp e ON e.deptno = d.deptno			 		-- Join only if filtering by user name 	//@ userName != null
WHERE 1 = 1
 	AND d.deptno = :deptno
	AND e.ename LIKE :userName
	AND d.deptno > 5										-- Add condition only if JS passed 		//@ jsVar > 3
ORDER BY ${orderBy}
