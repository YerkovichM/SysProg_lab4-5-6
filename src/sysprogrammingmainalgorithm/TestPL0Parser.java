package sysprogrammingmainalgorithm;

import JavaTeacherLib.MyLang;
import JavaTeacherLib.PL0Parser;

import java.io.IOException;

class TestPL0Parser extends PL0Parser {
    TestPL0Parser(MyLang lang0, String fname) throws IOException {
        super(lang0, fname);
    }
    void parse(){
        super.parserRecursive();
    }
}
