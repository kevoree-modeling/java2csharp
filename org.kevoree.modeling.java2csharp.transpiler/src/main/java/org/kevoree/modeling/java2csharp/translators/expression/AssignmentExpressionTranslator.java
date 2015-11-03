package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiAssignmentExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.JavaTokenTranslator;

public class AssignmentExpressionTranslator {

    public static void translate(PsiAssignmentExpression element, TranslationContext ctx) {
        ExpressionTranslator.translate(element.getLExpression(), ctx);
        ctx.append(' ');
        JavaTokenTranslator.translate(element.getOperationSign(), ctx);
        ctx.append(' ');
        ExpressionTranslator.translate(element.getRExpression(), ctx);
    }

}
