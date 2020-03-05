package tiralabra.datastructure;


import org.junit.Test;
import static org.junit.Assert.*;

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
    
    @Test
    public void testToString() {
        Node node = new Node("a", 1);
        String result = "a: 1";
        
        assertEquals(result, node.toString());
    }
    
    @Test
    public void testGetChar() {
        Node node = new Node("a", 1);
        String nodeChar = node.getChar();
        
        assertFalse(nodeChar.equals("b"));
        assertTrue(nodeChar.equals("a"));
    }
    
    @Test
    public void testGetCount() {
        Node node = new Node("a", 1);
        int nodeCount = node.getCount();
        
        assertFalse(nodeCount == 2);
        assertTrue(nodeCount == 1);
    }
    
    @Test
    public void testGetLeft() {
        Node node = new Node("a", 1);
        node.left = new Node("b", 2);
        
        assertFalse(node.getLeft()== null);
        assertTrue(node.getRight() == null);
    }
    
    @Test
    public void testGetRight() {
        Node node = new Node("a", 1);
        node.right = new Node("b", 2);
        
        assertTrue(node.getLeft() == null);
        assertFalse(node.getRight() == null);
    }
    
}
