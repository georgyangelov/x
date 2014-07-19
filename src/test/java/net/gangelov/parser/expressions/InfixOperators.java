package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class InfixOperators {

    /* Basic test cases */

    @Test
    public void testPlusOperator() throws Exception {
        XParserTest.testExpression(
                "a + b",
                "(expression (expression (variable a)) + (expression (variable b)))"
        );
    }

    @Test
    public void testMinusOperator() throws Exception {
        XParserTest.testExpression(
                "a - b",
                "(expression (expression (variable a)) - (expression (variable b)))"
        );
    }

    @Test
    public void testMultiplyOperator() throws Exception {
        XParserTest.testExpression(
                "a * b",
                "(expression (expression (variable a)) * (expression (variable b)))"
        );
    }

    @Test
    public void testDivideOperator() throws Exception {
        XParserTest.testExpression(
                "a / b",
                "(expression (expression (variable a)) / (expression (variable b)))"
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
                        "(expression (variable a)) " +
                        "* " +
                        "(expression (variable b))" +
                      ") " +
                      "/ " +
                      "(expression (variable c))" +
                    ") " +
                    "* " +
                    "(expression (variable d))" +
                  ") " +
                  "* " +
                  "(expression (variable e))" +
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
                        "(expression (variable a)) " +
                        "+ " +
                        "(expression (variable b))" +
                      ") " +
                      "- " +
                      "(expression (variable c))" +
                    ") " +
                    "+ " +
                    "(expression (variable d))" +
                  ") " +
                  "+ " +
                  "(expression (variable e))" +
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
                    "(expression (variable a)) " +
                    "+ " +
                    "(expression " +
                      "(expression (variable b)) " +
                      "/ " +
                      "(expression (variable c))" +
                    ")" +
                  ") " +
                  "- " +
                  "(expression " +
                    "(expression (variable d)) " +
                    "* " +
                    "(expression (variable e))" +
                  ")" +
                ")"
        );
    }
}
