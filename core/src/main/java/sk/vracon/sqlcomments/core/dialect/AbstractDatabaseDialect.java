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
package sk.vracon.sqlcomments.core.dialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.DBColumnMetadata;
import sk.vracon.sqlcomments.core.Type;
import sk.vracon.sqlcomments.core.types.ArrayType;
import sk.vracon.sqlcomments.core.types.BinaryType;
import sk.vracon.sqlcomments.core.types.BlobType;
import sk.vracon.sqlcomments.core.types.BooleanType;
import sk.vracon.sqlcomments.core.types.DateType;
import sk.vracon.sqlcomments.core.types.DoubleType;
import sk.vracon.sqlcomments.core.types.FloatType;
import sk.vracon.sqlcomments.core.types.IntegerType;
import sk.vracon.sqlcomments.core.types.LongType;
import sk.vracon.sqlcomments.core.types.NumericType;
import sk.vracon.sqlcomments.core.types.ObjectOrUndefinedType;
import sk.vracon.sqlcomments.core.types.OffsetDateTimeType;
import sk.vracon.sqlcomments.core.types.OffsetTimeType;
import sk.vracon.sqlcomments.core.types.StringType;

/**
 * Database dialect base class for most implementations.
 */
public abstract class AbstractDatabaseDialect implements DatabaseDialect {

	/**
	 * List of type mappings.
	 * <p>
	 * Inheriting classes can extend this list in static clause to add database specific data types and mappings.
	 */
    protected static final List<Type<?>> TYPE_MAPPINGS = new ArrayList<Type<?>>();
    
    /**
     * Map of custom data type mappings for specific table columns,
     */
    protected final Map<DBColumnMetadata, Type<?>> CUSTOM_TYPE_MAPPING = new HashMap<DBColumnMetadata, Type<?>>();

    static {
        TYPE_MAPPINGS.add(ObjectOrUndefinedType.getInstance());
        TYPE_MAPPINGS.add(StringType.getInstance());
        TYPE_MAPPINGS.add(DoubleType.getInstance());
        TYPE_MAPPINGS.add(FloatType.getInstance());
        TYPE_MAPPINGS.add(LongType.getInstance());
        TYPE_MAPPINGS.add(IntegerType.getInstance());
        TYPE_MAPPINGS.add(DateType.getInstance());
        TYPE_MAPPINGS.add(NumericType.getInstance());
        TYPE_MAPPINGS.add(BlobType.getInstance());
        TYPE_MAPPINGS.add(ArrayType.getInstance());
        TYPE_MAPPINGS.add(BinaryType.getInstance());
        TYPE_MAPPINGS.add(BooleanType.getInstance());
        TYPE_MAPPINGS.add(OffsetTimeType.getInstance());
        TYPE_MAPPINGS.add(OffsetDateTimeType.getInstance());
    }

    public Type<?> getMostGenericType(Set<Type<?>> types) {
        Type<?> resultType = null;
        for (Type<?> type : types) {
            if (resultType == null) {
                resultType = type;
                continue;
            }

            if (resultType.equals(type)) {
                continue;
            }

            if (ObjectOrUndefinedType.class.equals(type)) {
                return ObjectOrUndefinedType.getInstance();
            } else {
                int resultIndx = TYPE_MAPPINGS.indexOf(resultType);
                int clazzIndx = TYPE_MAPPINGS.indexOf(type);

                if (resultIndx < 0 || clazzIndx < 0) {
                    // Combination of classes not found in JAVA_TYPE_ORDER
                    // Unable to make a decision
                    return ObjectOrUndefinedType.getInstance();
                } else {
                    resultType = TYPE_MAPPINGS.get(Math.min(resultIndx, clazzIndx));
                }
            }
        }

        return resultType;
    }
    
    @Override
    public Type<?> getType(DBColumnMetadata columnMetadata) {
		Type<?> customType = CUSTOM_TYPE_MAPPING.get(columnMetadata);
		if (customType != null) {
			return customType;
		}
    	
    	int sqlType = columnMetadata.getSqlType();
    	
    	for(Type<?> type: TYPE_MAPPINGS) {
    		for (int supportedType : type.getSQLTypes()) {
    			if (supportedType == sqlType) {
    				return type;
    			}
			}
    	}
    	
    	return null;
    }

    @Override
	public void addCustomTypeMapping(DBColumnMetadata column, Type<?> type) {
		if (CUSTOM_TYPE_MAPPING.containsKey(column)) {
			throw new IllegalArgumentException("Duplicate custom type mapping for DB column " + column.getTableName() + "." + column.getColumnName() + ". Please check your configuration. ");
		}

		CUSTOM_TYPE_MAPPING.put(column, type);
	}
}
