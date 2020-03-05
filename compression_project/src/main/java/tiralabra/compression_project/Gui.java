package tiralabra.compression_project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
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
    private int divisionForHuff;

    public Gui(Scanner scanner) {
        this.scanner = scanner;
        this.fileHandler = new FileHandler();
        this.filename = "";
        this.divisionForHuff = 0;
    }

    public void start() throws IOException {
        System.out.print("Which file do you want to read? ");
        this.filename = scanner.nextLine();

        MyArrayList<String> fileLines = fileHandler.readFile(filename);
        if (fileLines.length() == 0) {
            return;
        }
        
        String s = String.join("\n", (String[]) fileLines.toArray());

        huffmanCompr = new HuffmanCompression();
        System.out.println("-----");
        System.out.println("Starting Huffman compression..");
        long timestampBeginn = System.currentTimeMillis();
        huffmanCompress(s);
        long timestampAfter = System.currentTimeMillis();
        double result = (timestampAfter - timestampBeginn / 1000);
        System.out.println("Huffman compression done!");
        System.out.println("Time spent: " + result);
        System.out.println("-----");
        huffmanDecompress();
        System.out.println("Huffman decompression done!");
        System.out.println("-----");

        lzCompr = new LZCompression();
        System.out.println("Starting LZW compression..");
        long timeStampLZ = System.currentTimeMillis();
        lzCompress(s);
        long timeStampLZAfter = System.currentTimeMillis();
        System.out.println("LZW compression done!");
        System.out.println("Time spent: " + 
                ((timeStampLZAfter - timeStampLZ) / 1000));
        System.out.println("-----");
        System.out.println("Starting LZW decompression..");

        lzDecompress();

    }

    public void huffmanCompress(String lines) throws IOException {
        huffmanCompr.countFreqs(lines);
        huffmanCompr.addFreqs();
        huffmanCompr.treeify();

        String bitString = huffmanCompr.linesToBits();
        divisionForHuff = fileHandler.writeHffByteFile(filename, bitString);

    }

    public void huffmanDecompress() throws IOException {
        byte[] array = fileHandler.readByteFile(filename, "compressed_hff.bin");

        //System.out.println(divisionForHuff);
        MyArrayList<String> binaryToString = new MyArrayList<>(String.class);
        for (int i = 0; i < array.length; i++) {
            byte b = array[i];
            String asBS = null;
            String formatted = null;

            if (i == array.length - 1 && this.divisionForHuff != 0) { // last
                asBS = Integer.toBinaryString((b & 0xFF) << (8 - this.divisionForHuff));
                //System.out.println(String.format("%8s", Integer.toBinaryString((b & 0xFF)<<2)).replace(' ', '0'));
                formatted = String.format("%8s", asBS).replace(' ', '0');
                formatted = formatted.substring(0, this.divisionForHuff);
            } else {
                asBS = Integer.toBinaryString(b & 0xFF);
                formatted = String.format("%8s", asBS).replace(' ', '0');
            }
            binaryToString.add(formatted);
        }

        String joined = String.join("", binaryToString.toArray());
        //System.out.println(joined);
        //System.out.println(joined.substring(joined.length() - 16));
        String decompressed = huffmanCompr.decompress(joined);
        fileHandler.writeFile(filename, decompressed, "decompressed_hff.txt");
    }

    public void lzCompress(String text) throws IOException {
        MyArrayList<Integer> compressedText = lzCompr.compress(text);

        File file = new File(filename.substring(0, filename.length() - 4)
                + "_compressed_lz.bin");
        try (FileOutputStream stream = new FileOutputStream(file)) {

            // take every number and convert it to bytes
            for (int i = 0; i < compressedText.length(); i++) {
                int num = compressedText.get(i);

                byte[] result = intToByteArray(num);
                stream.write(result);
            }
        }
        System.out.println("Compressed file size (LZW): "
                + (file.length() / 1024) + " kb");

        //String s = String.join("|", (String[]) compressedText.toArray());
        //fileHandler.writeFile(filename, s, "compressed_lz.txt");
    }

    /**
     * Convert the given integer into a byte array that represents a 32-bit
     * number.
     *
     * @param num The integer to be converted.
     * @return The array of bytes in the given integer.
     */
    public byte[] intToByteArray(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) ((num & 0xFF000000) >> 24);
        result[1] = (byte) ((num & 0x00FF0000) >> 16);
        result[2] = (byte) ((num & 0x0000FF00) >> 8);
        result[3] = (byte) ((num & 0x000000FF) >> 0);
        return result;
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
        while (i < byteFile.length) { // take every four chars (32 bits)
            int byteToInt = ((byteFile[i] & 0xFF) << 24)
                    | ((byteFile[i + 1] & 0xFF) << 16)
                    | ((byteFile[i + 2] & 0xFF) << 8)
                    | ((byteFile[i + 3] & 0xFF) << 0);
            nums.add(byteToInt + "");
            i += 4;
        }

        String decompressed = lzCompr.decompress(String.join("|",
                (String[]) nums.toArray()));

        fileHandler.writeFile(filename, decompressed, "decompressed_lz.txt");
        System.out.println("LZW decompression done!");
    }

}
