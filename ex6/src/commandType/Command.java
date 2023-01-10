package commandType;

import scope.Scope;

public abstract class Command {

    private String line;
    protected Scope scope;
    protected String[] commandArray;


    Command(String line, Scope scope) throws Exception {
        this.line = line;
        this.scope = scope;
        isValid();
    }

    protected abstract void isValid() throws Exception;

    protected abstract String typeCommand();

}
