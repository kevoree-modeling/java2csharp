
package org.kevoree.modeling.java2csharp.translators.statement;

import com.intellij.psi.PsiContinueStatement;
import org.kevoree.modeling.java2csharp.TranslationContext;

public class ContinueStatementTranslator {

    public static void translate(PsiContinueStatement element, TranslationContext ctx) {
        ctx.print("continue");
        if (element.getLabelIdentifier() != null) {
            ctx.append(' ');
            ctx.append(element.getLabelIdentifier().getText().trim());
        }
        ctx.append(";\n");
    }

}
