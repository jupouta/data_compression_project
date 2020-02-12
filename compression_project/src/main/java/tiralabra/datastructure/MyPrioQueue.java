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
    public int index;
    
    /**
     * The length of the array.
     * This bound tells if the array needs to be doubled.
     */
    private int bound;
    /**
     * The index of the last node in the array.
     * The bound can be smaller than the actual length of the array.
     */
    public int rightBound;

    public MyPrioQueue() {
        this.nodesList = new Node[10];
        this.nodesList[0] = new Node("0", 0);

        this.index = 0;
        this.rightBound = 0;
        this.bound = 10;
        this.items++;
    }

    public void addNode(Node newNode) {
        this.rightBound++;
        this.nodesList[this.rightBound] = newNode;
        this.items++;

        
        // the sorting part
        for (int i = this.rightBound; i > this.index; i--) {
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
        
        if ((this.rightBound+1 >= this.bound) || (this.items >= this.bound)) {
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
        if (this.index == 0) {
            this.index = 1;
        }
        
        Node polled = this.nodesList[this.index];
        this.nodesList[this.index] = null;
        
        this.index++;
        this.items--;
        
        return polled;
    }
    
    public boolean isEmpty() {
        if (this.index == this.rightBound) return true;
        return this.items == 0;
    }

}
