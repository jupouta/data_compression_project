package tiralabra.datastructure;

import java.util.Arrays;
import tiralabra.compression_project.Node;

public class MyPrioQueue {

    public Node[] nodesList;
    /**
     * The amount of items, i.e., nodes in the array.
     */
    public int items;
    
    /**
     * The index to start from.
     * If a node is deleted, the index moves one to the right.
     */
    //public int index;
    
    /**
     * The length of the array.
     * This bound tells if the array needs to be doubled.
     */
    private int bound;

    public MyPrioQueue() {
        this.nodesList = new Node[10];
        this.bound = 10;
    }

    public void addNode(Node newNode) {
        this.nodesList[this.items] = newNode;
        this.items++;

        
        // the sorting part
        for (int i = this.items-1; i > 0; i--) {
            if (this.nodesList[i].count < this.nodesList[i-1].count) {
                Node bigger = this.nodesList[i-1];
                Node smaller = this.nodesList[i];
                this.nodesList[i] = bigger;
                this.nodesList[i-1] = smaller;
            } else if (this.nodesList[i].count == this.nodesList[i-1].count) {
                if (this.nodesList[i-1].character.compareTo(this.nodesList[i].character) > 0) {
                    Node earlier = this.nodesList[i-1];
                    Node later = this.nodesList[i];
                    this.nodesList[i] = earlier;
                    this.nodesList[i-1] = later;
                }
            }
        }
        
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
    
    public int size() {
        return this.items;
    }
    
    public Node poll() {
        Node polled = this.nodesList[0];
        for (int i = 1; i < this.items; i++) {
            Node left = this.nodesList[i];
            this.nodesList[i-1] = left;
            this.nodesList[i] = null;
        }
        this.items--;
        
        return polled;
    }
    
    public boolean isEmpty() {
        return this.items == 0;
    }

}
