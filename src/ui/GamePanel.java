package ui;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import logic.KeyHandler;
import models.Player;
import models.TileBuilder;
import models.TileManager;

/**
 * Contains the game loop and UI.
 */
public class GamePanel extends JPanel implements Runnable {
    KeyHandler keyHandler = new KeyHandler();
    Thread gameloop;
    GameController gameController;
    Player player;
    TileManager tileManager;
    TileBuilder tileBuilder;

    JButton exitButton = new Button("exit", 14);
    JButton saveButton = new Button("save", 14);
    JButton restartButton = new Button("restart", 14);
    JLabel timerLabel = new Label("Time: 00:00:00", 14);

    int labelWidth;

    long elapsedTime;
    long startTime;

    /**
     * Class constructor.
     * @param gameController    Game controller object.
     * @param tileBuilder       Tile builder object.
     */
    public GamePanel(GameController gameController, TileBuilder tileBuilder) {
        this.gameController = gameController;
        this.tileManager = new TileManager(gameController);
        this.tileBuilder = tileBuilder;
        this.tileManager.setBuilder(tileBuilder);

        FontMetrics metrics = timerLabel.getFontMetrics(timerLabel.getFont());
        labelWidth = metrics.stringWidth(timerLabel.getText());

        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH + labelWidth + 150, Config.SCREEN_HEIGHT));
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

        timerLabel.setBounds(Config.SCREEN_WIDTH + 50, 0, labelWidth, 40);
        timerLabel.setBackground(Config.UNPLAYABLE_TILE_COLOR);

        exitButton.setBounds(Config.SCREEN_WIDTH + 50 , 70, labelWidth, 40);
        saveButton.setBounds(Config.SCREEN_WIDTH + 50, 140, labelWidth, 40);
        restartButton.setBounds(Config.SCREEN_WIDTH + 50, 210, labelWidth, 40);

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
    
    /**
     * Called when the game is won and sets the appropriate state.
     */
    public void wonGame() {
        gameController.gameWon();
        stopGameLoop();
    }

    /**
     * Sets up initial conditions of the player.
     */
    public void setupPlayer() {
        if (tileBuilder == null) {
            return;
        }
        player = new Player(tileBuilder.getSpawnX() - Config.PLAYER_WIDTH / 2, tileBuilder.getSpawnY() - Config.PLAYER_HEIGHT / 2, keyHandler, tileManager);
    }

    /**
     * Sets up the time label.
     */
    public void setupTimeLabel() {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
        timerLabel.setText("Time: 00:00:00");
    }

    /**
     * Sets the player in the spawn position and resets the timer.
     */
    public void restart() {
        setupPlayer();
        setupTimeLabel(); 

        keyHandler.reset();
    }

    /**
     * Sets up a new game, generating a new map.
     */
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

    /**
     * Starts the main loop thread of the game.
     */
    public void startGameLoop() {
        gameloop = new Thread(this);
        gameloop.start();
    }

    /**
     * Stops the game loop thread.
     */
    private void stopGameLoop() {
        try {
            if (gameloop != null) {
                gameloop.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        if (gameController.isGameStarted()) {
            player.update();
        }
    }

    /**
     * Formats the elapsed time from milliseconds to hours, minutes and seconds.
     * @param millis    Number of milliseconds.
     * @return          Formatted string containing the time.
     */
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
        g.fillRect(Config.SCREEN_WIDTH, 0, labelWidth + 150, Config.SCREEN_HEIGHT);
    }

    /**
     * Main loop of the game.
     */
    @Override
    public void run() {
        double drawInterval = 1E9 / Config.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;
        long timer = 0;

        while (gameController.isGameStarted()) {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            timer += currTime - lastTime;
            lastTime = currTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= 1E9) {
                elapsedTime = System.currentTimeMillis() - startTime;
                timerLabel.setText("Time: " + formatElapsedTime(elapsedTime));
                timer = 0;
            }

        }
    }
}
