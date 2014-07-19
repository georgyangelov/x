package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class Constants {
    @Test
    public void testIntegerConst() throws Exception {
        XParserTest.testExpression(
                "1234",
                "(expression (constant 1234))"
        );
    }

    @Test
    public void testBoolConstTrue() throws Exception {
        XParserTest.testExpression(
                "true",
                "(expression (constant true))"
        );
    }

    @Test
    public void testBoolConstFalse() throws Exception {
        XParserTest.testExpression(
                "false",
                "(expression (constant false))"
        );
    }

    @Test
    public void testStringConstSingleQuote() throws Exception {
        XParserTest.testExpression(
                "'test \\' string'",
                "(expression (constant 'test \\' string'))"
        );
    }

    @Test
    public void testStringConstDoubleQuote() throws Exception {
        XParserTest.testExpression(
                "\"hello \\\"\"",
                "(expression (constant \"hello \\\"\"))"
        );
    }

    @Test
    public void testClassID() throws Exception {
        XParserTest.testExpression("Global.test", "(expression (classID Global) . (methodID test))");
    }

    @Test
    public void testFullyQualifiedClassID() throws Exception {
        XParserTest.testExpression("x::lang::Global.test", "(expression (classID x::lang::Global) . (methodID test))");
    }
}
