package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.*;
import logic.*;

public class GamePanel extends JPanel implements Runnable {
    static final int originalTileSize = 16;
    static final int scale = 3;

    int FPS = Config.FPS;

    Player player;
    KeyHandler keyhandler = new KeyHandler();
    Thread gameloop;
    TileManager tileM = new TileManager();

    public GamePanel() {

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        this.setBackground(Color.cyan);
        this.addKeyListener(keyhandler);

        player = new Player(200, 100, 20, 20, keyhandler, tileM);
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
        tileM.draw(g);
        player.draw(g);
    }
}
