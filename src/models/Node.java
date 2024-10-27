package models;

public class Node {
    int row;
    int col;
    Node parent;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.parent = null;
    }

    Node getParent() {
        Node ret = this;
        while (ret.parent != null) {
            ret = ret.parent;
        }
        return ret;
    }
}
