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
package sk.vracon.sqlcomments.maven.generate;

import java.util.HashSet;
import java.util.Set;

public class ColumnInfo {

    private String columnName;
    private String javaIdentifier;
    private Class<?> javaClass;
    private boolean asterix;
    private Set<ColumnIdentifier> references = new HashSet<ColumnIdentifier>();
    private Set<AbstractStatementContext> subqueries = new HashSet<AbstractStatementContext>();

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String alias) {
        this.columnName = alias;
    }

    public String getJavaIdentifier() {
        return javaIdentifier;
    }

    public void setJavaIdentifier(String javaIdentifier) {
        this.javaIdentifier = javaIdentifier;
    }

    public Set<AbstractStatementContext> getSubqueries() {
        return subqueries;
    }

    public boolean isAsterix() {
        return asterix;
    }

    public void setAsterix(boolean asterix) {
        this.asterix = asterix;
    }

    public Set<ColumnIdentifier> getReferences() {
        return references;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ColumnInfo [columnName=");
        builder.append(columnName);
        builder.append(", javaIdentifier=");
        builder.append(javaIdentifier);
        builder.append(", javaClass=");
        builder.append(javaClass);
        builder.append(", asterix=");
        builder.append(asterix);
        builder.append(", references=");
        builder.append(references);
        builder.append(", subqueries=");
        builder.append(subqueries);
        builder.append("]");
        return builder.toString();
    }
}
