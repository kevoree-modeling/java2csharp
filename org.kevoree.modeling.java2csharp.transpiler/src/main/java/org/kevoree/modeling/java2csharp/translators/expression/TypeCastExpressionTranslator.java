
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiTypeCastExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.TypeHelper;

public class TypeCastExpressionTranslator {

    public static void translate(PsiTypeCastExpression element, TranslationContext ctx) {
        ctx.append("(").append(TypeHelper.printType(element.getType(), ctx)).append(")");
        ExpressionTranslator.translate(element.getOperand(), ctx);
    }

}
