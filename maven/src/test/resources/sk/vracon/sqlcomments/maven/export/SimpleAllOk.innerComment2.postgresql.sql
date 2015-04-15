-- Generated from sk.vracon.sqlcomments.maven.export.SimpleAllOk:61
-- @SQLComment(name="innerComment2", baseClass="sk.vracon.sqlcomments.maven.export.SimpleAllOk", database="postgresql")
            select * from users 
            where id = :placeholder
            order by name
