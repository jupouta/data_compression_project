
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import tiralabra.compression_project.Compression;
import tiralabra.compression_project.FileHandler;
import tiralabra.compression_project.Node;
import tiralabra.datastructure.MyArrayList;


public class NewMain {

    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        
        System.out.print("Which file to read? ");
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);
        
        Object[] fileLines = fileHandler.getLines();

        MyArrayList arrayLines = new MyArrayList();
        for (int i = 0; i < fileLines.length; i++) {
            arrayLines.add(fileLines[i]);
        }
        
        Compression compr = new Compression(arrayLines);
        
        compr.countFreqs();
        compr.addFreqs();
        
        PriorityQueue<Node> heap = compr.getHeap();
        
    }
    
    
    
}
