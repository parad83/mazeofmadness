package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Config {
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

    // Player
    public static final Color PLAYER_COLOR = Color.decode("#EAD637");
    public static final int PLAYER_HEIGHT = (int)(TILE_SIZE*0.4);
    public static final int PLAYER_WIDTH = (int)(TILE_SIZE*0.4);

    // GUI
    public static final Color FOREGROUND_COLOR = UNPLAYABLE_TILE_COLOR;

    // 10 bits
    public static final int TILE_FLAG = 0x3ff;
    public static final int TILE_ROW_FLAG = 0xF;
    public static final int TILE_COL_FLAG = 0xF;
    public static final int TILE_TYPE_FLAG = 0x3;




    // Custom font
    private static Font pressStart2p;

    public static void loadFont() {
        try {
            File fontFile = new File("resources/PressStart2P-Regular.ttf");
            pressStart2p = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pressStart2p);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            pressStart2p = new Font("Serif", Font.PLAIN, 12);
        }
    }

    public static Font getFont(float size) {
        if (pressStart2p != null) {
            return pressStart2p.deriveFont(size);
        } else {
            return new Font("Serif", Font.PLAIN, (int) size);
        }
    }
}
