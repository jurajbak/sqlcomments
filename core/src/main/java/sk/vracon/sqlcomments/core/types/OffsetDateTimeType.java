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
import java.time.OffsetDateTime;

import sk.vracon.sqlcomments.core.Type;

/**
 * OffsetDateTime data type.
 */
public class OffsetDateTimeType extends AbstractType<OffsetDateTime> {

	/**
	 * Supported SQL types defined by default.
	 */
	protected static final int[] SUPPORTED_TYPES = new int[] {
			/**
			 * The {@link Types#TIMESTAMP_WITH_TIMEZONE} type.
			 */
			Types.TIMESTAMP_WITH_TIMEZONE,
	};

	/**
	 * Default index used while calculating most generic type.
	 * 
	 * @see Type#getTypeOrderIndex()
	 */
	protected static final int DATA_RANGE_INDEX = 100;
	
	private static OffsetDateTimeType INSTANCE;

	/**
	 * Constructor to create instance initialised by default settings. 
	 */
	public OffsetDateTimeType() {
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
	public OffsetDateTimeType(int[] sqlTypes, int dataRangeIndex) {
		super(sqlTypes, OffsetDateTime.class, dataRangeIndex);
	}

	@Override
	public OffsetDateTime readValue(ResultSet resultSet, String columnName) throws SQLException {
		OffsetDateTime value = resultSet.getObject(columnName, OffsetDateTime.class);
		if (resultSet.wasNull()) {
			return null;
		}
		return value;
	}

	@Override
	public void writeValue(PreparedStatement statement, int columnIndex, OffsetDateTime value) throws SQLException {
		if (value == null) {
			statement.setNull(columnIndex, SUPPORTED_TYPES[0]);
		}
		statement.setObject(columnIndex, value);
	}

	/**
	 * Returns instance of class initialised by defined values.
	 * 
	 * @param params parameters 
	 * @return class instance
	 */
	public static OffsetDateTimeType getInstance(Object... params) {
		if (INSTANCE == null) {
			INSTANCE = new OffsetDateTimeType();
		}
		return INSTANCE;
	}
}
