package parsers.ast;

import java.util.ArrayList;

public class FunctionCall extends Expression {
    public final String functionName;
    public final ArrayList<Expression> arguments;

    public FunctionCall(String functionName, ArrayList<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return String.format("%s( %s )", functionName, arguments);
    }
}
