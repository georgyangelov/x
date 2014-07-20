package net.gangelov.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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

    public static void testType(String type, String expectedParse) {
        XParser parser = createParser(type);

        assertEquals(expectedParse, parser.type().toStringTree(parser));
    }

    public static void testClass(String klass, String expectedParse) {
        XParser parser = createParser(klass);

        assertEquals(expectedParse, parser.classDef().toStringTree(parser));
    }

    public static void testMethodDefinition(String method, String expectedParse) {
        XParser parser = createParser(method);

        assertEquals(expectedParse, parser.methodDef().toStringTree(parser));
    }

}
