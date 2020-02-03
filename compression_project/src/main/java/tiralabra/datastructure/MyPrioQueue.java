
package tiralabra.datastructure;

import tiralabra.compression_project.Node;


public class MyPrioQueue {
    
    private MyArrayList nodesList;
    
    public MyPrioQueue () {
        this.nodesList = new MyArrayList();
    }
    
    public void addNode(Node node) {
        this.nodesList.add(node);
    }
    
}
