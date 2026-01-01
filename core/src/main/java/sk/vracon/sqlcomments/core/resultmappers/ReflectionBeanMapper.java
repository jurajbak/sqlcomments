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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import sk.vracon.sqlcomments.core.ResultMapper;
import sk.vracon.sqlcomments.core.Utils;

/**
 * Simple result mapper based on reflection.
 * <p>
 * Generic result mapper based on reflection. It is intended for simple table/class mappings.
 * <p>
 * Implementation does not use custom type configuration so it is not able to map all data types correctly. 
 * 
 * @param <T> result class
 */
public class ReflectionBeanMapper<T> implements ResultMapper<T> {

    private Class<T> beanClass;

    private Map<String, Method> setters = new HashMap<String, Method>();

    public static <C> ReflectionBeanMapper<C> createInstance(Class<C> beanClass) {
        return new ReflectionBeanMapper<C>(beanClass);
    }

    public ReflectionBeanMapper(Class<T> beanClass) {
        if (beanClass == null) {
            throw new IllegalArgumentException("Bean class must be set");
        }

        this.beanClass = beanClass;
    }

    public T transform(ResultSet resultSet) throws SQLException {
        // Create bean instance
        T bean = createBeanInstance();

            // Iterate over result meta data and set values to bean
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = metaData.getColumnCount(); i > 0; i--) {

                String columnLabel = metaData.getColumnLabel(i);

                Method setter = setters.get(columnLabel);

                if (setter == null) {
                    setter = findSetter(columnLabel);
                }

                Object dbValue = resultSet.getObject(i);
				try {
					
					
					// Call setter
					setter.invoke(bean, dbValue);
				} catch (IllegalAccessException e) {
					throw new SQLException("Insufficient rights to call setter method "+setter.getName()+" Cause: " + e.getMessage(), e);
				} catch (IllegalArgumentException e) {
					throw new SQLException("Argument mismatch value class " + dbValue.getClass().getCanonicalName() + " setter: " + setter.getParameterTypes()[0] + " Cause: " + e.getMessage(), e);
				} catch (InvocationTargetException e) {
					throw new SQLException("Calling setter method failed: " + e.getMessage(), e);
				}
            }

        return bean;
    }

    private Method findSetter(String columnLabel) throws SQLException {
        Method setter = null;

        String setterName = "set" + Utils.transformToJavaIdentifier(columnLabel, true);

        Method[] methods = beanClass.getMethods();
        for (int j = 0; j < methods.length; j++) {
            Method method = methods[j];
            if (method.getName().equalsIgnoreCase(setterName) && method.getParameterTypes().length == 1) {
                setter = method;
                setters.put(columnLabel, setter);
                break;
            }
        }

        if (setter == null) {
            throw new SQLException("Method " + setterName + " not found for column " + columnLabel + " in class " + beanClass.getName());
        }
        return setter;
    }

    protected T createBeanInstance() throws SQLException {
        try {
            T bean = beanClass.newInstance();
            return bean;
        }
        catch (InstantiationException e) {
            throw new SQLException("Unable to instantiate class " + beanClass.getName() + ": " + e.getMessage(), e);
        }
        catch (IllegalAccessException e) {
            throw new SQLException("Insufficient rights to instantiate class " + beanClass.getName() + ": " + e.getMessage(), e);
        }
    }
}
