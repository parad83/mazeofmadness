package models;

public class Edge {
    int intersect;
    boolean hidden;
    Direction dir;

    public Edge(int intersect, Direction dir) {
        this.intersect = intersect;
        this.hidden = false;
        this.dir = dir;
    }
}
