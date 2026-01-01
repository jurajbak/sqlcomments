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

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;

public class AbstractMojoTest {

    protected void compareFiles(String sourceDir, String targetDir, String file) throws IOException {
        // Must be created canonical files otherwise exists() will report false
		File expectedFile = new File(sourceDir + (sourceDir.endsWith("/") ? "" : "/") + file);
		File generatedFile = new File(targetDir + (targetDir.endsWith("/") ? "" : "/") + file);

        Assert.assertTrue("Expected file does not exists: " + expectedFile.getAbsolutePath(), expectedFile.exists());
        Assert.assertTrue("Generated file does not exists: " + generatedFile.getAbsolutePath(), generatedFile.exists());

        boolean contentEquals = FileUtils.contentEquals(expectedFile, generatedFile);

        Assert.assertTrue("Content of files does not match: " + file, contentEquals);
    }

}
