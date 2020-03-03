package tiralabra.compression_project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import tiralabra.datastructure.MyArrayList;

/**
 * A class for the compression process with the two compression algorithms. Asks
 * the user the filename to be compressed.
 */
public class Gui {

    private final Scanner scanner;
    private final FileHandler fileHandler;
    private String filename;
    private HuffmanCompression huffmanCompr;
    private LZCompression lzCompr;

    public Gui(Scanner scanner) {
        this.scanner = scanner;
        this.fileHandler = new FileHandler();
        this.filename = "";
    }

    public void start() throws IOException {
        System.out.print("Which file do you want to read? ");
        this.filename = scanner.nextLine();

        MyArrayList<String> fileLines = fileHandler.readFile(filename);
        String s = String.join("\n", (String[]) fileLines.toArray());

        huffmanCompr = new HuffmanCompression();
        huffmanCompress(s);
        //huffmanDecompress();

        lzCompr = new LZCompression();
        lzCompress(s);
        lzDecompress();

    }

    public void huffmanCompress(String lines) throws IOException {
        huffmanCompr.countFreqs(lines);
        huffmanCompr.addFreqs();
        huffmanCompr.treeify();

        String bitString = huffmanCompr.linesToBits();

        fileHandler.writeByteFile(filename, bitString);
        System.out.println("Huffman compression done!");

    }

    public void huffmanDecompress() throws IOException {
        byte[] array = fileHandler.readByteFile(filename, "compressed_hff.bin");

        // TODO korjaa tämä arrayksi
        String asd = "";
        for (byte b : array) {
            asd += String.format("%8s", Integer.toBinaryString(b & 0xFF))
                    .replace(' ', '0');
        }

        String decompressed = huffmanCompr.decompress(asd);
        fileHandler.writeFile(filename, decompressed, "decompressed_hf.txt");

        System.out.println("Huffman decompression done!");
    }

    public void lzCompress(String text) throws IOException {
        MyArrayList<Integer> compressedText = lzCompr.compress(text);
        
        try (FileOutputStream stream = new FileOutputStream(
                filename.substring(0, filename.length() - 4)
                + "_compressed_lz.bin")) {
            
            for (int i = 0; i < compressedText.length(); i++) {
                int num = compressedText.get(i);
            
                byte[] result = new byte[4];
                result[0] = (byte) ((num & 0xFF000000) >> 24);
                result[1] = (byte) ((num & 0x00FF0000) >> 16);
                result[2] = (byte) ((num & 0x0000FF00) >> 8);
                result[3] = (byte) ((num & 0x000000FF) >> 0);
                stream.write(result);
            }

        }

        //String s = String.join("|", (String[]) compressedText.toArray());
        //fileHandler.writeFile(filename, s, "compressed_lz.txt");

        System.out.println("LZW compression done!");

    }

    public void lzDecompress() throws IOException {
        //String decompressedName = filename.substring(0, filename.length() - 4)
        //        + "_compressed_lz.txt";
        //MyArrayList<String> lines = fileHandler.readFile(decompressedName);
        //String decompressed = lzCompr.decompress(String.join("\n",
        //        (String[]) lines.toArray()));
        
        String ending = "compressed_lz.bin";
        byte[] byteFile = fileHandler.readByteFile(filename, ending);        
        MyArrayList<String> nums = new MyArrayList<>(String.class);
        
        int i = 0;
        while (i < byteFile.length) {

            int byteToInt = ((byteFile[i] & 0xFF) << 24) | 
                ((byteFile[i+1] & 0xFF) << 16) | 
                ((byteFile[i+2] & 0xFF) << 8 ) | 
                ((byteFile[i+3] & 0xFF) << 0 );
            nums.add(byteToInt + "");
            i += 4;
            }           
        
        String decompressed = lzCompr.decompress(String.join("|",
                (String[]) nums.toArray()));
        System.out.println(decompressed.substring(0, 150));

        fileHandler.writeFile(filename, decompressed, "decompressed_lz.txt");

        System.out.println("LZW decompression done!");
    }

}
