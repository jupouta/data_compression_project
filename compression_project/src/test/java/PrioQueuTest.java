

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
    public void testAdd20() {
        MyPrioQueue prioQ = new MyPrioQueue();
        
        for (int i = 0; i < 12; i++) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        assertEquals(20, prioQ.nodesList.length);
        assertEquals(13, prioQ.size());
    }
    
    @Test(timeout = 5000)
    public void testAdd100000() {
        MyPrioQueue prioQ = new MyPrioQueue();
        
        for (int i = 0; i < 100000; i++) {
            prioQ.addNode(new Node("" + i, i));
        }
        
        assertEquals(100001, prioQ.size());
    }
}
