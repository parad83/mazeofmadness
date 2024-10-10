package models;

import java.awt.Color;
import java.awt.Graphics;

import ui.GamePanel;

public class Tile {
    Color color;
    boolean left, right, top, bottom;
    int ts = GamePanel.tileSize;
    static int rowCount, colCount;

    public Tile(Color c, boolean l, boolean r, boolean t, boolean u) {
        color = c;
        left = l;
        right = r;
        top = t;
        bottom = u;
        if (colCount >= GamePanel.macreenCol) {
            colCount = 0;
            rowCount++;
        }
        colCount++;
    }

    public void draw(Graphics g) {
        int xpos = colCount * ts;
        int ypos = rowCount * ts;

        if (left) g.drawLine(xpos, ypos, xpos, ypos+ts);
        if (right) g.drawLine(xpos+ts, ypos, xpos+ts, ypos+ts); 
        if (top) g.drawLine(xpos, ypos, xpos+ts, ypos); 
        if (bottom) g.drawLine(xpos, ypos+ts, xpos+ts, ypos+ts); 
    }
}
