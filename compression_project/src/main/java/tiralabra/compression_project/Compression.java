package tiralabra.compression_project;

import java.util.Arrays;
import java.util.PriorityQueue;
import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyPrioQueue;

/**
 * The compression to be made.
 */
public class Compression {

    /**
     * All the data structures needed in the compression.
     */
    private MyPrioQueue heap;
    public int[] freq;
    private String[] code;

    /**
     * Creates a new Compression with the given list of lines found in a file.
     */
    public Compression() {
        this.heap = new MyPrioQueue();
        this.freq = new int[256];
        this.code = new String[256];
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
            this.heap.addNode(n);
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
            this.heap.addNode(n3);
        }
    }
    
    public void recursion(Node node, String code) {
        if (node.left == null) {
            System.out.println(node.character + ": " + code);
            char nodeChar = node.character.charAt(0);
            int asciiCode = (int) nodeChar;
            
            this.code[asciiCode] = code;
            return;
        }
        
        if (node.right == null) {
            System.out.println(node.character + ": " + code);
            char nodeChar = node.character.charAt(0);
            int asciiCode = (int) nodeChar;
            
            this.code[asciiCode] = code;
            return;
        }
        
        recursion(node.left, code + "0");
        recursion(node.right, code + "1");
    }
    
    
    public MyPrioQueue getHeap() {
        return this.heap;
    }
    
}
