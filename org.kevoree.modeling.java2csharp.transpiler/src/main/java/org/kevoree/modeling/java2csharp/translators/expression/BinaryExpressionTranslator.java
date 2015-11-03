
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiBinaryExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.JavaTokenTranslator;

public class BinaryExpressionTranslator {

    public static void translate(PsiBinaryExpression element, TranslationContext ctx) {
        ExpressionTranslator.translate(element.getLOperand(), ctx);
        ctx.append(' ');
        JavaTokenTranslator.translate(element.getOperationSign(), ctx);
        ctx.append(' ');
        ExpressionTranslator.translate(element.getROperand(), ctx);
    }

}
