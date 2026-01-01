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
package sk.vracon.sqlcomments.maven;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.oracle.OracleContainerProvider;

import sk.vracon.sqlcomments.core.dialect.OracleDialect;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DomainMojo_Oracle_Test extends AbstractDomainMojoTest {

	private static final String DATABASE = "Oracle";
	private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";

	@SuppressWarnings("rawtypes")
	private static JdbcDatabaseContainer databaseContainer;

	@BeforeClass
	public static void generateDatabase() throws Exception {

		databaseContainer = new OracleContainerProvider().newInstance();
		if (!databaseContainer.isRunning()) {
			databaseContainer.start();
		}

		// Load the JDBC driver
		Class.forName(JDBC_DRIVER);

		// Connect to the database.
		Connection connection = DriverManager.getConnection(databaseContainer.getJdbcUrl(), databaseContainer.getUsername(), databaseContainer.getPassword());

		// Create database
		ScriptUtils.executeSqlScript(connection, new ClassPathResource("/employees-test-database/scripts/Employees - Oracle.sql"));

		// Close connection
		connection.close();
	}

	public DomainMojo_Oracle_Test() {
		super(DATABASE, OracleDialect.class.getName(), JDBC_DRIVER, databaseContainer.getPassword(), databaseContainer.getUsername(), databaseContainer.getJdbcUrl());
	}
}
