package parsers.ast;

import java.util.ArrayList;

public class CompUnit {
    public ArrayList<FunctionDeclaration> functions = new ArrayList<FunctionDeclaration>();

    /*
    @Override
    public String toString() {
        StringBuilder back = new StringBuilder();
        for (FunctionDeclaration function : this.functions) {
            back.append(function.toString()).append("\n");
        }
        return back.toString();
    }
    */
}

