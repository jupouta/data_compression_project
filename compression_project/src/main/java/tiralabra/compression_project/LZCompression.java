package tiralabra.compression_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class LZCompression {
    
    
    public ArrayList<byte[]> compress(String text) {
        HashSet<String> dict = new HashSet<>();
        
        for (int i = 0; i < text.length(); i++) {
            dict.add(text.charAt(i) + "");
        }
        
        String p = text.charAt(0) + "";
        ArrayList<byte[]> code = new ArrayList<>();
        
        int i = 1;
        while (i < text.length()) {
            String c = text.charAt(i) + "";
            
            if (dict.contains(p+c)) {
                //System.out.println("yes: " + p+c);
                p = p+c;
                
            } else {
                //System.out.println(Arrays.toString(p.getBytes()));
                code.add(p.getBytes());
                
                dict.add(p+c);
                //System.out.println(Arrays.toString(p.getBytes()));
                //System.out.println("else: " + p+c);
                p = c;
            }
            
            i++;
            
            if (i == text.length()) {
                code.add(p.getBytes());
            }
        }
        
        
        return code;
    }
}
