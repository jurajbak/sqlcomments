package sk.vracon.sqlcomments.core.dialect;

import sk.vracon.sqlcomments.core.types.BinaryType;
import sk.vracon.sqlcomments.core.types.FloatType;
import sk.vracon.sqlcomments.core.types.StringType;
import sk.vracon.sqlcomments.core.types.UUIDType;

/**
 * PostgreSQL database dialect.
 * 
 */
public class PostgreSQLDialect extends AbstractDatabaseDialect {

	static {
		TYPE_MAPPINGS.add(new FloatType(new int[] {
				/**
				 * FLOAT
				 */
				7
		}, 110));
		TYPE_MAPPINGS.add(new BinaryType(new int[] {
				/**
				 * varbit
				 */
				1111
		}, 110));
		TYPE_MAPPINGS.add(new UUIDType(new int[] {
				/**
				 * uuid
				 */
				2950
		}, 110));
		TYPE_MAPPINGS.add(new StringType(new int[] {
				/**
				 * json
				 */
				114,
				/**
				 * jsonb
				 */
				3802,
				/**
				 * inet
				 */
				869,
				/**
				 * cidr
				 */
				650,
				/**
				 * macaddr
				 */
				859,
				/**
				 * macaddr8
				 */
				774,
				/**
				 * tsvector
				 */
				3614,
				/**
				 * tsquery
				 */
				3615,
				/**
				 * point
				 */
				600,
				/**
				 * xml
				 */
				142
		}, 110));
	}
	
    /**
     * Returns common database name 'postgresql'.
     */
    public String getDatabaseProductName() {
        return "postgresql";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        StringBuilder result = new StringBuilder(sql);

        if (limit != null) {
            result.append(" limit ");
            result.append(limit);
        }
        if (offset != null) {
            result.append(" offset ");
            result.append(offset);
        }

        return result.toString();
    }
}
