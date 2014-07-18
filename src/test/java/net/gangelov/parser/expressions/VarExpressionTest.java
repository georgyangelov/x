package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class VarExpressionTest {
    @Test
    public void testConstantID() throws Exception {
        XParserTest.testExpression("Global.test", "(expression (classID Global) . (methodID test))");
    }

    @Test
    public void testFullyQualifiedConstantID() throws Exception {
        XParserTest.testExpression("x::lang::Global.test", "(expression (classID x::lang::Global) . (methodID test))");
    }

    @Test
    public void testVarID() throws Exception {
        XParserTest.testExpression("num", "(expression (varExpression num))");
    }
}
