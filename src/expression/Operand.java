package expression;

/**
 * Created by raymond on 4/23/17.
 */
public class Operand extends ExpressionElement {

    private double value = 0.0f;

    Operand(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public ExpressionElementType getType() {
        return ExpressionElementType.OPERAND;
    }

    public String toString() {
        return super.toString() + String.format("%.2f", value);
    }
}
