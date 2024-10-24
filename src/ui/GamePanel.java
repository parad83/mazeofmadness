package ui;

import java.awt.*;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;


import models.*;
import logic.*;
import utils.*;

public class GamePanel extends JPanel implements Runnable {
    int FPS = Config.FPS;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameloop;
    GameController gameController;
    Player player;
    TileManager tileManager;

    int[] playerInitPos = new int[2];
    JButton exitButton = new JButton("exit");

    // for testimg
    TileBuilder tileBuilder = new TileBuilder();

    public GamePanel(GameController gameController) {
        this.gameController = gameController;
        this.tileManager = new TileManager(gameController);
        this.tileManager.setBuilder(tileBuilder);

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.addKeyListener(keyHandler);

        this.setFocusable(true);
        this.requestFocusInWindow();

        exitButton.addActionListener(e -> {
            stopGameLoop(false);
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
    
    public void wonGame() {
        stopGameLoop(true); 
    }

    public void setupGame() {
        tileBuilder.buildMap();
        playerInitPos = tileBuilder.getSpawn();
        player = new Player(playerInitPos[0], playerInitPos[1], (int) (Config.TILE_SIZE*0.4), (int) (Config.TILE_SIZE*0.4), keyHandler, tileManager);
        keyHandler.reset();
        this.add(player);
        startGameLoop();
    }

    private void startGameLoop() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    private void stopGameLoop(boolean isWon) {
        gameController.stopGame(isWon);
        try {
            if (gameloop != null) {
                gameloop.join();
            }
        } catch (InterruptedException e) {}
    }

    private void update() {
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
