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

import java.util.ArrayList;
import java.util.List;

/**
 * SQL statement representation.
 * <p>
 * Statement consists from a list of {@link RowInfo rows} and is identified by name. Statement name is a full path of
 * sql file without file extension.
 * </p>
 */
public class Statement {

    private String name;
    private List<RowInfo> rows = new ArrayList<RowInfo>();

    /**
     * Gets statement name.
     * 
     * @return statement name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets statement name
     * 
     * @param name
     *            statement name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets list of rows in statement.
     * 
     * @return list of rows in statement
     */
    public List<RowInfo> getRows() {
        return rows;
    }

    /**
     * Sets list of rows in statement.
     * 
     * @param rows
     *            rows in statement
     */
    public void setRows(List<RowInfo> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SQLStatement [name=");
        builder.append(name);
        builder.append(", rows=");
        builder.append(rows);
        builder.append("]");
        return builder.toString();
    }
}
