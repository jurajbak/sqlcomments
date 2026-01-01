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

import sk.vracon.sqlcomments.core.Type;

/**
 * String {@link Type}.
 */
public class StringType extends AbstractType<String> {

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
			 * The {@link Types#CLOB} type.
			 */
			Types.CLOB,
			/**
			 * The {@link Types#NVARCHAR} type.
			 */
			Types.NVARCHAR,
			/**
			 * The {@link Types#NCHAR} type.
			 */
			Types.NCHAR,
			/**
			 * The {@link Types#LONGNVARCHAR} type.
			 */
			Types.LONGNVARCHAR,
			/**
			 * The {@link Types#NCLOB} type.
			 */
			Types.NCLOB,
			/**
			 * The {@link Types#ROWID} type.
			 */
			Types.ROWID,
			/**
			 * The {@link Types#SQLXML} type.
			 */
			Types.SQLXML
			};

	/**
	 * Default index used while calculating most generic type.
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	protected static final int DATA_RANGE_INDEX = 30;

	private static StringType INSTANCE;

	/**
	 * Constructor to create instance initialised by default settings. 
	 */
	public StringType() {
		super(SUPPORTED_TYPES, String.class, DATA_RANGE_INDEX);
	}
	
	/**
	 * Constructor to register specific database types. 
	 *  
	 * @param sqlTypes codes of database types to support 
	 * @param dataRangeIndex index to be used while calculating most generic type
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	public StringType(int[] sqlTypes, int dataRangeIndex) {
		super(sqlTypes, String.class, dataRangeIndex);
	}

	@Override
	public String readValue(ResultSet resultSet, String columnName) throws SQLException {
		return resultSet.getString(columnName);
	}

	@Override
	public void writeValue(PreparedStatement statement, int columnIndex, String value) throws SQLException {
		if (value == null) {
			statement.setNull(columnIndex, SUPPORTED_TYPES[0]);
		}
		statement.setString(columnIndex, value);
	}

	/**
	 * Returns instance of class initialised by defined values.
	 * 
	 * @param params parameters 
	 * @return class instance
	 */
	public static StringType getInstance(Object... params) {
		if (INSTANCE == null) {
			INSTANCE = new StringType();
		}
		return INSTANCE;
	}
}