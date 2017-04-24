package expression;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by raymond on 4/23/17.
 */
public class Parser {

    public static ArrayList<ExpressionElement> parseIntoExpressionElements(ArrayList<Token> tokens) {
        ArrayList<ExpressionElement> elements = new ArrayList<>();
        int parenthesees = 0;
        int i = 0;
        boolean LookingForUnary = true;
        while (i < tokens.size()) {
            if (tokens.get(i).getType() == TokenType.OPERAND) {
                elements.add(new Operand(Double.parseDouble(tokens.get(i).getData())));
                LookingForUnary = false;
            } else if (tokens.get(i).getType() == TokenType.OPERATOR) {
                if (LookingForUnary) {
                    if (tokens.get(i).getData().equals("+")) {
                        elements.add(new Operator(OperatorType.UNARY_PLUS));
                    } else if (tokens.get(i).getData().equals("-")) {
                        elements.add(new Operator(OperatorType.UNARY_MINUS));
                    } else if (tokens.get(i).getData().equals(ExpressionsUtil.SQRT_CONST)){
                        elements.add(new Operator(OperatorType.SQRT));
                    } else {
                        throw new IllegalArgumentException("Not a Unary Operator -> " + tokens.get(i).getData());
                    }
                } else {
                    elements.add(new Operator(ExpressionsUtil.getOperatorType(tokens.get(i).getData())));
                    if (i + 1 < tokens.size()) {
                        LookingForUnary = tokens.get(i+1).getType() == TokenType.OPERATOR;
                    } else {
                        throw new IllegalArgumentException(String.format("Operator [%s] takes 2 arguments, @col = %d", tokens.get(i).getData(),i));
                    }
                }
            } else if (tokens.get(i).getType() == TokenType.PARENTHESIS) {
                boolean open = tokens.get(i).getData().equals("(");
                elements.add(new Parenthesis(tokens.get(i).getData().equals("(")));
                LookingForUnary = open;
                parenthesees = open ? parenthesees + 1 : parenthesees - 1;
            }
            i++;
        }

        if (parenthesees != 0) {
            throw new IllegalArgumentException("Unbalanced Parenthesees : " + parenthesees);
        }
        return elements;
    }

    static ArrayList<ExpressionElement> toPostFix(ArrayList<ExpressionElement> expr) {
        // Shunting yard implementation
        ArrayList<ExpressionElement> postfix = new ArrayList<>(expr.size());
        if (expr.isEmpty()) return postfix;
        Stack<ExpressionElement> operator_stack = new Stack<>();

        for (ExpressionElement ee : expr) {
            if (ee.getType() == ExpressionElementType.OPERAND) {
                postfix.add(ee);
            } else if (ee.getType() == ExpressionElementType.OPERATOR) {
                Operator ee_op = (Operator) ee;
                OperatorType ee_op_type = ee_op.getOperatorType();
                boolean left = ee_op_type.LEFT_ASSOCIATIVE;

                while (!operator_stack.isEmpty()) {
                    if (operator_stack.peek().getType() == ExpressionElementType.OPERATOR) {
                        Operator top_op = (Operator) operator_stack.peek();
                        if (((left && ee_op_type.comparePrecedence(top_op.getOperatorType()) <= 0)
                                || (!left && ee_op_type.comparePrecedence(top_op.getOperatorType()) < 0))) {
                            postfix.add(operator_stack.pop());
                            continue;
                        }
                        break;
                    }
                    break;
                }

                operator_stack.add(ee_op);
            } else if (ee.getType() == ExpressionElementType.LPAREN || ee.getType() == ExpressionElementType.RPAREN) {
                Parenthesis pao = (Parenthesis) ee;
                if (pao.isLeft()) {
                    operator_stack.add(pao);
                } else { // closed parenthesis -> keep popping off operators until open parenthesis
                    while (!operator_stack.isEmpty()) {
                        ExpressionElement op = operator_stack.pop();
                        if (op.getType() == ExpressionElementType.LPAREN) {
                            break;
                        }
                        postfix.add(op);
                    }
                }
            }
        }

        // Pop rest of the elements from stack
        while (!operator_stack.isEmpty()) {
            postfix.add(operator_stack.remove(operator_stack.size() - 1));
        }

        //System.out.println(postfix);
        return postfix;
    }

    static ArrayList<ExpressionElement> parse(ArrayList<Token> tokens) {
        return toPostFix(parseIntoExpressionElements(tokens));
    }
}
