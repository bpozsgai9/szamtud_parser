package parsers.ast;

public class Assignment implements Statement {
    public final String name;
    public final Expression value;

    public Assignment(String name, Expression value) {
        this.name = name;
        this.value = value;
    }
    public String toString(){
        String ret = name + " = " ;
        ret = ret + ((value == null) ? "<null>" : value.toString() );
        return ret;
    }
}
