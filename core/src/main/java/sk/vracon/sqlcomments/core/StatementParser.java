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

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.regex.Matcher;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 * Statement parser.
 */
public class StatementParser {

    /**
     * Parses statement.
     * <p>
     * Script engine must implement Compilable interface.
     * </p>
     * 
     * @param statementText
     *            statement body
     * @param engine
     *            script engine used for row control scripts
     * @return parsed statement
     */
    public Statement parseStatement(String statementText, Compilable engine) {
        // Check input
        if (statementText == null) {
            throw new IllegalArgumentException("No statement to parse defined.");
        }
        if (engine == null) {
            throw new IllegalArgumentException("No script engine defined.");
        }

        // Parse text
        try {
            StringBuilder errors = new StringBuilder();

            Statement statement = new Statement();

            LineNumberReader reader = new LineNumberReader(new StringReader(statementText));
            for (String line = null; (line = reader.readLine()) != null;) {
                // For each line create new RowInfo
                RowInfo row = new RowInfo();
                statement.getRows().add(row);

                // Compile script
                // TODO: This is not 100% correct - -- could be in string
                int commentIndex = line.indexOf("--");
                if (commentIndex > -1) {
                    // There's a comment which is a script
                    row.setLine(line.substring(0, commentIndex));

                    int scriptIndex = line.indexOf(Constants.LINE_SCRIPT, commentIndex);
                    if (scriptIndex > -1) {
                        String script = line.substring(scriptIndex + 3);
                        row.setScriptText(script);

                        try {
                            CompiledScript compiledScript = engine.compile(script);
                            row.setScript(compiledScript);
                        }
                        catch (ScriptException e) {
                            errors.append("\n\tLine " + reader.getLineNumber() + ": Compilation failed: " + e.getMessage());
                        }
                    }
                } else {
                    // No comment - no script
                    row.setLine(line);
                }

                // Check if there are some replacements
                Matcher matcher = Constants.REPLACEMENT_PATTERN.matcher(row.getLine());
                while (matcher.find()) {
                    if (row.getReplacementParameters() == null) {
                        row.setReplacementParameters(new HashSet<String>());
                    }

                    row.getReplacementParameters().add(matcher.group(1));
                }

                // Extract SQL parameters
                matcher = Constants.SQL_PARAM_PATTERN.matcher(row.getLine());
                while (matcher.find()) {
                    if (row.getSqlParameters() == null) {
                        row.setSqlParameters(new HashSet<String>());
                    }

                    row.getSqlParameters().add(matcher.group(1));
                }
            }

            // Check if there are errors
            if (errors.length() > 0) {
                throw new IllegalArgumentException("Parsing failed: " + errors.toString());
            }

            return statement;
        }
        catch (IOException e) {
            // Should never happen - StringReader doesn't throw exception
            throw new IllegalStateException(e);
        }
    }
}
