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
package sk.vracon.sqlcomments.maven.generate;

import sk.vracon.sqlcomments.maven.sql.SQLParser.Variable_placeholderContext;

public class PlaceholderInfo {

    private String name;
    private Class<?> javaClass;
    private boolean collection;
    private String mappedClass;
    private String mapperClass;
    private Variable_placeholderContext sqlContext;
    private AbstractStatementContext context;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class<?> javaClass) {
        this.javaClass = javaClass;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    /**
     * @return the mappedClass
     */
    public String getMappedClass() {
        return mappedClass;
    }

    /**
     * @param mappedClass the mappedClass to set
     */
    public void setMappedClass(String mappedClass) {
        this.mappedClass = mappedClass;
    }

    /**
     * @return the mapperClass
     */
    public String getMapperClass() {
        return mapperClass;
    }

    /**
     * @param mapperClass the mapperClass to set
     */
    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public Variable_placeholderContext getSqlContext() {
        return sqlContext;
    }

    public void setSqlContext(Variable_placeholderContext sqlContext) {
        this.sqlContext = sqlContext;
    }

    public AbstractStatementContext getContext() {
        return context;
    }

    public void setSelectContext(AbstractStatementContext selectContext) {
        this.context = selectContext;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PlaceholderInfo [name=");
        builder.append(name);
        builder.append(", javaClass=");
        builder.append(javaClass);
        builder.append(", collection=");
        builder.append(collection);
        builder.append(", mappedClass=");
        builder.append(mappedClass);
        builder.append(", mapperClass=");
        builder.append(mapperClass);
        builder.append(", sqlContext=");
        builder.append(sqlContext);
        builder.append("]");
        return builder.toString();
    }
}
