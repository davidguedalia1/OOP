package filesprocessing;
import java.io.File;
import java.util.*;
/**
 * Directory processor this class have the main function, and run the program.
 * @author David Guedalia
 */
public class DirectoryProcessor {

    /**
     * The number of arguments the main should get.
     */
    private static final int NUMBER_OG_ARGUMENTS = 2;

    /**
     * The first argument in the input is the source directory.
     */
    private static final int SOURCE_DIR = 0;

    /**
     * The second argument in the input is the command file.
     */
    private static final int COMMAND_FILE = 1;

    /**
     * The message if there is invalid arguments in the input.
     */
    private static final String INVALID_ARGUMENTS =  "ERROR: Invalid number of arguments";

    /**
     * This is array of the files.
     */
    private ArrayList<File> filesArray = new ArrayList<>();

    /**
     * Directory given as an input.
     */
    private final String sourceDirectory;

    /**
     * Command file given as an input.
     */
    private final String commandFile;

    /**
     *
     * @param sourceDirectory Directory given as an input.
     * @param commandFile Command file given as an input.
     */
    public DirectoryProcessor(String sourceDirectory, String commandFile){
        this.commandFile = commandFile;
        this.sourceDirectory = sourceDirectory;
    }

    /**
     * This method build the section array, and process each section.
     * @throws TypeSecondError Second type of exception.
     * @throws TypeFirstError First type of exception.
     */
    public void runProcessor() throws TypeSecondError, TypeFirstError {
        filesArray();
        Processor processor = new Processor(commandFile);
        LinkedList<Section> sectionArray = processor.run();
        for (Section section : sectionArray){
            processEachSection(section);
        }

    }

    /**
     * This function filtering the files, sort them and print them.
     * @param section section that contains filter an order
     * @throws TypeFirstError First type of exception.
     */
    private void processEachSection(Section section) throws TypeFirstError {
        HashSet<File> filteredFiles = new HashSet<>();
        for (File file : filesArray){
            if (section.getFilter().isFileFilter(file)){
                filteredFiles.add(file);
            }
        }
        File[] filteredFilesArray = new File[filteredFiles.size()];
        SortFiles sortFiles = new SortFiles(section.getOrder(), filteredFiles.toArray(filteredFilesArray));
        printFilesSorted(sortFiles, section);
    }

    /**
     * Print the warning files and the files.
     * @param sortFiles sort files of type SortFiles.
     * @param section section that contains filter an order.
     */
    private void printFilesSorted(SortFiles sortFiles, Section section) {
        for (String message : section.getWarningsMessages()){
            System.err.println(message);
        }
        for(File file : sortFiles.getFilesArray()){
            System.out.println(file.getName());
        }
    }

    /**
     * This function build the array of files in the directory.
     */
    private void filesArray() {
        File[] allFilesArray = null;
        File fileFolder = new File(sourceDirectory);
        if (fileFolder.isDirectory()) {
            allFilesArray = fileFolder.listFiles();
        }
        if (allFilesArray != null){
            buildArrayFiles(allFilesArray);
        }
    }

    /**
     * This function build array of files that are directory.
     * @param allFilesArray a Array of files.
     */
    private void buildArrayFiles(File[] allFilesArray) {
        for (File file : allFilesArray){
            if (!file.isDirectory()){
                filesArray.add(file);
            }
        }
    }


    /**
     * Main function, that run the program.
     * @param args The input of the user.
     * @throws TypeFirstError First type of exception.
     */
    public static void main(String[] args) throws TypeFirstError {
        try {
            if (args.length != NUMBER_OG_ARGUMENTS){
                throw new TypeSecondError(INVALID_ARGUMENTS);
            }
            DirectoryProcessor directoryProcessor = new DirectoryProcessor(args[SOURCE_DIR],args[COMMAND_FILE]);
            directoryProcessor.runProcessor();
        }
        catch (TypeSecondError secondError){
            System.err.println(secondError.getMessage());
        }
    }


}
