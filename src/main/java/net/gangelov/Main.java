package net.gangelov;

import net.gangelov.parser.XLexer;
import net.gangelov.parser.XParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        XLexer lexer = new XLexer(new ANTLRFileStream("test.x"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

//        XParser parser = new XParser(tokens);
        System.out.println(lexer.nextToken());
    }
}
