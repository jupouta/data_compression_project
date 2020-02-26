package tiralabra.compression_project;

import java.util.HashSet;
import tiralabra.datastructure.MyArrayList;


public class LZCompression {
    
    
    public MyArrayList<Integer> compress(String text) {
        HashSet<String> dict = new HashSet<>();
        
        String[] charArray = new String[300];

        // puuttuu enää hash, jolla tarkistetaan, onko ollut jo kyseinen merkkijono
        // + charArrayn koon muuttaminen
        
        for (int i = 0; i < text.length(); i++) {
            dict.add(text.charAt(i) + "");
            
            int asciiCode = (int) text.charAt(i);
            charArray[asciiCode] = text.charAt(i) + "";
        }
        
        String p = text.charAt(0) + "";

        MyArrayList<Integer> newCode = new MyArrayList<>(Integer.class);
        int indForCharCode = 256;
        
        int i = 1;
        while (i < text.length()) {
            String c = text.charAt(i) + "";
            
            if (dict.contains(p+c)) {
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
                
                dict.add(p+c);

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
    
    public MyArrayList<String> compress2(String text) {
        HashSet<String> dict = new HashSet<>();
        
        //String[] charCode = new String[text.length()*2];
        String[] isCharYetInArray = new String[300];

        // boolean array johon alustettu yksitt kirjaimet
        // tee array, johon lisätään kaikki löydetyt kirjaimet + merkkijonot
        // puuttuu enää hash, jolla tarkistetaan, onko ollut jo kyseinen merkkijono
        
        for (int i = 0; i < text.length(); i++) {
            dict.add(text.charAt(i) + "");
            
            int asciiCode = (int) text.charAt(i);
            isCharYetInArray[asciiCode] = text.charAt(i) + "";
        }
        
        String p = text.charAt(0) + "";
        MyArrayList<String> code = new MyArrayList<>(String.class);

        MyArrayList<Integer> newCode = new MyArrayList<>(Integer.class);
        int indForCharCode = 256;
        
        int i = 1;
        while (i < text.length()) {
            String c = text.charAt(i) + "";
            
            if (dict.contains(p+c)) {
                p = p+c;
            } else {
                code.add(p);
                isCharYetInArray[indForCharCode] = p;
                
                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    newCode.add(asciiP);
                } else {
                    newCode.add(indForCharCode);
                    indForCharCode++;
                }
               
                
                dict.add(p+c);

                p = c;
            }
            
            i++;
            
            if (i == text.length()) {
                code.add(p);
                isCharYetInArray[indForCharCode] = p;
                
                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    newCode.add(asciiP);

                } else {
                    newCode.add(indForCharCode);

                }
            }
        }


        System.out.println(indForCharCode);
        System.out.println(newCode);
        for (int indeksi = 0; indeksi < newCode.length(); indeksi++) {
            System.out.println(newCode.get(indeksi));
            System.out.println(isCharYetInArray[newCode.get(indeksi)]);
        }
        
        return code;
    }
    
    public boolean check (String character) {
        
        return false;
    }
}
