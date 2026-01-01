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
package sk.vracon.sqlcomments.maven;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;

/**
 * Container class to handle custom type and its init parameters.
 * 
 * @param <T> custom type
 */
public class CustomTypeWraper<T> implements Type<T> {

	private Type<T> parent;
	private Object[] params;

	public CustomTypeWraper(Type<T> parent, Object[] params) {
		this.parent = parent;
		this.params = params;
	}
	
	/**
	 * Returns wrapped instance.
	 * 
	 * @return wrapped instance.
	 */
	public Type<T> getParent() {
		return parent;
	}
	
	public Object[] getParams() {
		return params;
	}

	public int[] getSQLTypes() {
		return parent.getSQLTypes();
	}

	public Class<T> getJavaClass() {
		return parent.getJavaClass();
	}

	public T readValue(ResultSet resultSet, String columnName) throws SQLException {
		return parent.readValue(resultSet, columnName);
	}

	public void writeValue(PreparedStatement statement, int columnIndex, T value) throws SQLException {
		parent.writeValue(statement, columnIndex, value);
	}

	public void validateColumnMetadata(DBColumnMetadata metadata) throws SQLException {
		parent.validateColumnMetadata(metadata);
	}

	public int getTypeOrderIndex() {
		return parent.getTypeOrderIndex();
	}
}
