package tiralabra.compression_project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


/**
 * The compression to be made.
 */
public class Compression {
    
    /**
    * All the data structures needed in the compression.
    */
    private PriorityQueue<Node> heap;
    private HashMap<String, Integer> hashMap;
    private String[] lines;
    private String decoded;
    
    /**
    * Creates a new Compression with the given list
    * of lines found in a file.
    * @param listLines The lines in the file to be
    * compressed as an array list of lines.
    */
    public Compression (String[] listLines) {
        this.heap = new PriorityQueue<>();
        this.hashMap = new HashMap<>();
        this.lines = listLines;
    }
    
    /**
   * Counts the times a character is included
   * in a file.
   * @return the hash map with the frequencies
   *        of every character.
   */
    public HashMap<String, Integer> countFreqs() {

        for (int index = 0; index < this.lines.length; index++) {
            
            if (this.lines[index].equals("%§")) {
                break;
            }
            
            String line = this.lines[index];
            System.out.println(line.length());
            for (int i = 0; i < line.length(); i++) {
                String newChar = "" + line.charAt(i);

                if (this.hashMap.containsKey(newChar)) {
                    this.hashMap.put(newChar, this.hashMap.get(newChar)+1);
                } else {
                    this.hashMap.put(newChar, 1);
                }
            }
        }

        return this.hashMap;
    }

    
    /**
     * Adds the counted frequencies to the
     * priority queue.
     */
    public void addFreqs() {
        for (String key: this.hashMap.keySet()) {
            heap.add(new Node(key, this.hashMap.get(key)));
        }
        
        while (!heap.isEmpty()) {
            if (heap.size() == 1) {
                System.out.println("done");
                System.out.println(heap.peek());
                break;
            }
            Node first = heap.poll();
            System.out.println("the first: " + first);
            Node second = heap.poll();
            System.out.println("the second: " + second);
            
            String newChar = first.getChar() + second.getChar();
            Node newNode = new Node(newChar, first.getCount()+second.getCount());
            System.out.println("the new one: " + newNode);
            
            heap.add(newNode);
        }
    }
    
    public PriorityQueue<Node> getHeap() {
        return this.heap;
    }
    
}
