package parsers.util;

import parsers.ast.*;

import java.util.*;
import java.util.stream.Collectors;

public class DeclarationErrorReporter {
    private final ArrayList<String> errorMessages = new ArrayList<>();
    private final Map< String, List<Arg>> declaredFunctions = new HashMap<>();

    public ArrayList<String> checkCompilationUnit(CompUnit unit)
    {
        errorMessages.clear();
        declaredFunctions.clear();
        declaredFunctions.put(
                "print",
                Arrays.stream( new Arg[] { new Arg(Type.INT, "x") } ).collect(Collectors.toList())
        );
        unit.functions.forEach(this::addFunction);
        unit.functions.forEach(this::checkFunction);
        return errorMessages;
    }

    private void addFunction( FunctionDeclaration function )
    {
        if( declaredFunctions.containsKey( function.name ))
        {
            errorMessages.add("Duplicate function name: " + function.name);
        } else {
            declaredFunctions.put( function.name, function.arguments );
        }
    }

    private void checkFunction( FunctionDeclaration function )
    {
        Map< String, Type > declaredVariables = new HashMap<>();
        function.arguments.forEach(
                arg -> addVariable(arg, declaredVariables)
        );
        function.statements.forEach(
                statement -> checkStatement(statement, declaredVariables)
        );
    }

    private void addVariable( Type type, String name, Map< String, Type > declaredVariables )
    {
        if( declaredVariables.containsKey( name ))
        {
            errorMessages.add( "Duplicate variable declaration: " + name );
        } else {
            declaredVariables.put( name, type );
        }
    }

    private void addVariable( Arg arg, Map<String, Type > declaredVariables )
    {
        addVariable( arg.type, arg.name, declaredVariables );
    }

    private void checkStatement(Statement statement, Map<String, Type> declaredVariables) {
        if( statement instanceof Expression ) {
            checkExpression( (Expression)statement, declaredVariables );
        } else if( statement instanceof Assignment ) {
            Assignment assignment = (Assignment) statement;
            checkExpression( assignment.value, declaredVariables );
            checkVariable( assignment.name, declaredVariables );
        } else if( statement instanceof ReturnStatement ) {
            checkExpression( ((ReturnStatement) statement ).expression, declaredVariables );
        } else if( statement instanceof VariableDeclaration ) {
            VariableDeclaration declaration = (VariableDeclaration) statement;
            checkExpression( declaration.value, declaredVariables );
            addVariable( declaration.type, declaration.name, declaredVariables );
        } else throw new IllegalArgumentException("Unhandled statement class: " + statement.getClass() );
    }

    private void checkVariable(String name, Map<String, Type> declaredVariables) {
        if( !declaredVariables.containsKey( name ) )
        {
            errorMessages.add("Undeclared variable: " + name);
        }
    }

    private void checkExpression(Expression expression, Map<String, Type> declaredVariables) {
        if( expression instanceof NumberExpression ) {
            // do nothing, okay
        } else if( expression instanceof BinaryExpression ) {
            BinaryExpression binaryExpression = (BinaryExpression) expression;
            checkExpression( binaryExpression.left, declaredVariables );
            checkExpression( binaryExpression.right, declaredVariables );
        } else if( expression instanceof VariableExpression ) {
            checkVariable( ((VariableExpression)expression).name, declaredVariables );
        } else if( expression instanceof FunctionCall ) {
            FunctionCall call = (FunctionCall) expression;
            call.arguments.forEach(
                    arg -> checkExpression( arg, declaredVariables )
            );
            if( !declaredFunctions.containsKey( call.functionName ) ) {
                errorMessages.add("Undeclared function name: " + call.functionName);
            } else {
                // type check for arguments
                List<Arg> args = declaredFunctions.get(call.functionName);
                // .. or, instead, only for their cardinality
                if( args.size() != call.arguments.size() )
                {
                    errorMessages.add("Mismatching number of arguments at the call: " + call );
                }
            }
        }
    }

}
