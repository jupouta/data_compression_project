package tiralabra.compression_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import tiralabra.datastructure.MyArrayList;

/**
 * A class for handling reading and writing of the files.
 */
public class FileHandler {

    /**
     * Creates a new FileHandler. No parameters needed.
     */
    public FileHandler() {
    }

    /**
     * Read the lines in the given file.
     *
     * @param filename The name of the filename. The path can be absolute or
     * relative.
     * @return The lines of the file.
     */
    public MyArrayList<String> readFile(String filename) {
        File file = new File(filename);
        System.out.println("Original file size: " + (file.length() / 1024)
                + " kb");

        MyArrayList<String> lines = new MyArrayList<>(String.class);

        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        return lines;
    }

    /**
     * Write the compressed version of a file as bytes.
     *
     * @param filename The name of the file.
     * @param fileString The compressed file that includes bytes.
     * @return The division needed later for byte counting.
     * @throws FileNotFoundException Exception when file not found.
     * @throws IOException IOException.
     */
    public int writeHffByteFile(String filename, String fileString) throws
            FileNotFoundException, IOException {
        File file = new File(filename.substring(0, filename.length() - 4)
                + "_compressed_hff.bin");

        int division = fileString.length() % 8; // 1 byte = 8 bits

        try (FileOutputStream stream = new FileOutputStream(file)) {
            int pos = 0;
            while (pos < fileString.length()) {
                byte nextByte = 0x00;
                // takes the eight characters
                for (int i = 0; i < 8 && pos + i < fileString.length(); i++) {
                    nextByte <<= 1;
                    nextByte += fileString.charAt(pos + i) == '0' ? 0x0 : 0x1;
                }
                stream.write(new byte[]{nextByte});
                pos += 8;
            }
        }

        System.out.println("Compressed file size (Huffman): "
                + (file.length() / 1024) + " kb");

        return division;
    }

    /**
     * Read the compressed file with bytes in it.
     *
     * @param filename The name of the file.
     * @param fileEnding The ending of the file name, e.g. "compress_hff.txt"
     * @return The array of bytes written from the file.
     * @throws IOException IOException.
     */
    public byte[] readByteFile(String filename, String fileEnding)
            throws IOException {
        byte[] array = Files.readAllBytes(Paths.get(
                filename.substring(0, filename.length() - 4)
                + "_" + fileEnding));
        return array;
    }

    /**
     * Write the given lines to a file.
     *
     * @param filename The name of the file.
     * @param linesToWrite The lines to be written.
     * @param fileEnding The ending part of the file, e.g. "compressed_lz.txt"
     * @throws IOException IOException.
     */
    public void writeFile(String filename, String linesToWrite,
            String fileEnding) throws IOException {
        File file = new File(filename.substring(0, filename.length() - 4) + "_"
                + fileEnding);

        FileWriter fileWriter = new FileWriter(file);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(linesToWrite);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Decompressed file size: " + (file.length() / 1024)
                + " kb");
    }

}
