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
package sk.vracon.sqlcomments.core;

import java.util.Map;
import java.util.Set;

/**
 * Basic interface representing statement configuration.
 * <p>
 * Statement configuration means mainly its name and parameters controlling creation of SQL string.
 * </p>
 */
public interface StatementConfiguration {

    /**
     * Returns statement name.
     * <p>
     * Statement name can be represented in two ways:
     * <ol>
     * <li>{@link #baseClass()} returns null - name must be full path to sql file including file extension.</li>
     * <li>{@link #baseClass()} returns real class - name is just a part of path. Full name (and full path) is
     * concatenation of base classes' path this name and .sql extension.</li>
     * </ol>
     * </p>
     * <p>
     * Statement name may not be null.
     * </p>
     * 
     * @return statement name.
     * 
     * @see #baseClass()
     */
    public String statementName();

    /**
     * Returns base class.
     * <p>
     * Base class has two functions:
     * <ol>
     * <li>Class name is used as a prefix of statement file name.</li>
     * <li>Class loader of this class is used also to load statement file.</li>
     * </ol>
     * </p>
     * <p>
     * Statements generated from java comments use as a base class the same class from which they were extracted.
     * </p>
     * 
     * @return base class or null
     * 
     * @see #statementName()
     */
    public Class<?> baseClass();

    /**
     * Map of parameters to construct statement in runtime.
     * <p>
     * Map contains parameters which can be used as each of three possibilities:
     * <ul>
     * <li>SQL parameter</li>
     * <li>Script parameter</li>
     * <li>Replacement</li>
     * </ul>
     * </p>
     * <p>
     * In runtime if parameter is used as a SQL parameter and has null value or is missing at all - it means to skip
     * statement row. Exception to this rule are parameters returned by {@link #generateParametersAcceptingNull()}.
     * </p>
     * 
     * @return parameters to construct statement
     * 
     * @see #generateParametersAcceptingNull()
     */
    public Map<String, Object> generateParameterMap();

    /**
     * Returns parameters for which null doesn't mean to skip row.
     * <p>
     * This is useful mainly in Insert statements, rarely otherwise.
     * </p>
     * 
     * @return parameters for which null doesn't mean to skip row
     * 
     * @see #generateParameterMap()
     */
    public Set<String> generateParametersAcceptingNull();

}
