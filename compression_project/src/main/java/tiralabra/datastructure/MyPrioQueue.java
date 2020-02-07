
import tiralabra.compression_project.Node;

public class MyPrioQueue {

    private Node[] nodesList;

    public MyPrioQueue() {
        this.nodesList = new Node[10];
        this.nodesList[0] = new Node("0", 0);
    }

    public void addNode(Node newNode) {
        int i = 0;
        for (Node node : this.nodesList) {
            if (node == null) {
                this.nodesList[i] = newNode;
                return;
            }
            i++;
        }
        Node[] newList = new Node[this.nodesList.length * 2];

        int ind = 0;
        for (Node node : this.nodesList) {
            newList[ind] = this.nodesList[ind];
        }

        newList[this.nodesList.length] = newNode;

    }

}
