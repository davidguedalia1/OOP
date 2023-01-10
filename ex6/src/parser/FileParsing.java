package parser;
import function.Function;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class FileParsing {

    private final String sJavaFile;
    private BufferedReader buffer;

    /**
     * All the lines not space or comment.
     */
    LinkedList<String> lines = new LinkedList<String>();

    /**
     * Constructor
     * @param sJavaFile - The file to check
     */
    FileParsing(String sJavaFile){
        this.sJavaFile = sJavaFile;
    }

    /**
     * Read file and make buffer.
     * @throws IOException
     */
    private void readFileBuffer() throws IOException{
        buffer = new BufferedReader(new FileReader(sJavaFile));
    }

    /**
     * Do the 2 parsing methods.
     * @throws IOException
     */
    public void runParse() throws Exception {
        HashMap<String, Function> functionHashMap = new HashMap<String, Function>();
        FirstReader firstParseLines = new FirstReader(buffer);
        lines = firstParseLines.runParseToArray(functionHashMap);
        Manager manager = new Manager(lines);
        manager.runManager(functionHashMap);

    }
}
