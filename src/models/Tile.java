package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import ui.GamePanel;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class Tile {
    Color color = Color.BLACK;

    public Tile() {}

    public void draw(Graphics g, int row, int col) {

        Graphics2D g2 = (Graphics2D) g;

        int xpos = col * GamePanel.tileSize;
        int ypos = row * GamePanel.tileSize;

        g2.setColor(color);
        g2.fill(new Rectangle(xpos, ypos, GamePanel.tileSize, GamePanel.tileSize));
    }
}
