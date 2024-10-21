package models;

import java.awt.Color;
import java.awt.BasicStroke;
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
}

public class Tile {
    int xpos;
    int ypos;
    TileType type;

    public Tile(TileType t) {
        this.type = t;
    }

    public Color getColor() {
        return this.type.color;
    }

    public void draw(Graphics g, int row, int col) {
        Graphics2D g2 = (Graphics2D) g;
        this.xpos = col;
        this.ypos = row;
        
        g2.setColor(getColor());
        g2.fill(new Rectangle(col, row, Config.TILE_SIZE, Config.TILE_SIZE));
    }
}
