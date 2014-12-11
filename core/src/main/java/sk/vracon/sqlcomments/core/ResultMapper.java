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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Basic interface to transform {@link ResultSet} to Java objects.
 * 
 * <p>
 * Mapper implementation has only two responsibilities: to create instance of appropriate class and fill it with data
 * from given {@link ResultSet}. Framework takes responsibility for processing and closing {@link ResultSet}.
 * </p>
 * <p>
 * The same implementations are used for both transforming result lists and single results.
 * </p>
 * 
 * @param <T>
 *            Class of result objects.
 */
public interface ResultMapper<T> {

    /**
     * Transforms JDBC data into Java object.
     * <p>
     * This method is called for each row in {@link ResultSet}.
     * </p>
     * 
     * @param resultSet
     *            result set with data
     * @return filled instance of appropriate class
     * @throws SQLException
     *             in case of reading error
     */
    T transform(ResultSet resultSet) throws SQLException;
}
