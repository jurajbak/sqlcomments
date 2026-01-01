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

import java.sql.DatabaseMetaData;
import java.util.Objects;

/**
 * Represents column metadata read from a database.
 *
 * <p>
 * This class is typically populated using information obtained from {@link DatabaseMetaData#getColumns(String, String, String, String)}.
 * </p>
 */
public class DBColumnMetadata {

	/** Schema name of the table containing the column. */
	private String schema;

	/** Name of the table containing the column. */
	private String tableName;

	/** Name of the column. */
	private String columnName;

	/** SQL type code as defined in {@link java.sql.Types}. */
	private int sqlType;

	/** Database-specific SQL type name. */
	private String sqlTypeName;

	/** Column size (length for character or binary data). */
	private Integer columnSize;

	/** Number of fractional digits for numeric types. */
	private int decimalDigits;

	/** Radix (typically 10 or 2 for numeric columns). */
	private int radix;

	/** Description or comment associated with the column. */
	private String description;

	/** Default value of the column, if any. */
	private String defaultValue;

	/** Maximum number of bytes for character or binary columns. */
	private int charOctetLength;

	/** Ordinal position of the column within the table (1-based). */
	private int ordinalPosition;

	/** Indicates whether the column allows {@code NULL} values. */
	private Boolean nullable;

	/** Indicates whether the column is auto-incremented. */
	private Boolean autoincrement;

	/** Indicates whether the column is a generated column. */
	private Boolean generatedColumn;

	/**
	 * Creates an empty {@code DBColumnMetadata} instance.
	 */
	public DBColumnMetadata() {
	}

	/**
	 * Creates a {@code DBColumnMetadata} instance with basic column attributes.
	 *
	 * @param tableName     name of the table
	 * @param columnName    name of the column
	 * @param sqlType       SQL type code as defined in {@link java.sql.Types}
	 * @param sqlTypeName   database-specific SQL type name
	 * @param columnSize    column size (important for binary and text columns)
	 * @param decimalDigits number of fractional digits
	 * @param radix         numeric radix
	 * @param nullable      whether the column allows {@code NULL} values
	 */
	public DBColumnMetadata(String tableName, String columnName, int sqlType, String sqlTypeName, Integer columnSize, int decimalDigits, int radix, Boolean nullable) {
		this.tableName = tableName;
		this.columnName = columnName;
		this.sqlType = sqlType;
		this.sqlTypeName = sqlTypeName;
		this.columnSize = columnSize;
		this.decimalDigits = decimalDigits;
		this.radix = radix;
		this.nullable = nullable;
	}

	/**
	 * Returns the schema name.
	 *
	 * @return schema name, or {@code null} if not defined
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * Sets the schema name.
	 *
	 * @param schema schema name
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * Returns the numeric radix.
	 *
	 * @return radix value
	 */
	public int getRadix() {
		return radix;
	}

	/**
	 * Sets the numeric radix.
	 *
	 * @param radix radix value
	 */
	public void setRadix(int radix) {
		this.radix = radix;
	}

	/**
	 * Returns the column description or comment.
	 *
	 * @return column description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the column description.
	 *
	 * @param description column description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the default value of the column.
	 *
	 * @return default value, or {@code null} if none
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Sets the default value of the column.
	 *
	 * @param defaultValue default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Returns the maximum number of bytes for the column.
	 *
	 * @return character octet length
	 */
	public int getCharOctetLength() {
		return charOctetLength;
	}

	/**
	 * Sets the maximum number of bytes for the column.
	 *
	 * @param charOctetLength character octet length
	 */
	public void setCharOctetLength(int charOctetLength) {
		this.charOctetLength = charOctetLength;
	}

	/**
	 * Returns the ordinal position of the column in the table.
	 *
	 * @return ordinal position (1-based)
	 */
	public int getOrdinalPosition() {
		return ordinalPosition;
	}

	/**
	 * Sets the ordinal position of the column in the table.
	 *
	 * @param ordinalPosition ordinal position (1-based)
	 */
	public void setOrdinalPosition(int ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	/**
	 * Returns whether the column allows {@code NULL} values.
	 *
	 * @return {@code Boolean.TRUE} if nullable, {@code Boolean.FALSE} if not, or {@code null} if unknown
	 */
	public Boolean getNullable() {
		return nullable;
	}

	/**
	 * Sets whether the column allows {@code NULL} values.
	 *
	 * @param nullable nullable flag
	 */
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * Returns whether the column is auto-incremented.
	 *
	 * @return auto-increment flag
	 */
	public Boolean getAutoincrement() {
		return autoincrement;
	}

	/**
	 * Sets whether the column is auto-incremented.
	 *
	 * @param autoincrement auto-increment flag
	 */
	public void setAutoincrement(Boolean autoincrement) {
		this.autoincrement = autoincrement;
	}

	/**
	 * Returns whether the column is generated.
	 *
	 * @return generated column flag
	 */
	public Boolean getGeneratedColumn() {
		return generatedColumn;
	}

	/**
	 * Sets whether the column is generated.
	 *
	 * @param generatedColumn generated column flag
	 */
	public void setGeneratedColumn(Boolean generatedColumn) {
		this.generatedColumn = generatedColumn;
	}

	/**
	 * Returns the table name.
	 *
	 * @return table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the table name.
	 *
	 * @param tableName table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Returns the column name.
	 *
	 * @return column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName column name
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Returns the SQL type code.
	 *
	 * @return SQL type code
	 */
	public int getSqlType() {
		return sqlType;
	}

	/**
	 * Sets the SQL type code.
	 *
	 * @param sqlType SQL type code
	 */
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	/**
	 * Returns the SQL type name.
	 *
	 * @return SQL type name
	 */
	public String getSqlTypeName() {
		return sqlTypeName;
	}

	/**
	 * Sets the SQL type name.
	 *
	 * @param sqlTypeName SQL type name
	 */
	public void setSqlTypeName(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}

	/**
	 * Returns the column size.
	 *
	 * @return column size
	 */
	public Integer getColumnSize() {
		return columnSize;
	}

	/**
	 * Sets the column size.
	 *
	 * @param columnSize column size
	 */
	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * Returns the number of decimal digits.
	 *
	 * @return decimal digits
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * Sets the number of decimal digits.
	 *
	 * @param decimalDigits decimal digits
	 */
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	/**
	 * Returns whether the column allows {@code NULL} values.
	 *
	 * @return {@code true} if nullable, {@code false} otherwise
	 */
	public boolean isNullable() {
		return Boolean.TRUE.equals(nullable);
	}

	/**
	 * Sets whether the column allows {@code NULL} values.
	 *
	 * @param nullable nullable flag
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * Returns a string representation of this column metadata.
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DBColumnMetadata [schema=");
		builder.append(schema);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", columnName=");
		builder.append(columnName);
		builder.append(", sqlType=");
		builder.append(sqlType);
		builder.append(", sqlTypeName=");
		builder.append(sqlTypeName);
		builder.append(", columnSize=");
		builder.append(columnSize);
		builder.append(", decimalDigits=");
		builder.append(decimalDigits);
		builder.append(", radix=");
		builder.append(radix);
		builder.append(", description=");
		builder.append(description);
		builder.append(", defaultValue=");
		builder.append(defaultValue);
		builder.append(", charOctetLength=");
		builder.append(charOctetLength);
		builder.append(", ordinalPosition=");
		builder.append(ordinalPosition);
		builder.append(", nullable=");
		builder.append(nullable);
		builder.append(", autoincrement=");
		builder.append(autoincrement);
		builder.append(", generatedColumn=");
		builder.append(generatedColumn);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Computes a hash code based on table and column name (case-insensitive).
	 *
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		return Objects.hash(toUpperCase(columnName), toUpperCase(tableName));
	}

	/**
	 * Compares this instance with another for equality.
	 *
	 * <p>
	 * Two instances are considered equal if they have the same table name and column name, ignoring case.
	 * </p>
	 *
	 * @param obj object to compare with
	 * @return {@code true} if equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		DBColumnMetadata other = (DBColumnMetadata) obj;
		return Objects.equals(toUpperCase(columnName), toUpperCase(other.columnName)) && Objects.equals(toUpperCase(tableName), toUpperCase(other.tableName));
	}

	/**
	 * Converts the given string to upper case.
	 *
	 * @param s input string
	 * @return upper-case string, or {@code null} if input is {@code null}
	 */
	private static String toUpperCase(String s) {
		return s == null ? null : s.toUpperCase();
	}
}
