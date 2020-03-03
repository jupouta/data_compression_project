package tiralabra.compression_project;

/**
 * Represents the node in a binary tree. The node includes the character and its
 * count.
 */
public class Node implements Comparable<Node> {

    /**
     * The character as a string. The character is, e.g., the letter 'a'.
     */
    public String character;

    /**
     * The node on the left side of the node.
     */
    public Node left;
    /**
     * The node on the right side of the node.
     */
    public Node right;

    /**
     * The frequency of the character, i.e., its frequency in a file.
     */
    public int count;

    /**
     * Creates a new Node with the given character and count.
     *
     * @param character This Node's character which should be a string.
     * @param count The count of this Node's character.
     */
    public Node(String character, int count) {
        this.character = character;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        if (this.count == o.count) {
            return this.character.compareTo(o.character);
        }
        return this.count - o.count;
    }

    public String getChar() {
        // TODO Should I remove these?
        return this.character;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.character + ": " + this.count;
    }

}
