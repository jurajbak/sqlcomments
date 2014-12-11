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
package sk.vracon.sqlcomments.maven;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultDatabaseDialect implements DatabaseDialect {

    private static final Map<Integer, Class<?>> MAPPINGS = new HashMap<Integer, Class<?>>();

    private static final List<Class<?>> JAVA_TYPE_ORDER = new ArrayList<Class<?>>();

    static {
        /**
         * The {@link Types#CHAR} type.
         */
        MAPPINGS.put(1, String.class);

        /**
         * The {@link Types#VARCHAR} type.
         */
        MAPPINGS.put(12, String.class);

        /**
         * The {@link Types#LONGVARCHAR} type.
         */
        MAPPINGS.put(-1, String.class);

        /**
         * The {@link Types#CLOB} type.
         */
        MAPPINGS.put(2005, String.class);

        /**
         * The {@link Types#NVARCHAR} type.
         */
        MAPPINGS.put(-9, String.class);

        /**
         * The {@link Types#NCHAR} type.
         */
        MAPPINGS.put(-15, String.class);

        /**
         * The {@link Types#LONGNVARCHAR} type.
         */
        MAPPINGS.put(-16, String.class);

        /**
         * The {@link Types#NCLOB} type.
         */
        MAPPINGS.put(2011, String.class);

        // -------------------------------------------------------------------------
        // Boolean types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#BOOLEAN} type.
         */
        MAPPINGS.put(16, Boolean.class);

        /**
         * The {@link Types#BIT} type.
         */
        MAPPINGS.put(-7, Boolean.class);

        // -------------------------------------------------------------------------
        // Integer types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#TINYINT} type.
         */
        MAPPINGS.put(-6, Byte.class);

        /**
         * The {@link Types#SMALLINT} type.
         */
        MAPPINGS.put(5, Short.class);

        /**
         * The {@link Types#INTEGER} type.
         */
        MAPPINGS.put(4, Integer.class);

        /**
         * The {@link Types#BIGINT} type.
         */
        MAPPINGS.put(-5, Long.class);

        // -------------------------------------------------------------------------
        // Floating point types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#FLOAT} type.
         */
        MAPPINGS.put(6, Double.class);

        /**
         * The {@link Types#REAL} type.
         */
        MAPPINGS.put(7, Double.class);

        /**
         * The {@link Types#DOUBLE} type.
         */
        MAPPINGS.put(8, Double.class);

        // -------------------------------------------------------------------------
        // Numeric types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#NUMERIC} type.
         */
        MAPPINGS.put(2, BigDecimal.class);

        /**
         * The {@link Types#DECIMAL} type.
         */
        MAPPINGS.put(3, BigDecimal.class);

        // -------------------------------------------------------------------------
        // Datetime types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#DATE} type.
         */
        MAPPINGS.put(91, Date.class);

        /**
         * The {@link Types#TIME} type.
         */
        MAPPINGS.put(92, Date.class);

        /**
         * The {@link Types#TIMESTAMP} type.
         */
        MAPPINGS.put(93, Date.class);

        // -------------------------------------------------------------------------
        // Binary types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#BINARY} type.
         */
        MAPPINGS.put(-2, byte[].class);

        /**
         * The {@link Types#VARBINARY} type.
         */
        MAPPINGS.put(-3, byte[].class);

        /**
         * The {@link Types#LONGVARBINARY} type.
         */
        MAPPINGS.put(-4, byte[].class);

        /**
         * The {@link Types#BLOB} type.
         */
        MAPPINGS.put(2004, java.sql.Blob.class);

        // -------------------------------------------------------------------------
        // Other types
        // -------------------------------------------------------------------------

        /**
         * The {@link Types#OTHER} type.
         */
        MAPPINGS.put(1111, Object.class);

        /**
         * Define java type order.
         */
        JAVA_TYPE_ORDER.add(Object.class);
        JAVA_TYPE_ORDER.add(String.class);
        JAVA_TYPE_ORDER.add(BigDecimal.class);
        JAVA_TYPE_ORDER.add(Double.class);
        JAVA_TYPE_ORDER.add(Long.class);
        JAVA_TYPE_ORDER.add(Integer.class);
        JAVA_TYPE_ORDER.add(Short.class);
        JAVA_TYPE_ORDER.add(Byte.class);
    }

    public Class<?> getJavaTypeForSQL(int sqlTypeNumber, String sqlTypeName) {
        Class<?> resultClass = MAPPINGS.get(sqlTypeNumber);

        if (resultClass == null) {
            System.err.println("No class for type: " + sqlTypeNumber + " " + sqlTypeName);
        }

        return resultClass;
    }

    public Class<?> getMostGenericClass(Set<Class<?>> classes) {
        Class<?> resultClass = null;
        for (Class<?> clazz : classes) {
            if (resultClass == null) {
                resultClass = clazz;
                continue;
            }

            if (resultClass.equals(clazz)) {
                continue;
            }

            if (Object.class.equals(clazz)) {
                return Object.class;
            } else {
                int resultIndx = JAVA_TYPE_ORDER.indexOf(resultClass);
                int clazzIndx = JAVA_TYPE_ORDER.indexOf(clazz);

                if (resultIndx < 0 || clazzIndx < 0) {
                    // Combination of classes not found in JAVA_TYPE_ORDER
                    // Unable to make a decision
                    return Object.class;
                } else {
                    resultClass = JAVA_TYPE_ORDER.get(Math.min(resultIndx, clazzIndx));
                }
            }
        }

        return resultClass;
    }
}
