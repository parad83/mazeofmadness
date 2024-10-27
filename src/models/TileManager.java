package models;

import java.awt.Graphics;
import ui.Config;
import ui.GameController;

/**
 * Controls the logic of building and drawing the tiles.
 */
public class TileManager {
    GameController gameController;
    int ts = Config.TILE_SIZE;
    Tile[] tiles;

    public TileManager(GameController gc) {
        this.gameController = gc;
    }

    /**
     * Sets the tile builder object.
     * @param tb Tile builder object.
     */
    public void setBuilder(TileBuilder tb) {
        this.tiles = null;
        this.tiles = tb.getTiles();
    }

    /**
     * Draws all the tiles.
     * @param g Graphics object to use in drawing.
     */
    public void draw(Graphics g) {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].draw(g);
        }
    }

    /**
     * Calculates whether the player will intersect an unplayable/winning tile.
     * If the player intersects a winning tile, it sends a message to the gameController.
     * @param x X position of the player.
     * @param y Y position of the player.
     * @return  True when it intersects, false when it doesn't.
     */
    public boolean intersects(int x, int y) {
        int idx = x / ts + (y / ts * Config.MAX_SCREEN_COL);
        if (idx >= tiles.length) {
            return false;
        }
        Tile curr = tiles[idx];
        if (curr.type == TileType.UNPLAYABLE) {
            return true;
        } else if (curr.type == TileType.WINNING) {
            int posX = curr.getX();
            int posY = curr.getY();
            if (x >= posX && x + Config.PLAYER_WIDTH <= posX + ts
                && y >= posY && y + Config.PLAYER_HEIGHT <= posY + ts) {
                gameController.gameWon();
            }
        }
        return false;
    }
}
