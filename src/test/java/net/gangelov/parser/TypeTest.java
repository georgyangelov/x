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
                "(type (varExpression var_type))"
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
                "(type List < (typeList (type (varExpression a))) >)"
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
                "(type { (typeList (type (varExpression a)) , (type (varExpression b))) -> (type (varExpression c)) })"
        );
    }

    @Test
    public void testFunctionAliasWithoutReturnType() throws Exception {
        XParserTest.testType(
                "{a, b ->}",
                "(type { (typeList (type (varExpression a)) , (type (varExpression b))) -> })"
        );
    }

    @Test
    public void testFunctionAliasWithoutArgs() throws Exception {
        XParserTest.testType(
                "{-> c}",
                "(type { -> (type (varExpression c)) })"
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
