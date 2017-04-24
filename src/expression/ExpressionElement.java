package expression;

/**
 * Created by raymond on 4/23/17.
 */
public abstract class ExpressionElement {
    ExpressionElement() {}
    public abstract ExpressionElementType getType();

    @Override
    public String toString() {
        return String.format("[ %-8s ] : ", getType());
    }
}
