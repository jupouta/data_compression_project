
import java.util.ArrayList;
import tiralabra.compression_project.Compression;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


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
        String[] testList = new String[5];
        testList[0] = "k";
        testList[1] = "i";
        testList[2] = "s";
        testList[3] = "s";
        testList[4] = "a";
        
        Compression compr = new Compression(testList);
        
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
}
