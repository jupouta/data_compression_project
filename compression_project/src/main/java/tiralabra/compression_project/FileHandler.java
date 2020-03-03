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
     * @param filename The name of the filename. The path can be absolute or
     * relative.
     * @return The lines of the file.
     */
    public MyArrayList<String> readFile(String filename) {
        File testFile = new File(filename);
        MyArrayList<String> lines = new MyArrayList<>(String.class);

        try (Scanner fileReader = new Scanner(testFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Write the compressed version of a file as bytes.
     * @param filename The name of the file.
     * @param fileString The compressed file.
     * @throws FileNotFoundException Exception when file not found.
     * @throws IOException IOException.
     */
    public void writeByteFile(String filename, String fileString) throws FileNotFoundException, IOException {
        try (FileOutputStream stream = new FileOutputStream(filename.substring(0, filename.length() - 4) + "_compressed.bin")) {
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
    }

    /**
     * Read the compressed file with bytes in it.
     * @param filename The name of the file.
     * @return The array of bytes written from the file.
     * @throws IOException IOException.
     */
    public byte[] readByteFile(String filename) throws IOException {
        byte[] array = Files.readAllBytes(Paths.get(filename.substring(0, filename.length() - 4) + "_compressed.bin"));
        return array;
    }

    /**
     * Write the given lines to a file.
     * @param filename  The name of the file.
     * @param linesToWrite  The lines to be written.
     * @param fileEnding    The ending part of the file, e.g. "compressed_lz.txt"
     * @throws IOException IOException.
     */
    public void writeFile(String filename, String linesToWrite, String fileEnding) throws IOException {
        FileWriter fileWriter = new FileWriter(filename.substring(0, filename.length() - 4) + "_" + fileEnding);
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(linesToWrite);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
