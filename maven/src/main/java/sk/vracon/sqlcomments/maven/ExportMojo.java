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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultMavenProjectHelper;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

import sk.vracon.sqlcomments.core.Constants;
import sk.vracon.sqlcomments.maven.java.Java8Lexer;

@Mojo(name = "export", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresProject = true)
public class ExportMojo extends AbstractMojo {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    private static final String[] DEFAULT_INCLUDES = {"**/*.java"};

    /**
     * The directory which contains the sources you want to be parsed.
     * 
     * The default value is ${project.build.sourceDirectory}.
     */
    @Parameter(defaultValue = "${project.build.sourceDirectory}")
    protected File sourceDirectory;

    /**
     * The output directory where to generate files and java classes.
     * 
     * The default value is ${project.build.directory}/generated-sources/sqlcomments.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/sqlcomments")
    protected File outputDirectory;

    /**
     * A list of files to include. Can contain ant-style wildcards and double wildcards.
     * 
     * The default includes are <code>**&#47;*.java</code>
     */
    @Parameter(required = false)
    protected String[] includes;

    /**
     * A list of files to exclude. Can contain ant-style wildcards and double wildcards.
     */
    @Parameter(required = false)
    protected String[] excludes;

    /**
     * Indicates whether to use standard or test class path for compilation of generated classes.
     * 
     * The default value is false - using standard class path.
     */
    @Parameter(required = false)
    protected boolean compileWithTestClasses = false;

    /**
     * The current Maven project.
     */
    @Parameter(property = "project", required = true, readonly = true)
    protected MavenProject project;

    /**
     * Internal state indicates if execution had errors and should be stopped.
     */
    private boolean hasErrors = false;

    public void execute() throws MojoExecutionException, MojoFailureException {
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

        scanner.addDefaultExcludes();
        scanner.scan();

        String[] includedFiles = scanner.getIncludedFiles();
        for (int j = 0; j < includedFiles.length; j++) {
            String resource = includedFiles[j];

            processFile(resource);
        }

        if (hasErrors) {
            throw new MojoFailureException("Processing SQL comments failed.");
        }

        // Tell Maven that there are some new source files underneath the output directory.
        if (compileWithTestClasses) {
            new DefaultMavenProjectHelper().addTestResource(project, outputDirectory.getPath(), Collections.singletonList("**/*.sql"), null);
        } else {
            new DefaultMavenProjectHelper().addResource(project, outputDirectory.getPath(), Collections.singletonList("**/*.sql"), null);
        }
    }

    private void processFile(String resource) {

        FileInputStream input = null;
        File file = new File(sourceDirectory, resource);
        getLog().debug("Processing file: " + file.getAbsolutePath());

        try {
            // Open file
            input = new FileInputStream(file);

            // Create Java lexer and token stream
            Java8Lexer lexer = new Java8Lexer(new ANTLRInputStream(input));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            // Force token stream to read input - do not remove!
            tokenStream.getNumberOfOnChannelTokens();
            // Filter comment tokens from others
            List<Token> comments = new LinkedList<Token>();
            List<Token> tokens = tokenStream.getTokens();
            for (Token token : tokens) {
                if (token.getChannel() == Token.HIDDEN_CHANNEL) {
                    comments.add(token);
                }
            }

            // Extract statements from comments
            for (Token commentToken : comments) {
                processComment(resource, commentToken);
            }
        }
        catch (FileNotFoundException e) {
            getLog().error("Unable to open file: " + file.getAbsolutePath(), e);
            hasErrors = true;
        }
        catch (IOException e) {
            getLog().error("Unable to read file: " + file.getAbsolutePath(), e);
            hasErrors = true;
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {}
            }
        }
    }

    private void processComment(String fileName, Token comment) throws IOException {
        // Extract declarations and SQL statements
        StatementDeclaration declaration = null;
        StringBuilder statementText = null;
        LineNumberReader reader = new LineNumberReader(new StringReader(comment.getText()));
        for (String line = null; (line = reader.readLine()) != null;) {
            if (line.contains(Constants.SQLCOMMENT)) {
                // Parse declaration
                int declarationLineNumber = comment.getLine() + reader.getLineNumber() - 1;
                try {
                    declaration = StatementDeclarationParser.parseStatementDeclaration(line.substring(line.indexOf(Constants.SQLCOMMENT)));

                    String className = fileName;
                    int appendixPos = fileName.lastIndexOf('.');
                    if (appendixPos > 0) {
                        className = className.substring(0, appendixPos);
                    }
                    className = className.replace(File.separatorChar, '.');
                    declaration.setBaseClassName(className);
                    declaration.setDeclarationLineNumber(declarationLineNumber);

                    statementText = new StringBuilder();
                }
                catch (SyntaxErrorException e) {
                    getLog().error(fileName + ":" + declarationLineNumber + " Syntax error in @SQLComment declaration: " + e.getMessage());
                    hasErrors = true;
                }
                catch (IOException e) {
                    getLog().error(fileName + ":" + declarationLineNumber + " Error while reading declaration: " + e.getMessage());
                    hasErrors = true;
                }
            } else if (declaration != null) {
                String cleanLine = line.trim();
                if (cleanLine.length() == 0 || "*".equals(cleanLine) || "*/".equals(cleanLine)) {
                    // End of statement
                    declaration.setStatementText(statementText.toString());

                    writeStatementFile(fileName, declaration);

                    declaration = null;
                } else {
                    // Part of SQL statement
                    statementText.append(line);
                    statementText.append(LINE_SEPARATOR);
                }
            }
        }
        if (declaration != null) {
            declaration.setStatementText(statementText.toString());
        }
    }

    private void writeStatementFile(String inputFileName, StatementDeclaration declaration) throws IOException {
        // Construct SQL file name
        StringBuilder sqlFileName = new StringBuilder();
        int suffixPos = inputFileName.lastIndexOf('.');
        if (suffixPos > -1) {
            sqlFileName.append(inputFileName.substring(0, suffixPos));
        } else {
            sqlFileName.append(inputFileName);
        }
        sqlFileName.append(".");
        sqlFileName.append(declaration.getName());
        if(declaration.getDatabase() != null) {
            sqlFileName.append(".");
            sqlFileName.append(declaration.getDatabase());
        }
        sqlFileName.append(".sql");
        
        getLog().info("Generating file: " + sqlFileName);

        // Write file
        File sqlFile = new File(outputDirectory, sqlFileName.toString());
        FileWriter fw = null;
        try {
            // Create missing directories
            sqlFile.getParentFile().mkdirs();

            // Open file
            fw = new FileWriter(sqlFile);

            // Write file info
            fw.write("-- Generated from ");
            fw.write(declaration.getBaseClassName());
            fw.write(":");
            fw.write(Integer.toString(declaration.getDeclarationLineNumber()));
            fw.write(LINE_SEPARATOR);

            // Write declaration
            fw.write("-- ");
            fw.write(Constants.SQLCOMMENT);
            fw.write("(");
            boolean addComma = writeParameter(fw, Constants.PARAM_NAME, declaration.getName(), false, false);
            addComma = writeParameter(fw, Constants.PARAM_BASECLASS, declaration.getBaseClassName(), false, addComma);
            addComma = writeParameter(fw, Constants.PARAM_RESULTCLASS, declaration.getResultClassName(), declaration.isDefaultResultClass(), addComma);
            addComma = writeParameter(fw, Constants.PARAM_CONFIGCLASS, declaration.getConfigurationClassName(), declaration.isDefaultConfigurationClass(), addComma);
            writeParameter(fw, Constants.PARAM_DATABASE, declaration.getDatabase(), false, addComma);
            fw.write(")" + LINE_SEPARATOR);

            // Write SQL statement
            fw.write(declaration.getStatementText());
        }
        catch (IOException e) {
            getLog().error(sqlFile.getAbsolutePath() + " Error while writing file: " + e.getMessage());
            hasErrors = true;
        }
        finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                }
                catch (IOException e) {
                    getLog().warn("Error while closing file: " + e.getMessage(), e);
                }
            }
        }
    }

    private boolean writeParameter(FileWriter fw, String paramName, String value, boolean useDefault, boolean addComma) throws IOException {
        if (value != null && value.trim().length() > 0) {
            if (addComma) {
                fw.write(", ");
            }
            fw.write(paramName);
            fw.write("=\"");
            fw.write(value);
            fw.write("\"");

            return true;
        } else if (useDefault) {
            if (addComma) {
                fw.write(", ");
            }
            fw.write(paramName);
            fw.write("=default");
        }
        return addComma;
    }
}
