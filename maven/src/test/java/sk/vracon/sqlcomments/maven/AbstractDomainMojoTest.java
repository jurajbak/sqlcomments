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

import sk.vracon.sqlcomments.core.types.EnumType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractDomainMojoTest extends AbstractMojoTest {


    private String targetDir = "target/generated-test/sk/vracon/sqlcomments/maven/domain/";
    private String sourceDir = "src/test/resources/sk/vracon/sqlcomments/maven/domain/";

    private String database;
    private String jdbcDriver;
    private String dbPassword;
    private String dbUsername;
    private String dbUrl;
    private String databaseDialect;
    
    public AbstractDomainMojoTest(String database, String databaseDialect, String jdbcDriver, String dbPassword, String dbUsername, String dbUrl) {
		super();
		this.database = database;
		this.jdbcDriver = jdbcDriver;
		this.dbPassword = dbPassword;
		this.dbUsername = dbUsername;
		this.dbUrl = dbUrl;
		this.databaseDialect = databaseDialect;
		
		sourceDir += database;
		targetDir += database;
	}

	@Test
    @SuppressWarnings("serial")
    public void test1_GenerateDomain() throws Exception {
        DomainMojo mojo = new DomainMojo();

        mojo.project = new MavenProject();
        mojo.outputDirectory = new File("target/generated-test");
		mojo.packageName = "sk.vracon.sqlcomments.maven.domain." + database;
		mojo.databaseDialect = databaseDialect;
        mojo.tables = new HashMap<String, String>() {
            {
                put("Emp", null);
				put("Dept", DomainMojo.TABLE_PROP_PK_GENERATOR + "=null\n"
						+ DomainMojo.TABLE_PROP_PK_GENERATOR + ".postgresql=nextval('companies_seq')\n"
						+ "dname"	+ AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_TYPE + "=" + EnumType.class.getName() + "(" + DepartmenTypesEnum.class.getName() + ")");
                put("Proj", DomainMojo.TABLE_PROP_CLASS_NAME + "=sk.vracon.sqlcomments.maven.domain." + database + ".Project\n"
						+ DomainMojo.TABLE_PROP_INTERFACES + "=sk.vracon.sqlcomments.maven.IDomain\n"
                		+ DomainMojo.TABLE_PROP_PK_GENERATOR + ".postgresql=nextval('documents_seq')");
                put("Managers", null);
                put("Datatypes", null);
            }
        };
        //mojo.mappingFiles = new String[] {"**/domain/sqlcomments.xml"};
        mojo.jdbcDriverClass = jdbcDriver;
        mojo.databaseUrl = dbUrl;
        mojo.dbUserName = dbUsername;
        mojo.dbPassword = dbPassword;

        mojo.execute();
    }

    @Test
    public void testEmp() throws Exception {
        compareAllFiles("Emp");
    }

    @Test
    public void testDept() throws Exception {
        compareAllFiles("Dept");
        compareFiles(sourceDir, targetDir, "Dept.insert.postgresql.sql");
    }

    @Test
    public void testProject() throws Exception {
        compareAllFiles("Project");
        compareFiles(sourceDir, targetDir, "Project.insert.postgresql.sql");
    }

    @Test
    public void testManagers() throws Exception {
    	compareFiles(sourceDir, targetDir, "Managers.java");
        compareFiles(sourceDir, targetDir, "sqlcomments/ManagersMapper.java");
        compareFiles(sourceDir, targetDir, "sqlcomments/ManagersConfig.java");
    }

    @Test
    public void testDatatypes() throws Exception {
        compareAllFiles("Datatypes");
    }

    private void compareAllFiles(String domainName) throws Exception {
        compareFiles(sourceDir, targetDir, domainName + ".insert.sql");
        compareFiles(sourceDir, targetDir, domainName + ".update.sql");
        compareFiles(sourceDir, targetDir, domainName + ".delete.sql");
        compareFiles(sourceDir, targetDir, domainName + ".findByPK.sql");
        compareFiles(sourceDir, targetDir, domainName + ".java");
        compareFiles(sourceDir, targetDir, "sqlcomments/" + domainName + "Mapper.java");
        compareFiles(sourceDir, targetDir, "sqlcomments/" + domainName + "Config.java");
        compareFiles(sourceDir, targetDir, "sqlcomments/" + domainName + "PKConfig.java");
    }
}
