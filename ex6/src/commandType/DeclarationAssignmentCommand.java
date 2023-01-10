package commandType;

import scope.Scope;
import variable.Variable;
import variable.VariableFactory;

import java.util.regex.Matcher;

public class DeclarationAssignmentCommand extends  Command{
    private static final String COMMA = ",";
    private static final String EQUAL = "=";
    private static final int FINAL = 1;
    private static final int TYPE_VARIABLE = 2;
    private static final int VARIABLES = 3;
    private final String type;
    private final Boolean isFinal;

    public DeclarationAssignmentCommand(String line, Scope scope, Matcher matcher) throws Exception {
        super(line, scope);
        this.commandArray = matcher.group(VARIABLES).split(COMMA);
        this.type = matcher.group(TYPE_VARIABLE);
        this.isFinal = matcher.group(FINAL) != null;
        isValid();
    }

    @Override
    protected void isValid() throws Exception {
        for (String variableName : commandArray) {
            variableName = variableName.trim();
            if (declarationOrAssignment(variableName)){
                Variable variable = VariableFactory.createVariable(isFinal, variableName, type, false);
                scope.add(variable);
            }
            else {
                String[] assignment = variableName.split(EQUAL);
                Variable variable = VariableFactory.createVariable(isFinal, assignment[0], type, true);
                variable.isAssignmentValid(assignment[1]);
                scope.add(variable);
            }
        }
    }

    private boolean declarationOrAssignment(String variableName) {
        return !variableName.contains(EQUAL);
    }

    @Override
    protected String typeCommand() {
        return null;
    }
}
