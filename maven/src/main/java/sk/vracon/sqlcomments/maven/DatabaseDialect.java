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

import java.sql.Types;
import java.util.Set;

public interface DatabaseDialect {

    /**
     * Returns most generic class from set of classes.
     * 
     * @param classes
     *            classes
     * @return most generic class
     */
    public Class<?> getMostGenericClass(Set<Class<?>> classes);

    /**
     * Returns java class for specified SQL type.
     * 
     * <p>
     * This method is making mapping between java and SQL types.
     * </p>
     * 
     * @param sqlTypeNumber
     *            SQL type number according to {@link Types}
     * @param sqlTypeName
     *            SQL type name as returned by database
     * @return java class corresponding to SQL type
     */
    public Class<?> getJavaTypeForSQL(int sqlTypeNumber, String sqlTypeName);
}
