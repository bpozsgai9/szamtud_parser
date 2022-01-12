package parsers.ast;

public class NumberExpression extends Expression {
    public final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
