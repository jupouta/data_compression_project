
package tiralabra.compression_project;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class for handling reading and writing
 * of the files.
 */
public class FileHandler {
    /**
     * The lines of the file as an array of strings.
     */
    private final String[] lines;
    /**
     * Creates a new FileHandler.
     * No parameters needed.
     */
    public FileHandler () {
        this.lines = new String[10];
        
        for (int i = 0; i < this.lines.length; i++) {
            this.lines[i] = "%§";
        }
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
            int i = 0;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                this.lines[i] = line + "\n";
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * The lines of the file returned as an array.
     * @return the array of lines.
     */
    public String[] getLines() {
        return this.lines;
    }
    
}
