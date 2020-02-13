
import java.util.Arrays;
import tiralabra.datastructure.MyPrioQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.compression_project.Node;

public class PrioQueuTest {

    public PrioQueuTest() {
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
    public void testAddOrder() {
        MyPrioQueue prioQ = new MyPrioQueue();

        for (int i = 5; i > 0; i--) {
            prioQ.addNode(new Node("" + i, i));
        }

        assertEquals(5, prioQ.size());
        assertEquals("1", prioQ.nodesList[0].character);
        assertEquals("2", prioQ.nodesList[1].character);
        assertEquals(5, prioQ.nodesList[4].count);

        assertEquals(4, prioQ.nodesList[3].count);
    }

    @Test
    public void testSameCount() {
        MyPrioQueue prioQ = new MyPrioQueue();

        for (int i = 5; i > 0; i--) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        prioQ.addNode(new Node("55", 5));

        assertEquals(6, prioQ.size());
        assertEquals("1", prioQ.nodesList[0].character);
        assertEquals("2", prioQ.nodesList[1].character);
        assertEquals(4, prioQ.nodesList[3].count);
        
        assertEquals(5, prioQ.nodesList[5].count);
        assertEquals(5, prioQ.nodesList[4].count);
        assertEquals("55", prioQ.nodesList[5].character);
        assertEquals("5", prioQ.nodesList[4].character);
    }

    @Test
    public void testAdd20() {
        MyPrioQueue prioQ = new MyPrioQueue();

        for (int i = 0; i < 12; i++) {
            prioQ.addNode(new Node("" + i, i));
        }

        assertEquals(12, prioQ.size());
    }

    @Test(timeout = 1000)
    public void testAdd10000() {
        MyPrioQueue prioQ = new MyPrioQueue();

        for (int i = 0; i < 10000; i++) {
            prioQ.addNode(new Node("" + i, i));
        }

        assertEquals(10000, prioQ.size());
    }
    
    @Test
    public void testRemove1() {
        MyPrioQueue prioQ = new MyPrioQueue();
        
        for (int i = 5; i > 0; i--) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        assertEquals(5, prioQ.size());
        
        Node polled = prioQ.poll();
        System.out.println(Arrays.toString(prioQ.nodesList));
 
        assertEquals(4, prioQ.size());
        assertEquals(1, polled.count);
        assertEquals(2, prioQ.nodesList[0].count);
    }
    
    @Test
    public void testRemove2() {
        MyPrioQueue prioQ = new MyPrioQueue();
        
        for (int i = 5; i > 0; i--) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        assertEquals(5, prioQ.size());
        
        Node polled = prioQ.poll();
        assertEquals(4, prioQ.size());
        
        Node polled2 = prioQ.poll();
        System.out.println(Arrays.toString(prioQ.nodesList));
        
        assertEquals(3, prioQ.size());
        assertEquals(1, polled.count);
        assertEquals(3, prioQ.nodesList[0].count);
        
        assertEquals(2, polled2.count);
    }
    
    @Test
    public void testRemoveAndAdd() {
        MyPrioQueue prioQ = new MyPrioQueue();
        
        for (int i = 5; i > 0; i--) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        assertEquals(5, prioQ.size());
        
        Node polled = prioQ.poll();
        assertEquals(4, prioQ.size());
        
        Node polled2 = prioQ.poll();
        assertEquals(3, prioQ.size());
        
        prioQ.addNode(new Node("100", 100));
        System.out.println(Arrays.toString(prioQ.nodesList));
        
        assertEquals(4, prioQ.size());
        assertEquals(1, polled.count);
        assertEquals(3, prioQ.nodesList[0].count);
        
        assertEquals(2, polled2.count);
        assertEquals("100", prioQ.nodesList[prioQ.items-1].character);
    }
    
    @Test
    public void testBible() {
        
    }
}
