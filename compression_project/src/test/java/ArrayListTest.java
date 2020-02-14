
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datastructure.MyArrayList;

public class ArrayListTest {

    @Test(timeout = 1000)
    public void add100() {
        MyArrayList<Integer> theList = new MyArrayList(Integer.class);

        for (int i = 0; i < 100; i++) {
            theList.add(i);
        }

        assertEquals(theList.length(), 100);
        assertEquals((long) theList.get(0), (long) 0);
        assertEquals((long) theList.get(50), (long) 50);

    }

    @Test(timeout = 1000)
    public void add1000000() {
        MyArrayList<Integer> theList = new MyArrayList(Integer.class);

        for (int i = 0; i < 1000000; i++) {
            theList.add(i);
        }

        assertEquals(theList.length(), 1000000);
    }

    @Test
    public void testToArray() {
        MyArrayList<String> theList = new MyArrayList(String.class);

        theList.add("this is a test");
        theList.add("and another test");
        theList.add("just to be sure,");
        theList.add("I will add another one.");

        String[] listAsArray = new String[4];
        listAsArray[0] = "this is a test";
        listAsArray[1] = "and another test";
        listAsArray[2] = "just to be sure,";
        listAsArray[3] = "I will add another one.";

        assertArrayEquals(listAsArray, theList.toArray());
    }

    @Test
    public void testLength() {
        MyArrayList<Integer> theList = new MyArrayList(Integer.class);

        for (int i = 0; i < 99; i++) {
            theList.add(i);
        }

        assertEquals(theList.length(), 99);
    }
    
    @Test
    public void testGet() {
        MyArrayList<String> theList = new MyArrayList(String.class);
        theList.add("this is a test");
        theList.add("and another test");
        theList.add("just to be sure,");
        theList.add("I will add another one.");
        
        String index = theList.get(2);
        
        assertEquals(index, "just to be sure,");
    }
    
    @Test
    public void arrayListTest() {
        MyArrayList newList = new MyArrayList(String.class);
        for (int i = 1; i < 15; i++) {
            newList.add("" + i);
        }
        
        Object[] lines = newList.toArray();
        String[] compare = {"1","2","3","4","5","6","7","8","9","10","11","12",
            "13","14"};
        
        assertArrayEquals(compare, lines);
    }
}
