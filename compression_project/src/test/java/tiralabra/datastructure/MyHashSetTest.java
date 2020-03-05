package tiralabra.datastructure;


import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyHashSet;


public class MyHashSetTest {

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
    
    @Test
    public void testAdd() {
        MyHashSet hashList = new MyHashSet();

        for (int i = 0; i < 11; i++) {
            hashList.add(i + "");
        }
        
        assertEquals(20, hashList.theList.length);
    }
    
    @Test
    public void testHashCode() {
        MyHashSet hashList = new MyHashSet();
        int num = hashList.hashCode("kissa");
        
        assertTrue(num > -1);
    }
    
    @Test
    public void testHashCodeForString() {
        MyHashSet hashList = new MyHashSet();
        int num = hashList.hashCodeForString("kissa");
        
        int realNum = 0;
        int elemLength = "kissa".length();

        for (int i = 0; i < elemLength; i++) {
            int ascii = (int) "kissa".charAt(i);

            realNum += (ascii * (Math.pow(2.0, elemLength - 1 + i)));
        }
        
        assertEquals(realNum % 10, num);
    }
    
    @Test
    public void testHashCodeNotBigger() {
        MyHashSet hashList = new MyHashSet();
        int num = hashList.hashCode("kissa");

        assertTrue((num % 10) < 10);
    }
    
    /*
    @Test(timeout = 10000)
    public void addMore() {
        MyHashSet hashList = new MyHashSet();
        
        for (int i = 1; i < 100000; i++) {
            hashList.add("num"+ (i-100));
        }
        
        assertEquals(true, hashList.contains("" + 1));
        assertEquals(true, hashList.contains("" + 5));
        assertEquals(true, hashList.contains("" + 1000));
    }*/
    
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
    
    @Test
    public void hashFalseContains() {
        MyHashSet hashList = new MyHashSet();
        assertFalse(hashList.contains("an example"));
    }
    
    @Test
    public void testGrowing() {
        MyHashSet hashList = new MyHashSet();
        for (int i = 0; i < 10; i++) {
            hashList.add(i + "");
        }
        
        hashList.growList();
        
        assertEquals(20, hashList.theList.length);
    }
}
