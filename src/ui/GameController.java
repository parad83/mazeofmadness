package ui;

import javax.swing.JFrame;
import logic.GameState;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import models.TileBuilder;

public class GameController {
    GameState gameState;
    JFrame window;

    GamePanel gamePanel;
    GameMenu gameMenu;
    GameScore gameScore;
    CardLayout layout;

    TileBuilder tileBuilder;

    public GameController(JFrame window) {
        this.window = window;
        this.gameState = GameState.NOT_STARTED;

        this.newBuilder();

        this.gamePanel = new GamePanel(this, tileBuilder);
        this.gameMenu = new GameMenu(this);
        this.gameScore = new GameScore(this, 0);

        layout = new CardLayout();
        window.getContentPane().setLayout(layout);
        window.getContentPane().setBackground(Config.UNPLAYABLE_TILE_COLOR);

        window.getContentPane().add(gameMenu, "Menu");
        window.getContentPane().add(gamePanel, "Game");
        window.getContentPane().add(gameScore, "Score");

        window.setVisible(true);
        showMenu();
    }

    public void newBuilder() {
        tileBuilder = new TileBuilder();
    }

    public void restartGame() {
        this.setGameState(GameState.STARTED);
        gamePanel.restart();
        window.getContentPane().setLayout(layout);
        showGame();

    }

    public boolean isGameStarted() {
        return (this.gameState == GameState.STARTED);
    }

    public void startGame() {
        if (gameState == GameState.STARTED) {
            return;
        }

        this.setGameState(GameState.STARTED);
        gamePanel.setupGame();
        showGame();
    }

    public void exitGame() {
        this.setGameState(GameState.NOT_STARTED);
        window.getContentPane().setLayout(layout);
        showMenu();
    }

    public void gameWon() {
        this.setGameState(GameState.NOT_STARTED);
        window.getContentPane().setLayout(layout);
        gameScore.updateTime(gamePanel.getElapsedTime());
        showScore();
    }

    private void showScore() {
        layout.show(window.getContentPane(), "Score");
        window.getContentPane().setLayout(new GridBagLayout());
        gameScore.requestFocusInWindow();
    }

    private void showMenu() {
        layout.show(window.getContentPane(), "Menu");
    }

    private void showGame() {
        layout.show(window.getContentPane(), "Game");
        window.getContentPane().setLayout(new GridBagLayout());
        gamePanel.requestFocusInWindow();
    }

    public GameState getGameState() {
        return this.gameState;
    }

    private void setGameState(GameState s) {
        this.gameState = s;
    }
}
