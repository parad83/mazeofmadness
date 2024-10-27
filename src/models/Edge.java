package models;

/**
 * Represents a tile attached to an 'intersect' in one of four possible directions.
 */
public class Edge {
    int intersect;
    boolean hidden;
    Direction dir;

    /**
     * Class constructor.
     * @param intersect Index into the array of intersects of this edge's corresponding intersect.
     * @param dir       One of four directions relative to the intersect.
     */
    public Edge(int intersect, Direction dir) {
        this.intersect = intersect;
        this.hidden = false;
        this.dir = dir;
    }
}
