package models;

import java.awt.Color;
import java.awt.Graphics;

import ui.Config;
import ui.GamePanel;

public class TileManager {
    Tile[] tile;
    static int tileCount;
    
    public TileManager() {
        tile = new Tile[Config.MAX_SCREEN_COL * Config.MAX_SCREEN_ROW];
        

        // TODO: initialise using initial conditions
        for (int i=0; i<tile.length; i++) {
            if (i < 20 ) {
                tile[i] = new NotPlayableTile();
            }
            else {
                tile[i] = new Tile();
            }
        }
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
        return (tile[x/ts + ( y/ts * Config.MAX_SCREEN_COL )] instanceof NotPlayableTile );
    }
}
