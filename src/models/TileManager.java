package models;

import java.awt.Graphics;
import ui.Config;
import ui.GameController;

public class TileManager {
    GameController gameController;
    int ts = Config.TILE_SIZE;
    Tile[] tiles;

    public TileManager(GameController gc) {
        this.gameController = gc;
    }

    public void setBuilder(TileBuilder tb) {
        this.tiles = null;
        this.tiles = tb.getTiles();
    }

    public void draw(Graphics g) {
        for (int i=0; i<tiles.length; i++ ) {
            tiles[i].draw(g);
        }
    }

    public boolean intersects(int x, int y) {
        int idx = x/ts + (y/ts * Config.MAX_SCREEN_COL);
        if (idx >= tiles.length) {
            return false;
        }
        Tile curr = tiles[idx];
        if (curr.type == TileType.UNPLAYABLE) {
            return true;
        } else if (curr.type == TileType.WINNING) {
            int[] pos = curr.getPos();
            if (x >= pos[0] && x + Config.PLAYER_WIDTH <= pos[0] + ts && y >= pos[1] && y + Config.PLAYER_HEIGHT <= pos[1] + ts) {
                gameController.gameWon();
            }        }
        return false;
    }
}
