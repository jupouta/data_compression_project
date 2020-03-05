package tiralabra.datastructure;

/**
 * Manually created version of the priority queue.
 */
public class MyPrioQueue {

    public Node[] nodesList;
    /**
     * The amount of items, i.e., nodes in the array.
     */
    public int items;

    /**
     * The length of the array. This bound tells if the array needs to be
     * doubled.
     */
    private int bound;

    public MyPrioQueue() {
        this.nodesList = new Node[10];
        this.bound = 10;
    }

    /**
     * Add a new node to the priority queue.
     * @param newNode The new node to be added.
     */
    public void addNode(Node newNode) {
        this.nodesList[this.items] = newNode;
        this.items++;

        // the sorting part
        for (int i = this.items - 1; i > 0; i--) {
            if (this.nodesList[i].count < this.nodesList[i - 1].count) {
                Node bigger = this.nodesList[i - 1];
                Node smaller = this.nodesList[i];
                this.nodesList[i] = bigger;
                this.nodesList[i - 1] = smaller;
            } else if (this.nodesList[i].count == this.nodesList[i - 1].count) {
                if (this.nodesList[i - 1].character.compareTo(
                        this.nodesList[i].character) > 0) {
                    Node earlier = this.nodesList[i - 1];
                    Node later = this.nodesList[i];
                    this.nodesList[i] = earlier;
                    this.nodesList[i - 1] = later;
                }
            }
        }

        // make bigger if the bound is met
        if ((this.items >= this.bound)) {
            this.bound *= 2;
            Node[] a = new Node[this.bound];

            // copy
            for (int i = 0; i < this.items; i++) {
                a[i] = this.nodesList[i];
            }
            this.nodesList = a;
        }

    }

    /**
     * Returns the size of the priority queue.
     * @return The number of items in the priority queue.
     */
    public int size() {
        return this.items;
    }

    /**
     * Retrieves the node with the smallest count from the priority queue.
     * @return The smallest node.
     */
    public Node poll() {
        Node polled = this.nodesList[0];
        for (int i = 1; i < this.items; i++) {
            Node left = this.nodesList[i];
            this.nodesList[i - 1] = left;
            this.nodesList[i] = null;
        }
        this.items--;

        return polled;
    }

    /**
     * Check if the priority queue is empty.
     * @return True if there are items in the queue, false if there's none.
     */
    public boolean isEmpty() {
        return this.items == 0;
    }

}
