package expression;

/**
 * Created by raymond on 4/23/17.
 */
public final class ExpressionsUtil {
    public static final String SQRT_CONST = "√";
    public static final String[] OPERATOR_STRINGS = {"^", "√", "*", "/", "+", "-"};
    public static final OperatorType[] OPERATOR_STRING_TYPES = {
            OperatorType.POWER,
            OperatorType.SQRT,
            OperatorType.MULTIPLY,
            OperatorType.DIVIDE,
            OperatorType.PLUS,
            OperatorType.MINUS
    };

    public static int indexOf(String search, String[] source) {
        for (int i = 0; i < source.length; ++i) {
            if (source[i].equals(search)) {
                return i;
            }
        }
        return -1;
    }
    public static boolean isDigit(char p) { return '0' <= p && p <= '9'; }
    public static boolean isOperator(String op) {
        return indexOf(op, OPERATOR_STRINGS) != -1;
    }
    public static boolean isOperator(char c) {
        return isOperator(c + "");
    }

    private ExpressionsUtil() {}

    public static OperatorType getOperatorType(String data) {
        int ind = indexOf(data, OPERATOR_STRINGS);
        if (ind == -1 || ind >= OPERATOR_STRING_TYPES.length) {
            throw new IllegalArgumentException("Not an operator -> " + data);
        }
        return OPERATOR_STRING_TYPES[ind];
    }
}
