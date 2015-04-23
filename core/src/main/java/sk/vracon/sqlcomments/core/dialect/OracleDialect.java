package sk.vracon.sqlcomments.core.dialect;

/**
 * Oracle database dialect.
 * 
 */
public class OracleDialect implements DatabaseDialect {

    /**
     * Returns common database name 'oracle'.
     */
    public String getDatabaseProductName() {
        return "oracle";
    }

    public String generateSQLWithOffsetAndLimit(String sql, Long offset, Long limit) {

        Long fixedLimit = limit;
        if (fixedLimit == null) {
            fixedLimit = 2147483647l;
        }

        sql = sql.trim();
        String forUpdateClause = null;
        boolean isForUpdate = false;
        final int forUpdateIndex = sql.toLowerCase().lastIndexOf("for update");
        if (forUpdateIndex > -1) {
            // save 'for update ...' and then remove it
            forUpdateClause = sql.substring(forUpdateIndex);
            sql = sql.substring(0, forUpdateIndex - 1);
            isForUpdate = true;
        }

        final StringBuilder result = new StringBuilder(sql.length() + 100);
        if (offset != null) {
            result.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            result.append("select * from ( ");
        }
        result.append(sql);
        if (offset != null) {
            result.append(" ) row_ where rownum <= ");
            result.append(fixedLimit + offset);
            result.append(") where rownum_ > ");
            result.append(offset);
        } else {
            result.append(" ) where rownum <= ");
            result.append(fixedLimit);
        }

        if (isForUpdate) {
            result.append(" ");
            result.append(forUpdateClause);
        }

        return result.toString();
    }
}
