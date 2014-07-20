package net.gangelov.parser;

import org.junit.Test;

public class Methods {
    @Test
    public void testEmpty() throws Exception {
        XParserTest.testMethodDefinition(
                "def empty\n" +
                "end",

                "(methodDef def (methodID empty) \\n end)"
        );
    }

    @Test
    public void testWithExpression() throws Exception {
        XParserTest.testMethodDefinition(
                "def return_2\n" +
                    "2\n" +
                "end",

                "(methodDef def (methodID return_2) \\n (code (expression (constant 2)) \\n) end)"
        );
    }

    @Test
    public void testWithArgument() throws Exception {
        XParserTest.testMethodDefinition(
                "def return_arg(arg type) type\n" +
                    "arg\n" +
                "end",

                "(methodDef def (methodID return_arg) ( (formalArgumentList (formalArgument arg (type (variable type)))) ) (type (variable type)) \\n (code (expression (variable arg)) \\n) end)"
        );
    }

    @Test
    public void testWithArguments() throws Exception {
        XParserTest.testMethodDefinition(
                "def add(a Int, b Int) Int\n" +
                    "a + b\n" +
                "end",

                "(methodDef def (methodID add) ( (formalArgumentList (formalArgument a (type Int)) , (formalArgument b (type Int))) ) (type Int) \\n (code (expression (expression (variable a)) + (expression (variable b))) \\n) end)"
        );
    }

    @Test
    public void testMultipleExpressions() throws Exception {
        XParserTest.testMethodDefinition(
                "def do_stuff\n" +
                    "nuke = Nuke.new\n" +
                    "nuke.launch\n" +
                "end",

                "(methodDef def (methodID do_stuff) \\n (code (expression (expression (variable nuke)) = (expression (type Nuke) . new)) \\n (expression (expression (variable nuke)) . (methodID launch)) \\n) end)"
        );
    }
}
