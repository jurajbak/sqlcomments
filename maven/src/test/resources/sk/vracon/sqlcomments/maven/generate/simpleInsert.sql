-- 
-- This is simple insert.
-- Testing basic functionality.
--
-- @SQLComment(name="simpleInsert", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.SimpleInsertConfig")
INSERT INTO proj (PROJID,EMPNO,STARTDATE,ENDDATE) 
VALUES (:projectId, :employeeNo, :startDate, :endDate)