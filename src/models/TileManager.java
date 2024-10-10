package models;

import java.awt.Color;
import java.awt.Graphics;
import ui.GamePanel;

public class TileManager {
    Tile[] tile;
    
    public TileManager() {
        tile = new Tile[10];
    }

    public void draw(Graphics g) {
        tile[0] = new Tile(Color.red, true, true, false, false);
        tile[1] = new Tile(Color.red, true, true, false, false);
        tile[2] = new Tile(Color.red, true, false, true, false);
        tile[0].draw(g);
        tile[1].draw(g);
        tile[2].draw(g);
    }
}
