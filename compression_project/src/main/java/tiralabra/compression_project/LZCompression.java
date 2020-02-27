package tiralabra.compression_project;

import java.util.HashSet;
import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyHash;


public class LZCompression {
    
    
    public MyArrayList<Integer> compress(String text) {
        //HashSet<String> dict = new HashSet<>();
        MyHash myDict = new MyHash();
        
        String[] charArray = new String[256+text.length()];
        // charArrayn koon muuttaminen + koon valitseminen
        
        for (int i = 0; i < text.length(); i++) {
            //dict.add(text.charAt(i) + "");
            myDict.add(text.charAt(i) + "");
            
            int asciiCode = (int) text.charAt(i);
            charArray[asciiCode] = text.charAt(i) + "";
        }
        
        String p = text.charAt(0) + "";

        MyArrayList<Integer> newCode = new MyArrayList<>(Integer.class);
        int indForCharCode = 256;
        
        int i = 1;
        while (i < text.length()) {
            String c = text.charAt(i) + "";
            
            if (myDict.contains(p+c)) {
                p = p+c;
            } else {
                charArray[indForCharCode] = p;
                
                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    newCode.add(asciiP);
                } else {
                    newCode.add(indForCharCode);
                    indForCharCode++;
                }
                
                //dict.add(p+c);
                myDict.add(p+c);

                p = c;
            }
            
            i++;
            
            if (i == text.length()) {
                charArray[indForCharCode] = p;
                
                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    newCode.add(asciiP);

                } else {
                    newCode.add(indForCharCode);
                }
            }
        }
        
        
        return newCode;
    }
    
    public boolean check (String character) {
        
        return false;
    }
}
