package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class ParenthesizedExpressionTest {
    @Test
    public void testParenthesizedConstExpression() throws Exception {
        XParserTest.testExpression(
                "(true)",
                "(expression ( (expression (constExpression true)) ))"
        );
    }

    @Test
    public void testParensPriority() throws Exception {
        XParserTest.testExpression(
                "1 * (2 + 3)",
                "(expression (expression (constExpression 1)) (infixOperatorPrec1 *) (expression ( (expression (expression (constExpression 2)) (infixOperatorPrec2 +) (expression (constExpression 3))) )))"
        );
    }
}
