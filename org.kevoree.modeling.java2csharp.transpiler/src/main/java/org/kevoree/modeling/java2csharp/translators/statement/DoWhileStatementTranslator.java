
package org.kevoree.modeling.java2csharp.translators.statement;

import com.intellij.psi.PsiDoWhileStatement;
import org.kevoree.modeling.java2csharp.TranslationContext;
import org.kevoree.modeling.java2csharp.translators.expression.ExpressionTranslator;

public class DoWhileStatementTranslator {

  public static void translate(PsiDoWhileStatement element, TranslationContext ctx) {
    ctx.print("do {\n");
    ctx.increaseIdent();
    StatementTranslator.translate(element.getBody(), ctx);
    ctx.decreaseIdent();
    ctx.print("} while (");
    ExpressionTranslator.translate(element.getCondition(),ctx);
    ctx.append(")\n");
  }
}
