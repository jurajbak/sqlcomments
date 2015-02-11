package sk.vracon.sqlcomments.maven;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.logging.Log;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
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
     * Assigns mapper class and mapped class properties to column.
     * 
     * @param databaseColumns
     *            database metadata
     * @param tableProperties
     *            parsed table configuration
     * @param column
     *            parsed column
     * @param selectContext
     *            select context
     */
    public static void fillMapperToPlaceholder(Log log, Map<String, DBColumnMetadata> databaseColumns, Map<String, Properties> tableProperties,
            PlaceholderInfo placeholder, Set<TableColumnIdentifier> identifiers) {

        if (identifiers.size() == 0) {
            throw new IllegalStateException("No identifiers for placeholder " + placeholder.getName());
        }

        Set<DBColumnMetadata> metadata = new HashSet<DBColumnMetadata>();
        for (TableColumnIdentifier identifier : identifiers) {
            Set<DBColumnMetadata> identifierMetadata = findDBColumnMetadataForColumnIdentifier(databaseColumns, placeholder.getContext(), identifier);
            metadata.addAll(identifierMetadata);
        }

        String mappedClass = null;
        String mapperClass = null;

        boolean atLeastOneAssigned = false;
        for (DBColumnMetadata columnMetadata : metadata) {
            Properties properties = tableProperties.get(columnMetadata.getTableName());

            if (atLeastOneAssigned) {
                // Second and other entries
                String nextMappedClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_JAVA_CLASS);
                String nextMapperClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_MAPPER);

                if ((mappedClass == null && nextMappedClass != null) || (mappedClass != null && !mappedClass.equals(nextMappedClass))) {
                    // Configurations are not the same for all identifiers in column
                    log.warn("Various java class defined for placeholder " + placeholder.getName() + " - found " + mappedClass + " and " + nextMappedClass
                            + " Using first one.");
                    break;
                }
                if ((mapperClass == null && nextMapperClass != null) || (mapperClass != null && !mapperClass.equals(nextMapperClass))) {
                    // Configurations are not the same for all identifiers in column
                    log.warn("Various mapper class defined for placeholder " + placeholder.getName() + " - found " + mapperClass + " and " + nextMapperClass
                            + " Using first one.");
                    break;
                }
            } else {
                // First entry
                mappedClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_JAVA_CLASS);
                mapperClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_MAPPER);

                atLeastOneAssigned = true;
            }
        }

        placeholder.setMappedClass(mappedClass);
        placeholder.setMapperClass(mapperClass);
    }

    /**
     * Assigns mapper class and mapped class properties to column.
     * 
     * @param databaseColumns
     *            database metadata
     * @param tableProperties
     *            parsed table configuration
     * @param column
     *            parsed column
     * @param selectContext
     *            select context
     */
    public static void fillMapperToColumn(Log log, Map<String, DBColumnMetadata> databaseColumns, Map<String, Properties> tableProperties,
            ResultColumnInfo column, AbstractStatementContext selectContext) {

        if (column.getReferences().size() == 0) {
            throw new IllegalStateException("No identifiers for column " + column.getColumnName());
        }

        Set<DBColumnMetadata> metadata = new HashSet<DBColumnMetadata>();
        for (TableColumnIdentifier identifier : column.getReferences()) {
            Set<DBColumnMetadata> identifierMetadata = findDBColumnMetadataForColumnIdentifier(databaseColumns, selectContext, identifier);
            metadata.addAll(identifierMetadata);
        }

        String mappedClass = null;
        String mapperClass = null;

        boolean atLeastOneAssigned = false;
        for (DBColumnMetadata columnMetadata : metadata) {
            Properties properties = tableProperties.get(columnMetadata.getTableName());

            if (atLeastOneAssigned) {
                // Second and other entries
                String nextMappedClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_JAVA_CLASS);
                String nextMapperClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_MAPPER);

                if ((mappedClass == null && nextMappedClass != null) || (mappedClass != null && !mappedClass.equals(nextMappedClass))) {
                    // Configurations are not the same for all identifiers in column
                    log.warn("Various java class defined for column " + column.getColumnName() + " - found " + mappedClass + " and " + nextMappedClass
                            + " Using first one.");
                    break;
                }
                if ((mapperClass == null && nextMapperClass != null) || (mapperClass != null && !mapperClass.equals(nextMapperClass))) {
                    // Configurations are not the same for all identifiers in column
                    log.warn("Various mapper class defined for column " + column.getColumnName() + " - found " + mapperClass + " and " + nextMapperClass
                            + " Using first one.");
                    break;
                }
            } else {
                // First entry
                mappedClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_JAVA_CLASS);
                mapperClass = findColumnProperty(properties, columnMetadata.getColumnName() + AbstractSqlCommentsMojo.TABLE_PROP_COLUMN_MAPPER);

                atLeastOneAssigned = true;
            }
        }

        column.setMappedClass(mappedClass);
        column.setMapperClass(mapperClass);
    }

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
     * @param selectContext
     *            select context
     * @param identifier
     *            identifier
     * @return
     */
    public static Set<DBColumnMetadata> findDBColumnMetadataForColumnIdentifier(Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext selectContext, TableColumnIdentifier identifier) {
        Set<DBColumnMetadata> result = new HashSet<DBColumnMetadata>();

        if (identifier.getTableAlias() == null) {
            // There's no table alias on identifier - try to find table with such column

            DBColumnMetadata dbColumn = null;
            for (TableInfo table : selectContext.getTables()) {
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
            for (TableInfo table : selectContext.getTables()) {
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
    public static Class<?> findJavaClassForColumn(DatabaseDialect dialect, Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext selectContext, ResultColumnInfo column) {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Find classes for all references in column
        for (TableColumnIdentifier identifier : column.getReferences()) {
            Class<?> javaClass = findJavaClassForColumnIdentifier(dialect, databaseColumns, selectContext, identifier);

            if (javaClass == null) {
                throw new SyntaxErrorException("No java type found for column identifier" + identifier);
            }

            classes.add(javaClass);
        }

        // Get most generic class
        return dialect.getMostGenericClass(classes);
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
    public static Class<?> findJavaClassForColumnIdentifier(DatabaseDialect dialect, Map<String, DBColumnMetadata> databaseColumns,
            AbstractStatementContext selectContext, TableColumnIdentifier identifier) {
        // Check if identifier has class set
        if (identifier.getJavaType() != null) {
            return identifier.getJavaType();
        }

        Set<DBColumnMetadata> dbColumns = ColumnUtils.findDBColumnMetadataForColumnIdentifier(databaseColumns, selectContext, identifier);

        Set<Class<?>> classes = new HashSet<Class<?>>();
        for (DBColumnMetadata dbColumn : dbColumns) {
            Class<?> javaType = dialect.getJavaTypeForSQL(dbColumn.getSqlType(), dbColumn.getSqlTypeName());
            if (javaType == null) {
                throw new SyntaxErrorException("No java type found for SQL type " + dbColumn.getSqlTypeName() + " Referenced by column "
                        + dbColumn.getTableName() + "." + dbColumn.getColumnName());
            }

            classes.add(javaType);
        }

        // Get most generic class
        return dialect.getMostGenericClass(classes);
    }
}
