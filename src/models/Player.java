package models;

import logic.KeyHandler;
import ui.Config;

public class Player extends Object {
    KeyHandler kh;
    TileManager tm;

    public Player(int xInit, int yInit, KeyHandler kh, TileManager tm) {
        super(xInit, yInit, Config.PLAYER_WIDTH, Config.PLAYER_HEIGHT);
        this.kh = kh;
        this.tm = tm;
    }

    public void update() {    
        if (kh.leftPressed) {
            if (!tm.intersects(this.x - 1, this.y) && !tm.intersects(this.x - 1, this.y + this.height - 1)) {
                this.moveLeft();
            }
        }
        if (kh.rightPressed) {
            if (!tm.intersects(this.x + this.width, this.y) && !tm.intersects(this.x + this.width, this.y + this.height - 1)) {
                this.moveRight();
            }
        }
        if (kh.upPressed) {
            if (!tm.intersects(this.x, this.y - 1) && !tm.intersects(this.x + this.width - 1, this.y - 1)) {
                this.moveUp();
            }
        }
        if (kh.downPressed) {
            if (!tm.intersects(this.x, this.y + this.height) && !tm.intersects(this.x + this.width - 1, this.y + this.height)) {
                this.moveDown();
            }
        }
    }
}
