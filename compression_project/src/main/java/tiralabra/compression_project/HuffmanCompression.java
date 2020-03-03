package tiralabra.compression_project;

import java.util.Arrays;
import tiralabra.datastructure.MyArrayList;
import tiralabra.datastructure.MyPrioQueue;

/**
 * The compression to be made.
 */
public class HuffmanCompression {

    public MyPrioQueue heap;
    public int[] freq;
    private String[] code;
    private String text;
    private Node highestNode;

    /**
     * Creates a new Compression with the given list of lines found in a file.
     */
    public HuffmanCompression() {
        this.heap = new MyPrioQueue();
        this.freq = new int[256];
        this.code = new String[256];
    }

    /**
     * Counts the times a character is included in a file.
     *
     * @param text The file to be compressed as a string.
     */
    public void countFreqs(String text) {
        this.text = text;
        for (char c : text.toCharArray()) {
            int asciiCode = (int) c;
            freq[asciiCode] += 1;
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

    /**
     * Make a heap of the frequencies using recursion.
     */
    public void treeify() {
        while (!this.heap.isEmpty()) {
            if (this.heap.size() == 1) {
                Node kingNode = heap.poll();
                this.highestNode = kingNode;

                if (highestNode.left != null) {
                    recursion(highestNode.left, "0");
                }
                if (highestNode.right != null) {
                    recursion(highestNode.right, "1");
                }
                return;
            }

            Node n1 = heap.poll();
            Node n2 = heap.poll();

            Node n3 = new Node(n1.character + n2.character, n1.count + n2.count);
            n3.left = n1;
            n3.right = n2;

            this.heap.addNode(n3);
        }
    }

    /**
     * Traverse recursively the nodes. Takes the node and the code as
     * parameters.
     *
     * @param node The node to be checked.
     * @param code The current code as zeroes and ones.
     */
    public void recursion(Node node, String code) {
        if (node == null) {
            return;
        }

        if (node.left == null) {
            char nodeChar = node.character.charAt(0);
            int asciiCode = (int) nodeChar;

            this.code[asciiCode] = code;
            return;
        }

        if (node.right == null) {
            char nodeChar = node.character.charAt(0);
            int asciiCode = (int) nodeChar;

            this.code[asciiCode] = code;
            return;
        }

        recursion(node.left, code + "0");
        recursion(node.right, code + "1");
    }

    /**
     * Convert the text to a bitlike representation.
     *
     * @return The compressed lines to write to a file as a string.
     */
    public String linesToBits() {
        MyArrayList list = new MyArrayList<>(String.class);

        for (char c : text.toCharArray()) {
            int asciiCode = (int) c;
            list.add(this.code[asciiCode]);
        }

        return String.join("", (String[]) list.toArray());
    }

    /**
     * Go through the compressed string and add the encountered characters to an
     * array list.
     *
     * @param compressed The compressed file as a string.
     * @return A string of the characters found in the heap.
     */
    public String decompress(String compressed) {
        Node node = this.highestNode;   // start from the highest node

        MyArrayList list = new MyArrayList<>(String.class);

        int ind = 0;
        while (ind < compressed.length()) {
            char c = compressed.charAt(ind);

            if (node.left == null && node.right == null) {
                list.add(node.character);
                node = this.highestNode;
            }
            if (c == '0') {
                node = node.left;
            }
            if (c == '1') {
                node = node.right;
            }
            ind++;

            if (ind == compressed.length()) {
                list.add(node.character);
            }
        }

        return String.join("", (String[]) list.toArray());
    }

}
