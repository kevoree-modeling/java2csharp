
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.*;
import org.kevoree.modeling.java2csharp.TranslationContext;

public class MethodCallExpressionTranslator {

    public static void translate(PsiMethodCallExpression element, TranslationContext ctx) {
        ReferenceExpressionTranslator.translate(element.getMethodExpression(), ctx);
        if (!element.getMethodExpression().toString().endsWith(".length")) {
            ctx.append('(');
            PsiExpression[] arguments = element.getArgumentList().getExpressions();
            for (int i = 0; i < arguments.length; i++) {
                ExpressionTranslator.translate(arguments[i], ctx);
                if (i != arguments.length - 1) {
                    ctx.append(", ");
                }
            }
            ctx.append(")");
        }
    }

}
