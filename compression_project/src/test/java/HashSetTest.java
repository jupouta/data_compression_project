
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyHashSet;


public class HashSetTest {

    @Test
    public void addOne() {
        MyHashSet hashList = new MyHashSet();
        hashList.add("a");
        
        assertEquals(true, hashList.contains("a"));
    }
    
    @Test
    public void addSame() {
        MyHashSet hashList = new MyHashSet();
        
        for (int i = 0; i < 1000000; i++) {
            hashList.add("esim");
        }
        
        assertEquals(true, hashList.contains("esim"));
        assertEquals(false, hashList.contains("not"));
    }
    
    @Test(timeout = 10000)
    public void addMore() {
        MyHashSet hashList = new MyHashSet();
        
        for (int i = 1; i < 100000; i++) {
            hashList.add("num"+ (i-100));
        }
        
        assertEquals(true, hashList.contains("" + 1));
        assertEquals(true, hashList.contains("" + 5));
        assertEquals(true, hashList.contains("" + 1000));
    }
    
    @Test
    public void hashContains() {
        MyHashSet hashList = new MyHashSet();
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
