package parsers.ast;

public class VariableExpression extends Expression {
    public final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
