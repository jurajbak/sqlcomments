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
 * Binary data type.
 */
public class BinaryType extends AbstractType<byte[]> {

	/**
	 * Supported SQL types defined by default.
	 */
	protected static final int[] SUPPORTED_TYPES = new int[] {
			/**
			 * The {@link Types#BIT} type.
			 */
			Types.BIT,
			/**
			 * The {@link Types#BINARY} type.
			 */
			Types.BINARY,
			/**
			 * The {@link Types#VARBINARY} type.
			 */
			Types.VARBINARY			
	};

	/**
	 * Default index used while calculating most generic type.
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	protected static final int DATA_RANGE_INDEX = 100;
	
	private static BinaryType INSTANCE;

	/**
	 * Constructor to create instance initialised by default settings. 
	 */
	public BinaryType() {
		this(SUPPORTED_TYPES, DATA_RANGE_INDEX);
	}

	/**
	 * Constructor to register specific database types. 
	 *  
	 * @param sqlTypes codes of database types to support 
	 * @param dataRangeIndex index to be used while calculating most generic type
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	public BinaryType(int[] sqlTypes, int dataRangeIndex) {
		super(sqlTypes, byte[].class, dataRangeIndex);
	}

	@Override
	public byte[] readValue(ResultSet resultSet, String columnName) throws SQLException {
		byte[] value = resultSet.getBytes(columnName);
		if (resultSet.wasNull()) {
			return null;
		}
		return value;
	}

	@Override
	public void writeValue(PreparedStatement statement, int columnIndex, byte[] value) throws SQLException {
		if (value == null) {
			statement.setNull(columnIndex, SUPPORTED_TYPES[0]);
		}
		statement.setBytes(columnIndex, value);
	}

	/**
	 * Returns instance of class initialised by defined values.
	 * 
	 * @param params parameters 
	 * @return class instance
	 */
	public static BinaryType getInstance(Object... params) {
		if (INSTANCE == null) {
			INSTANCE = new BinaryType();
		}
		return INSTANCE;
	}
}
