
package org.kevoree.modeling.java2csharp.translators.statement;

import com.intellij.psi.PsiForStatement;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.expression.ExpressionTranslator;

public class ForStatementTranslator {

    public static void translate(PsiForStatement element, TranslationContext ctx) {
        ctx.print("for (");
        if (element.getInitialization() != null) {
            StatementTranslator.translate(element.getInitialization(), ctx);
        }
        ctx.append("; ");
        if (element.getCondition() != null) {
            ExpressionTranslator.translate(element.getCondition(), ctx);
        }
        ctx.append("; ");
        if (element.getUpdate() != null) {
            StatementTranslator.translate(element.getUpdate(), ctx);
        }
        ctx.append(") {\n");
        ctx.increaseIdent();
        StatementTranslator.translate(element.getBody(), ctx);
        ctx.decreaseIdent();
        ctx.print("}\n");
    }

}
