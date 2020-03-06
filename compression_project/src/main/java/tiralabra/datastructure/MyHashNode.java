package tiralabra.datastructure;

/**
 * The object to be used for the hash set.
 * @param <String> The string object.
 */
public class MyHashNode<String> {

    String value;
    MyHashNode<String> next;

    public MyHashNode(String value, MyHashNode<String> next) {
        this.value = value;
        this.next = next;
    }
}
