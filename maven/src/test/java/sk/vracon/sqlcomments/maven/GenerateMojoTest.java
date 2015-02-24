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
import java.io.IOException;
import java.util.HashMap;

import org.apache.maven.project.MavenProject;
import org.junit.Test;

public class GenerateMojoTest extends AbstractMojoTest {

    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DB_PASSWORD = "";
    private static final String DB_USERNAME = "sa";
    private static final String DB_URL = "jdbc:hsqldb:res:testdb";
    private static final String SQL_DIR = "sk/vracon/sqlcomments/maven/generate/";

    private static final String TARGET_DIR = "target/generated-test/sk/vracon/sqlcomments/maven/generate/";
    private static final String SOURCE_DIR = "src/test/resources/sk/vracon/sqlcomments/maven/generate/";

    @Test
    public void testSimpleSelect() throws Exception {
        GenerateMojo mojo = createMojo("simpleSelect.sql");

        mojo.execute();

        compareAllJavaFiles("SimpleSelect");
    }

    @Test
    public void testSelectWithPlaceholder() throws Exception {
        GenerateMojo mojo = createMojo("selectWithPlaceholder.sql");

        mojo.execute();

        compareAllJavaFiles("SelectWithPlaceholder");
    }

    @Test
    public void testMappedPlaceholder() throws Exception {
        GenerateMojo mojo = createMojo("mappedPlaceholder.sql");

        mojo.execute();

        compareAllJavaFiles("MappedPlaceholder");
    }

    @Test
    public void testPlaceholderInsideInClause() throws Exception {
        GenerateMojo mojo = createMojo("placeholderInsideInClause.sql");

        mojo.execute();

        compareAllJavaFiles("PlaceholderInsideInClause");
    }

    @Test
    public void testReplacementParameter() throws Exception {
        GenerateMojo mojo = createMojo("replacementParameter.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/ReplacementParameterConfig");
    }

    @Test
    public void testSimpleAsterix() throws Exception {
        GenerateMojo mojo = createMojo("simpleAsterix.sql");

        mojo.execute();

        compareAllJavaFiles("SimpleAsterix");
    }

    @Test
    public void testMultipleAsterixes() throws Exception {
        GenerateMojo mojo = createMojo("multipleAsterixes.sql");

        mojo.execute();

        compareAllJavaFiles("MultipleAsterixes");
    }

    @Test
    public void testFullFeatured1() throws Exception {
        GenerateMojo mojo = createMojo("fullFeatured1.sql");

        mojo.execute();

        compareAllJavaFiles("FullFeatured1");
    }

    @Test
    public void testBlobMappings() throws Exception {
        GenerateMojo mojo = createMojo("blobMappings.sql");

        mojo.execute();

        compareAllJavaFiles("BlobMappings");
    }

    @Test
    public void testSimpleUpdate() throws Exception {
        GenerateMojo mojo = createMojo("simpleUpdate.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/SimpleUpdateConfig");
    }

    @Test
    public void testSimpleInsert() throws Exception {
        GenerateMojo mojo = createMojo("simpleInsert.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/SimpleInsertConfig");
    }

    @Test
    public void testSimpleDelete() throws Exception {
        GenerateMojo mojo = createMojo("simpleDelete.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/SimpleDeleteConfig");
    }

    @Test
    public void testDefaultConfigClass() throws Exception {
        GenerateMojo mojo = createMojo("defaultConfig.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/DefaultConfigConfig");
    }

    @Test
    public void testDefaultResultClass() throws Exception {
        GenerateMojo mojo = createMojo("defaultResult.sql");

        mojo.execute();

        compareJavaFiles("DefaultResult");
        compareJavaFiles("sqlcomments/DefaultResultMapper");
    }
    
    @Test
    public void testDeleteWithInnerSelect() throws Exception {
        GenerateMojo mojo = createMojo("deleteWithInnerSelect.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/DeleteWithInnerSelectConfig");
    }
    
    @Test
    public void testUpdateInSelect() throws Exception {
        GenerateMojo mojo = createMojo("updateInSelect.sql");

        mojo.execute();

        compareJavaFiles("sqlcomments/UpdateInSelectConfig");
    }
    
    @SuppressWarnings("serial")
    private GenerateMojo createMojo(String sqlFile) {
        GenerateMojo mojo = new GenerateMojo();

        mojo.project = new MavenProject();
        mojo.sourceDirectory = new File("src/test/resources");
        mojo.outputDirectory = new File("target/generated-test");
        mojo.includes = new String[] {SQL_DIR + sqlFile};
        mojo.jdbcDriverClass = JDBC_DRIVER;
        mojo.databaseUrl = DB_URL;
        mojo.dbUserName = DB_USERNAME;
        mojo.dbPassword = DB_PASSWORD;

        mojo.tables = new HashMap<String, String>() {
            {
                put("Users", null);
                put("Companies", "country" + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_JAVA_CLASS + "=sk.vracon.sqlcomments.maven.ExampleEnum\ncountry"
                        + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_MAPPER + "=sk.vracon.sqlcomments.core.mappers.EnumMapper");
                put("Documents", AbstractSqlCommentsMojo.TABLE_PROP_CLASS_NAME + "=Documents");
            }
        };

        return mojo;
    }

    protected void compareAllJavaFiles(String fileName) throws IOException {
        compareJavaFiles(fileName);
        compareJavaFiles("sqlcomments/" + fileName + "Mapper");
        compareJavaFiles("sqlcomments/" + fileName + "Config");
    }

    protected void compareJavaFiles(String fileName) throws IOException {
        compareFiles(SOURCE_DIR, TARGET_DIR, fileName + ".java");
    }
}
