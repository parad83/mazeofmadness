package models;

import logic.KeyHandler;

public class Player extends Object {
    KeyHandler kh;
    TileManager tm;

    public Player(int xInit, int yInit, int width, int height, KeyHandler kh, TileManager tm) {
        super(xInit, yInit, width, height);
        this.kh = kh;
        this.tm = tm;
    }

    public void update() {    
        if (kh.leftPressed) {
            if (!tm.intersects(this.x - 1, this.y)) {
                this.moveLeft();
            }
        }
        if (kh.rightPressed) {
            if (!tm.intersects(this.x + 1, this.y)) {
                this.moveRight();
            }
        }
        if (kh.upPressed) {
            if (!tm.intersects(this.x, this.y - 1)) {
                this.moveUp();
            }
        }
        if (kh.downPressed) {
            if (!tm.intersects(this.x, this.y + 1)) {
                this.moveDown();
            }
        }
    }
}
