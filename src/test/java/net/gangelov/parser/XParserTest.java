package net.gangelov.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XParserTest {

    public static XParser createParser(String input) {
        ANTLRInputStream stream = new ANTLRInputStream(input);
        XLexer lexer = new XLexer(stream);

        return new XParser(new CommonTokenStream(lexer));
    }

    public static void testExpression(String expression, String expectedParse) {
        XParser parser = createParser(expression);

        assertEquals(expectedParse, parser.expression().toStringTree(parser));
    }

}