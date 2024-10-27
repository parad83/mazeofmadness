package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import ui.Config;

/**
 * Represents a moveable object on the map.
 */
public class Object extends JComponent {
    public final int MOVE_PIXELS = 2;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private final int X_BOUND = Config.SCREEN_WIDTH;
    private final int Y_BOUND = Config.SCREEN_HEIGHT;

    /**
     * Class constructor.
     * @param xInit     Initial x position of the object.
     * @param yInit     Initial y position of the object.
     * @param sWidth    Width of the object.
     * @param sHeight   Height of the object.
     */
    public Object(int xInit, int yInit, int sWidth, int sHeight) {
        this.x = xInit;
        this.y = yInit;
        width = sWidth;
        height = sHeight;
    }

    /**
     * Moves the object MOVE_PIXELS pixels to the right.
     */
    public void moveRight() {
        if (this.x + MOVE_PIXELS + width <= X_BOUND) {
            this.x += MOVE_PIXELS;
        } else {
            this.x = X_BOUND - width;
        }
    }

    /**
     * Moves the object MOVE_PIXELS pixels to the left.
     */
    public void moveLeft() {
        if (this.x - MOVE_PIXELS >= 0) {
            this.x -= MOVE_PIXELS;
        } else {
            this.x = 0;
        }
    }

    /**
     * Moves the object MOVE_PIXELS pixels up.
     */
    public void moveUp() {
        if (this.y - MOVE_PIXELS >= 0) {
            this.y -= MOVE_PIXELS;
        } else {
            this.y = 0;
        }
    }

    /**
     * Moves the object MOVE_PIXELS pixels down.
     */
    public void moveDown() {
        if (this.y + MOVE_PIXELS + height <= Y_BOUND) {
            this.y += MOVE_PIXELS;
        } else {
            this.y = Y_BOUND - height;
        }
    }

    /**
     * Draws the object.
     * @param g Graphics class used to draw the object.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fill(new Rectangle(this.x, this.y, width, height));
    }
}
