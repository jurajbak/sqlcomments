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

import java.util.Set;

import javax.script.CompiledScript;

/**
 * POJO class containing single parsed line of a SQL statement.
 */
@SuppressWarnings("restriction")
public class RowInfo {

    private String line;
    private String scriptText;
    private Set<String> replacementParameters;
    private Set<String> sqlParameters;
    private CompiledScript script;

    /**
     * Returns SQL line part of a statement.
     * <p>
     * SQL part of line without comments (and control script).
     * </p>
     * 
     * @return SQL part of a line.
     */
    public String getLine() {
        return line;
    }

    /**
     * Sets SQL row part of statement.
     * 
     * @param line
     *            SQL part of a line.
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * Returns control script as a text.
     * 
     * @return control script as a text or null if there's no control script.
     */
    public String getScriptText() {
        return scriptText;
    }

    /**
     * Sets control script as a text.
     * 
     * @param scriptText
     *            control script as a text
     */
    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    /**
     * Returns compiled control script.
     * 
     * @return compiled control script or null if there's no script at row.
     */
    public CompiledScript getScript() {
        return script;
    }

    /**
     * Sets compiled control script.
     * 
     * @param script
     *            compiled control script.
     */
    public void setScript(CompiledScript script) {
        this.script = script;
    }

    /**
     * Returns set of replacement parameters at this row.
     * 
     * @return set of replacement parameters at this row or null if there are no replacements.
     */
    public Set<String> getReplacementParameters() {
        return replacementParameters;
    }

    /**
     * Sets replacement parameters at this row.
     * 
     * @param replacementParameters
     *            replacement parameters at this row
     */
    public void setReplacementParameters(Set<String> replacementParameters) {
        this.replacementParameters = replacementParameters;
    }

    /**
     * Returns set of SQL parameters at this row.
     * 
     * @return set of SQL parameters at this row or null if there are no parameters.
     */
    public Set<String> getSqlParameters() {
        return sqlParameters;
    }

    /**
     * Returns set of SQL parameters at this row.
     * 
     * @param sqlParameters
     *            SQL parameters at this row
     */
    public void setSqlParameters(Set<String> sqlParameters) {
        this.sqlParameters = sqlParameters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RowInfo [line=");
        builder.append(line);
        builder.append(", scriptText=");
        builder.append(scriptText);
        builder.append(", replacementParameters=");
        builder.append(replacementParameters);
        builder.append(", sqlParameters=");
        builder.append(sqlParameters);
        builder.append(", script=");
        builder.append(script);
        builder.append("]");
        return builder.toString();
    }
}
