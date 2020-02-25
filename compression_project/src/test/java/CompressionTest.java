
import tiralabra.compression_project.Compression;
import org.junit.Test;
import static org.junit.Assert.*;

public class CompressionTest {

    @Test
    public void countTest() {
        String[] arrayList = new String[1];
        arrayList[0] = "kissa";
        
        Compression compr = new Compression();
        compr.countFreqs(arrayList);
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
    public void testAString() {
        String string = "dabdccadadad";
        String[] lines = new String[1];
        lines[0] = string;
        
        Compression compr = new Compression();
        compr.countFreqs(lines);
        compr.addFreqs();
        compr.treeify();
        String[] bitString = compr.linesToBits();
        String[] decompressed = compr.decompress(bitString);
        
        String[] test = {"0111000101101110110110"};
        
        assertArrayEquals(test, bitString);
        assertEquals(string, decompressed[0]);
    }
    
}