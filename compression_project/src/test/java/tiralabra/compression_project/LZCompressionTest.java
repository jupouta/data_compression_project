package tiralabra.compression_project;


import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyArrayList;


public class LZCompressionTest {

    @Test
    public void testOne() {
        String s = "esimerkki";
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("esimerkki", decompressed);
    }
    
    @Test
    public void testMoreRep() {
        String s = "badadadabaab";
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("badadadabaab", decompressed);
    }
    
    @Test
    public void testWithNewLine() {
        String s = "esimerkki\nrivi";
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        
        
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("esimerkki\nrivi", decompressed);
    }
    
    @Test
    public void testWithOneChar() {
        String s = "a";
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals(1, compressed.length());
        assertEquals("a", decompressed);
    }
    
    @Test
    public void testWithRepeatedOneChar() {
        String s = "aaa";
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("aaa", decompressed);
    }
    
    @Test
    public void testWithScandic () {
        String s = "ääkköset";
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("ääkköset", decompressed);
    }
    
    @Test (timeout = 10000)
    public void testWith5000() {
        MyArrayList<String> list = new MyArrayList<>(String.class);
        
        for (int i = 0; i < 5000; i++) {
            list.add("num" + i);
        }
        
        String s = String.join("", list.toArray());
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("num0", decompressed.substring(0, 4));
        assertEquals("num1", decompressed.substring(4, 8));
        assertEquals("num2", decompressed.substring(8, 12));
        
    }
    
    @Test (timeout = 10000)
    public void testWith10000() {
        MyArrayList<String> list = new MyArrayList<>(String.class);
        
        for (int i = 0; i < 10000; i++) {
            list.add("num" + i);
        }
        
        String s = String.join("", list.toArray());
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("num0", decompressed.substring(0, 4));
        assertEquals("num1", decompressed.substring(4, 8));
        assertEquals("num2", decompressed.substring(8, 12));
        
    }
    
    @Test (timeout = 5000)
    public void testWith100000() {
        MyArrayList<String> list = new MyArrayList<>(String.class);
        
        for (int i = 0; i < 100000; i++) {
            list.add("num" + i);
        }
        
        String s = String.join("", list.toArray());
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("num0", decompressed.substring(0, 4));
        assertEquals("num1", decompressed.substring(4, 8));
        assertEquals("num2", decompressed.substring(8, 12));
        
    }
    
    @Test (timeout = 30000)
    public void testWith999999() {
        MyArrayList<String> list = new MyArrayList<>(String.class);
        
        for (int i = 0; i < 999999; i++) {
            list.add("num" + i);
        }
        
        String s = String.join("", list.toArray());
        
        LZCompression compr = new LZCompression();
        MyArrayList<Integer> compressed = compr.compress(s);
        
        MyArrayList<String> compressedString = new MyArrayList<>(String.class);
        
        for (int i: compressed.toArray()) {
            compressedString.add(i + "");
        }
        String comprLines = String.join("|", compressedString.toArray());
        
        String decompressed = compr.decompress(comprLines);
        
        assertEquals("num0", decompressed.substring(0, 4));
        assertEquals("num1", decompressed.substring(4, 8));
        assertEquals("num2", decompressed.substring(8, 12));
        
    }
 
}
