
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyHash;


public class HashTest {

    @Test
    public void addOne() {
        MyHash hashList = new MyHash();
        hashList.add("a");
        
        assertEquals(true, hashList.contains("a"));
        assertEquals(100000000, hashList.theList.length);
    }
    
    @Test(timeout = 5000)
    public void addMore() {
        MyHash hashList = new MyHash();
        
        for (int i = 0; i < 10000000; i++) {
            hashList.add("" + i);
            //assertEquals(true, hashList.contains("" + i));
        }
    }
    
    @Test
    public void hashContains() {
        MyHash hashList = new MyHash();

        hashList.add("an example");
        hashList.add("another example");
        hashList.add("it continues");
        hashList.add("here");
        
        assertEquals(true, hashList.contains("an example"));
        assertEquals(true, hashList.contains("another example"));
        assertEquals(true, hashList.contains("it continues"));
        assertEquals(true, hashList.contains("here"));
        assertEquals(false, hashList.contains("blaa"));
        assertEquals(false, hashList.contains("no example"));
    }
    
    
}
