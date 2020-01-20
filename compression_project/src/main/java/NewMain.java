
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import tiralabra.compression_project.FileHandler;


public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        System.out.print("Which file to read? ");
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);
        
        ArrayList<String> fileLines = fileHandler.getLines();
        
        for (String line: fileLines) {
            System.out.println(line);
        }
        
    }
    
    
    
}
