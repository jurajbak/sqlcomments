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

import java.util.regex.Pattern;

/**
 * Basic framework constants.
 * 
 */
public class Constants {

    /**
     * Line script identifier string.
     * <p>
     * Line script must start with this string. Everything after {@link #LINE_SCRIPT} is considered to be a control
     * script, until the end of line.
     * </p>
     */
    public static final String LINE_SCRIPT = "//@";

    /**
     * SQLComment directive string.
     */
    public static final String SQLCOMMENT = "@SQLComment";

    /**
     * Name of parameter 'name' in SQLComment directive.
     */
    public static final String PARAM_NAME = "name";

    /**
     * Name of parameter 'resultClass' in SQLComment directive.
     */
    public static final String PARAM_RESULTCLASS = "resultClass";

    /**
     * Name of parameter 'configClass' in SQLComment directive.
     */
    public static final String PARAM_CONFIGCLASS = "configClass";

    /**
     * Name of parameter 'baseClass' in SQLComment directive.
     */
    public static final String PARAM_BASECLASS = "baseClass";

    /**
     * Name of parameter 'database' in SQLComment directive.
     */
    public static final String PARAM_DATABASE = "database";

    /**
     * Token marking start of replacement.
     */
    public static final String REPLACEMENT_START_TOKEN = "${";

    /**
     * Token marking end of replacement.
     */
    public static final String REPLACEMENT_END_TOKEN = "}";

    /**
     * Replacement regular expression.
     * <p>
     * This pattern is used to find and process replacements in SQL statements at runtime.
     * </p>
     */
    public static final Pattern REPLACEMENT_PATTERN = Pattern.compile("\\$\\{(\\w+)}");

    /**
     * SQL param regular expression.
     * <p>
     * This pattern is used to find and process SQL parameters in statements at runtime.
     * </p>
     */
    public static final Pattern SQL_PARAM_PATTERN = Pattern.compile(":(\\w+)");
}
