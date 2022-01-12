package parsers.jflex;
import parsers.cup.*;
import java_cup.runtime.*;
import java.util.ArrayList;
%%

%class ExpressionLexer
%cupsym ExpressionParserSym
%cup
%line
%column
%unicode
%public

%{
    public ArrayList<String> errorList = new ArrayList<String>();
    public void collectErrors(String text) {
        errorList.add(text);
    }

    private Symbol createSymbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
%}

%%

"+" { return createSymbol(ExpressionParserSym.PLUS); }
"*" { return createSymbol(ExpressionParserSym.STAR); }
"^" { return createSymbol(ExpressionParserSym.POW); }
"(" { return createSymbol(ExpressionParserSym.LPAREN); }
")" { return createSymbol(ExpressionParserSym.RPAREN); }
"int" { return createSymbol(ExpressionParserSym.INT); }
"bool" { return createSymbol(ExpressionParserSym.BOOL); }
"return" { return createSymbol(ExpressionParserSym.RETURN); }
"if" { return createSymbol(ExpressionParserSym.IF); }
"else" { return createSymbol(ExpressionParserSym.ELSE); }
"{" { return createSymbol(ExpressionParserSym.LBRACE); }
"}" { return createSymbol(ExpressionParserSym.RBRACE); }
"=" { return createSymbol(ExpressionParserSym.EQ); }
";" { return createSymbol(ExpressionParserSym.SEMI); }
"," { return createSymbol(ExpressionParserSym.COMMA); }
[\d]+ { return new Symbol(ExpressionParserSym.NUMBER, yyline, yycolumn, Integer.parseInt(yytext())); }
[a-zA-Z][a-zA-Z0-9_]* { return new Symbol(ExpressionParserSym.IDENTIFIER, yyline, yycolumn, yytext()); }
[\s]+ {}
[^] { collectErrors("Hibás karakter! A futás a karakter illesztése nélkül fut tovább karakter='" + yytext() + "' sor='" + yyline + "')"); }