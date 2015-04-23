package sk.vracon.sqlcomments.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import sk.vracon.sqlcomments.core.dialect.OracleDialect;

public class StatementContainerTest {

    @Test
    public void testAddStatement_Raw() {

        String statementName = "xxx.sql";
        String statementText = "some sql statement";

        StatementContainer container = new StatementContainer();
        Statement newStatement = container.addStatement(statementName, statementText);

        assertNotNull(newStatement);
        assertEquals(statementName, newStatement.getName());
        assertNotNull(newStatement.getRows());
        assertEquals(1, newStatement.getRows().size());
        assertEquals(statementText, newStatement.getRows().get(0).getLine());

        Statement statement2 = container.getStatement((ClassLoader) null, statementName);

        assertEquals(newStatement, statement2);
    }

    @Test(expected = StatementNotFoundException.class)
    public void testGetStatement_NotFound() {

        StatementContainer container = new StatementContainer();

        container.getStatement(this.getClass().getClassLoader(), "xxx.sql");

        fail(StatementNotFoundException.class.getName() + " should be thrown.");
    }

    @Test
    public void testGetStatement_GenericDB() {

        StatementContainer container = new StatementContainer();

        Statement statement = container.getStatement(this.getClass().getClassLoader(), "sk/vracon/sqlcomments/core/select1.sql");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(1, statement.getRows().size());
    }

    @Test
    public void testGetStatement_SpecificDB() {

        StatementContainer container = new StatementContainer();
        container.setDialect(new OracleDialect());

        Statement statement = container.getStatement(this.getClass().getClassLoader(), "sk/vracon/sqlcomments/core/select1.sql");

        assertNotNull(statement);
        assertNotNull(statement.getRows());
        assertEquals(2, statement.getRows().size());
    }
}
