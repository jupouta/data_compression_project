package tiralabra.compression_project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    /**
     * The division result needed for the Huffman decompression when
     * reading the bytes.
     */
    private int divisionForHuff;

    public Gui(Scanner scanner) {
        this.scanner = scanner;
        this.fileHandler = new FileHandler();
        this.filename = "";
        this.divisionForHuff = 0;
    }

    /**
     * Start the compression program.
     *
     * @throws IOException when there is an issue.
     */
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
        double result = ((double) timestampAfter - (double) timestampBeginn);
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
        System.out.println("Time spent: "
                + (((double) timeStampLZAfter - (double) timeStampLZ)));
        System.out.println("-----");
        System.out.println("Starting LZW decompression..");

        lzDecompress();

    }

    /**
     * Compress the file with the Huffman algorithm.
     *
     * @param lines The lines of the file as a string.
     * @throws IOException when there is an issue.
     */
    public void huffmanCompress(String lines) throws IOException {
        huffmanCompr.countFreqs(lines);
        huffmanCompr.addFreqs();
        huffmanCompr.treeify();

        String bitString = huffmanCompr.linesToBits();
        divisionForHuff = fileHandler.writeHffByteFile(filename, bitString);

    }

    /**
     * Decompress the compressed file with the Huffman algorithm.
     *
     * @throws IOException when there is an issue.
     */
    public void huffmanDecompress() throws IOException {
        byte[] fileArray = fileHandler.readByteFile(filename,
                "compressed_hff.bin");

        MyArrayList<String> binaryToString = new MyArrayList<>(String.class);
        for (int i = 0; i < fileArray.length; i++) {
            byte bt = fileArray[i];
            String asBinStr = null;
            String formatted = null;

            if (i == fileArray.length - 1 && this.divisionForHuff != 0) { //last
                asBinStr = Integer.toBinaryString(
                        (bt & 0xFF) << (8 - this.divisionForHuff));

                formatted = String.format("%8s", asBinStr).replace(' ', '0');
                formatted = formatted.substring(0, this.divisionForHuff);
            } else {
                asBinStr = Integer.toBinaryString(bt & 0xFF);
                formatted = String.format("%8s", asBinStr).replace(' ', '0');
            }
            binaryToString.add(formatted);
        }

        String joinedArray = String.join("", binaryToString.toArray());

        String decompressed = huffmanCompr.decompress(joinedArray);
        fileHandler.writeFile(filename, decompressed, "decompressed_hff.txt");
    }

    /**
     * Compress the file using the LZW algorithm.
     *
     * @param text The lines of the text as a string.
     * @throws IOException when there is an issue.
     */
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

    /**
     * Decompress the compressed file using LZW algorithm.
     *
     * @throws IOException when there is an issue.
     */
    public void lzDecompress() throws IOException {

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
