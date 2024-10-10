package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.*;
import logic.*;

public class GamePanel extends JPanel implements Runnable {

    boolean first = true;
    
    static final int originalTileSize = 16;
    static final int scale = 3;

    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;
    Player player;
    KeyHandler keyhandler = new KeyHandler();
    Thread gameloop;
    TileManager tileM = new TileManager();

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setBackground(Color.cyan);
        this.addKeyListener(keyhandler);

        player = new Player(200, 100, 20, 20, keyhandler);
        this.add(player);
    
        setFocusable(true);



        startGameLoop();

    }

    private void startGameLoop() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameloop != null) {

            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long) remainingTime );
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            nextDrawTime += drawInterval;
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        player.draw(g);

        if (first) {tileM.draw(g);} else first = false;
    }
}
