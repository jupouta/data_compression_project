
import tiralabra.compression_project.Compression;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyArrayList;


public class CompressionTest {
    
    public CompressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void countTest() {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("k");
        arrayList.add("i");
        arrayList.add("s");
        arrayList.add("s");
        arrayList.add("a");
        
        Compression compr = new Compression(arrayList);
        
        HashMap<String, Integer> freqs = compr.countFreqs();
        int k = freqs.get("k");
        int i = freqs.get("i");
        int s = freqs.get("s");
        int a = freqs.get("a");
        assertEquals(k, 1);
        assertEquals(i, 1);
        assertEquals(s, 2);
        assertEquals(a, 1);
    }
    
    @Test
    public void arrayListTest() {
        MyArrayList newList = new MyArrayList();
        for (int i = 1; i < 15; i++) {
            newList.add("" + i);
        }
        
        Object[] lines = newList.getLines();
        String[] compare = {"1","2","3","4","5","6","7","8","9","10","11","12",
            "13","14", null, null, null, null, null, null};
        
        assertEquals(20, lines.length);
        assertArrayEquals(compare, lines);
    }
}
