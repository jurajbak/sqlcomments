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

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsBaseListener;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsLexer;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser.BaseClassParamContext;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser.CommentNameContext;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser.ConfigClassParamContext;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser.DeclarationContext;
import sk.vracon.sqlcomments.maven.sqlcomments.SQLCommentsParser.ResultClassParamContext;

public class StatementDeclarationParser {

    public static StatementDeclaration parseStatementDeclaration(String line) throws IOException, SyntaxErrorException {

        // Create input and antlr parser
        StringReader input = new StringReader(line);
        SQLCommentsLexer lexer = new SQLCommentsLexer(new ANTLRInputStream(input));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SQLCommentsParser parser = new SQLCommentsParser(tokenStream);

        // Set error listener to throw exception
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new SyntaxErrorException(msg);
            }
        });

        // Parse declaration
        DeclarationContext declarationContext = parser.declaration();

        final StatementDeclaration declaration = new StatementDeclaration();

        // Transform data
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new SQLCommentsBaseListener() {
            @Override
            public void enterCommentName(CommentNameContext ctx) {
                declaration.setName(ctx.Identifier().getText());
            }

            @Override
            public void enterConfigClassParam(ConfigClassParamContext ctx) {
                if (ctx.className() != null) {
                    declaration.setConfigurationClassName(ctx.className().getText());
                } else {
                    declaration.setDefaultConfigurationClass(true);
                }
            }

            @Override
            public void enterResultClassParam(ResultClassParamContext ctx) {
                if (ctx.className() != null) {
                    declaration.setResultClassName(ctx.className().getText());
                } else {
                    declaration.setDefaultResultClass(true);
                }
            }

            @Override
            public void enterBaseClassParam(BaseClassParamContext ctx) {
                declaration.setBaseClassName(ctx.className().getText());
            }

        }, declarationContext);

        return declaration;
    }
}
