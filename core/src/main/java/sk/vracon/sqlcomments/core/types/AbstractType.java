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
package sk.vracon.sqlcomments.core.types;

import java.sql.Types;

import sk.vracon.sqlcomments.core.Type;

/**
 * Abstract {@link Type} class exposing descriptor methods.
 * 
 * @param <T> java type
 */
public abstract class AbstractType<T> implements Type<T> {

	private int[] sqlTypes;
	private Class<T> javaClass;
	private int dataRangeIndex;
	
	/**
	 * Helper constructor to fill descriptor values.
	 * 
	 * @param sqlTypes a list of SQL type codes (see {@link Types})
	 * @param javaClass java class which maps SQL types  
	 * @param dataRangeIndex date range index (see {@link Type#getTypeOrderIndex()})
	 */
	public AbstractType(int[] sqlTypes, Class<T> javaClass, int dataRangeIndex) {
		super();
		this.sqlTypes = sqlTypes;
		this.javaClass = javaClass;
		this.dataRangeIndex = dataRangeIndex;
	}

	@Override
	public int[] getSQLTypes() {
		return sqlTypes;
	}

	@Override
	public Class<T> getJavaClass() {
		return javaClass;
	}

	@Override
	public int getTypeOrderIndex() {
		return dataRangeIndex;
	}

}
