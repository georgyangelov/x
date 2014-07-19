package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class MethodCalls {
    @Test
    public void testSendOnVariableIDWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "var.method",
                "(expression (expression (variable var)) . (methodID method))"
        );
    }

    @Test
    public void testSendWithSingleArgument() throws Exception {
        XParserTest.testExpression(
                "var.method 3",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3))))"
        );
    }

    @Test
    public void testSendWithMultipleArguments() throws Exception {
        XParserTest.testExpression(
                "var.method 3, 4, 5",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3)) , (expression (constant 4)) , (expression (constant 5))))"
        );
    }

    @Test
    public void testSendWithArgumentsOnMultipleLines() throws Exception {
        XParserTest.testExpression(
                "var.method 3,\n4,\n5",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3)) , \\n (expression (constant 4)) , \\n (expression (constant 5))))"
        );
    }

    @Test
    public void testSendOnInfixExpressionWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "(1 + 2).abs",
                "(expression (expression ( (expression (expression (constant 1)) + (expression (constant 2))) )) . (methodID abs))"
        );
    }

    @Test
    public void testSendPrecedence() throws Exception {
        XParserTest.testExpression(
                "1 + 2.abs",
                "(expression (expression (constant 1)) + (expression (expression (constant 2)) . (methodID abs)))"
        );
    }

    @Test
    public void testSendPrecedenceWithArguments() throws Exception {
        XParserTest.testExpression(
                "1 + 2.between? 3, 4",
                "(expression (expression (constant 1)) + (expression (expression (constant 2)) . (methodID between?) (actualArgumentList (expression (constant 3)) , (expression (constant 4)))))"
        );
    }
}
