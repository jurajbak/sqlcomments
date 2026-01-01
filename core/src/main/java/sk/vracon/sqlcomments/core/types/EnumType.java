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
package sk.vracon.sqlcomments.core.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;

/**
 * Mapper implementation for java Enums.
 * <p>
 * Implementation expects a database type representing some of string type.
 * 
 * @param <T> enum type
 */
public class EnumType<T extends Enum<?>> extends AbstractType<T> {

	/**
	 * Supported SQL types defined by default.
	 */
	protected static final int[] SUPPORTED_TYPES = new int[] {
			/**
			 * The {@link Types#CHAR} type.
			 */
			Types.CHAR,
			/**
			 * The {@link Types#VARCHAR} type.
			 */
			Types.VARCHAR,
			/**
			 * The {@link Types#LONGVARCHAR} type.
			 */
			Types.LONGVARCHAR,
			/**
			 * The {@link Types#NVARCHAR} type.
			 */
			Types.NVARCHAR,
			/**
			 * The {@link Types#NCHAR} type.
			 */
			Types.NCHAR };

	/**
	 * Default index used while calculating most generic type.
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	protected static final int DATA_RANGE_INDEX = 200;

	private static final Map<String, EnumType<?>> INSTANCES = new HashMap<String, EnumType<?>>();

	/**
	 * Constructor to create instance initialised by default settings.
	 * 
	 * @param enumClass enum class
	 */
	public EnumType(Class<T> enumClass) {
		this(enumClass, SUPPORTED_TYPES, DATA_RANGE_INDEX);
	}

	/**
	 * Constructor to register specific database types. 
	 *  
	 * @param enumClass enum class
	 * @param sqlTypes codes of database types to support 
	 * @param dataRangeIndex index to be used while calculating most generic type
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	public EnumType(Class<T> enumClass, int[] sqlTypes, int dataRangeIndex) {
		super(sqlTypes, enumClass, dataRangeIndex);
	}

	/**
	 * Returns instance of class initialised by defined values.
	 * <p>
	 * Instances are cached with input parameters as a key.
	 * 
	 * @param params parameters 
	 * @return class instance
	 */
	@SuppressWarnings("unchecked")
	public static EnumType<?> getInstance(Object... params) {
		if (params == null || params.length != 1) {
			throw new IllegalArgumentException("Required one parameter - Enum class name which represents database values in Java.");
		}
		if (!(params[0] instanceof String)) {
			throw new IllegalArgumentException("Wrong parameter type, required string - Enum class name which represents database values in Java.");
		}

		EnumType<?> instance = INSTANCES.get(params[0]);
		if (instance == null) {
			try {
				Class<Enum<?>> enumClass = (Class<Enum<?>>) Class.forName((String) params[0]);
				instance = new EnumType<>(enumClass);
				
				INSTANCES.put((String) params[0], instance);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Unable to instantiate class " + params[0] + " Cause: " + e.getMessage(), e);
			}
		}
		return instance;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T readValue(ResultSet resultSet, String columnName) throws SQLException {
		String dbValue = resultSet.getString(columnName);

		if (resultSet.wasNull() || dbValue == null) {
			return null;
		}

		return (T) Enum.valueOf((Class) getJavaClass(), dbValue);
	}

	@Override
	public void writeValue(PreparedStatement statement, int columnIndex, T value) throws SQLException {
		if (value == null) {
			statement.setString(columnIndex, null);
		} else {
			statement.setString(columnIndex, value.name());
		}
	}

	@Override
	public void validateColumnMetadata(DBColumnMetadata metadata) throws SQLException {
		// Check if SQL type matches
		int[] sqlTypes = getSQLTypes();
		boolean typeFound = false;
		for (int type : sqlTypes) {
			if (metadata.getSqlType() == type) {
				typeFound = true;
				break;
			}
		}
		if (!typeFound) {
			throw new SQLException(
					"Not supported SQL type " + metadata.getSqlType() + " of column " + metadata.getTableName() + "." + metadata.getColumnName() + ". Expected one of " + Arrays.toString(sqlTypes));
		}

		// Check length of enum values according to column size
		for (Enum<?> e : getJavaClass().getEnumConstants()) {
			if (metadata.getColumnSize() < e.name().length()) {
				throw new SQLException("Enum value " + getJavaClass().getName() + "#" + e.name() + " too long for column " + metadata.getTableName() + "." + metadata.getColumnName() + " ("
						+ metadata.getColumnSize() + ")");
			}
		}
	}
}
