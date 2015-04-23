-- Generated from sk.vracon.sqlcomments.maven.export.SimpleAllOk:56
-- @SQLComment(name="innerComment2", baseClass="sk.vracon.sqlcomments.maven.export.SimpleAllOk", resultClass="sk.vracon.InnerComment2", configClass="sk.vracon.InnerComment2Config")
            select * from users 
            where id = :placeholder
            order by name
