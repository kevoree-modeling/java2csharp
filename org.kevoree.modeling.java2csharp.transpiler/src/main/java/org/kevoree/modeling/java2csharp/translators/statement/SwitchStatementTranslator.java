
package org.kevoree.modeling.java2csharp.translators.statement;

import com.intellij.psi.PsiSwitchStatement;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.CodeBlockTranslator;
import org.kevoree.modeling.java2csharp.translators.expression.ExpressionTranslator;

public class SwitchStatementTranslator {

    public static void translate(PsiSwitchStatement element, TranslationContext ctx) {
        ctx.print("switch (");
        ExpressionTranslator.translate(element.getExpression(), ctx);
        ctx.append(") {\n");
        ctx.increaseIdent();
        CodeBlockTranslator.translate(element.getBody(), ctx);
        ctx.decreaseIdent();
        ctx.print("}\n");
    }

}
