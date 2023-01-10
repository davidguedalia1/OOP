package parser;
import commandType.Command;
import commandType.CommandFactory;
import function.Function;
import variable.Variable;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Pattern;
import scope.Scope;


public class Manager {

    private final String CLOSE_BRACKET = "\\s*}\\s*";
    private final Pattern patternCloseBracket = Pattern.compile(CLOSE_BRACKET);

    /**
     *
     */
    LinkedList<String> lines = new LinkedList<String>();

    Manager(LinkedList<String> lines){
        this.lines = lines;
    }

    /**
     * This function run the second time on the file.
     * @throws IOException
     * @param functionHashMap A map with the function in the file.
     */
    public void runManager(HashMap<String, Function> functionHashMap) throws Exception {
        HashSet<Variable> variableGlobalHashMap = new HashSet<Variable>();
        Scope scope = new Scope(variableGlobalHashMap, null);
        for (String line : lines){
            if (patternCloseBracket.matcher(line).matches()){
                if (scope == null){
                    throw new Exception();
                }
                scope = scope.getWrapperScope();
            }
            Command command = CommandFactory.createCommand(line, scope, functionHashMap);
            if (newScope(command)){
                HashSet<Variable> variableScopeHashMap = new HashSet<Variable>();
                scope = new Scope(variableScopeHashMap, scope);
            }
        }

        // finished the read all lines and need to check if theres is more Scopes//
        if (scope.getWrapperScope() != null){
            throw new Exception();
        }

    }

    /**
     * Check if the command is a new scope.
     * @param command the command.
     * @return True if in is new scope, false otherwise.
     */
    private boolean newScope(Command command) {
        return true;
    }


}
