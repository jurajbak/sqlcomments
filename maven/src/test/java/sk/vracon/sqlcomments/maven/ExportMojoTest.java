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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.project.MavenProject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import sk.vracon.sqlcomments.maven.export.SimpleAllOk;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExportMojoTest extends AbstractMojoTest {

    private static final String TARGET_DIR = "target/generated-test/sk/vracon/sqlcomments/maven/export/";
    private static final String SOURCE_DIR = "src/test/resources/sk/vracon/sqlcomments/maven/export/";

    @Test
    public void test1_Export() throws MojoExecutionException, MojoFailureException {
        ExportMojo mojo = new ExportMojo();
        mojo.setLog(new SystemStreamLog());
        mojo.sourceDirectory = new File("src/test/java");
        mojo.outputDirectory = new File("target/generated-test");
        mojo.includes = new String[] {SimpleAllOk.class.getPackage().getName().replace('.', '/') + "/*.java"};
        mojo.project = new MavenProject();
        mojo.compileWithTestClasses = true;

        mojo.execute();
    }

    @Test
    public void testSimpleClassComment() throws IOException {
        compareFiles("SimpleAllOk.simpleClassComment1.sql");
    }

    @Test
    public void testMultilineClassComment2() throws IOException {
        compareFiles("SimpleAllOk.multilineClassComment2.sql");
    }

    @Test
    public void testMethodComment1() throws IOException {
        compareFiles("SimpleAllOk.methodComment1.sql");
    }

    @Test
    public void testMethodComment2() throws IOException {
        compareFiles("SimpleAllOk.methodComment2.sql");
    }

    @Test
    public void testInnerComment1() throws IOException {
        compareFiles("SimpleAllOk.innerComment1.sql");
    }

    @Test
    public void testInnerComment2() throws IOException {
        compareFiles("SimpleAllOk.innerComment2.sql");
        compareFiles("SimpleAllOk.innerComment2.postgresql.sql");
    }

    @Test
    public void testDefaultResultClass() throws IOException {
        compareFiles("SimpleAllOk.defaultResult.sql");
    }

    @Test
    public void testDefaultConfig() throws IOException {
        compareFiles("SimpleAllOk.defaultConfig.sql");
    }

    protected void compareFiles(String file) throws IOException {
        super.compareFiles(SOURCE_DIR, TARGET_DIR, file);
    }
}
