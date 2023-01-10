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
public class tester {
    public static void main(String[] args) {
        Pattern ifOrWhile = Pattern.compile("\\s*(int|char|boolean|String|double)\\s*\\w*");
        Matcher matcher = ifOrWhile.matcher("int x");
        System.out.println(matcher.matches());


    }
}
