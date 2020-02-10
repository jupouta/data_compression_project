package tiralabra.compression_project;

import java.util.HashMap;
import java.util.PriorityQueue;
import tiralabra.datastructure.MyArrayList;

/**
 * The compression to be made.
 */
public class Compression {

    /**
     * All the data structures needed in the compression.
     */
    private PriorityQueue<Node> heap;
    public int[] freq;

    /**
     * Creates a new Compression with the given list of lines found in a file.
     */
    public Compression() {
        this.heap = new PriorityQueue<>();
        this.freq = new int[256];
    }

    /**
     * Counts the times a character is included in a file.
     */
    public void countFreqs(String[] lines) {
        for (String l : lines) {
            for (char c : l.toCharArray()) {
                int asciiCode = (int) c;
                freq[asciiCode] += 1;
                //System.out.println(c + ":" + asciiCode + " = " + freq[asciiCode]);
            }
        }
    }


    /**
     * Adds the counted frequencies to the priority queue.
     */
    public void addFreqs() {
        for (int i = 0; i < this.freq.length; i++) {
            char c = (char) i;
            int f = this.freq[i];
            if (f == 0) {
                continue;
            }
            Node n = new Node("" + c, f);
            this.heap.add(n);
        }
    }
    
    public void treeify() {
        while (!this.heap.isEmpty()) {
            if (this.heap.size() == 1) {
                Node king = heap.poll();
                System.out.println("YOU WON");
                
                recursion(king.left, "0");
                recursion(king.right, "1");
                
                return;
            }
            Node n1 = heap.poll();
            Node n2 = heap.poll();

            Node n3 = new Node(n1.character + n2.character, n1.count + n2.count);
            n3.left = n1;
            n3.right = n2;
            System.out.println(n3.character + ": " + n3.count);
            this.heap.add(n3);
        }
    }
    
    public void recursion(Node node, String code) {
        if (node.left == null) {
            System.out.println(node.character + ": " + code);
            return;
        }
        
        if (node.right == null) {
            System.out.println(node.character + ": " + code);
            return;
        }
        
        recursion(node.left, code + "0");
        recursion(node.right, code + "1");
    }
    
    
    public PriorityQueue<Node> getHeap() {
        return this.heap;
    }
    
}
