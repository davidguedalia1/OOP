package commandType;

import scope.Scope;

import java.util.regex.Matcher;

public class CallFunctionCommand extends Command {
    public CallFunctionCommand(String line, Scope scope, Matcher matcherCallFunction) throws Exception {
        super(line, scope);
        isValid();
    }

    @Override
    protected void isValid() throws Exception {

    }

    @Override
    protected String typeCommand() {
        return null;
    }
}
