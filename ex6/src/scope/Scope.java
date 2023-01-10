package scope;

import variable.Variable;

import java.util.HashSet;

public class Scope {

    HashSet<Variable> variableHashSet = new HashSet<Variable>();
    private final Scope wrapperScope;

    /**
     *
     * @param variableHashSet Hash set of the variable
     * @param wrapperScope The wrapper Scope
     */
    public Scope(HashSet<Variable> variableHashSet, Scope wrapperScope){
        this.variableHashSet = variableHashSet;
        this.wrapperScope = wrapperScope;
    }

    /**
     * @return This method return the wrapper scope.
     */
    public Scope getWrapperScope(){
        return wrapperScope;
    }

    /**
     * @return Hash Map of the variables in the scope.
     */
    public HashSet<Variable> getVariables(){
        return variableHashSet;
    }

    /**
     * Check if the variable exist, and add it if its not.
     * @param variable the Variable to be added
     */
    public void add(Variable variable) throws Exception {
        if(exist(variable.getName()) == null){
            variableHashSet.add(variable);
        }
        else {
            throw new Exception();
        }
    }

    /**
     * This method checks if variable is already in any scop.
     * @param nameToCheck the variable to check.
     * @return The variable or null if not exist.
     */
    public Variable exist(String nameToCheck) throws Exception {
        HashSet<Variable> variableHashSetCurrent = variableHashSet;
        for (Variable variable : variableHashSetCurrent){
            if (variable.getName().equals(nameToCheck)){
                return variable;
            }
        }
        Scope wrapperScope = this.getWrapperScope();
        variableHashSetCurrent = wrapperScope.getVariables();
        for (Variable variable : variableHashSetCurrent){
            if (variable.getName().equals(nameToCheck)){
                return variable;
            }
            wrapperScope = wrapperScope.getWrapperScope();
            variableHashSetCurrent = wrapperScope.getVariables();
        }
        return null;
    }
}
