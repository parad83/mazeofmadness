package models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

import logic.KeyHandler;

public class Player extends Object {
    KeyHandler kh;

    public Player(int xInit, int yInit, int width, int height, KeyHandler kh) {
        super(xInit, yInit, width, height);
        this.kh = kh;
    }

    public void update() {
        if (kh.goLeft) {
            this.moveLeft();
        } 
        if (kh.goRight) {
            this.moveRight();
        }
        if (kh.goUp) {
            this.moveUp();
        } 
        if (kh.goDown) {
            this.moveDown();
        }
    }
}
