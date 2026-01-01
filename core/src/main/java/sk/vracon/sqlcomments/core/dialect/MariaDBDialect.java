package sk.vracon.sqlcomments.core.dialect;

import sk.vracon.sqlcomments.core.types.BinaryType;
import sk.vracon.sqlcomments.core.types.FloatType;

/**
 * MySQL database dialect.
 * 
 */
public class MariaDBDialect extends AbstractDatabaseDialect {

	static {
		TYPE_MAPPINGS.add(new FloatType(new int[] {
				/**
				 * FLOAT
				 */
				7
		}, 110));
		TYPE_MAPPINGS.add(new BinaryType(new int[] {
				/**
				 * GEOMETRY
				 */
				1111
		}, 110));
	}
	
    /**
     * Returns common database name 'mysql'.
     */
    public String getDatabaseProductName() {
        return "mariadb";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        StringBuilder result = new StringBuilder(sql);

        result.append(" limit ");
        if (offset != null) {
            result.append(offset);
            result.append(", ");
        }
        if (limit != null) {
            result.append(limit);
        } else {
            result.append("2147483647");
        }

        return result.toString();
    }
}
