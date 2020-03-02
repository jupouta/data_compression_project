package tiralabra.compression_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import tiralabra.datastructure.MyArrayList;

/**
 * A class for handling reading and writing of the files.
 */
public class FileHandler {

    /**
     * The lines of the file as an array of strings. For the array, use a
     * manually created class.
     */
    private MyArrayList<String> lines;

    /**
     * Creates a new FileHandler. No parameters needed.
     */
    public FileHandler() {
        this.lines = new MyArrayList<>(String.class);
    }

    /**
     * Read the lines in the given file.
     *
     * @param filename The name of the filename. The path can be absolute or
     * relative.
     */
    public void readFile(String filename) {
        File testFile = new File(filename);

        try (Scanner fileReader = new Scanner(testFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                this.lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * The lines of the file returned as an array.
     *
     * @return the array of lines.
     */
    public String[] getLines() {
        return this.lines.toArray();
    }

    public void writeFile(String fileName, String[] lines) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName + "_compressed");
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (int i = 0; i < lines.length; i++) {
                printWriter.print(lines[i]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
