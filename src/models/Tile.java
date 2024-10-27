package models;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import ui.Config;

/**
 * Represents a type of tile.
 */
enum TileType {
    UNPLAYABLE(Config.UNPLAYABLE_TILE_COLOR),
    PLAYABLE(Config.PLAYABLE_TILE_COLOR),
    SPAWN(Config.SPAWN_TILE_COLOR),
    WINNING(Config.WINNING_TILE_COLOR);

    Color color;

    /**
     * Enum constructor.
     * @param c Color of the tile.
     */
    TileType(Color c) {
        this.color = c;
    }

    /**
     * Retrieves the type of tile based on an integer value.
     * @param t Integer value representing the tile type.
     * @return Enum value of the tile type.
     */
    public static TileType getType(int t) {
        switch (t) {
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

/**
 * Represents a tile in the in-game world.
 */
public class Tile {
    TileType type;
    private int row;
    private int col;

    /**
     * Class constructor.
     * @param col   Column of the tile, 0-indexed.
     * @param row   Row of the tile, 0-indexed.
     * @param t     Type of tile.
     */
    public Tile(int col, int row, int t) {
        this.type = TileType.getType(t);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getX() {
        return this.col * Config.TILE_SIZE;
    }

    public int getY() {
        return this.row * Config.TILE_SIZE;
    }

    public Color getColor() {
        return this.type.color;
    }

    /**
     * Draws the tile on the screen.
     * @param g Graphics object used to draw the tile.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getColor());
        g2.fill(new Rectangle(
            this.col * Config.TILE_SIZE,
            this.row * Config.TILE_SIZE, Config.TILE_SIZE, Config.TILE_SIZE));
    }
}
