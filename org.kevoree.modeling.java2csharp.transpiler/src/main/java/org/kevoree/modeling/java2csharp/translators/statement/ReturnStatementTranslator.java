
package org.kevoree.modeling.java2csharp.translators.statement;

import com.intellij.psi.PsiReturnStatement;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.expression.ExpressionTranslator;

public class ReturnStatementTranslator {

  public static void translate(PsiReturnStatement element, TranslationContext ctx) {
    ctx.print("return");
    if (element.getReturnValue() != null) {
      ctx.append(' ');
      ExpressionTranslator.translate(element.getReturnValue(),ctx);
    }
    ctx.append(";\n");
  }

}
