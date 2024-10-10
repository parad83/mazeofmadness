
package models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import ui.GamePanel;

public class Object extends JComponent {
    public final int MOVE_PIXELS = 1;
    private int x;
    private int y;
    private int width;
    private int height;

    private final int X_BOUND = GamePanel.SCREEN_WIDTH;
    private final int Y_BOUND = GamePanel.SCREEN_HEIGHT;

    public Object(int xInit, int yInit, int width, int height) {
        this.x = xInit;
        this.y = yInit;
        this.width = width;
        this.height = height;
        setLocation(xInit, yInit);
        setSize(width, height);
    }

    public void draw() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
    }

    public void moveRight() {
        if (x + MOVE_PIXELS + width <= X_BOUND) {
            x += MOVE_PIXELS;
        } else {
            x = X_BOUND - width;
        }
        setLocation(x, y);
        repaint();
    }

    public void moveLeft() {
        if (x - MOVE_PIXELS >= 0) {
            x -= MOVE_PIXELS;
        } else {
            x = 0;
        }
        setLocation(x, y);
        repaint();
    }

    public void moveUp() {
        if (y - MOVE_PIXELS >= 0) {
            y -= MOVE_PIXELS;
        } else {
            y = 0;
        }
        setLocation(x, y);
        repaint();
    }

    public void moveDown() {
        if (y + MOVE_PIXELS + height <= Y_BOUND) {
            y += MOVE_PIXELS;
            System.out.println(y);
        } else {
            y = Y_BOUND - height;
        }
        setLocation(x, y);
        repaint();
    }
}
