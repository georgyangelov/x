package net.gangelov.parser;

import org.junit.Test;

public class MethodCallExpressionTest {
    @Test
    public void testSendOnVariableIDWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "var.method",
                "(expression (expression (varExpression var)) . (methodID method))"
        );
    }

    @Test
    public void testSendWithSingleArgument() throws Exception {
        XParserTest.testExpression(
                "var.method 3",
                "(expression (expression (varExpression var)) . (methodID method) (actualArgumentList (expression (constExpression 3))))"
        );
    }

    @Test
    public void testSendWithMultipleArguments() throws Exception {
        XParserTest.testExpression(
                "var.method 3, 4, 5",
                "(expression (expression (varExpression var)) . (methodID method) (actualArgumentList (expression (constExpression 3)) , (expression (constExpression 4)) , (expression (constExpression 5))))"
        );
    }

    @Test
    public void testSendWithArgumentsOnMultipleLines() throws Exception {
        XParserTest.testExpression(
                "var.method 3,\n4,\n5",
                "(expression (expression (varExpression var)) . (methodID method) (actualArgumentList (expression (constExpression 3)) , \\n (expression (constExpression 4)) , \\n (expression (constExpression 5))))"
        );
    }

    @Test
    public void testSendOnInfixExpressionWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "(1 + 2).abs",
                "(expression (expression ( (expression (expression (constExpression 1)) (infixOperatorPrec2 +) (expression (constExpression 2))) )) . (methodID abs))"
        );
    }

    @Test
    public void testSendPrecedence() throws Exception {
        XParserTest.testExpression(
                "1 + 2.abs",
                "(expression (expression (constExpression 1)) (infixOperatorPrec2 +) (expression (expression (constExpression 2)) . (methodID abs)))"
        );
    }

    @Test
    public void testSendPrecedenceWithArguments() throws Exception {
        XParserTest.testExpression(
                "1 + 2.between? 3, 4",
                "(expression (expression (constExpression 1)) (infixOperatorPrec2 +) (expression (expression (constExpression 2)) . (methodID between?) (actualArgumentList (expression (constExpression 3)) , (expression (constExpression 4)))))"
        );
    }
}
