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

import sk.vracon.sqlcomments.core.Type;

/**
 * Represents table column identifier in statement.
 */
public class TableColumnIdentifier {

    private String tableAlias;
    private String columnName;
    private Type<?> type;

    public TableColumnIdentifier() {
    }

    public TableColumnIdentifier(String tableAlias, String columnName) {
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

    public Type<?> getType() {
        return type;
    }

    public void setType(Type<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ColumnIdentifier [tableAlias=");
        builder.append(tableAlias);
        builder.append(", columnName=");
        builder.append(columnName);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }

}
