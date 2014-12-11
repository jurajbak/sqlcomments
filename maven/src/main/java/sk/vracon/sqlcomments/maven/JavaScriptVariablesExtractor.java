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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptBaseListener;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser.ArgumentListContext;
import sk.vracon.sqlcomments.maven.ecmascript.ECMAScriptParser.IdentifierExpressionContext;

public class JavaScriptVariablesExtractor extends ECMAScriptBaseListener {

    private Set<String> variables = new HashSet<String>();

    @Override
    public void enterIdentifierExpression(IdentifierExpressionContext ctx) {
        // It is hard to distinguish variable identifier from function
        // If identifier is followed by parenthesis - it is function
        List<ParseTree> siblings = ctx.getParent().children;
        int currentChildIndex = siblings.indexOf(ctx);
        if (siblings.size() - 1 == currentChildIndex || !(siblings.get(currentChildIndex + 1) instanceof ArgumentListContext)) {
            // Identifier is the last child or next child is not argument list - it is not a function
            variables.add(ctx.Identifier().getText());
        }
    }

    public Set<String> getVariables() {
        return variables;
    }
}
