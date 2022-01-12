package parsers.ast;

public class Arg {
    public final Type type;
    public final String name;

    public Arg(Type type, String name) {
        this.type = type;
        this.name = name;
    }
}
