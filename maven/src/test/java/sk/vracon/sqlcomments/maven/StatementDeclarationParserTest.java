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

import org.junit.Assert;
import org.junit.Test;

public class StatementDeclarationParserTest {

    @Test
    public void test1() throws Exception {

        String text = "@SQLComment(name=\"simpleSelect\", resultClass = default)";

        StatementDeclaration declaration = StatementDeclarationParser.parseStatementDeclaration(text);

        Assert.assertNotNull(declaration);
        Assert.assertEquals("simpleSelect", declaration.getName());
        Assert.assertNull(declaration.getBaseClassName());
        Assert.assertNull(declaration.getResultClassName());
        Assert.assertTrue(declaration.isDefaultResultClass());
        Assert.assertNull(declaration.getConfigurationClassName());
        Assert.assertFalse(declaration.isDefaultConfigurationClass());
    }

    @Test
    public void test2() throws Exception {

        String text = "@SQLComment(name=\"simpleSelect\", baseClass=\"sk.vracon.BaseClass\", resultClass =\"sk.vracon.Result\", configClass=default)";

        StatementDeclaration declaration = StatementDeclarationParser.parseStatementDeclaration(text);

        Assert.assertNotNull(declaration);
        Assert.assertEquals("simpleSelect", declaration.getName());
        Assert.assertEquals("sk.vracon.BaseClass", declaration.getBaseClassName());
        Assert.assertEquals("sk.vracon.Result", declaration.getResultClassName());
        Assert.assertFalse(declaration.isDefaultResultClass());
        Assert.assertNull(declaration.getConfigurationClassName());
        Assert.assertTrue(declaration.isDefaultConfigurationClass());
    }

    @Test
    public void test3() throws Exception {

        String text = "@SQLComment(name=\"simpleSelect\", baseClass=\"sk.vracon.BaseClass\", resultClass =\"sk.vracon.Result\", configClass=\"sk.vracon.Config\")";

        StatementDeclaration declaration = StatementDeclarationParser.parseStatementDeclaration(text);

        Assert.assertNotNull(declaration);
        Assert.assertEquals("simpleSelect", declaration.getName());
        Assert.assertEquals("sk.vracon.BaseClass", declaration.getBaseClassName());
        Assert.assertEquals("sk.vracon.Result", declaration.getResultClassName());
        Assert.assertFalse(declaration.isDefaultResultClass());
        Assert.assertEquals("sk.vracon.Config", declaration.getConfigurationClassName());
        Assert.assertFalse(declaration.isDefaultConfigurationClass());
    }
}
