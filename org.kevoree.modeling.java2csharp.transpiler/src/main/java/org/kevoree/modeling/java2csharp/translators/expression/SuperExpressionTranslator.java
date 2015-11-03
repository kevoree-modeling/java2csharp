
package org.kevoree.modeling.java2csharp.translators.expression;

import com.intellij.psi.PsiSuperExpression;
import org.kevoree.modeling.java2csharp.TranslationContext;

public class SuperExpressionTranslator {

  public static void translate(PsiSuperExpression element, TranslationContext ctx) {
    ctx.append("super");
  }

}
