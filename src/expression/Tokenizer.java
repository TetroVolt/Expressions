package expression;

import java.util.ArrayList;

/**
 * Created by raymond on 4/22/17.
 */
public class Tokenizer {
    
    public static ArrayList<Token> tokenize(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder numberbuilder = new StringBuilder();

        char[] exp = input.toCharArray();
        int i = 0;
        
        while (i < exp.length) {
            if (exp[i] == ' ') { i++; continue; } // skip whitespace
            //System.out.printf("expr[%d] : %c\n", i, exp[i]);
            if (ExpressionsUtil.isDigit(exp[i])) {
                while (i < exp.length && ExpressionsUtil.isDigit(exp[i])) {
                    numberbuilder.append(exp[i++]);
                }
                if (i < exp.length && exp[i] == '.' && (i + 1) < exp.length && ExpressionsUtil.isDigit(exp[i + 1])) {
                    numberbuilder.append(exp[i++]);
                    while (i < exp.length && ExpressionsUtil.isDigit(exp[i])) {
                        numberbuilder.append(exp[i++]);
                    }
                }
                tokens.add(new Token(numberbuilder.toString(), TokenType.OPERAND));
                numberbuilder.setLength(0);
                continue;
            } else if (ExpressionsUtil.isOperator(exp[i])) {
                if (numberbuilder.length() > 0) {
                    tokens.add(new Token(numberbuilder.toString(), TokenType.OPERAND));
                    numberbuilder.setLength(0);
                }
                tokens.add(new Token(exp[i++] + "", TokenType.OPERATOR));
                continue;
            } else if (exp[i] == '(' || exp[i] == ')') {
                tokens.add(new Token(exp[i++] + "", TokenType.PARENTHESIS));
                continue;
            } else if (exp[i] == 's' && i + 3 < exp.length){ // custom word matching case
                // Not modular but special case for sqrt
                if (exp[i + 1] == 'q' && exp[i + 2] =='r' && exp[i + 3] == 't') {
                    tokens.add(new Token(ExpressionsUtil.SQRT_CONST, TokenType.OPERATOR));
                    i += 4;
                    continue;
                }
            }// else // Not a number and not an operator and not a parenthesis
            throw new IllegalArgumentException(String.format("Illegal character @expr[%d] : %c\n", i, exp[i]));
        }

        //System.out.println(tokens);
        return tokens;
    }


}
