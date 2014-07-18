package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class InfixOperatorExpressionTest {

    /* Basic test cases */

    @Test
    public void testPlusOperator() throws Exception {
        XParserTest.testExpression(
                "a + b",
                "(expression (expression (varExpression a)) (infixOperatorPrec2 +) (expression (varExpression b)))"
        );
    }

    @Test
    public void testMinusOperator() throws Exception {
        XParserTest.testExpression(
                "a - b",
                "(expression (expression (varExpression a)) (infixOperatorPrec2 -) (expression (varExpression b)))"
        );
    }

    @Test
    public void testMultiplyOperator() throws Exception {
        XParserTest.testExpression(
                "a * b",
                "(expression (expression (varExpression a)) (infixOperatorPrec1 *) (expression (varExpression b)))"
        );
    }

    @Test
    public void testDivideOperator() throws Exception {
        XParserTest.testExpression(
                "a / b",
                "(expression (expression (varExpression a)) (infixOperatorPrec1 /) (expression (varExpression b)))"
        );
    }


    /* Associativity tests */

    @Test
    public void testAssociativityPrec1() throws Exception {
        XParserTest.testExpression(
                "a * b / c * d * e",
                "(expression " + // * e
                  "(expression " + // * d
                    "(expression " + // / c
                      "(expression " + // a * b
                        "(expression (varExpression a)) " +
                        "(infixOperatorPrec1 *) " +
                        "(expression (varExpression b))" +
                      ") " +
                      "(infixOperatorPrec1 /) " +
                      "(expression (varExpression c))" +
                    ") " +
                    "(infixOperatorPrec1 *) " +
                    "(expression (varExpression d))" +
                  ") " +
                  "(infixOperatorPrec1 *) " +
                  "(expression (varExpression e))" +
                ")"
        );
    }

    @Test
    public void testAssociativityPrec2() throws Exception {
        XParserTest.testExpression(
                "a + b - c + d + e",
                "(expression " + // + e
                  "(expression " + // + d
                    "(expression " + // - c
                      "(expression " + // a + b
                        "(expression (varExpression a)) " +
                        "(infixOperatorPrec2 +) " +
                        "(expression (varExpression b))" +
                      ") " +
                      "(infixOperatorPrec2 -) " +
                      "(expression (varExpression c))" +
                    ") " +
                    "(infixOperatorPrec2 +) " +
                    "(expression (varExpression d))" +
                  ") " +
                  "(infixOperatorPrec2 +) " +
                  "(expression (varExpression e))" +
                ")"
        );
    }


    /* Precedence tests */

    @Test
    public void testPrecedence() throws Exception {
        XParserTest.testExpression(
                "a + b / c - d * e",
                "(expression " +
                  "(expression " +
                    "(expression (varExpression a)) " +
                    "(infixOperatorPrec2 +) " +
                    "(expression " +
                      "(expression (varExpression b)) " +
                      "(infixOperatorPrec1 /) " +
                      "(expression (varExpression c))" +
                    ")" +
                  ") " +
                  "(infixOperatorPrec2 -) " +
                  "(expression " +
                    "(expression (varExpression d)) " +
                    "(infixOperatorPrec1 *) " +
                    "(expression (varExpression e))" +
                  ")" +
                ")"
        );
    }
}
