package expression;

/**
 * Created by raymond on 4/23/17.
 */
public class Operator extends ExpressionElement {

    private OperatorType type;

    Operator(OperatorType type) {
        this.type = type;
    }

    public OperatorType getOperatorType() {
        return type;
    }

    public Operand operate(Operand... operands) {
        double value;
        switch (type) {
            case POWER:
                value = Math.pow(operands[0].getValue(), operands[1].getValue());
                break;
            case SQRT:
                value = Math.sqrt(operands[0].getValue());
                break;
            case UNARY_MINUS:
                value = -operands[0].getValue();
                break;
            case MULTIPLY:
                value = operands[0].getValue() * operands[1].getValue();
                break;
            case DIVIDE:
                value = operands[0].getValue() / operands[1].getValue();
                break;
            case PLUS:
                value = operands[0].getValue() + operands[1].getValue();
                break;
            case MINUS:
                value = operands[0].getValue() - operands[1].getValue();
                break;
            default: // UNARY_PLUS
                value = operands[0].getValue();
                break;
        }
        return new Operand(value);
    }

    @Override
    public ExpressionElementType getType() {
        return ExpressionElementType.OPERATOR;
    }

    @Override
    public String toString() {
        return super.toString() + type;
    }
}
