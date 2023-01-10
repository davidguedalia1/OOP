package commandType;

import scope.Scope;
import variable.BooleanVariable;
import variable.Type;

public class IfWhileCommand extends Command {

    private final String WHILE_OR_IF = "whileOrIf";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";


    IfWhileCommand(String line, Scope scope) throws Exception {
        super(line, scope);
        String expression = line.substring(line.indexOf(OPEN_BRACKET) + 1, line.lastIndexOf(CLOSE_BRACKET));
        this.commandArray = expression.split(WHILE_OR_IF);
        isValid();
    }

    @Override
    protected void isValid() throws Exception {
        for (String expression : commandArray) {
            expression = expression.trim();
            BooleanVariable variable = (BooleanVariable) scope.exist(expression); // check if I can do it.
            if (!variable.isAssignmentValid(expression)){
                throw new Exception();
            }
            if (scope.exist(expression) == null){
                throw new Exception();
            }
        }
    }

    /**
     * Check if a type is type of expression - int, boolean or double.
     * @param type enum Type to check.
     * @return True if it is, false otherwise.
     */
    private boolean legalVariable(Type type){
        return type.equals(Type.DOUBLE) || type.equals(Type.BOOLEAN) || type.equals(Type.INT);
    }

    @Override
    protected String typeCommand() {
        return WHILE_OR_IF;
    }
}
