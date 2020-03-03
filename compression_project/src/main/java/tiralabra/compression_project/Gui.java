
package tiralabra.compression_project;

import java.io.IOException;
import java.util.Scanner;
import tiralabra.datastructure.MyArrayList;

/**
 * A class for the compression process with the two compression algorithms.
 * Asks the user the filename to be compressed.
 */
public class Gui {

    private Scanner scanner;
    private FileHandler fileHandler;
    private String filename;
    private HuffmanCompression huffmanCompr;
    private LZCompression lzCompr;

    public Gui (Scanner scanner) {
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
        byte[] array = fileHandler.readByteFile(filename);
        
        String asd = "";
        for (byte b:array) {
            asd += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        
        String decompressed = huffmanCompr.decompress(asd);
        fileHandler.writeFile(filename, decompressed, "decompressed_hf.txt");
        
        System.out.println("Huffman decompression done!");
    }
    
    public void lzCompress(String text) throws IOException {
        MyArrayList<String> compressedText = lzCompr.compress(text);
        
        String s = String.join("|", (String[]) compressedText.toArray());
        fileHandler.writeFile(filename, s, "compressed_lz.txt");
        
        System.out.println("LZW compression done!");
        
    }
    
    public void lzDecompress() throws IOException {
        String decompressedName = filename.substring(0, filename.length() - 4) + "_compressed_lz.txt";
        MyArrayList<String> lines = fileHandler.readFile(decompressedName);
        String decompressed = lzCompr.decompress(String.join("\n", (String[]) lines.toArray()));
        
        fileHandler.writeFile(filename, decompressed, "decompressed_lz.txt");
        
        System.out.println("LZW decompression done!");
    }
    
}
