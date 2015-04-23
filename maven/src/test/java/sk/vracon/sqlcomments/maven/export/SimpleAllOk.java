/*
 * Copyright 2014 Vracon s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.vracon.sqlcomments.maven.export;

/**
 * Simple class with all correct selects.
 * 
 */
/*-
 @SQLComment(name="simpleClassComment1")
 select * from users

 This is some other statement
 @SQLComment(name="multilineClassComment2")
 select * from users
 where id = :placeholder
 order by name
 */
public class SimpleAllOk {

    /**
     * Javadoc comment.
     */
    /*-
         @SQLComment(name="methodComment1")
         select * from users
         where id = :placeholder
         order by name

         @SQLComment(name="methodComment2", resultClass="sk.vracon.MethodComment2", configClass="sk.vracon.MethodComment2Config")
         select * from users 
         where id = :placeholder
         order by name
     */
    public void doSomething() {
        /*-
            @SQLComment(name="innerComment1")
            select * from users
            where id = :placeholder 
               and name like 'John %'  -- Condition only if //@ x < 3
            order by name

            @SQLComment(name="innerComment2", resultClass="sk.vracon.InnerComment2", configClass="sk.vracon.InnerComment2Config")
            select * from users 
            where id = :placeholder
            order by name

            @SQLComment(name="innerComment2", database="PostgreSQL")
            select * from users 
            where id = :placeholder
            order by name
         */

        // Some java code
        System.out.println("Hello world");
    }

    public void defaultClasses() {
        /*-
            @SQLComment(name="defaultResult", resultClass=default)
            select * from users 

            @SQLComment(name="defaultConfig", configClass=default)
            select * from users 

         */
    }
}
