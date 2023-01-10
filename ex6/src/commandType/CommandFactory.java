package commandType;
import function.Function;
import scope.Scope;
import variable.VariableFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommandFactory {


    private static final String SPACE = "\\s*";
    private static final String AND_OR = "(&&)|(\\|\\|)";
    private static final String WHILE_IF_FORM = SPACE + "(if|while)" + SPACE + "\\(" + SPACE + "(\\s*([\\w\\.]"+ SPACE +
             AND_OR + ")*" + SPACE + "[\\w\\.]+" + SPACE + ")\\)" + SPACE +"\\{" + SPACE;

    private static final String DECLARATION = "\\s*(final)?\\s*(int|char|boolean|String|double)(\\s+\\w+\\s*(,\\s*\\w+\\s*)*?);\\s*";
    private static final String DECLARATION_ASSIGNMENT= "\\s*(final)?\\s*(int|char|boolean|String|double)((\\s+\\w+\\s*(=\\s*(\\d*\\.?\\d|'.'|\".*\"))?)"+
            "(,\\s*\\w+\\s*(=\\s*(\\d*\\.?\\d*|'.'|\"(.*)\"))?)*)\\s*;\\s*";
    private static final String CALL_FUNCTION= "\\s*(final)?\\s*(int|char|boolean|String|double)((\\s+\\w+\\s*(=\\s*(\\d*\\.?\\d|'.'|\".*\"))?)"+
            "(,\\s*\\w+\\s*(=\\s*(\\d*\\.?\\d*|'.'|\"(.*)\"))?)*)\\s*;\\s*";
    /**
     * This function get a line.
     * @param line Gets line from file
     * @param scope The current scope.
     * @param functionHashMap Hash Map of the function names.
     * @return Type command.
     * @throws Exception
     */
    public static Command createCommand(String line, Scope scope, HashMap<String, Function> functionHashMap)
            throws Exception {
        Pattern ifOrWhile = Pattern.compile(WHILE_IF_FORM);

        if (ifOrWhile.matcher(line).matches()) {
            return new IfWhileCommand(line, scope);
        }

        Pattern declaration = Pattern.compile(DECLARATION_ASSIGNMENT);
        Matcher matcherDeclaration = declaration.matcher(line);

        if (matcherDeclaration.matches()){
            return new DeclarationAssignmentCommand(line, scope, matcherDeclaration);
        }

        Pattern callFunction = Pattern.compile(CALL_FUNCTION);
        Matcher matcherCallFunction = callFunction.matcher(line);

        if (matcherDeclaration.matches()) {
            return new CallFunctionCommand(line, scope, matcherDeclaration);
        }

        else {
            throw new Exception();
        }
        }
    }
