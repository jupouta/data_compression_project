
import tiralabra.compression_project.Node;

public class MyPrioQueue {

    public Node[] nodesList;
    private int items;

    public MyPrioQueue() {
        this.nodesList = new Node[10];
        this.nodesList[0] = new Node("0", 0);
        this.items++;
    }

    public void addNode(Node newNode) {
        int i = 0;
        for (Node node : this.nodesList) {
            if (node == null) {
                this.nodesList[i] = newNode;
                this.items++;
                return;
            }
            i++;
        }
        
        Node[] newList = new Node[this.nodesList.length * 2];

        for (int ind = 0; ind < this.nodesList.length; ind++) {
            newList[ind] = this.nodesList[ind];
        }

        newList[this.nodesList.length] = newNode;
        this.items++;
        
        this.nodesList = newList;
    }
    
    public int size() {
        return this.items;
    }

}
