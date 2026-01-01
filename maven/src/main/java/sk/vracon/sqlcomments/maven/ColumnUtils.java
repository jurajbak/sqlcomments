package sk.vracon.sqlcomments.maven;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.core.dialect.DatabaseDialect;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.ResultColumnInfo;
import sk.vracon.sqlcomments.maven.generate.SelectContext;
import sk.vracon.sqlcomments.maven.generate.TableColumnIdentifier;
import sk.vracon.sqlcomments.maven.generate.TableInfo;

/**
 * Column manipulation utility methods.
 * 
 */
public class ColumnUtils {

    /**
     * Case insensitive property search.
     * 
     * @param properties
     *            properties
     * @param propertyName
     *            property name
     * @return property value or null
     */
    public static String findColumnProperty(Properties properties, String propertyName) {
        if (properties == null) {
            return null;
        }

        for (Entry<Object, Object> entry : properties.entrySet()) {
            String entryName = (String) entry.getKey();

            if (entryName.equalsIgnoreCase(propertyName)) {
                return (String) entry.getValue();
            }
        }

        return null;
    }

    /**
     * Finds database metadata for column identifier.
     * 
     * @param databaseColumns
     *            database metadata
     * @param statementContext
     *            statement context
     * @param identifier
     *            identifier
     * @return column metadata
     */
    public static Set<DBColumnMetadata> findDBColumnMetadataForColumnIdentifier(Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext statementContext, TableColumnIdentifier identifier) {
        Set<DBColumnMetadata> result = new HashSet<DBColumnMetadata>();

        if (identifier.getTableAlias() == null) {
            // There's no table alias on identifier - try to find table with such column

            DBColumnMetadata dbColumn = null;
            for (TableInfo table : statementContext.getTables()) {
                String columnFullName = table.getName().toLowerCase() + "." + identifier.getColumnName().toLowerCase();
                dbColumn = databaseColumns.get(columnFullName);
                if (dbColumn != null) {
                    // Column found
                    break;
                }
            }

            if (dbColumn == null) {
                // Column was not found
                throw new SyntaxErrorException("No table with column '" + identifier.getColumnName() + "' found in database.");
            }

            result.add(dbColumn);
            return result;
        } else {
            // Find appropriate table from database metadata
            for (TableInfo table : statementContext.getTables()) {
                if (identifier.getTableAlias().equalsIgnoreCase(table.getAlias())) {
                    if (table.getSubqueries().size() == 0) {
                        // Direct table definition
                        String columnFullName = table.getName().toLowerCase() + "." + identifier.getColumnName().toLowerCase();

                        DBColumnMetadata dbColumn = databaseColumns.get(columnFullName);
                        if (dbColumn == null) {
                            // Column was not found
                            throw new SyntaxErrorException("Column not found: " + columnFullName);
                        }

                        result.add(dbColumn);
                    } else {
                        // Table defined by inner query
                        for (SelectContext innerQuery : table.getSubqueries()) {
                            if (identifier.getTableAlias().equalsIgnoreCase(table.getAlias())) {
                                // Table definition has the same alias as identifier
                                // Find appropriate column
                                for (ResultColumnInfo columnInfo : innerQuery.getColumns()) {
                                    if (identifier.getColumnName().equalsIgnoreCase(columnInfo.getColumnName())) {
                                        for (TableColumnIdentifier innerIdentifier : columnInfo.getReferences()) {
                                            Set<DBColumnMetadata> innerResult = findDBColumnMetadataForColumnIdentifier(databaseColumns, innerQuery,
                                                    innerIdentifier);
                                            result.addAll(innerResult);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Finds java class for column.
     * 
     * @param dialect
     *            database dialect
     * @param databaseColumns
     *            database metadata
     * @param selectContext
     *            select context
     * @param column
     *            column
     * @return java class
     */
    public static Type<?> findTypeForColumn(DatabaseDialect dialect, Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext selectContext, ResultColumnInfo column) {
        Set<Type<?>> types = new HashSet<Type<?>>();

        // Find classes for all references in column
        for (TableColumnIdentifier identifier : column.getReferences()) {
            Type<?> type = findTypeForColumnIdentifier(dialect, databaseColumns, selectContext, identifier);

            if (type == null) {
                throw new SyntaxErrorException("No java type found for column identifier" + identifier);
            }

            types.add(type);
        }

        // Get most generic class
        return dialect.getMostGenericType(types);
    }

    /**
     * Finds database metadata for column identifier.
     * 
     * @param dialect
     *            database dialect
     * @param databaseColumns
     *            database metadata
     * @param selectContext
     *            select context
     * @param identifier
     *            column identifier
     * @return java class
     */
    public static Type<?> findTypeForColumnIdentifier(DatabaseDialect dialect, Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext selectContext, TableColumnIdentifier identifier) {
        // Check if identifier has class set
        if (identifier.getType() != null) {
            return identifier.getType();
        }

        Set<DBColumnMetadata> dbColumns = ColumnUtils.findDBColumnMetadataForColumnIdentifier(databaseColumns, selectContext, identifier);

        Set<Type<?>> types = new HashSet<Type<?>>();
        for (DBColumnMetadata dbColumn : dbColumns) {
            Type<?> type = dialect.getType(dbColumn);
            if (type == null) {
				throw new SyntaxErrorException("No java type defined for SQL type " + dbColumn.getSqlTypeName() + " sql type ID " + dbColumn.getSqlType() + " Referenced by column "
						+ dbColumn.getTableName() + "." + dbColumn.getColumnName());
            }

            types.add(type);
        }

        // Get most generic class
        return dialect.getMostGenericType(types);
    }
}
