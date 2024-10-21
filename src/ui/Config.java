package ui;

import java.awt.Color;

public class Config {

    // Player
    public static final Color PLAYER_COLOR = Color.decode("#EAD637");

    // Tiles
    public static final Color UNPLAYABLE_TILE_COLOR = Color.decode("#D9534F");
    public static final Color PLAYABLE_TILE_COLOR = Color.decode("#5BC0DE");
    public static final Color SPAWN_TILE_COLOR = Color.decode("#FFEB3B");
    public static final Color WINNING_TILE_COLOR = Color.decode("#4CAF50");


    // Grid
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    static final int ORIGINAL_TILE_SIZE = 16;
    static final int TILE_SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE; 
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; 
    public static final int FPS = 60;



}
