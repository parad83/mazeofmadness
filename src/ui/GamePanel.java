package ui;

import java.awt.*;
import javax.swing.*;

import models.*;
import logic.*;

public class GamePanel extends JPanel implements Runnable {
    int FPS = Config.FPS;

    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager();
    Thread gameloop;
    Player player = new Player(200, 100, (int) (Config.TILE_SIZE*0.4), (int) (Config.TILE_SIZE*0.4), keyHandler, tileManager);
    GameController gameController;


    public GamePanel(GameController gameController) {
        this.gameController = gameController;

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.addKeyListener(keyHandler);

        this.setFocusable(true);
    }

    public void setupGame() {
        this.add(player);
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
        if (gameController.getGameState() == GameState.STARTED) {
            player.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (gameController.getGameState() == GameState.STARTED) {
            tileManager.draw(g);
            player.draw(g);
        } else {
            // TODO: other logic
        }
    }
}
