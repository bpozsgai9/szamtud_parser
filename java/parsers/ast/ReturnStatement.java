package parsers.ast;

public class ReturnStatement implements Statement {
    public final Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }
    public String toString() {
        return "RETURN " + (expression == null? "<null>" : expression.toString());
    }
}
