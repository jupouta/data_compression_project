package tiralabra.datastructure;

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
    private Node left;
    /**
     * The node on the right side of the node.
     */
    private Node right;

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

    /**
     * Override the compareTo method in java.
     * @param o The node to compare to the node itself.
     * @return The number which represents the result.
     */
    @Override
    public int compareTo(Node o) {
        if (this.count == o.count) {
            return this.character.compareTo(o.character);
        }
        return this.count - o.count;
    }

    /**
     * Returns the node's character.
     *
     * @return The character as a string.
     */
    public String getChar() {
        return this.character;
    }

    /**
     * Returns the node's count.
     *
     * @return The node as an integer.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns the node on the left side of the node.
     *
     * @return The node on the left-side of the node.
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Returns the node on the right side of the node.
     *
     * @return The node on the right side of the node.
     */
    public Node getRight() {
        return this.right;
    }
    
    /**
     * Set the node on the left side to the given node.
     * @param left The node to be put to the left side.
     */
    public void setLeft(Node left) {
        this.left = left;
    }
    
    /**
     * Set the node on the right side to the given node.
     * @param right The node to be put to the right side.
     */
    public void setRight(Node right){
        this.right = right;
    }

    /**
     * Override the toString method in java.
     * @return The toString method returns the node's character and its count.
     */
    @Override
    public String toString() {
        return this.character + ": " + this.count;
    }

}
