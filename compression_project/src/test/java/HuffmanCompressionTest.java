
import java.util.Arrays;
import tiralabra.compression_project.HuffmanCompression;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanCompressionTest {

    @Test
    public void countTest() {
        String s = "kissa";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(s);
        int[] list = compr.freq;

        int asciiCodeK = (int) 'k';
        int asciiCodeI = (int) 'i';
        int asciiCodeS = (int) 's';
        int asciiCodeA = (int) 'a';

        assertEquals(list[asciiCodeK], 1);
        assertEquals(list[asciiCodeI], 1);
        assertEquals(list[asciiCodeS], 2);
        assertEquals(list[asciiCodeA], 1);
    }

    
    @Test
    public void testOnlyCompression() {
        String string = "dabdccadadad";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();

        String test = "0111000101101110110110";

        assertEquals(test, bitString);
    }

    @Test
    public void testAStringDecompressed() {
        String string = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_ BED";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        assertEquals(string, decompressed);
    }

    @Test
    public void testSameStringTwice() {
        String string = "dabdccadadad";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        HuffmanCompression compr2 = new HuffmanCompression();
        compr2.countFreqs(string);
        compr2.addFreqs();
        compr2.treeify();
        String bitString2 = compr2.linesToBits();
        String decompressed2 = compr2.decompress(bitString2);

        String test = "0111000101101110110110";

        assertEquals(test, bitString);
        assertEquals(string, decompressed);

        assertEquals(test, bitString2);
        assertEquals(string, decompressed2);
    }
/*
    @Test
    public void testRepeatedOneChar() {
        String string = "aaa";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        String test = "000";

        assertEquals(test, bitString);
        assertEquals(string, decompressed);
    }

    @Test
    public void testWithOneChar() {
        String string = "a";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        String test = "0";

        assertEquals(test, bitString);
        assertEquals(string, decompressed);
    }
    */
    @Test
    public void testWithTwoChars() {
        String string = "ab";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        String test = "01";

        assertEquals(test, bitString);
        assertEquals(string, decompressed);
    }
    
    @Test
    public void testWithLine() {
        String string = "a\nline";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        assertEquals(string, decompressed);
    }
    
    @Test
    public void testScandic() {
        String string = "ääkköset";

        HuffmanCompression compr = new HuffmanCompression();
        compr.countFreqs(string);
        compr.addFreqs();
        compr.treeify();
        String bitString = compr.linesToBits();
        String decompressed = compr.decompress(bitString);

        assertEquals(string, decompressed);
    }
    

}
