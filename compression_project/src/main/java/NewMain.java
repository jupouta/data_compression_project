
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import tiralabra.compression_project.HuffmanCompression;
import tiralabra.compression_project.FileHandler;
import tiralabra.compression_project.LZCompression;
import tiralabra.datastructure.MyArrayList;

public class NewMain {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);

        System.out.print("Which file to read? ");
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);

        String[] fileLines = fileHandler.getLines();
        String s = String.join("\n", fileLines);

        HuffmanCompression compr = new HuffmanCompression();

        compr.countFreqs(s);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        System.out.println(bitString);

        
        // jos viimeinen rivi ei jaollinen 8:lla, ei toimi
        // ota ylös jakojäännös; katso viimeinen rivi/8 pätkä, josta otetaan
        // jakojäännöksen verran mukaan (eli poistetaan ne ylimääräiset nollat
        // alusta)
        try (FileOutputStream stream = new FileOutputStream(filename.substring(0, filename.length() - 4) + "_compressed.jl")) {
            int pos = 0;
            while (pos < bitString.length()) {
                byte nextByte = 0x00;
                for (int i = 0; i < 8 && pos + i < bitString.length(); i++) {
                    nextByte <<= 1;
                    nextByte += bitString.charAt(pos + i) == '0' ? 0x0 : 0x1;
                }
                stream.write(new byte[]{nextByte});
                pos += 8;
                System.out.println(bitString.length() - pos);
            }
        }
        
        byte[] array = Files.readAllBytes(Paths.get("./test_compressed.jl"));
        System.out.println(Arrays.toString(array));
        String asd = "";
        for (byte b:array) {
            // jos viimeinen rivi, ehkä "%{jäännös}s"
            asd += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        System.out.println(asd);
        System.out.println(compr.decompress(asd));

        /*
        FileWriter fileWriter = new FileWriter(filename.substring(0, filename.length() - 4) + "_compressed.txt");
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (int i = 0; i < bitString.length; i++) {
                byte[] bytes = bitString[i].getBytes();
                printWriter.print(bytes);
            }
        }
         catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }*/
 /*
        String[] decompressed = compr.decompress(bitString);
        System.out.println(Arrays.toString(decompressed).substring(0, 1000));

        FileWriter fileWriter2 = new FileWriter(filename.substring(0, filename.length() - 4) + "_decompressed.txt");
        try (PrintWriter printWriter = new PrintWriter(fileWriter2)) {
            
            for (int i = 0; i < decompressed.length; i++) {
                printWriter.print(decompressed[i]);
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } */
 /*
        LZCompression lzComp = new LZCompression();
        
        
        System.out.print("Which file to read? ");
        Scanner reader = new Scanner(System.in);
        String filename = reader.nextLine();

        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile(filename);
        
        String[] text = fileHandler.getLines();
        
        //String[] text = {"badadadabaab"};
        //String[] text = {"abc"};
        MyArrayList<String> compressedText = lzComp.compress3(text);
        System.out.println("compression done..");
        System.out.println(compressedText.length());
        /*
        /*
        
        
        FileWriter fileWriter = new FileWriter(filename.substring(0, filename.length() - 4) + "_compressed_lzw.txt");
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (String line: compressedText.toArray()) {
                printWriter.print(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        //for (String item: compressedText.toArray()) {
        //    System.out.println(item);
        //}
         */
    }

}
