

import java.util.Scanner;
import tiralabra.compression_project.Compression;
import tiralabra.compression_project.FileHandler;


public class NewMain {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.print("Which file to read? ");
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);

        String[] fileLines = fileHandler.getLines();

        Compression compr = new Compression();
        compr.countFreqs(fileLines);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        System.out.println(bitString);
        
        String decompressed = compr.decompress(bitString);
        System.out.println(decompressed);
    }

}
