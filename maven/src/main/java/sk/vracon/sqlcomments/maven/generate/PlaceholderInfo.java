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

import java.util.Set;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Variable_placeholderContext;

public class PlaceholderInfo {

	private String name;
	private boolean collection;
	private Type<?> type;
	private Variable_placeholderContext sqlContext;
	private AbstractStatementContext context;
	private Set<TableColumnIdentifier> tableColumnIdentifiers;
	private Set<DBColumnMetadata> dbColumns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getJavaClass() {
		return getType().getJavaClass();
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	public Variable_placeholderContext getSqlContext() {
		return sqlContext;
	}

	public void setSqlContext(Variable_placeholderContext sqlContext) {
		this.sqlContext = sqlContext;
	}

	public AbstractStatementContext getContext() {
		return context;
	}

	public void setSelectContext(AbstractStatementContext selectContext) {
		this.context = selectContext;
	}

	public Set<TableColumnIdentifier> getTableColumnIdentifiers() {
		return tableColumnIdentifiers;
	}

	public void setTableColumnIdentifiers(Set<TableColumnIdentifier> tableColumnIdentifiers) {
		this.tableColumnIdentifiers = tableColumnIdentifiers;
	}

	public Set<DBColumnMetadata> getDbColumns() {
		return dbColumns;
	}

	public void setDbColumns(Set<DBColumnMetadata> dbColumns) {
		this.dbColumns = dbColumns;
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
		builder.append("PlaceholderInfo [name=");
		builder.append(name);
		builder.append(", collection=");
		builder.append(collection);
		builder.append(", type=");
		builder.append(type);
		builder.append(", sqlContext=");
		builder.append(sqlContext);
		builder.append(", context=");
		builder.append(context);
		builder.append(", tableColumnIdentifiers=");
		builder.append(tableColumnIdentifiers);
		builder.append(", dbColumns=");
		builder.append(dbColumns);
		builder.append("]");
		return builder.toString();
	}
}
