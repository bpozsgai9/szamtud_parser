package parsers.ast;

public class BinaryExpression extends Expression {
    public static enum Operator {
        PLUS,
        STAR,
        POW
    }
    public final Expression left, right;
    public final Operator op;

    public BinaryExpression(Expression left, Operator op, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left, op, right);
    }
}
