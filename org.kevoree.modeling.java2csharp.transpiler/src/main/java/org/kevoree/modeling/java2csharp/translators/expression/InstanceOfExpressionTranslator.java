
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiInstanceOfExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.TypeHelper;

public class InstanceOfExpressionTranslator {

    public static void translate(PsiInstanceOfExpression element, TranslationContext ctx) {
        ExpressionTranslator.translate(element.getOperand(), ctx);
        ctx.append(" instanceof ").append(TypeHelper.printType(element.getCheckType().getType(), ctx));
    }

}
