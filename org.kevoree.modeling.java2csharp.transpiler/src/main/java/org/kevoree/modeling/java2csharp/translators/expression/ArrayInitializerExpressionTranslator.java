package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiArrayInitializerExpression;
import com.intellij.psi.PsiExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.TypeHelper;

public class ArrayInitializerExpressionTranslator {

    public static void translate(PsiArrayInitializerExpression element, TranslationContext ctx) {
        ctx.append("new ");
        ctx.append(TypeHelper.printType(element.getType(),ctx));
        ctx.append("[");
        PsiExpression[] initializers = element.getInitializers();
        if (initializers.length > 0) {
            for (int i = 0; i < initializers.length; i++) {
                ExpressionTranslator.translate(initializers[i], ctx);
                if (i != initializers.length - 1) {
                    ctx.append(", ");
                }
            }
        }
        ctx.append("]");
    }

}
