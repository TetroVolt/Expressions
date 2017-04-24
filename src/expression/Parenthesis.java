package expression;

/**
 * Created by raymond on 4/23/17.
 */
public class Parenthesis extends ExpressionElement {

    private boolean left;

    Parenthesis(boolean left) {
        this.left = left;
    }

    public boolean isLeft() {
        return left;
    }

    @Override
    public ExpressionElementType getType() {
        return left ? ExpressionElementType.LPAREN : ExpressionElementType.RPAREN;
    }

    @Override
    public String toString() {
        return super.toString() + (left ? "(" : ")");
    }
}
