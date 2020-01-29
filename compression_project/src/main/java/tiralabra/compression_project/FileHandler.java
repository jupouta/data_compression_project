
package tiralabra.compression_project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for handling reading and writing
 * of the files.
 */
public class FileHandler {
    /**
     * The lines of the file.
     */
    private ArrayList<String> lines;
    
    /**
     * Creates a new FileHandler.
     * No parameters needed.
     */
    public FileHandler () {
        this.lines = new ArrayList<>();
    }
    
    /**
     * Read the lines in the given file.
     * @param filename The name of the filename.
     *                  The path can be absolute
     *                  or relative.
     */
    public void readFile(String filename) {
        File testFile = new File(filename);
        
        try (Scanner fileReader = new Scanner(testFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                this.lines.add(line + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public ArrayList<String> getLines() {
        return this.lines;
    }
    
}
