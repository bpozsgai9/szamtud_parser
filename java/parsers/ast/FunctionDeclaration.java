package parsers.ast;

import java.util.ArrayList;

public class FunctionDeclaration {
    public Type type;
    public String name;
    public ArrayList<Arg> arguments;
    public ArrayList<Statement> statements;

    public FunctionDeclaration(Type type, String name, ArrayList<Arg> arguments, ArrayList<Statement> statements) {
        this.type = type;
        this.name = name;
        this.arguments = arguments;
        this.statements = statements;
    }
    public String toString() {
        String ret = type.toString() + " " + name + "( ";
        for( Arg arg: arguments){
            ret = ret + arg.type;
            ret = ret + " ";
            ret = ret + arg.name;
            ret = ret + " ";
        }
        ret = ret + ") {\n";
        for( Statement statement : statements ) {
            ret = ret + "  " + statement + "\n";
        }
        ret = ret + "}";
        return ret;
    }
}
