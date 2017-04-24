package expression;

/**
 * Created by raymond on 4/23/17.
 */

public class Token {
    private String data;
    private TokenType type;

    Token(String data, TokenType tokenType) {
        this.data = data;
        this.type = tokenType;
    }

    String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("[ %-12s ]  ->  %s", type, data);
    }
}
