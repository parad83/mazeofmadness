package models;

import java.util.Random;
import ui.Config;

/**
 * Handles building the randomly generated maps.
 */
public class TileBuilder {
    Tile[] tiles;
    Tile[] intersects;
    Edge[] edges;
    Node[] nodes;
    int[] obstacles;
    boolean obstacleState;
    Tile spawnTile;

    int nodesHorizontal;
    int nodesVertical;
    int nodeCount;
    int intersectCount;
    int edgeCount;

    /**
     * Class constructor.
     */
    public TileBuilder() {
        nodesHorizontal = (Config.MAX_SCREEN_COL + 1) / 2;
        nodesVertical = (Config.MAX_SCREEN_ROW + 1) / 2;
        nodeCount = nodesHorizontal * nodesVertical;
        intersectCount = (nodesHorizontal - 1) * (nodesVertical - 1);
        edgeCount = intersectCount * 2 + (nodesHorizontal - 1) + (nodesVertical - 1);

        this.tiles = new Tile[Config.MAX_SCREEN_COL * Config.MAX_SCREEN_ROW];
        this.intersects = new Tile[intersectCount];
        this.edges = new Edge[edgeCount];
        this.nodes = new Node[nodeCount];
        this.obstacles = new int[nodeCount - 4];
        this.obstacleState = false;
    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public void buildMap() {
        randomMap();
    }

    public int getSpawnX() {
        return spawnTile.getX() + Config.TILE_SIZE / 2;
    }

    public int getSpawnY() {
        return spawnTile.getY() + Config.TILE_SIZE / 2;
    }

    /*
     * Performs a Durstenfeld random shuffle on arr.
     */
    private static void arrayShuffle(Edge[] arr) {
        Random rand = new Random();

        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = rand.nextInt(i + 1);
            Edge temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /*
     * Performs a Durstenfeld random shuffle on arr.
     */
    private static void arrayShuffle(int[] arr) {
        Random rand = new Random();

        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = rand.nextInt(i + 1);
            int temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Turn coordinates into node.
     * @param col   Column of desired node.
     * @param row   Row of desired node.
     * @return      Node at specified column and row.
     */
    private Node coordsToNode(int col, int row) {
        if (col % 2 == 1 || row % 2 == 1 || col < 0 || row < 0 
            || col >= Config.MAX_SCREEN_COL || row >= Config.MAX_SCREEN_ROW) {
            System.out.println("coordsToNode() called with invalid coords!");
            // return new Node(0, 0);
        }

        int index = col / 2 + (row / 2) * nodesHorizontal;
        return nodes[index];
    }

    /**
     * Get the two nodes adjacent to an edge.
     * @param edge  Edge to get adjacent nodes from.
     * @return      Two adjacent nodes.
     */
    private Node[] edgeConnection(Edge edge) {
        Tile coords = intersects[edge.intersect];
        int col = coords.getCol();
        int row = coords.getRow();
        switch (edge.dir) {
            case NORTH:
                return new Node[] {coordsToNode(col - 1, row - 1), coordsToNode(col + 1, row - 1)};
            case EAST:
                return new Node[] {coordsToNode(col + 1, row - 1), coordsToNode(col + 1, row + 1)};
            case SOUTH:
                return new Node[] {coordsToNode(col - 1, row + 1), coordsToNode(col + 1, row + 1)};
            case WEST:
                return new Node[] {coordsToNode(col - 1, row + 1), coordsToNode(col - 1, row - 1)};
            default:
                System.out.println("Invalid edge passed to edgeConnection()!");
                return new Node[] {};
        }
    }

    /**
     * Get the coordinates of an edge.
     * @param edge  Edge to get coordinates from.
     * @return      Edge coordinates {x, y}.
     */
    private int[] edgeToCoords(Edge edge) {
        Tile coords = intersects[edge.intersect];
        int col = coords.getCol();
        int row = coords.getRow();
        switch (edge.dir) {
            case NORTH:
                return new int[] {col, row - 1};
            case EAST:
                return new int[] {col + 1, row};
            case SOUTH:
                return new int[] {col, row + 1};
            case WEST:
                return new int[] {col - 1, row};
            default:
                System.out.println("Invalid edge passed to edgeToCoords()!");
                return new int[] {};
        }
    }

    /**
     * Highlight the tiles that are about to alternate.
     */
    public void highlightTiles() {
        if (obstacleState) {
            for (int i = 0; i < obstacles.length / 2; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 4);
            }
        } else {
            for (int i = obstacles.length / 2; i < obstacles.length; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 4);
            }
        }
    }

    /**
     * Switches between a random half of the obstacles.
     */
    public void alternateObstacles() {
        if (obstacleState) {
            for (int i = 0; i < obstacles.length / 2; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 0);
            }

            for (int i = obstacles.length / 2; i < obstacles.length; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 3);
            }
        } else {
            for (int i = obstacles.length / 2; i < obstacles.length; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 0);
            }

            for (int i = 0; i < obstacles.length / 2; i++) {
                Node node = nodes[obstacles[i]];
                int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(node.col, node.row, 3);
            }
        }

        obstacleState = !obstacleState;
    }

    /**
     * Make all obstacles playable.
     */
    public void clearObstacles() {
        for (int i = 0; i < obstacles.length; i++) {
            Node node = nodes[obstacles[i]];
            int tileIndex = node.col + node.row * Config.MAX_SCREEN_COL;
            tiles[tileIndex] = new Tile(node.col, node.row, 3);
        }
    }

    /**
     * Generate the random map.
     */
    private void randomMap() {
        // Set nodes up
        for (int i = 0; i < nodesVertical; i++) {
            for (int j = 0; j < nodesHorizontal; j++) {
                nodes[i * nodesHorizontal + j] = new Node(i * 2, j * 2);
            }
        }

        // Set intersects up
        for (int i = 0; i < intersectCount; i++) {
            intersects[i] = new Tile(
                (i % (nodesHorizontal - 1)) * 2 + 1,
                (i / (nodesHorizontal - 1)) * 2 + 1,
                0
            );
        }

        // Set edges up
        int edgeIndex = 0;
        for (int i = 0; i < intersectCount; i++) {
            edges[edgeIndex] = new Edge(i, Direction.NORTH);
            edges[edgeIndex + 1] = new Edge(i, Direction.WEST);
            edgeIndex += 2;
        }
        for (int i = 0; i < nodesHorizontal - 1; i++) {
            edges[edgeIndex] = new Edge(
                intersectCount - (nodesHorizontal - 1) + i,
                Direction.SOUTH
            );
            edgeIndex++;
        }
        for (int i = 0; i < nodesVertical - 1; i++) {
            edges[edgeIndex] = new Edge(
                (nodesHorizontal - 1) * (i + 1) - 1,
                Direction.EAST
            );
            edgeIndex++;
        }
        assert (edgeIndex == edgeCount);

        // Hide edges (building the maze)
        arrayShuffle(edges);
        for (int i = 0; i < edgeCount; i++) {
            Node[] relevantNodes = edgeConnection(edges[i]);
            if (relevantNodes[0].getParent() != relevantNodes[1].getParent()) {
                edges[i].hidden = true;
                relevantNodes[1].parent = relevantNodes[0];
            }
        }

        // Initialize the map with every tile as playable
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(i % Config.MAX_SCREEN_COL, i / Config.MAX_SCREEN_COL, 3);
        }

        // Mark unplayable tiles
        for (int i = 0; i < edgeCount; i++) {
            int[] coords = edgeToCoords(edges[i]);
            if (!edges[i].hidden) {
                int tileIndex = coords[0] + coords[1] * Config.MAX_SCREEN_COL;
                tiles[tileIndex] = new Tile(coords[0], coords[1], 0);
            }
        }
        for (int i = 0; i < intersectCount; i++) {
            int tileIndex = intersects[i].getCol() + intersects[i].getRow() * Config.MAX_SCREEN_COL;
            tiles[tileIndex] = new Tile(intersects[i].getCol(), intersects[i].getRow(), 0);
        }
        tiles[Config.MAX_SCREEN_COL * (Config.MAX_SCREEN_ROW - 1) + 1] = 
            new Tile(1, Config.MAX_SCREEN_ROW - 1, 0);
        
        // Add spawn tile (top left)
        spawnTile = tiles[0];

        // Add winning tile (bottom right)
        tiles[tiles.length - 1] = new Tile(Config.MAX_SCREEN_COL - 1, Config.MAX_SCREEN_ROW - 1, 2);

        // Set up obstacles
        for (int i = 3; i < nodeCount - 1; i++) {
            obstacles[i - 3] = i;
        }
        arrayShuffle(obstacles);
    }
}
