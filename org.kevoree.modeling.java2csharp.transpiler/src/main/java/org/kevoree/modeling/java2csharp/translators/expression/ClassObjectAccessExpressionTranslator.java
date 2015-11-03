package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiClassObjectAccessExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.TypeHelper;

public class ClassObjectAccessExpressionTranslator {

    public static void translate(PsiClassObjectAccessExpression element, TranslationContext ctx) {
        ctx.append(TypeHelper.printType(element.getOperand().getType(), ctx));
    }

}
