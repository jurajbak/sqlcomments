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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.DirectoryScanner;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, requiresProject = true)
public class GenerateMojo extends AbstractSqlCommentsMojo {

    private static final String[] DEFAULT_INCLUDES = {"**/*.sql"};

    /**
     * The directory which contains the *.sql files you want to be parsed.
     * 
     * The default value is ${project.build.directory}/generated-sources/sqlcomments
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/sqlcomments")
    protected File sourceDirectory;

    /**
     * A list of files to include. Can contain ant-style wildcards and double wildcards.
     * 
     * The default includes are <code>**&#47;*.sql</code>
     */
    @Parameter(required = false)
    protected String[] includes;

    /**
     * A list of files to exclude. Can contain ant-style wildcards and double wildcards.
     */
    @Parameter(required = false)
    protected String[] excludes;

    public void execute() throws MojoExecutionException, MojoFailureException {
        // Do generic stuff first
        super.execute();

        if (!sourceDirectory.exists()) {
            getLog().warn("Source directory doesn't exists: " + sourceDirectory.getAbsolutePath());
            return;
        }

        // Load database metadata
        if (databaseColumns == null) {
            loadDatabaseMetadata();
        }

        // Find files to be processed
        DirectoryScanner scanner = new DirectoryScanner();

        scanner.setBasedir(sourceDirectory);
        if (includes != null && includes.length != 0) {
            scanner.setIncludes(includes);
        } else {
            scanner.setIncludes(DEFAULT_INCLUDES);
        }

        if (excludes != null && excludes.length != 0) {
            scanner.setExcludes(excludes);
        }

        scanner.scan();

        // Process files
        String[] includedFiles = scanner.getIncludedFiles();
        for (int j = 0; j < includedFiles.length; j++) {
            String resource = includedFiles[j];

            processFile(sourceDirectory, resource);
        }

        if (hasErrors) {
            throw new MojoExecutionException("Processing SQL comments failed.");
        }

        // Tell Maven that there are some new source files underneath the output directory.
        if (compileWithTestClasses) {
            project.addTestCompileSourceRoot(outputDirectory.getPath());
        } else {
            project.addCompileSourceRoot(outputDirectory.getPath());
        }
    }
}
