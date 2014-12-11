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

public class ColumnIdentifier {

    private String tableAlias;
    private String columnName;
    private Class<?> javaType;

    public ColumnIdentifier() {
    }

    public ColumnIdentifier(String tableAlias, String columnName) {
        super();
        this.tableAlias = tableAlias;
        this.columnName = columnName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ColumnIdentifier [tableAlias=");
        builder.append(tableAlias);
        builder.append(", columnName=");
        builder.append(columnName);
        builder.append(", javaType=");
        builder.append(javaType);
        builder.append("]");
        return builder.toString();
    }

}
