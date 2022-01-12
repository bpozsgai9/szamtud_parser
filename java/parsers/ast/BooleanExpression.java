package parsers.ast;

public class BooleanExpression extends Expression {

    public final boolean value;

    public BooleanExpression(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value ? "true" : "false";
    }
}
