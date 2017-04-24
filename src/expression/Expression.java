package expression;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by raymond on 4/23/17.
 */

public class Expression {
    private ArrayList<ExpressionElement> postfixexpr;

    private Expression(ArrayList<ExpressionElement> postfixexpr) {
        this.postfixexpr = postfixexpr;
    }

    public Operand evaluate() {
        Stack<Operand> operandStack = new Stack<>();

        for (ExpressionElement ee : postfixexpr) {
            if (ee.getType() == ExpressionElementType.OPERAND) {
                operandStack.push((Operand) ee);
            } else if (ee.getType() == ExpressionElementType.OPERATOR) {
                Operand arg2 = operandStack.pop();
                if (((Operator) ee).getOperatorType().BINARY) {
                    arg2 = ((Operator) ee).operate(operandStack.pop(), arg2);
                } else {
                    arg2 = ((Operator) ee).operate(arg2);
                }
                operandStack.push(arg2);
            }
        }

        if (operandStack.size() != 1) {
            throw new IllegalStateException("Expression evaluated to operand size : " + operandStack.size());
        }

        // size == 1
        return operandStack.pop();
    }

    public static Expression compile(String infix_expr) {

        infix_expr = infix_expr.trim();
        if (infix_expr.isEmpty()) {
            throw new IllegalArgumentException("Empty Expression");
        }

        /*
        // Un-optimized for now
        ArrayList<Token> tokens = Tokenizer.tokenize(infix_expr);
        ArrayList<ExpressionElement> elements = Parser.parseIntoExpressionElements(tokens);
        ArrayList<ExpressionElement> postfix = Parser.toPostFix(elements);

        //System.out.println(infix_expr);
        //System.out.println(elements);
        System.out.println(postfix);
        return new Expression(postfix);
        */
        return new Expression(Parser.parse(Tokenizer.tokenize(infix_expr)));
    }

}
