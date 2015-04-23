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
package sk.vracon.sqlcomments.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.script.Compilable;
import javax.script.ScriptEngineManager;

import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;

/**
 * Statement container implements loading and caching of statements.
 * 
 * <p>
 * Statement container is not mandatory, but it has some useful features like loading, caching and takes care of
 * compiling control scripts.
 * </p>
 * <p>
 * Statement files are loaded from classpath in order:
 * <ol>
 * <li>Database specific statement file (if {@link #databaseProductName} is <code>null</code> this step is skipped).</li>
 * <li>Generic statement file (without database product name).</li>
 * </ol>
 * If no statement file is found, {@link IllegalArgumentException} is thrown.
 * </p>
 * <p>
 * The default script engine for control scripts is used JavaScript. All scripts are compiled before first usage. It is
 * possible to use custom script engine by using {@link #StatementContainer(String)} constructor. In this case used
 * engine must implement Compilable interface.
 * </p>
 * <p>
 * For performance reasons caching is enabled by default. To disable it call {@link #setEnableCache(boolean)}. Disabling
 * cache is useful for development.
 * </p>
 * 
 * @see StatementParser
 * @see #setEnableCache(boolean)
 */
public class StatementContainer {

    private static final String DEFAULT_SCRIPT_ENGINE = "javascript";

    private boolean enableCache = true;
    private DatabaseDialect dialect;
    private Compilable scriptEngine;
    private Map<String, Statement> statements = new HashMap<String, Statement>();

    /**
     * Creates instance of statement container with default settings.
     * <p>
     * Using javascript as a default script engine.
     */
    public StatementContainer() {
        this(DEFAULT_SCRIPT_ENGINE);
    }

    /**
     * Creates instance of statement container with specified script engine.
     * 
     * @param scriptEngineName
     *            script engine name
     * 
     * @see ScriptEngineManager
     */
    public StatementContainer(String scriptEngineName) {
        // Check input
        if (scriptEngineName == null) {
            throw new IllegalArgumentException("No script engine defined.");
        }

        // Get script engine
        scriptEngine = (Compilable) new ScriptEngineManager().getEngineByName(scriptEngineName);
        if (scriptEngine == null) {
            throw new IllegalStateException("No script engine '" + scriptEngineName + "' found.");
        }
    }

    /**
     * Creates instance of statement container with specified script engine.
     * 
     * @param scriptEngine
     *            script engine
     */
    public StatementContainer(Compilable scriptEngine) {
        // Check input
        if (scriptEngine == null) {
            throw new IllegalArgumentException("No script engine defined.");
        }

        this.scriptEngine = scriptEngine;
    }

    /**
     * Loads statement and adds it to cache.
     * 
     * @param baseClass
     *            base class (mandatory)
     * @param name
     *            statement name (mandatory)
     * @return loaded statement
     * @throws StatementNotFoundException
     *             if statement file not found
     * 
     * @see StatementConfiguration
     */
    public Statement addStatement(Class<?> baseClass, String name) throws StatementNotFoundException {
        if (baseClass == null) {
            throw new IllegalArgumentException("Base class must be must be set.");
        }
        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("Base class must be must be set.");
        }

        String fullName = constructFullName(baseClass, name);

        String text = loadStatement(baseClass.getClassLoader(), fullName);

        return addStatement(fullName, text);
    }

    /**
     * Loads statement file by given class loader.
     * 
     * @param classLoader
     *            class loader
     * @param fullName
     *            full file name
     * @return loaded file as a String
     * @throws StatementNotFoundException
     *             if statement file not found
     */
    private String loadStatement(ClassLoader classLoader, String fullName) throws StatementNotFoundException {

        InputStream input = null;

        if (dialect != null) {
            // Try to load database specific file first
            int suffixPos = fullName.lastIndexOf('.');
            if (suffixPos > -1) {
                String dbSpecificName = fullName.substring(0, suffixPos) + "." + dialect.getDatabaseProductName() + fullName.substring(suffixPos);
                input = classLoader.getResourceAsStream(dbSpecificName);
            }
        }

        if (input == null) {
            // Load generic file
            input = classLoader.getResourceAsStream(fullName);
        }

        if (input == null) {
            throw new StatementNotFoundException("Statement file found: " + fullName);
        }

        // Read input into string
        Scanner scanner = new Scanner(input).useDelimiter("\\A");
        String text = scanner.next();

        try {
            input.close();
        }
        catch (Exception e) {
            throw new IllegalStateException("Unexpected exception while closing stream: " + e.getMessage(), e);
        }

        return text;
    }

    /**
     * Constructs full statement name from base class and short name.
     * 
     * @param baseClass
     *            base class
     * @param name
     *            short name
     * @return full statement name (file path)
     */
    protected String constructFullName(Class<?> baseClass, String name) {
        return baseClass.getName().replace('.', '/') + "." + name + ".sql";
    }

    /**
     * Parses statement text and adds it to cache if cache is enabled.
     * 
     * @param statementName
     *            statement name under which it should be cached
     * @param statementText
     *            statement body
     * @return parsed statement
     */
    public Statement addStatement(String statementName, String statementText) {
        Statement statement = new StatementParser().parseStatement(statementText, scriptEngine);
        statement.setName(statementName);

        if (enableCache) {
            statements.put(statementName, statement);
        }

        return statement;
    }

    /**
     * Gets statement from cache.
     * <p>
     * Statement is lazy loaded if not found in cache.
     * </p>
     * 
     * @param baseClass
     *            statement base class
     * @param name
     *            statement simple name
     * @return statement
     */
    public Statement getStatement(Class<?> baseClass, String name) throws IllegalArgumentException {
        if (baseClass == null) {
            throw new IllegalArgumentException("Base class must be must be set.");
        }

        ClassLoader classLoader = baseClass.getClassLoader();

        String fullName = constructFullName(baseClass, name);

        return getStatement(classLoader, fullName);
    }

    /**
     * Gets statement from cache.
     * <p>
     * Statement is lazy loaded if not found in cache.
     * </p>
     * 
     * @param classLoader
     *            class loader to load statement
     * @param fullName
     *            statement full file name
     * @return statement
     * @throws StatementNotFoundException
     *             if statement file not found
     */
    public Statement getStatement(ClassLoader classLoader, String fullName) throws StatementNotFoundException {
        // Get statement from cache
        Statement statement = statements.get(fullName);

        if (statement == null) {
            // Statement is not loaded yet - try to lazy load it
            if (classLoader == null) {
                // No class loader defined.

                // Try context class loader first
                classLoader = Thread.currentThread().getContextClassLoader();

                if (classLoader == null) {
                    // Use this class loader as a last option
                    classLoader = this.getClass().getClassLoader();
                }
            }

            // Load statement file from class path
            String sql = loadStatement(classLoader, fullName);

            // Add statement to cache
            statement = addStatement(fullName, sql);
        }

        if (statement == null) {
            throw new IllegalStateException("No statement found: " + fullName);
        }

        return statement;
    }

    /**
     * Enable statement caching.
     * <p>
     * Caching is enabled by default.
     * </p>
     * 
     * @param enableCache
     *            true to enable statement caching, false to disable
     */
    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    /**
     * Sets database dialect.
     * 
     * @param dialect
     *            database dialect or <code>null</code>.
     */
    public void setDialect(DatabaseDialect dialect) {
        this.dialect = dialect;
    }
}
