
import tiralabra.compression_project.Compression;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyArrayList;


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
    
}