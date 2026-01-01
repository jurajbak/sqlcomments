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

/**
 * Empty implementation of {@link StatementConfiguration}.
 */
public abstract class StatementConfigurationAdapter implements StatementConfiguration {

    private String statementName;
    private Class<?> baseClass;
    private Long limit;
    private Long offset;

    /**
     * Creates instance of configuration with mandatory fields.
     * 
     * @param statementName
     *            statement name
     * @param baseClass
     *            base class
     */
    public StatementConfigurationAdapter(String statementName, Class<?> baseClass) {
        this.statementName = statementName;
        this.baseClass = baseClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sk.vracon.sqlcomments.core.StatementConfiguration#statementName()
     */
    public String statementName() {
        return statementName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sk.vracon.sqlcomments.core.StatementConfiguration#baseClass()
     */
    public Class<?> baseClass() {
        return baseClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sk.vracon.sqlcomments.core.StatementConfiguration#generateParameterMap()
     */
    public Map<String, Object> parameterMap() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sk.vracon.sqlcomments.core.StatementConfiguration#generateParametersAcceptingNull()
     */
    public Set<String> parametersAcceptingNull() {
        return null;
    }
    
    @Override
    public Set<String> primaryKey() {
    	return null;
    }

    public Long limit() {
        return limit;
    }

    public void limit(Long limit) {
        this.limit = limit;
    }

    public Long offset() {
        return offset;
    }

    public void offset(Long offset) {
        this.offset = offset;
    }
}