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

public class DBColumnDefinition {

    private String tableName;
    private String columnName;
    private int sqlType;
    private String sqlTypeName;
    private Integer columnSize;
    private int decimalDigits;
    private boolean nullable;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlTypeName() {
        return sqlTypeName;
    }

    public void setSqlTypeName(String sqlTypeName) {
        this.sqlTypeName = sqlTypeName;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DBColumnDefinition [tableName=");
        builder.append(tableName);
        builder.append(", columnName=");
        builder.append(columnName);
        builder.append(", sqlType=");
        builder.append(sqlType);
        builder.append(", sqlTypeName=");
        builder.append(sqlTypeName);
        builder.append(", columnSize=");
        builder.append(columnSize);
        builder.append(", decimalDigits=");
        builder.append(decimalDigits);
        builder.append(", nullable=");
        builder.append(nullable);
        builder.append("]");
        return builder.toString();
    }

}
