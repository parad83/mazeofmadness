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
        Tile curr = tiles[x/ts + ( y/ts * Config.MAX_SCREEN_COL )];
        if (curr.type == TileType.UNPLAYABLE) {
            return true;
        } else if (curr.type == TileType.WINNING) {
            gameController.stopGame(true);
        }
        return false;
    }
}
