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

import java.util.LinkedList;
import java.util.List;

public class InsertContext extends AbstractStatementContext {

    List<ColumnIdentifier> columnIdentifiers = new LinkedList<ColumnIdentifier>();

    public List<ColumnIdentifier> getColumnIdentifiers() {
        return columnIdentifiers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InsertContext [columnIdentifiers=");
        builder.append(columnIdentifiers);
        builder.append(", getTables()=");
        builder.append(getTables());
        builder.append("]");
        return builder.toString();
    }

}
