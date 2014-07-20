package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class MethodCalls {
    @Test
    public void testOnVariableIDWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "var.method",
                "(expression (expression (variable var)) . (methodID method))"
        );
    }

    @Test
    public void testWithSingleArgument() throws Exception {
        XParserTest.testExpression(
                "var.method 3",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3))))"
        );
    }

    @Test
    public void testWithMultipleArguments() throws Exception {
        XParserTest.testExpression(
                "var.method 3, 4, 5",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3)) , (expression (constant 4)) , (expression (constant 5))))"
        );
    }

    @Test
    public void testWithArgumentsOnMultipleLines() throws Exception {
        XParserTest.testExpression(
                "var.method 3,\n4,\n5",
                "(expression (expression (variable var)) . (methodID method) (actualArgumentList (expression (constant 3)) , \\n (expression (constant 4)) , \\n (expression (constant 5))))"
        );
    }

    @Test
    public void testOnInfixExpressionWithoutArguments() throws Exception {
        XParserTest.testExpression(
                "(1 + 2).abs",
                "(expression (expression ( (expression (expression (constant 1)) + (expression (constant 2))) )) . (methodID abs))"
        );
    }

    @Test
    public void testPrecedence() throws Exception {
        XParserTest.testExpression(
                "1 + 2.abs",
                "(expression (expression (constant 1)) + (expression (expression (constant 2)) . (methodID abs)))"
        );
    }

    @Test
    public void testPrecedenceWithArguments() throws Exception {
        XParserTest.testExpression(
                "1 + 2.between? 3, 4",
                "(expression (expression (constant 1)) + (expression (expression (constant 2)) . (methodID between?) (actualArgumentList (expression (constant 3)) , (expression (constant 4)))))"
        );
    }

    @Test
    public void testAssociativity() throws Exception {
        XParserTest.testExpression(
                // 1.test(1, 2.test(2, 3, 4))
                "1.test 1, 2.test 2, 3, 4",
                "(expression (expression (constant 1)) . (methodID test) (actualArgumentList (expression (constant 1)) , (expression (expression (constant 2)) . (methodID test) (actualArgumentList (expression (constant 2)) , (expression (constant 3)) , (expression (constant 4))))))"
        );
    }

    @Test
    public void testAssociativityStatic() throws Exception {
        XParserTest.testExpression(
                // 1.test(1, 2.test(2, 3, 4))
                "A.test 1, B.test 2, 3, 4",
                "(expression (classID A) . (methodID test) (actualArgumentList (expression (constant 1)) , (expression (classID B) . (methodID test) (actualArgumentList (expression (constant 2)) , (expression (constant 3)) , (expression (constant 4))))))"
        );
    }

    @Test
    public void testParentheses() throws Exception {
        XParserTest.testExpression(
                "1.test 1, 2.test(2, 3), 4",
                "(expression (expression (constant 1)) . (methodID test) (actualArgumentList (expression (constant 1)) , (expression (expression (constant 2)) . (methodID test) (actualArgumentList ( (expression (constant 2)) , (expression (constant 3)) ))) , (expression (constant 4))))"
        );
    }

    @Test
    public void testParenthesesWithSingleExpression() throws Exception {
        XParserTest.testExpression(
                "1.test 1, 2.test(2), 4",
                "(expression (expression (constant 1)) . (methodID test) (actualArgumentList (expression (constant 1)) , (expression (expression (constant 2)) . (methodID test) (actualArgumentList ( (expression (constant 2)) ))) , (expression (constant 4))))"
        );
    }

    @Test
    public void testBlock() throws Exception {
        XParserTest.testExpression(
                "5.times:\n" +
                    "Global.print 'Hello World!'\n" +
                "end",

                "(expression (expression (constant 5)) . (methodID times) (block : \\n (code (expression (classID Global) . (methodID print) (actualArgumentList (expression (constant 'Hello World!')))) \\n) end))"
        );
    }

    @Test
    public void testBlockAfterArguments() throws Exception {
        XParserTest.testExpression(
                "5.times 1, 'hai':\n" +
                    "Global.print 1\n" +
                "end",

                "(expression (expression (constant 5)) . (methodID times) (actualArgumentList (expression (constant 1)) , (expression (constant 'hai'))) (block : \\n (code (expression (classID Global) . (methodID print) (actualArgumentList (expression (constant 1)))) \\n) end))"
        );
    }

    @Test
    public void testBlockWithArgumentList() throws Exception {
        XParserTest.testExpression(
                "5.times: index\n" +
                    "Global.print index, ' times'\n" +
                "end",

                "(expression (expression (constant 5)) . (methodID times) (block : (blockArgumentNames (variable index)) \\n (code (expression (classID Global) . (methodID print) (actualArgumentList (expression (variable index)) , (expression (constant ' times')))) \\n) end))"
        );
    }
}
