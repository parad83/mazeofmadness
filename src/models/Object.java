
package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import ui.Config;

public class Object extends JComponent {
    public final int MOVE_PIXELS = 1;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private final int X_BOUND = Config.SCREEN_WIDTH;
    private final int Y_BOUND = Config.SCREEN_HEIGHT;

    public Object(int xInit, int yInit, int sWidth, int sHeight) {
        this.x = xInit;
        this.y = yInit;
        width = sWidth;
        height = sHeight;
    }

    public void moveRight() {
        if (this.x + MOVE_PIXELS + width <= X_BOUND) {
            this.x += MOVE_PIXELS;
        } else {
            this.x = X_BOUND - width;
        }
    }

    public void moveLeft() {
        if (this.x - MOVE_PIXELS >= 0) {
            this.x -= MOVE_PIXELS;
        } else {
            this.x = 0;
        }
    }

    public void moveUp() {
        if (this.y - MOVE_PIXELS >= 0) {
            this.y -= MOVE_PIXELS;
        } else {
            this.y = 0;
        }
    }

    public void moveDown() {
        if (this.y + MOVE_PIXELS + height <= Y_BOUND) {
            this.y += MOVE_PIXELS;
        } else {
            this.y = Y_BOUND - height;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fill(new Rectangle(this.x, this.y, width, height));
    }
}
