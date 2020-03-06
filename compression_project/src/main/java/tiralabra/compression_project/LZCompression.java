package tiralabra.compression_project;

import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyHashSet;

/**
 * The compression to be made by using the LZW algorithm.
 */
public class LZCompression {

    /**
     * The code which helps to decompress the compressed file. This variable
     * holds the strings of characters that don't have an ASCII code.
     */
    private final MyArrayList<String> code;

    public LZCompression() {
        this.code = new MyArrayList<>(String.class);
    }

    /**
     * Compress the file lines to integers.
     *
     * @param lines The string of lines in the file to be compressed.
     * @return The array list of integers representing the compressed version.
     */
    public MyArrayList<Integer> compress(String lines) {
        MyHashSet myHashSet = new MyHashSet();

        // the encoding for the file
        MyArrayList<Integer> encoding = new MyArrayList<>(Integer.class);

        // alustus
        for (char c : lines.toCharArray()) {
            String ch = "" + c;
            if (!myHashSet.contains(ch)) {
                myHashSet.add(ch);
            }
        }

        if (lines.length() == 1) {   // texts with only one char
            encoding.add((int) lines.charAt(0));
            return encoding;
        }

        int indForCharCode = 256;   // the numbers after ascii code
        String p = lines.charAt(0) + "";

        for (int i = 1; i < lines.length(); i++) {

            String c = lines.charAt(i) + "";

            if (myHashSet.contains(p + c)) {
                p = p + c;
            } else {
                if (p.length() == 1) { // single character
                    int asciiP = (int) p.charAt(0);
                    encoding.add(asciiP);
                } else { // multiple characters
                    encoding.add(indForCharCode);
                    code.add(p);
                    indForCharCode++;
                }

                myHashSet.add(p + c);
                p = c;
            }

            // add the last chars to encoding
            if (i == lines.length() - 1) {
                if (p.length() == 1) {  // single char
                    int asciiP = (int) p.charAt(0);
                    encoding.add(asciiP);
                } else {    // multiple characters
                    encoding.add(indForCharCode);
                    code.add(p);
                }
            }
        }
        return encoding;
    }

    /**
     * Return the decompressed version of the file as a string.
     *
     * @param lines The compressed version of the file as a string.
     * @return The decompressed file as a string.
     */
    public String decompress(String lines) {

        String[] linesSplit = lines.split("\\|");

        MyArrayList listDecomp = new MyArrayList<>(String.class);

        for (String line : linesSplit) {
            int number = Integer.parseInt(line);
            if (number < 256) { // ascii
                char c = (char) number;
                listDecomp.add("" + c);
            } else { // strings with no ascii (strings len > 1)
                String c = this.code.get(number - 256);
                listDecomp.add(c);
            }
        }

        String returnS = String.join("", (String[]) listDecomp.toArray());
        return returnS;
    }

}
