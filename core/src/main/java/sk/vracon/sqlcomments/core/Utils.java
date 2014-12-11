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

/**
 * Utility methods.
 * 
 */
public class Utils {

    /**
     * Creates java identifier compatible string.
     * 
     * @param string
     *            string to process
     * @param firstLetterUpperCase
     *            indicates whether to make first letter upper case or lower case
     * @return java identifier compatible string
     */
    public static String transformToJavaIdentifier(String string, boolean firstLetterUpperCase) {
        if (string == null) {
            // Nothing to do
            return null;
        }

        char[] chars = string.toCharArray();

        // Skip not valid characters from the beginning
        int pos = 0;
        while (pos < chars.length && !Character.isJavaIdentifierStart(chars[pos])) {
            pos++;
        }

        boolean hasLowerCase = false;
        for (int i = pos; i < chars.length; i++) {
            if (Character.isLowerCase(chars[pos])) {
                hasLowerCase = true;
                break;
            }
        }

        // Create camel case identifier
        StringBuffer buf = new StringBuffer();
        boolean toUpperCaseNextChar = false;
        while (pos < chars.length) {
            if (buf.length() == 0) {
                // First letter
                if (firstLetterUpperCase) {
                    buf.append(Character.toUpperCase(chars[pos]));
                } else {
                    buf.append(Character.toLowerCase(chars[pos]));
                }
            } else if (!Character.isJavaIdentifierPart(chars[pos]) || Character.isWhitespace(chars[pos]) || chars[pos] == '_') {
                toUpperCaseNextChar = true;
            } else {
                if (toUpperCaseNextChar) {
                    buf.append(Character.toUpperCase(chars[pos]));
                    toUpperCaseNextChar = false;
                } else if (hasLowerCase) {
                    // String already has some lower case characters - don't make it all lower case
                    buf.append(chars[pos]);
                } else {
                    // All in upper case - make it lower
                    buf.append(Character.toLowerCase(chars[pos]));
                }
            }
            pos++;
        }
        return buf.toString();
    }
}
