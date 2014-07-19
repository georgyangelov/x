package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class Parenthesized {
    @Test
    public void testParenthesizedConstExpression() throws Exception {
        XParserTest.testExpression(
                "(true)",
                "(expression ( (expression (constant true)) ))"
        );
    }

    @Test
    public void testParensPriority() throws Exception {
        XParserTest.testExpression(
                "1 * (2 + 3)",
                "(expression (expression (constant 1)) * (expression ( (expression (expression (constant 2)) + (expression (constant 3))) )))"
        );
    }
}
