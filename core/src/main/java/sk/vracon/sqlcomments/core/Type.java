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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Defines the de/serialization of a typed Java object from a ResultSet or to a PreparedStatement
 *
 * <p>
 * Method {@link #readValue(ResultSet, String)} is used for data extraction from {@link ResultSet}. {@link #writeValue(PreparedStatement, int, Object)} is used for population.
 * <p>
 * Each custom implementation must provide static method <code>getInstance(Object... params)</code> to generate new instances. Parameters are values used in configuration during build.
 * Constructor is not called. This approach was decided due to different use cases during build and in runtime.  
 * 
 * @param <T> Java type
 */
public interface Type<T> {

	/**
	 * Get the SQL supported SQL types
	 *
	 * @return sql types
	 * @see Types
	 */
	int[] getSQLTypes();

	/**
	 * Get the Java class which represents supported SQL types.
	 *
	 * @return returned class
	 */
	Class<T> getJavaClass();

	/**
	 * Get the object from the result set
	 *
	 * @param resultSet   result set
	 * @param columnName column name in result set
	 * @return value red value converted to defined Java type 
	 * @throws SQLException when unable to read value or convert value to required Java type
	 */
	T readValue(ResultSet resultSet, String columnName) throws SQLException;

	/**
	 * Set the object to the statement
	 * <p>
	 * Method is called every time value should be set to database statement.
	 * It is responsible for correct transformation from Java type to database defined type.  
	 *
	 * @param statement   statement
	 * @param columnIndex column index in statement
	 * @param value       value to be set
	 * @throws SQLException when unable to write value
	 */
	void writeValue(PreparedStatement statement, int columnIndex, T value) throws SQLException;

	/**
	 * Validates column metadata read from database against current configuration.
	 * 
	 * @param metadata column metadata
	 * @throws SQLException if column does not fit required parameters
	 */
	default void validateColumnMetadata(DBColumnMetadata metadata) throws SQLException {
	}

	/**
	 * Gets type order index.
	 * <p>
	 * Index is used in algorithm to find a most generic {@link Type} supporting all required data types. It is crucial information when the same placeholder is used in conjunction with multiple
	 * database columns or in script.
	 * <p>
	 * Index should be positive integer value where zero means most generic {@link Object} type. Higher value means more specific data type. E.g. Object &lt; BigDecimal &lt; Double &lt; Float &lt;
	 * Integer &lt; Short.
	 * 
	 * @return index of this {@link Type}
	 */
	int getTypeOrderIndex();
	
}
