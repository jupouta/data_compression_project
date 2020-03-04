
package tiralabra.datastructure;

public class HashNode<String> {
    
    // K int, V string
    String value;
    HashNode<String> next;
    
    public HashNode(String value, HashNode<String> next) {
        this.value = value;
        this.next = next;
    }
}
    

