
package tiralabra.compression_project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private ArrayList<String> lines;
    
    public FileHandler () {
        this.lines = new ArrayList<>();
    }
    
    public void readFile(String filename) {
        System.out.println(filename);
        File testFile = new File(filename);
        
        try (Scanner fileReader = new Scanner(testFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                
                this.lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public ArrayList<String> getLines() {
        return this.lines;
    }
    
}
