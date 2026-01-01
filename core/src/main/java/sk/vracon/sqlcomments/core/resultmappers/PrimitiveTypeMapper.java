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
package sk.vracon.sqlcomments.core.resultmappers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import sk.vracon.sqlcomments.core.ResultMapper;

/**
 * Mapper implementation for basic Java types.
 * <p>
 * Mapper is usefull for queries with simple result. E.g. SELECT count(*) FROM ... or SELECT id FROM ...
 * 
 * @param <T> primitive type wrapper class
 */
public class PrimitiveTypeMapper<T> implements ResultMapper<T> {

	public static PrimitiveTypeMapper<Integer> INTEGER = new PrimitiveTypeMapper<>(Integer.class);
	public static PrimitiveTypeMapper<Long> LONG = new PrimitiveTypeMapper<>(Long.class);
	public static PrimitiveTypeMapper<Double> DOUBLE = new PrimitiveTypeMapper<>(Double.class);
	public static PrimitiveTypeMapper<Float> FLOAT = new PrimitiveTypeMapper<>(Float.class);
	public static PrimitiveTypeMapper<String> STRING = new PrimitiveTypeMapper<>(String.class);
	public static PrimitiveTypeMapper<BigDecimal> BIGDECIMAL = new PrimitiveTypeMapper<>(BigDecimal.class);
	public static PrimitiveTypeMapper<BigInteger> BIGINTEGER = new PrimitiveTypeMapper<>(BigInteger.class);

	private Class<T> type;

	private PrimitiveTypeMapper(Class<T> type) {
		this.type = type;
	}

	@Override
	public T transform(ResultSet rs) throws SQLException {
		return rs.getObject(1, type);
	}

}
