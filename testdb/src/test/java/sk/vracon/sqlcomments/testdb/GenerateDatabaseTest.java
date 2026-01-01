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
package sk.vracon.sqlcomments.testdb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.FileSystemUtils;

public class GenerateDatabaseTest {

    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DB_PASSWORD = "";
    private static final String DB_USERNAME = "sa";
    private static final String DB_URL = "jdbc:hsqldb:file:target/classes/testdb";

    @Test
    public void generateDatabase() throws Exception {
        // Delete old database if exists
    	FileSystemUtils.deleteRecursively(new File("target/clasess/testdb.tmp"));
        new File("target/classes/testdb.lobs").delete();
        new File("target/classes/testdb.log").delete();
        new File("target/classes/testdb.properties").delete();
        new File("target/classes/testdb.script").delete();

        // Load the HSQL Database Engine JDBC driver
        // hsqldb.jar should be in the class path or made part of the current
        // jar
        Class.forName(JDBC_DRIVER);

        // Connect to the database.
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        
        System.out.println(connection.getMetaData().getDatabaseProductName());

        // Create database
        ScriptUtils.executeSqlScript(connection, new ClassPathResource("/employees-test-database/scripts/Employees - HSQLDB.sql"));

        // Close connection
        connection.close();
    }
}
