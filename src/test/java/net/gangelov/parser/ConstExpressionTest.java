package net.gangelov.parser;

import org.junit.Test;

public class ConstExpressionTest {
    @Test
    public void testIntegerConst() throws Exception {
        XParserTest.testExpression(
                "1234",
                "(expression (constExpression 1234))"
        );
    }

    @Test
    public void testBoolConstTrue() throws Exception {
        XParserTest.testExpression(
                "true",
                "(expression (constExpression true))"
        );
    }

    @Test
    public void testBoolConstFalse() throws Exception {
        XParserTest.testExpression(
                "false",
                "(expression (constExpression false))"
        );
    }

    @Test
    public void testStringConstSingleQuote() throws Exception {
        XParserTest.testExpression(
                "'test \\' string'",
                "(expression (constExpression 'test \\' string'))"
        );
    }

    @Test
    public void testStringConstDoubleQuote() throws Exception {
        XParserTest.testExpression(
                "\"hello \\\"\"",
                "(expression (constExpression \"hello \\\"\"))"
        );
    }
}