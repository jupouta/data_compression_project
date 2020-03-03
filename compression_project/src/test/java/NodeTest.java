
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.compression_project.Node;


public class NodeTest {

    @Test
    public void testOne() {
        Node node = new Node("a", 1);
        
        assertEquals("a", node.character);
        assertEquals(1, node.count);
    }
    
    @Test
    public void testCompare() {
        Node node = new Node("a", 1);
        Node node2 = new Node("b", 2);
        
        int result = node.compareTo(node2);
        
        assertEquals(-1, result);
    }
    
    @Test
    public void testCompareSameCount () {
        Node node = new Node("a", 1);
        Node node2 = new Node("b", 1);
        
        int result = node.compareTo(node2);
        
        assertEquals(-1, result);
    }
}
