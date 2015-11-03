
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiThisExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;

public class ThisExpressionTranslator {

    public static void translate(PsiThisExpression element, TranslationContext ctx) {
        ctx.append("this");
    }

}
