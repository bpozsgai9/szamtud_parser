package parsers.ast;

public class IfElseStatement implements Statement {
    public final Expression condition;
    public final Statement ifTrue;
    public final Statement ifFalse;


    public IfElseStatement(Expression condition, Statement ifTrue, Statement ifFalse) {
        this.condition = condition;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public String toString() {
        return "IF (" + condition.toString() + ") {\n\t" + ifTrue.toString() + "\n} ELSE {\n\t" + ifFalse.toString() + "\n}";
    }

}
