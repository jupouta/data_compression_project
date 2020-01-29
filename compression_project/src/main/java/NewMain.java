
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import tiralabra.compression_project.Compression;
import tiralabra.compression_project.FileHandler;
import tiralabra.compression_project.Node;


public class NewMain {

    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        System.out.print("Which file to read? ");
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);
        
        ArrayList<String> fileLines = fileHandler.getLines();
        
        Compression compr = new Compression(fileLines);
        compr.countFreqs();
        compr.addFreqs();
        
        PriorityQueue<Node> heap = compr.getHeap();
        
        
    }
    
    
    
}
