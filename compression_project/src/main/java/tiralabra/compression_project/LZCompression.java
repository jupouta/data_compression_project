package tiralabra.compression_project;

import java.util.HashSet;
import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyHash;

public class LZCompression {

    public MyArrayList<String> compress3(String[] lines) {
        MyHash myHashSet = new MyHash();
        //HashSet<String> myHashSet = new HashSet<>();
        MyArrayList<String> encoding = new MyArrayList<>(String.class);
        String l = String.join("\n", lines);

        // alustus
        for (char c : l.toCharArray()) {
            String ch = "" + c;
            if (!myHashSet.contains(ch)) {
                myHashSet.add(ch);
            }
        }

        // window
        for (int i = 0; i < l.length(); i++) {

            // TODO: consider last.
            System.out.println(i);

            for (int j = i; j < l.length(); j++) { // askel

                String window = l.substring(i, j);
               

                if (window.length() == 0) {
                    continue;
                }

                //System.out.println("win: " + window);

                if (myHashSet.contains(window)) {
                    continue;
                } else {
                    myHashSet.add(window);

                    String prev = window.substring(0, window.length() - 1);

                    if (prev.length() == 1) { // single character
                        encoding.add(prev + "|" + (int) prev.charAt(0));
                    } else {
                        encoding.add(prev + "|" + encoding.length());
                    }
                    i = j - 1;

                }
            }

        }


        return encoding;
    }

    public MyArrayList<String> compress2(String[] text) {
        MyHash myDict = new MyHash();

        String[] charArray = new String[1000000];
        // charArrayn koon muuttaminen + koon valitseminen

        for (int i = 0; i < text.length; i++) {
            for (int ind = 0; ind < text[i].length(); ind++) {
                myDict.add(text[i].charAt(ind) + "");

                int asciiCode = (int) text[i].charAt(ind);
                charArray[asciiCode] = text[i].charAt(ind) + "";
            }
        }

        System.out.println("alustus done..");

        MyArrayList<String> newCode = new MyArrayList<>(String.class);
        int indForCharCode = 256;

        String p = text[0].charAt(0) + "";
        for (int index = 0; index < text.length; index++) {

            for (int i = 0; i < text[index].length(); i++) {

                String c = text[index].charAt(i) + "";
                //System.out.println("index: " + index + ", i: " + i + ", c: " + c);

                if (myDict.contains(p + c)) {
                    p = p + c;
                } else {
                    charArray[indForCharCode] = p;

                    if (p.length() == 1) {
                        int asciiP = (int) p.charAt(0);
                        //System.out.println("added: " + p.charAt(0));
                        newCode.add(asciiP + ",");
                    } else {
                        //System.out.println("added: " + p);
                        newCode.add(indForCharCode + ",");
                        indForCharCode++;
                    }

                    myDict.add(p + c);

                    p = c;
                }

            }

            p = p + "\n";

            if (index == text.length - 1) {
                System.out.println("the last one");
                charArray[indForCharCode] = p;

                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    //System.out.println("added: " + p.charAt(0));
                    newCode.add(asciiP + ",");

                } else {
                    //System.out.println("added: " + p);
                    newCode.add(indForCharCode + ",");
                }
            }

        }

        return newCode;
    }

    public MyArrayList<String> compress(String[] text) {
        //HashSet<String> dict = new HashSet<>();
        MyHash myDict = new MyHash();

        String[] charArray = new String[1000000];
        // charArrayn koon muuttaminen + koon valitseminen

        for (int i = 0; i < text.length; i++) {
            for (int ind = 0; ind < text[i].length(); ind++) {
                //dict.add(text.charAt(i) + "");
                myDict.add(text[i].charAt(ind) + "");

                int asciiCode = (int) text[i].charAt(ind);
                charArray[asciiCode] = text[i].charAt(ind) + "";
            }
        }

        MyArrayList<String> newCode = new MyArrayList<>(String.class);
        int indForCharCode = 256;

        String p = text[0].charAt(0) + "";
        for (int index = 0; index < text.length; index++) {
            int i = 1;

            //System.out.println(p);
            char[] charsOfLine = text[index].toCharArray();

            while (i < text[index].length()) {
                String c = charsOfLine[i] + "";
                //String c = text[index].charAt(i) + "";
                //System.out.println("index: " + index + ", i: " + i + ", c: " + c);

                if (myDict.contains(p + c)) {
                    p = p + c;
                } else {
                    charArray[indForCharCode] = p;

                    if (p.length() == 1) {
                        int asciiP = (int) p.charAt(0);
                        newCode.add(asciiP + ",");
                    } else {
                        newCode.add(indForCharCode + ",");
                        indForCharCode++;
                    }

                    //dict.add(p+c);
                    myDict.add(p + c);

                    p = c;
                }

                i++;
                p = p + "\n";

            }

            if (index == text.length - 1) {
                System.out.println("the last one");
                charArray[indForCharCode] = p;

                if (p.length() == 1) {
                    int asciiP = (int) p.charAt(0);
                    newCode.add(asciiP + ",");

                } else {
                    newCode.add(indForCharCode + ",");
                }
            }

        }

        return newCode;
    }

}
