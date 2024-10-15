package models;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ui.Config;
import java.awt.*;

public class Tile {
    Color color; 
    int xpos;
    int ypos;

    public Tile() {
        this.color = Config.TILE_COLOR;

    }

    public void draw(Graphics g, int row, int col) {

        Graphics2D g2 = (Graphics2D) g;
        this.xpos = col;
        this.ypos = row;
        
        g2.setColor(this.color);
        g2.fill(new Rectangle(col, row, Config.TILE_SIZE, Config.TILE_SIZE));

        // g2.setStroke(new BasicStroke(Config.TILE_BORDER));
        // g2.setColor(Config.TILE_BORDER_COLOR); 
        // g2.draw(new Rectangle(col, row, Config.TILE_SIZE-Config.TILE_BORDER, Config.TILE_SIZE-Config.TILE_BORDER));

    }
}
