package commandType;

import scope.Scope;
import variable.Variable;
import variable.VariableFactory;

import java.util.regex.Matcher;

public class DeclarationCommand extends Command{
    private static final String COMMA = ",";
    private static final int FINAL = 1;
    private static final int TYPE_VARIABLE = 2;
    private static final int VARIABLES = 3;
    private String type;
    private Boolean isFinal;

    public DeclarationCommand(String line, Scope scope, Matcher matcher) throws Exception {
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
            Variable variable = VariableFactory.createVariable(isFinal, variableName, type, false);
            scope.add(variable);
        }
    }

    @Override
    protected String typeCommand() {
        return null;
    }
}
