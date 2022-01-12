package parsers;

import java_cup.runtime.Symbol;
import parsers.ast.CompUnit;
import parsers.ast.FunctionDeclaration;
import parsers.cup.ExpressionParser;
import parsers.jflex.ExpressionLexer;
import parsers.util.DeclarationErrorReporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {

            //fájlbeolvasás
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/parsers/source.file"));
            ExpressionLexer lexer = new ExpressionLexer(br);
            ExpressionParser parser = new ExpressionParser(lexer);
            Symbol topSymbol = parser.parse(); //parser.debug_parse();

            //lexer hibák listája
            ArrayList<String> localErrorList = lexer.errorList;
            if (localErrorList.size() != 0) {
                System.err.println("Hibák a lexer-ben:");
                for (String error : localErrorList) {
                    System.err.println(error);
                }
            }

            System.out.println( "symbol code: " + topSymbol.sym + " value: " + topSymbol.value);
            CompUnit unit = (CompUnit)topSymbol.value;
            for( FunctionDeclaration function: unit.functions ) {
                System.out.println("Fuggveny: " + function );
            }
            DeclarationErrorReporter reporter = new DeclarationErrorReporter();
            ArrayList<String> errorList = reporter.checkCompilationUnit(unit);
            errorList.forEach(System.out::println);

        } catch (Exception e ) {
            System.out.println("Hiba a Main függvényben: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
