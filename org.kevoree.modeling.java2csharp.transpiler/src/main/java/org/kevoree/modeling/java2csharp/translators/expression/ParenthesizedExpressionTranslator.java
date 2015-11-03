
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiParenthesizedExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;

public class ParenthesizedExpressionTranslator {

    public static void translate(PsiParenthesizedExpression element, TranslationContext ctx) {
        ctx.append('(');
        ExpressionTranslator.translate(element.getExpression(), ctx);
        ctx.append(')');
    }

}
