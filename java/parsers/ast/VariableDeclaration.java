package parsers.ast;

public class VariableDeclaration implements Statement {
    public Type type;
    public String name;
    public Expression value;

    public VariableDeclaration(Type type, String name, Expression value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
    public String toString(){
        String ret = type + " " + name + " = " ;
        ret = ret + ((value == null) ? "<null>" : value.toString() );
        return ret;
    }
}
