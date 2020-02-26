
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import tiralabra.compression_project.Compression;
import tiralabra.compression_project.FileHandler;
import tiralabra.compression_project.LZCompression;
import tiralabra.datastructure.MyArrayList;

public class NewMain {

    public static void main(String[] args) throws IOException {
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Before file: ");
//        System.out.print(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
//
//        System.out.print("Which file to read? ");
//        String filename = reader.nextLine();
//
//        FileHandler fileHandler = new FileHandler();
//        fileHandler.readFile(filename);
//
//        String[] fileLines = fileHandler.getLines();
//
//        Compression compr = new Compression();
//        System.out.print("Memory beginn: ");
//        System.out.print(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
//        long timeBeginn = System.currentTimeMillis();
//        
//        compr.countFreqs(fileLines);
//        compr.addFreqs();
//        compr.treeify();
//        String[] bitStrings = compr.linesToBits();
//        
//        long timeEnd = System.currentTimeMillis();
//        System.out.println(timeEnd - timeBeginn);
//        System.out.print("Memory end: ");
//        System.out.print(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
//        
//        System.out.println(Arrays.toString(bitStrings).substring(0, 100));
//        
//        FileWriter fileWriter = new FileWriter(filename.substring(0, filename.length() - 4) + "_compressed.txt");
//        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            for (int i = 0; i < bitStrings.length; i++) {
//                byte[] bytes = bitStrings[i].getBytes();
//                printWriter.print(bytes);
//            }
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        String[] decompressed = compr.decompress(bitStrings);
//        System.out.println(Arrays.toString(decompressed).substring(0, 1000));

        LZCompression lzComp = new LZCompression();
        String text = "badadadabaab";
        MyArrayList<Integer> compressedText = lzComp.compress(text);
        for (Integer item: compressedText.toArray()) {
            System.out.println(item);
        }
        //for (String item: compressedText.toArray()) {
        //    System.out.println(item);
        //}
    }

}
