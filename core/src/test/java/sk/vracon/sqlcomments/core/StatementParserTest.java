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

import static org.junit.Assert.*;
import org.junit.Test;

public class StatementParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullStatement() {
        new StatementParser().parseStatement(null, "javascript");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEngine() {
        new StatementParser().parseStatement("some text", null);
    }

    @Test
    public void testEmptyStatement() {
        Statement statement = new StatementParser().parseStatement("", "javascript");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(0, statement.getRows().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testNonExistingEngine() {
        new StatementParser().parseStatement("some text", "ImaginaryEngine");
    }

    @Test
    public void testSimpleSelect() {
        String query = "select * from accounts";

        Statement statement = new StatementParser().parseStatement(query, "javascript");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(1, statement.getRows().size());
        assertEquals(query, statement.getRows().get(0).getLine());
        assertNull(statement.getRows().get(0).getReplacementParameters());
        assertNull(statement.getRows().get(0).getSqlParameters());
    }

    @Test
    public void testSelectWithSQLParams() {
        StringBuilder query = new StringBuilder();
        query.append("select * from accounts \n");
        query.append("where id = :sqlParam");

        Statement statement = new StatementParser().parseStatement(query.toString(), "javascript");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(2, statement.getRows().size());
        assertEquals("select * from accounts ", statement.getRows().get(0).getLine());
        assertNull(statement.getRows().get(0).getReplacementParameters());
        assertNotNull(statement.getRows().get(1).getSqlParameters());
        assertEquals(1, statement.getRows().get(1).getSqlParameters().size());
    }

    @Test
    public void testSelectWithScript() {
        StringBuilder query = new StringBuilder();
        query.append("select * from accounts \n");
        query.append("where id = :sqlParam -- Regular comment //@ x == 1 && y == 2");

        Statement statement = new StatementParser().parseStatement(query.toString(), "javascript");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(2, statement.getRows().size());
        assertEquals("select * from accounts ", statement.getRows().get(0).getLine());
        assertNull(statement.getRows().get(0).getReplacementParameters());
        assertNotNull(statement.getRows().get(1).getSqlParameters());
        assertEquals(1, statement.getRows().get(1).getSqlParameters().size());
        assertNotNull(statement.getRows().get(1).getScript());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithBrokenScript() {
        StringBuilder query = new StringBuilder();
        query.append("select * from accounts \n");
        query.append("where id = :sqlParam -- Regular comment //@ x == 1 ERROR && y == 2");

        new StatementParser().parseStatement(query.toString(), "javascript");
    }
}
