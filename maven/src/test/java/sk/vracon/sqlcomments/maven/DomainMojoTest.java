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

import java.io.File;
import java.util.HashMap;

import org.apache.maven.project.MavenProject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DomainMojoTest extends AbstractMojoTest {

    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DB_PASSWORD = "";
    private static final String DB_USERNAME = "sa";
    private static final String DB_URL = "jdbc:hsqldb:res:testdb";

    private static final String TARGET_DIR = "target/generated-test/sk/vracon/sqlcomments/maven/domain/";
    private static final String SOURCE_DIR = "src/test/resources/sk/vracon/sqlcomments/maven/domain/";

    @Test
    @SuppressWarnings("serial")
    public void test1_GenerateDomain() throws Exception {
        DomainMojo mojo = new DomainMojo();

        mojo.project = new MavenProject();
        mojo.outputDirectory = new File("target/generated-test");
        mojo.packageName = "sk.vracon.sqlcomments.maven.domain";
        mojo.tables = new HashMap<String, String>() {
            {
                put("Users", null);
                put("Companies", DomainMojo.TABLE_PROP_PK_GENERATOR + "=null\n" + DomainMojo.TABLE_PROP_PK_GENERATOR + ".postgresql=nextval('companies_seq')");
                put("Documents", DomainMojo.TABLE_PROP_CLASS_NAME + "=sk.vracon.sqlcomments.maven.domain.Document\n" + DomainMojo.TABLE_PROP_INTERFACES
                        + "=sk.vracon.sqlcomments.maven.IDomain\n" + DomainMojo.TABLE_PROP_PK_GENERATOR + ".postgresql=nextval('documents_seq')");
            }
        };
        mojo.mappingFiles = new String[] {"**/domain/sqlcomments.xml"};
        mojo.jdbcDriverClass = JDBC_DRIVER;
        mojo.databaseUrl = DB_URL;
        mojo.dbUserName = DB_USERNAME;
        mojo.dbPassword = DB_PASSWORD;

        mojo.execute();
    }

    @Test
    public void testUsers() throws Exception {
        compareAllFiles("Users");
    }

    @Test
    public void testCompanies() throws Exception {
        compareAllFiles("Companies");
        compareFiles(SOURCE_DIR, TARGET_DIR, "Companies.insert.postgresql.sql");
    }

    @Test
    public void testDocuments() throws Exception {
        compareAllFiles("Document");
        compareFiles(SOURCE_DIR, TARGET_DIR, "Document.insert.postgresql.sql");
    }

    private void compareAllFiles(String domainName) throws Exception {
        compareFiles(SOURCE_DIR, TARGET_DIR, domainName + ".insert.sql");
        compareFiles(SOURCE_DIR, TARGET_DIR, domainName + ".update.sql");
        compareFiles(SOURCE_DIR, TARGET_DIR, domainName + ".delete.sql");
        compareFiles(SOURCE_DIR, TARGET_DIR, domainName + ".findByPK.sql");
        compareFiles(SOURCE_DIR, TARGET_DIR, domainName + ".java");
        compareFiles(SOURCE_DIR, TARGET_DIR, "sqlcomments/" + domainName + "Mapper.java");
        compareFiles(SOURCE_DIR, TARGET_DIR, "sqlcomments/" + domainName + "Config.java");
        compareFiles(SOURCE_DIR, TARGET_DIR, "sqlcomments/" + domainName + "PKConfig.java");
    }
}
