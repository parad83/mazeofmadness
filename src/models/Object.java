
package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import ui.GamePanel;

public class Object extends JComponent {
    public final int MOVE_PIXELS = 1;
    private int x;
    private int y;
    private int width;
    private int height;

    private final int X_BOUND = GamePanel.screenWidth;
    private final int Y_BOUND = GamePanel.screenHeight;

    public Object(int xInit, int yInit, int sWidth, int sHeight) {
        x = xInit;
        y = yInit;
        width = sWidth;
        height = sHeight;
    }

    public void moveRight() {
        if (x + MOVE_PIXELS + width <= X_BOUND) {
            x += MOVE_PIXELS;
        } else {
            x = X_BOUND - width;
        }
    }

    public void moveLeft() {
        if (x - MOVE_PIXELS >= 0) {
            x -= MOVE_PIXELS;
        } else {
            x = 0;
        }
    }

    public void moveUp() {
        if (y - MOVE_PIXELS >= 0) {
            y -= MOVE_PIXELS;
        } else {
            y = 0;
        }
    }

    public void moveDown() {
        if (y + MOVE_PIXELS + height <= Y_BOUND) {
            y += MOVE_PIXELS;
        } else {
            y = Y_BOUND - height;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fill(new Rectangle(x, y, width, height));
    }
}
