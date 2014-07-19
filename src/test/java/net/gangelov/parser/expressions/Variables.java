package net.gangelov.parser.expressions;

import net.gangelov.parser.XParserTest;
import org.junit.Test;

public class Variables {
    @Test
    public void testVarID() throws Exception {
        XParserTest.testExpression("num", "(expression (varExpression num))");
    }
}
