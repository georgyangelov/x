package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class Assignments {
    @Test
    public void testAssignmentToVariable() throws Exception {
        XParserTest.testExpression(
                "a = 5",
                "(expression (expression (variable a)) = (expression (constant 5)))"
        );
    }

    @Test
    public void testNestedAssignments() throws Exception {
        XParserTest.testExpression(
                "c = b = a = 5",
                "(expression (expression (variable c)) = (expression (expression (variable b)) = (expression (expression (variable a)) = (expression (constant 5)))))"
        );
    }
}
