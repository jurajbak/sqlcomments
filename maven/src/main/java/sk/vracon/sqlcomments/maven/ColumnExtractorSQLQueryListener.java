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

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.maven.plugin.logging.Log;

import sk.vracon.sqlcomments.core.Utils;
import sk.vracon.sqlcomments.maven.generate.AbstractStatementContext;
import sk.vracon.sqlcomments.maven.generate.ColumnIdentifier;
import sk.vracon.sqlcomments.maven.generate.ColumnInfo;
import sk.vracon.sqlcomments.maven.generate.DeleteContext;
import sk.vracon.sqlcomments.maven.generate.InsertContext;
import sk.vracon.sqlcomments.maven.generate.PlaceholderInfo;
import sk.vracon.sqlcomments.maven.generate.SelectContext;
import sk.vracon.sqlcomments.maven.generate.TableInfo;
import sk.vracon.sqlcomments.maven.generate.UpdateContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Case_expressionContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Cast_specificationContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Column_name_listContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Column_referenceContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Delete_statement_searchedContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Derived_columnContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.From_clauseContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.IdentifierContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.In_predicate_valueContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Insert_statementContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Nonparenthesized_value_expression_primaryContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Pattern_matching_predicateContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Qualified_asteriskContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Query_specificationContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Routine_invocationContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Scalar_subqueryContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Set_function_specificationContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Table_nameContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Table_primaryContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Unsigned_literalContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Unsigned_value_specificationContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Update_statementContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Variable_placeholderContext;
import sk.vracon.sqlcomments.maven.sql.SQLParser.Where_clauseContext;
import sk.vracon.sqlcomments.maven.sql.SQLParserBaseListener;

public class ColumnExtractorSQLQueryListener extends SQLParserBaseListener {

    private static final Pattern COLUMN_DIRECT_PATTERN = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*\\.[a-zA-Z_][a-zA-Z0-9_]*");

    private Log log;

    private Stack<AbstractStatementContext> contexts = new Stack<AbstractStatementContext>();
    private Stack<ProcessedPart> queryPart = new Stack<ProcessedPart>();
    private List<PlaceholderInfo> placeholders = new LinkedList<PlaceholderInfo>();

    public ColumnExtractorSQLQueryListener(Log log) {
        this.log = log;
    }

    @Override
    public void enterUpdate_statement(Update_statementContext ctx) {
        log.debug("Created update context");
        contexts.push(new UpdateContext());
        queryPart.push(ProcessedPart.UPDATE);
    }

    @Override
    public void enterInsert_statement(Insert_statementContext ctx) {
        log.debug("Created insert context");
        contexts.push(new InsertContext());
        queryPart.push(ProcessedPart.INSERT);
    }

    @Override
    public void enterDelete_statement_searched(Delete_statement_searchedContext ctx) {
        log.debug("Created delete context");
        contexts.push(new DeleteContext());
        queryPart.push(ProcessedPart.DELETE);
    }

    @Override
    public void enterQuery_specification(Query_specificationContext ctx) {
        log.debug("Created select context");
        contexts.push(new SelectContext());
        queryPart.push(ProcessedPart.SELECT);
    }

    @Override
    public void exitQuery_specification(Query_specificationContext ctx) {
        // End of query
        // Keep main query context
        if (contexts.size() > 1) {
            // Finish inner query, pop from stack
            log.debug("Finished select context");
            SelectContext finishedContext = (SelectContext) contexts.pop();
            queryPart.pop();

            SelectContext context = (SelectContext) contexts.peek();
            if (queryPart.peek() == ProcessedPart.SELECT) {
                // Set subquery to processed column
                ColumnInfo column = context.getColumns().peek();
                column.getSubqueries().add(finishedContext);
            } else if (queryPart.peek() == ProcessedPart.FROM) {
                // Set subquery to processed table
                TableInfo table = context.getTables().peek();
                table.getSubqueries().add(finishedContext);
            }
        }
    }

    @Override
    public void enterFrom_clause(From_clauseContext ctx) {
        // Select part ended, start From part - collect table definitions
        queryPart.pop();
        queryPart.push(ProcessedPart.FROM);
    }

    @Override
    public void enterWhere_clause(Where_clauseContext ctx) {
        // From part ended, started Where part - not interesting for us
        queryPart.pop();
        queryPart.push(ProcessedPart.WHERE);
    }

    @Override
    public void enterTable_primary(Table_primaryContext ctx) {
        // Table definition start
        // Only in select
        TableInfo table = new TableInfo();
        if (ctx.table_or_query_name() != null) {
            table.setName(ctx.table_or_query_name().getText());
        }
        if (ctx.identifier() != null) {
            table.setAlias(ctx.identifier().getText());
        }
        contexts.peek().getTables().push(table);
    }

    @Override
    public void enterTable_name(Table_nameContext ctx) {
        // Used by all statement types, but SELECT is handled by enterTable_primary (due to a aliases)
        AbstractStatementContext context = contexts.peek();
        if (!(context instanceof SelectContext)) {
            TableInfo table = new TableInfo();
            table.setName(ctx.getText());
            context.getTables().push(table);
        }
    }

    @Override
    public void enterDerived_column(Derived_columnContext ctx) {
        // Called only from select
        // Register new column
        ColumnInfo column = new ColumnInfo();

        // Get column alias
        String alias = null;
        if (ctx.as_clause() != null) {
            alias = ctx.as_clause().Identifier().getText();
        } else {
            alias = ctx.getText();

            if (COLUMN_DIRECT_PATTERN.matcher(alias).matches()) {
                // Column used without alias but in form 'tbcalias.column'
                // We can get column name from it
                int columnNamePos = alias.lastIndexOf('.');
                alias = alias.substring(columnNamePos + 1);
            }
        }

        column.setColumnName(alias);
        column.setJavaIdentifier(Utils.transformToJavaIdentifier(alias, false));

        ((SelectContext) contexts.peek()).getColumns().push(column);
        log.debug("Registered column " + column);
    }

    @Override
    public void enterNonparenthesized_value_expression_primary(Nonparenthesized_value_expression_primaryContext ctx) {
        ProcessedPart processedPart = queryPart.peek();
        if (processedPart == ProcessedPart.SELECT) {
            // Collecting columns
            SelectContext context = (SelectContext) contexts.peek();
            ColumnInfo column = context.getColumns().peek();

            ParseTree child = ctx.getChild(0);

            if (child instanceof Column_referenceContext) {
                // Direct column reference
                Column_referenceContext childCtx = (Column_referenceContext) child;
                column.getReferences().add(new ColumnIdentifier(childCtx.tb_name == null ? null : childCtx.tb_name.getText(), childCtx.name.getText()));
            } else if (child instanceof Unsigned_value_specificationContext) {
                // Constant - numeric or string
                Unsigned_value_specificationContext childCtx = (Unsigned_value_specificationContext) child;
                Unsigned_literalContext unsignedLiteral = childCtx.unsigned_literal();
                if (unsignedLiteral.unsigned_numeric_literal() != null) {
                    // Numerical value
                    ColumnIdentifier columnIdentifier = new ColumnIdentifier();
                    column.getReferences().add(columnIdentifier);
                    try {
                        Long.parseLong(unsignedLiteral.unsigned_numeric_literal().getText());
                        // Number is not decimal
                        columnIdentifier.setJavaType(Long.class);
                    }
                    catch (NumberFormatException e) {
                        // Number is decimal
                        columnIdentifier.setJavaType(Double.class);
                    }
                } else if (unsignedLiteral.general_literal() != null) {
                    // String value
                    ColumnIdentifier columnIdentifier = new ColumnIdentifier();
                    columnIdentifier.setJavaType(String.class);
                    column.getReferences().add(columnIdentifier);
                } else {
                    log.warn("Unrecognized type at line " + unsignedLiteral.getStart().getLine() + ":" + unsignedLiteral.getStart().getCharPositionInLine()
                            + " text: " + unsignedLiteral.getText());
                }
            } else if (child instanceof Routine_invocationContext) {
                // Routine (procedure) call
                // We don't know return type of procedure
                column.setJavaClass(Object.class);
            } else if (child instanceof Scalar_subqueryContext) {
                // Subquery
                // TODO maybe not needed - column reference and value
                // specification will catch it
            } else if (child instanceof Cast_specificationContext) {
                // Cast to type
                // TODO maybe not needed - column reference and value
                // specification will catch it
            } else if (child instanceof Set_function_specificationContext) {
                // Aggregate function (min, max, ...)
                // TODO maybe not needed - column reference and value
                // specification will catch it
            } else if (child instanceof Case_expressionContext) {
                // Case expression
                // TODO maybe not needed - column reference and value
                // specification will catch it
            } else {
                log.error("Unsupported type: " + child.getClass().getName());
            }
        }
    }

    public void enterQualified_asterisk(Qualified_asteriskContext ctx) {
        // Asterix column definition - could be only in select part
        // This column will be later replaced with real columns

        ColumnInfo column = new ColumnInfo();
        column.setAsterix(true);
        SelectContext context = (SelectContext) contexts.peek();
        context.getColumns().push(column);

        ColumnIdentifier columnIdentifier = new ColumnIdentifier();
        if (ctx.tb_name != null) {
            // All column from specified table or subselect
            columnIdentifier.setTableAlias(ctx.tb_name.getText());
        }
        column.getReferences().add(columnIdentifier);
    }

    @Override
    public void enterVariable_placeholder(Variable_placeholderContext ctx) {
        PlaceholderInfo placeholder = new PlaceholderInfo();

        // Remove colon from the beginning
        placeholder.setName(ctx.getText().substring(1));
        placeholder.setSqlContext(ctx);
        placeholder.setSelectContext(contexts.peek());

        if (ctx.getParent() instanceof In_predicate_valueContext) {
            // Placeholder is used inside IN clause - it must be Collection
            placeholder.setCollection(true);
        }
        if (ctx.getParent() instanceof Pattern_matching_predicateContext) {
            // Placeholder is used inside pattern clause (LIKE, REGEXP, ...) - it must be String
            placeholder.setJavaClass(String.class);
        }

        placeholders.add(placeholder);
    }

    @Override
    public void enterColumn_name_list(Column_name_listContext ctx) {
        // Could be used in joins and insert
        AbstractStatementContext context = contexts.peek();
        if (context instanceof InsertContext) {
            InsertContext insertContext = (InsertContext) context;

            if (ctx.identifier() != null) {
                for (IdentifierContext identifier : ctx.identifier()) {
                    insertContext.getColumnIdentifiers().add(new ColumnIdentifier(null, identifier.getText()));
                }
            }
        }
    }

    public List<PlaceholderInfo> getPlaceholders() {
        return placeholders;
    }

    public AbstractStatementContext getPrimaryContext() {
        if (contexts.size() > 0) {
            return contexts.get(0);
        } else {
            return null;
        }
    }

    private enum ProcessedPart {
        SELECT, FROM, WHERE, UPDATE, INSERT, DELETE
    }
}
