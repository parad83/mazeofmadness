package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import ui.Config;
import java.awt.*;

enum TileType {
    UNPLAYABLE(Config.UNPLAYABLE_TILE_COLOR),
    PLAYABLE(Config.PLAYABLE_TILE_COLOR),
    SPAWN(Config.SPAWN_TILE_COLOR),
    WINNING(Config.WINNING_TILE_COLOR);

    Color color;
    TileType(Color c) {
        this.color = c;
    }

    public static TileType getType(int t) {
        switch(t) {
            case 0:
            return UNPLAYABLE;
            case 1:
            return SPAWN;
            case 2:
            return WINNING;
            default:
            return PLAYABLE;
        }
    }
}

public class Tile {
    TileType type;
    private int row;
    private int col;

    public Tile(int col, int row, int t) {
        this.type = TileType.getType(t);
        this.row = row;
        this.col = col;
    }

    public int[] getPos() {
        return new int[] {this.col*Config.TILE_SIZE+Config.TILE_SIZE/2, this.row*Config.TILE_SIZE + Config.TILE_SIZE/2};
    }

    public Color getColor() {
        return this.type.color;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getColor());
        g2.fill(new Rectangle(this.col*Config.TILE_SIZE, this.row*Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE));
    }
}
