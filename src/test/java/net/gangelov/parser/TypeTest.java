package net.gangelov.parser;

import org.junit.Test;

public class TypeTest {
    @Test
    public void testClassIDType() throws Exception {
        XParserTest.testType(
                "x::lang::Global",
                "(type x::lang::Global)"
        );
    }

    @Test
    public void testVarType() throws Exception {
        XParserTest.testType(
                "var_type",
                "(type (variable var_type))"
        );
    }

    @Test
    public void testSimpleGenericType() throws Exception {
        XParserTest.testType(
                "List<String>",
                "(type List < (typeList (type String)) >)"
        );
    }

    @Test
    public void testGenericWithVarType() throws Exception {
        XParserTest.testType(
                "List<a>",
                "(type List < (typeList (type (variable a))) >)"
        );
    }

    @Test
    public void testNestedGenericTypes() throws Exception {
        XParserTest.testType(
                "Hash< String, List<String> >",
                "(type Hash < (typeList (type String) , (type List < (typeList (type String)) >)) >)"
        );
    }


    // Syntax sugars

    @Test
    public void testArrayAlias() throws Exception {
        XParserTest.testType(
                "[String]",
                "(type [ (type String) ])"
        );
    }

    @Test
    public void testSetAlias() throws Exception {
        XParserTest.testType(
                "{String}",
                "(type { (type String) })"
        );
    }

    @Test
    public void testHashAlias() throws Exception {
        XParserTest.testType(
                "{String: Integer}",
                "(type { (type String) : (type Integer) })"
        );
    }

    @Test
    public void testFunctionAliasWithArgsAndReturnType() throws Exception {
        XParserTest.testType(
                "{a, b -> c}",
                "(type { (typeList (type (variable a)) , (type (variable b))) -> (type (variable c)) })"
        );
    }

    @Test
    public void testFunctionAliasWithoutReturnType() throws Exception {
        XParserTest.testType(
                "{a, b ->}",
                "(type { (typeList (type (variable a)) , (type (variable b))) -> })"
        );
    }

    @Test
    public void testFunctionAliasWithoutArgs() throws Exception {
        XParserTest.testType(
                "{-> c}",
                "(type { -> (type (variable c)) })"
        );
    }

    @Test
    public void testFunctionAliasWithoutArgsAndReturnType() throws Exception {
        XParserTest.testType(
                "{->}",
                "(type { -> })"
        );
    }
}
