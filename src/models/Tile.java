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
    }
}
