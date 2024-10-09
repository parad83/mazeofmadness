
package models;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import ui.GamePanel;

public class Object extends JComponent {
    public final int MOVE_PIXELS = 5;
    private int x, y, width, height;

    private final int X_BOUND = GamePanel.SCREEN_WIDTH;
    private final int Y_BOUND = GamePanel.SCREEN_HEIGHT;

    public Object(int x_init, int y_init, int width, int height) {
        this.x = x_init;
        this.y = y_init;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocation(x, y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
    }

    public void moveRight() {
        if (x + MOVE_PIXELS + this.width <= X_BOUND) {
            this.x += MOVE_PIXELS;
        } else {
            this.x = X_BOUND - this.width;
        }
        this.setLocation(x, y);
        repaint();
    }

    public void moveLeft() {
        if (x - MOVE_PIXELS >= 0) {
            this.x -= MOVE_PIXELS;
        } else {
            this.x = 0;
        }
        this.setLocation(x, y);
        repaint();
    }

    public void moveUp() {
        if (y - MOVE_PIXELS >= 0) {
            this.y -= MOVE_PIXELS;
        } else {
            this.y = 0;
        }
        this.setLocation(x, y);
        repaint();
    }

    public void moveDown() {
        if (y + MOVE_PIXELS + this.height <= Y_BOUND) {
            this.y += MOVE_PIXELS;
        } else {
            this.y = Y_BOUND - this.height;
        }
        this.setLocation(x, y);
        repaint();
    }
}
