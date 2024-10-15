package ui;

import java.awt.Color;

public class Config {

    // Colors
    public static final Color TILE_COLOR = Color.decode("#D8E1FF");
    public static final Color PLAYER_COLOR = Color.decode("#EAD637");
    public static final Color NOT_PLAYABLE_TILE_COLOR = Color.decode("#78C091");
    public static final Color TILE_BORDER_COLOR = Color.BLACK;


    // Tiles
    public static final int TILE_BORDER = 2;

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
