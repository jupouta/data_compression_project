
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyHash;


public class HashTest {
    
    public HashTest() {
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

    @Test
    public void addOne() {
        MyHash hashList = new MyHash();
        hashList.add("a");
        
        assertEquals(true, hashList.contains("a"));
        assertEquals(100000000, hashList.theList.length);
    }
    
    @Test
    public void hashContains() {
        MyHash hashList = new MyHash();

        hashList.add("an example");
        hashList.add("another example");
        hashList.add("it continues");
        hashList.add("here");
        
        System.out.println(hashList.hashCode("another example"));
        System.out.println(hashList.hashCode("an example"));
        
        assertEquals(true, hashList.contains("an example"));
        assertEquals(true, hashList.contains("another example"));
        assertEquals(true, hashList.contains("it continues"));
        assertEquals(true, hashList.contains("here"));
        assertEquals(false, hashList.contains("blaa"));
        assertEquals(false, hashList.contains("no example"));

    }
    
    
}
