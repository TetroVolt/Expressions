package expression;

/**
 * Created by raymond on 4/23/17.
 */
public final class ExpressionsUtil {
    public static final String SQRT_CONST = "√";
    public static final String[] OPERATOR_STRINGS = {"^", "√", "*", "/", "+", "-"};
    //public static final String[] PARENTHESEES_STRINGS = {"(", ")"};
    //public static final String OPERATOR_REGEX = "[-+*/^√]";

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
        if (ind == -1) throw new IllegalArgumentException("Not an operator -> " + data);

        OperatorType ret = null;
        switch (ind) {
            case 0:
                ret = OperatorType.POWER;
                break;
            case 1:
                ret = OperatorType.SQRT;
                break;
            case 2:
                ret = OperatorType.MULTIPLY;
                break;
            case 3:
                ret = OperatorType.DIVIDE;
                break;
            case 4:
                ret = OperatorType.PLUS;
                break;
            case 5:
                ret = OperatorType.MINUS;
                break;
            default:
                break;
        }

        return ret;
    }
}
