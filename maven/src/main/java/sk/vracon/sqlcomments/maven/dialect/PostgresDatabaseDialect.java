package sk.vracon.sqlcomments.maven.dialect;

import java.sql.Blob;

/**
 * Database dialect extension for PostgreSQL.
 * <p>
 * Implementation adds mapping for {code oid} database type to {@link Blob}.
 * </p>
 */
public class PostgresDatabaseDialect extends DefaultDatabaseDialect {

    @Override
    public Class<?> getJavaTypeForSQL(int sqlTypeNumber, String sqlTypeName) {
        
        // Map OID to java.sql.Blob
        if("oid".equalsIgnoreCase(sqlTypeName)) {
            return Blob.class;
        }
        
        return super.getJavaTypeForSQL(sqlTypeNumber, sqlTypeName);
    }
}
