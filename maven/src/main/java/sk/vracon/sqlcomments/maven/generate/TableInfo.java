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

public class TableInfo {

    private String name;
    private String alias;
    private Set<SelectContext> subqueries = new HashSet<SelectContext>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Set<SelectContext> getSubqueries() {
        return subqueries;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TableInfo [name=");
        builder.append(name);
        builder.append(", alias=");
        builder.append(alias);
        builder.append(", subqueries=");
        builder.append(subqueries);
        builder.append("]");
        return builder.toString();
    }
}
