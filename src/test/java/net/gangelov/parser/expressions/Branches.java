package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class Branches {
    @Test
    public void testBasicIf() throws Exception {
        XParserTest.testExpression(
                "if true\n" +
                        "1 + 2\n" +
                        "varName\n" +
                        "end",

                "(expression (branch if (expression (constant true)) (expressions \\n (expression (expression (constant 1)) + (expression (constant 2))) \\n (expression (variable varName)) \\n) end))"
        );
    }

    @Test
    public void testIfElse() throws Exception {
        XParserTest.testExpression(
                "if 1\n" +
                    "2\n" +
                "else\n" +
                    "3\n" +
                "end",

                "(expression (branch if (expression (constant 1)) (expressions \\n (expression (constant 2)) \\n) else (expressions \\n (expression (constant 3)) \\n) end))"
        );
    }

    @Test
    public void testElsif() throws Exception {
        XParserTest.testExpression(
                "if 1\n" +
                    "2\n" +
                "elsif 3\n" +
                    "4\n" +
                "end",

                "(expression (branch if (expression (constant 1)) (expressions \\n (expression (constant 2)) \\n) elsif (expression (constant 3)) (expressions \\n (expression (constant 4)) \\n) end))"
        );
    }
}
