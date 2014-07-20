package net.gangelov.parser;

import org.junit.Test;

public class Classes {
    @Test
    public void testEmpty() throws Exception {
        XParserTest.testClass(
                "class Empty\n" +
                "end",

                "(classDef class (type Empty) \\n end)"
        );
    }

    @Test
    public void testEmptySingleLine() throws Exception {
        XParserTest.testClass(
                "class Empty; end",

                "(classDef class (type Empty) ; end)"
        );
    }

    @Test
    public void testGeneric() throws Exception {
        XParserTest.testClass(
                "class Hash<key_type, value_type>; end",

                "(classDef class (type Hash < (typeList (type (variable key_type)) , (type (variable value_type))) >) ; end)"
        );
    }

    @Test
    public void testMethods() throws Exception {
        XParserTest.testClass(
                "class Test\n" +
                    "def test_this\n" +
                        "1\n" +
                    "end\n" +

                    "def test_that\n" +
                        "2\n" +
                    "end\n" +
                "end",

                "(classDef class (type Test) \\n (methodDef def (methodID test_this) \\n (code (expression (constant 1)) \\n) end) \\n (methodDef def (methodID test_that) \\n (code (expression (constant 2)) \\n) end) \\n end)"
        );
    }
}
