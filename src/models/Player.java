package models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Player extends Object {
    public Player(int xInit, int yInit, int width, int height) {
        super(xInit, yInit, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
