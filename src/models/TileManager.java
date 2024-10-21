package models;

import java.awt.Color;
import java.awt.Graphics;

import ui.Config;
import ui.GameController;
import ui.GamePanel;

public class TileManager {
    Tile[] tile;
    GameController gameController;
    
    public TileManager(GameController gc) {
        this.gameController = gc;
        tile = new Tile[Config.MAX_SCREEN_COL * Config.MAX_SCREEN_ROW];
        

        // TODO: initialise using initial conditions
        for (int i=0; i<tile.length-1; i++) {
            if (i < 20 ) {
                tile[i] = new Tile(TileType.UNPLAYABLE);
            }
            else {
                tile[i] = new Tile(TileType.PLAYABLE);
            }
        }

        tile[tile.length-1] = new Tile(TileType.WINNING);
    }


    int ts = Config.TILE_SIZE;

    public void draw(Graphics g) {
        int row=0, col=0;

        for (int i=0; i<tile.length; i++) {

            if (col >= Config.MAX_SCREEN_COL) {
                col = 0;
                row++;
            }

            tile[i].draw(g, row*ts, col*ts);
            col++;
        }
    }

    int maxCols = Config.MAX_SCREEN_COL;

    public boolean intersects(int x, int y) {
        Tile curr = tile[x/ts + ( y/ts * Config.MAX_SCREEN_COL )];
        if (curr.type == TileType.UNPLAYABLE) {
            return true;
        } else if (curr.type == TileType.WINNING) {
            gameController.stopGame(true);
        }
        return false;
    }
}
