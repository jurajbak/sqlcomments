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

import sk.vracon.sqlcomments.core.Type;

public class ResultColumnInfo {

	private String columnName;
	private String javaIdentifier;
	private Type<?> type;
	private boolean asterix;
	private Set<TableColumnIdentifier> references = new HashSet<TableColumnIdentifier>();
	private Set<AbstractStatementContext> subqueries = new HashSet<AbstractStatementContext>();

	public ResultColumnInfo() {
		// TODO Auto-generated constructor stub
	}

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

	public Set<TableColumnIdentifier> getReferences() {
		return references;
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
		builder.append("ResultColumnInfo [columnName=");
		builder.append(columnName);
		builder.append(", javaIdentifier=");
		builder.append(javaIdentifier);
		builder.append(", type=");
		builder.append(type);
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
