package models;

import logic.KeyHandler;
import ui.Config;
import ui.GamePanel;

/**
 * Represents a player.
 */
public class Player extends Object {
    KeyHandler kh;
    TileManager tm;

    /**
     * Class constructor.
     * @param xInit Initial x position of the player.
     * @param yInit Initial y position of the player.
     * @param kh    KeyHandler object.
     * @param tm    TileManager object.
     */
    public Player(int xInit, int yInit, KeyHandler kh, TileManager tm) {
        super(xInit, yInit, Config.PLAYER_WIDTH, Config.PLAYER_HEIGHT);
        this.kh = kh;
        this.tm = tm;
    }

    /**
     * Updates the player based on key events from the KeyHandler object.
     */
    public void update() {    
        if (kh.leftPressed
            && !tm.intersects(this.x - 1, this.y)
            && !tm.intersects(this.x - 1, this.y + this.height - 1)) {
            this.moveLeft();
        }
        if (kh.rightPressed
            && !tm.intersects(this.x + this.width, this.y)
            && !tm.intersects(this.x + this.width, this.y + this.height - 1)) {
            this.moveRight();
        }
        if (kh.upPressed
            && !tm.intersects(this.x, this.y - 1)
            && !tm.intersects(this.x + this.width - 1, this.y - 1)) {
            this.moveUp();
        }
        if (kh.downPressed
            && !tm.intersects(this.x, this.y + this.height)
            && !tm.intersects(this.x + this.width - 1, this.y + this.height)) {
            this.moveDown();
        }
    }
}
