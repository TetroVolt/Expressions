import expression.*;

import java.util.ArrayList;

/**
 * Created by raymond on 4/23/17.
 */

public class Tester {

    private static void TestTokenizer() {
        //Put Code Here
        String expr = "(1+2-3*4)/5^6√7";
        ArrayList<Token> tokens = Tokenizer.tokenize(expr);
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    private static void testRandom() {
        String plus = "+";
        String minus = "/";
        String regex = "[-+*/^√]";
        System.out.println(plus.matches(regex));
        System.out.println(minus.matches(regex));
    }

    private static void testParser() {
        System.out.println("testParser()");
        String expr = "1*--" + ExpressionsUtil.SQRT_CONST + "-(--5+10-3)";
        System.out.println("Original Expression: \"" + expr + "\"\n");
        ArrayList<Token> tokens = Tokenizer.tokenize(expr);
        ArrayList<ExpressionElement> elements = Parser.parseIntoExpressionElements(tokens);
        for (ExpressionElement ee : elements) {
            System.out.println(ee);
        }
    }

    private static void testCompiler() {
        System.out.println("testCompiler()");
        String expr = "-(-(-5))";
        System.out.println("Original Expression: \"" + expr + "\"\n");

        Expression ee = Expression.compile(expr);
        System.out.println(ee.evaluate());
    }

    private static void testStringHelper(String inp) {
        inp = inp.trim();
    }
    private static void testString() {
        String test = "     s     ";
        testStringHelper(test);
        System.out.println(test);
    }




    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        {
            //TestTokenizer();
            //testRandom();
            //testParser();
            //testString();
            testCompiler();
        }
        long end = System.currentTimeMillis();

        System.out.printf("\nProgram completed in: %d ms.\n", (end - start));
    }
}

