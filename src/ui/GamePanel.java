package ui;

import java.awt.*;

import javax.print.attribute.SupportedValuesAttribute;
import javax.sql.rowset.spi.SyncResolver;
import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;


import models.*;
import logic.*;

public class GamePanel extends JPanel implements Runnable {
    int FPS = Config.FPS;

    KeyHandler keyHandler = new KeyHandler(this);
    TileManager tileManager = new TileManager();
    Thread gameloop;
    Player player = new Player(200, 100, (int) (Config.TILE_SIZE*0.4), (int) (Config.TILE_SIZE*0.4), keyHandler, tileManager);
    GameController gameController;

    JButton exitButton = new JButton("exit");

    public GamePanel(GameController gameController) {
        this.gameController = gameController;

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.addKeyListener(keyHandler);

        this.setFocusable(true);
        this.requestFocusInWindow();

        exitButton.addActionListener(e -> {
            exitGame();
        });
        this.add(exitButton);

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                requestFocusInWindow();
            }

            @Override
            public void focusLost(FocusEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public void setupGame() {
        this.add(player);
        startGameLoop();
    }

    private void startGameLoop() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    private void exitGame() {
        gameController.stopGame();
        try {
            if (gameloop != null) {
                gameloop.join();
            }
        } catch (InterruptedException e) {}
    }

    public void update() {
        if (gameController.isGameStarted()) {
            player.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameController.isGameStarted()) {
            tileManager.draw(g);
            player.draw(g);
        }
    }

    @Override
    public void run() {
        double drawInterval = 1E9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;
        int drawCount = 0;

        while (gameController.getGameState() == GameState.STARTED) {
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
}
