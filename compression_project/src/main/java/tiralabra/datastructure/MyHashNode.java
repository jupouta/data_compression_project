package tiralabra.datastructure;

public class MyHashNode<String> {

    // K int, V string
    String value;
    MyHashNode<String> next;

    public MyHashNode(String value, MyHashNode<String> next) {
        this.value = value;
        this.next = next;
    }
}
