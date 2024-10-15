package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.*;
import logic.*;

public class GamePanel extends JPanel implements Runnable {
    int FPS = Config.FPS;

    Player player;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager();
    Thread gameloop;

    public GamePanel() {

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        this.setBackground(Color.cyan);
        this.addKeyListener(keyHandler);

        player = new Player(200, 100, 20, 20, keyHandler, tileManager);
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
        double drawInterval = 1E9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;
        int drawCount = 0;

        while (gameloop != null) {

            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            timer += currTime - lastTime;
            lastTime = currTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1E9) {
                System.out.println(drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        tileManager.draw(g);
        player.draw(g);
    }
}
