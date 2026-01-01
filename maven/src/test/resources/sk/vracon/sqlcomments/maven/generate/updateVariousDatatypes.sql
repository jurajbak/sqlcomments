-- 
-- This is simple update.
-- Testing basic functionality.
--
-- @SQLComment(name="updateVariousDatatypes", configClass="sk.vracon.sqlcomments.maven.generate.sqlcomments.UpdateVariousDatatypesConfig")
UPDATE datatypes 
SET timestamp_tz_ = :tsVar,
 blob_ = :blobVar
WHERE integer_ = :intVar