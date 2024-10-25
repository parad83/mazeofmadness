package ui;

import java.awt.*;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import models.Player;
import models.TileBuilder;
import models.TileManager;
import logic.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    Thread gameloop;
    GameController gameController;
    Player player;
    TileManager tileManager;
    TileBuilder tileBuilder;

    int[] playerInitPos = new int[2];
    JButton exitButton = new Button("exit", 14);
    JButton saveButton = new Button("save", 14);
    JButton restartButton = new Button("restart", 14);
    JLabel timerLabel = new Label("Time: 00:00:00", 14);

    long elapsedTime;
    long startTime;

    public GamePanel(GameController gameController, TileBuilder tileBuilder) {
        this.gameController = gameController;
        this.tileManager = new TileManager(gameController);
        this.tileBuilder = tileBuilder;
        this.tileManager.setBuilder(tileBuilder);

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH + 250, Config.SCREEN_HEIGHT));
        this.setLayout(null);
        this.addKeyListener(keyHandler);

        this.setFocusable(true);
        this.requestFocusInWindow();

        restartButton.addActionListener(e -> {
            restart();
            
        });
        exitButton.addActionListener(e -> {
            gameController.exitGame();
            stopGameLoop();
        });
        saveButton.addActionListener(e -> {
            // TODO: save map
        });

        timerLabel.setBounds(Config.SCREEN_WIDTH+50, 0, 200 ,40);
        timerLabel.setBackground(Config.UNPLAYABLE_TILE_COLOR);

        exitButton.setBounds(Config.SCREEN_WIDTH+150, 70, 100, 40);
        saveButton.setBounds(Config.SCREEN_WIDTH+150, 140, 100, 40);
        restartButton.setBounds(Config.SCREEN_WIDTH+150, 210, 100, 40);

        this.add(timerLabel);
        this.add(exitButton);
        this.add(saveButton);
        this.add(restartButton);

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

    public long getElapsedTime() {
        return elapsedTime;
    }
    
    public void wonGame() {
        gameController.gameWon();
        stopGameLoop();
    }

    public void setupPlayer() {
        if (tileBuilder == null) {
            return;
        }
        playerInitPos = tileBuilder.getSpawn();
        player = new Player(playerInitPos[0], playerInitPos[1], keyHandler, tileManager);
    }

    public void setupTimeLabel() {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
        timerLabel.setText("Time: 00:00:00");
    }

    public void restart() {
        setupPlayer();
        setupTimeLabel(); 

        keyHandler.reset();
        startGameLoop();
    }

    public void setupGame() {
        tileBuilder.buildMap();
        setupPlayer();
        setupTimeLabel(); 

        if (player == null) {
            return;
        }
        this.add(player);
        startGameLoop();
    }

    private void startGameLoop() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    private void stopGameLoop() {
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

    public String formatElapsedTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds %= 60;
        minutes %= 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameController.isGameStarted()) {
            tileManager.draw(g);
            player.draw(g);
        }

        g.setColor(Config.UNPLAYABLE_TILE_COLOR);
        g.fillRect(Config.SCREEN_WIDTH, 0, 250, Config.SCREEN_HEIGHT);
    }

    @Override
    public void run() {
        double drawInterval = 1E9/Config.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;
        int drawCount = 0;

        while (gameController.isGameStarted()) {
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
                elapsedTime = System.currentTimeMillis() - startTime;
                timerLabel.setText("Time: " + formatElapsedTime(elapsedTime));
                drawCount = 0;
                timer = 0;
            }

        }
    }
}
