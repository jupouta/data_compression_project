package tiralabra.compression_project;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Compression {
    private PriorityQueue<String> heap;
    private HashMap<Character, Integer> hashMap;
    private ArrayList<String> lines;
    
    public Compression (ArrayList<String> listLines) {
        this.heap = new PriorityQueue<>();
        this.hashMap = new HashMap<>();
        this.lines = listLines;
    }
    
    // count the frequencies of every character
    public HashMap<Character, Integer> countFreqs() {
        for (String line: this.lines) {
            for (int i = 0; i < line.length(); i++) {
                char newChar = line.charAt(i);

                if (this.hashMap.containsKey(newChar)) {
                    this.hashMap.put(newChar, this.hashMap.get(newChar)+1);
                } else {
                    this.hashMap.put(newChar, 1);
                }
            }
        }
        
        
        for (char key: this.hashMap.keySet()) {
            System.out.println(key + " " + this.hashMap.get(key));
        }

        return this.hashMap;
    }
    
    // TODO: the rest of the algo
    public void addFreqs() {
        for (char key: this.hashMap.keySet()) {
            
        }
    }
    
}
