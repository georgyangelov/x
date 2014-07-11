package net.gangelov.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XParserTest {

    private XParser createParser(String input) {
        ANTLRInputStream stream = new ANTLRInputStream(input);
        XLexer lexer = new XLexer(stream);

        return new XParser(new CommonTokenStream(lexer));
    }

    private void testExpression(String expression, String expectedParse) {
        XParser parser = createParser(expression);

        assertEquals(expectedParse, parser.expression().toStringTree(parser));
    }

    @Test
    public void testIntegerConst() throws Exception {
        testExpression("1234", "(expression (constExpression 1234))");
    }

    @Test
    public void testBoolConstTrue() throws Exception {
        testExpression("true", "(expression (constExpression true))");
    }

    @Test
    public void testBoolConstFalse() throws Exception {
        testExpression("false", "(expression (constExpression false))");
    }

    @Test
    public void testStringConstSingleQuote() throws Exception {
        testExpression("'test \\' string'", "(expression (constExpression 'test \\' string'))");
    }

    @Test
    public void testStringConstDoubleQuote() throws Exception {
        testExpression("\"hello \\\"\"", "(expression (constExpression \"hello \\\"\"))");
    }
}