package tiralabra.compression_project;

import java.util.Arrays;
import java.util.HashSet;
import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyHash;

/**
 * The compression to be made by using the LZW algorithm.
 */
public class LZCompression {
    
    /**
     * The code which helps to decompress the compressed file.
     * This variable holds the strings of characters that don't have
     * an ASCII code.
     */
    private MyArrayList<String> code;
    
    public LZCompression() {
        this.code = new MyArrayList<>(String.class);
    }

    public MyArrayList<String> compress(String l) {
        MyHash myHashSet = new MyHash();
        //HashSet<String> myHashSet = new HashSet<>();
        MyArrayList<String> encoding = new MyArrayList<>(String.class);

        // alustus
        for (char c : l.toCharArray()) {
            String ch = "" + c;
            if (!myHashSet.contains(ch)) {
                myHashSet.add(ch);
            }
        }

        int index = 256;
        // window
        for (int i = 0; i < l.length(); i++) {

            // TODO: consider last.
            for (int j = i; j < l.length(); j++) { // askel

                String window = l.substring(i, j);

                if (window.length() == 0) {
                    continue;
                }

                if (myHashSet.contains(window)) {
                    continue;
                } else {
                    myHashSet.add(window);

                    String prev = window.substring(0, window.length() - 1);

                    if (prev.length() == 1) { // single character (with ascii)
                        encoding.add((int) prev.charAt(0) + "");
                    } else {
                        encoding.add(index + "");
                        code.add(prev);     // string of characters without ascii
                        index++;
                    }
                    i = j - 1;

                }
            }
        }

        return encoding;
    }
    
    public String decompress(String lines) {
        // TODO: change this
        String[] linesSplit = lines.split("\\|");
        
        MyArrayList listDecomp = new MyArrayList<>(String.class);
        
        for (String line: linesSplit) {
            int number = Integer.parseInt(line);
            if (number < 256) { // ascii
                char c = (char) number;
                listDecomp.add("" + c);
            } else { // strings with no ascii (strings len > 1)
                String c = this.code.get(number-256);
                listDecomp.add(c);
            }
        }
        
        String returnS = String.join("", (String[]) listDecomp.toArray());
        return returnS;
    }

}
