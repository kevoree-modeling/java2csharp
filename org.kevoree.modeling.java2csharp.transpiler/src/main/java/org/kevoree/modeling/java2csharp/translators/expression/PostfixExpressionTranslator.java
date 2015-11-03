package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiPostfixExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.JavaTokenTranslator;

/**
 * Created by duke on 11/6/14.
 */
public class PostfixExpressionTranslator {

    public static void translate(PsiPostfixExpression element, TranslationContext ctx) {
        ExpressionTranslator.translate(element.getOperand(), ctx);
        JavaTokenTranslator.translate(element.getOperationSign(), ctx);
    }

}
