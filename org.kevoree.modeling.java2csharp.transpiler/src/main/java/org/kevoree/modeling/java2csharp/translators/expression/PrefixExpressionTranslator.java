package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiPrefixExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.JavaTokenTranslator;

/**
 * Created by duke on 11/6/14.
 */
public class PrefixExpressionTranslator {

    public static void translate(PsiPrefixExpression element, TranslationContext ctx) {
        JavaTokenTranslator.translate(element.getOperationSign(), ctx);
        ExpressionTranslator.translate(element.getOperand(), ctx);
    }

}
