-- Generated from sk.vracon.sqlcomments.maven.export.SimpleAllOk:50
-- @SQLComment(name="innerComment1", baseClass="sk.vracon.sqlcomments.maven.export.SimpleAllOk")
            select * from users
            where id = :placeholder 
               and name like 'John %'  -- Condition only if //@ x < 3
            order by name
