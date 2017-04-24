package expression;

/**
 * Created by raymond on 4/23/17.
 */
public enum OperatorType {
    POWER(50, true, false),

    SQRT(40, false, false),

    UNARY_PLUS(40, false, false),

    UNARY_MINUS(40, false, false),

    MULTIPLY(20, true, true),

    DIVIDE(20, true, true),

    PLUS(10, true, true),

    MINUS(10, true, true);

    public final boolean BINARY, LEFT_ASSOCIATIVE;
    public final int PRECEDENCE;

    OperatorType(int PRECEDENCE, boolean BINARY, boolean LEFT_ASSOCIATIVE) {
        this.PRECEDENCE = PRECEDENCE;
        this.BINARY = BINARY;
        this.LEFT_ASSOCIATIVE = LEFT_ASSOCIATIVE;
    }

    public int comparePrecedence(OperatorType other) {
        return PRECEDENCE - other.PRECEDENCE;
    }

}
