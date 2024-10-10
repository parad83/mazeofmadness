package models;

import java.awt.Color;
import java.awt.Graphics;
import ui.GamePanel;

public class TileManager {
    Tile[] tile;
    
    public TileManager() {
        tile = new Tile[10];

        for (int i=0; i<10; i++) {
            tile[i] = new Tile();
        }

    }

    public void draw(Graphics g) {
        int row=0, col=0;

        for (int i=0; i<10; i++) {

            tile[i].draw(g, row, col);
            if (col >= GamePanel.maxScreenCol) {
                col = 0;
                row++;
            }
            col++;
        }
    }
}
