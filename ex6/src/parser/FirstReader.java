package parser;

import function.Function;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class read first time the file, ignore white spaces, and save the function names.
 * @author Shakked River, David Guedalia.
 */
public class FirstReader {

    /**
     * Array of lines.
     */
    LinkedList<String> lines = new LinkedList<String>();

    private final Pattern patternBlank =  Pattern.compile("");
    private final Pattern patternComment =  Pattern.compile("");
    private final Pattern patternFunction =  Pattern.compile("");

    private BufferedReader buffer;

    /**
     * Constructor.
     * @param buffer
     */
    FirstReader(BufferedReader buffer){
        this.buffer = buffer;
    }

    /**
     *
     * @return Lined list of the lines
     * @throws IOException
     */
    public LinkedList<String> runParseToArray(HashMap<String, Function> functions) throws IOException {
        String line = buffer.readLine();
        while (line != null) {
            Matcher blankLineMatcher = patternBlank.matcher(line);
            Matcher commentMatcher = patternComment.matcher(line);
            if (!(blankLineMatcher.matches() || commentMatcher.matches())) {
                lines.add(line);
            }
            if (ifFunction(line)){
                buildFunction(line, functions);
            }
            line = buffer.readLine();
        }
        return lines;
    }

    /**
     * Build Function Object, and save the name method.
     */
    private boolean ifFunction(String line){
        return patternFunction.matcher(line).matches();

    }

    /**
     * TODO
     * This function create Object of Function.
     */
    private void buildFunction(String line, HashMap<String, Function> functions){

    }
}
