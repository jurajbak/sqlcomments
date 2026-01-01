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
package sk.vracon.sqlcomments.core.impl;

import java.util.Map;
import java.util.Set;

import sk.vracon.sqlcomments.core.StatementConfiguration;
import sk.vracon.sqlcomments.core.Type;

/**
 * Configuration wrapper class.
 */
public class StatementConfigurationWrapper implements StatementConfiguration {

	private StatementConfiguration parent;

	public StatementConfigurationWrapper(StatementConfiguration parent) {
		this.parent = parent;
	}

	public String statementName() {
		return parent.statementName();
	}

	public Class<?> baseClass() {
		return parent.baseClass();
	}

	public Map<String, Object> parameterMap() {
		return parent.parameterMap();
	}

	public Map<String, Type<?>> typeMap() {
		return parent.typeMap();
	}

	public Set<String> parametersAcceptingNull() {
		return parent.parametersAcceptingNull();
	}

	public Set<String> primaryKey() {
		return parent.primaryKey();
	}

	public Long limit() {
		return parent.limit();
	}

	public void limit(Long limit) {
		parent.limit(limit);
	}

	public Long offset() {
		return parent.offset();
	}

	public void offset(Long offset) {
		parent.offset(offset);
	}
}
