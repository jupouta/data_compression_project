package tiralabra.compression_project;


import java.util.HashMap;
import java.util.PriorityQueue;


public class Compression {
    private PriorityQueue<String> heap;
    private HashMap<Character, Integer> hashMap;
    
    public Compression () {
        this.heap = new PriorityQueue<>();
        this.hashMap = new HashMap<>();
    }
    
    // count the frequencies of every character
    public HashMap<Character, Integer> countFreqs(String stringData) {
        for (int i = 0; i < stringData.length(); i++) {
            char newChar = stringData.charAt(i);

            if (this.hashMap.containsKey(newChar)) {
                this.hashMap.put(newChar, this.hashMap.get(newChar)+1);
            } else {
                this.hashMap.put(newChar, 1);
            }
        }
        
        for (char key: this.hashMap.keySet()) {
            System.out.println(key + " " + this.hashMap.get(key));
        }

        return this.hashMap;
    }
    
    public void addFreqs() {
        for (char key: this.hashMap.keySet()) {
            
        }
    }
    
}
