package models;

/**
 * Represents a tile that is always playable, regardless of the maze configuration.
 * Additionally, this class contains a reference to another node, creating a primitive linked list.
 */
public class Node {
    int row;
    int col;
    Node parent;

    /**
     * Class constructor.
     * @param row   Row of the node, 0-indexed.
     * @param col   Column of the node, 0-indexed.
     */
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.parent = null;
    }

    /**
     * Climbs up the tree of references to reach the parent node.
     * This is used for checking if two nodes are connected between each other.
     * @return  Parent node. Null if this node doesn't have a parent.
     */
    Node getParent() {
        Node ret = this;
        while (ret.parent != null) {
            ret = ret.parent;
        }
        return ret;
    }
}
