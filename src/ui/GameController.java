package ui;

import javax.swing.JFrame;
import logic.GameState;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

public class GameController {
    private GameState gameState;
    private JFrame window;
    private GamePanel gamePanel;
    private GameMenu gameMenu;
    private GameScore gameScore;
    private CardLayout layout;

    public GameController(JFrame window) {
        this.window = window;
        this.gameState = GameState.NOT_STARTED;

        this.gamePanel = new GamePanel(this);
        this.gameMenu = new GameMenu(this);
        this.gameScore = new GameScore(this);

        layout = new CardLayout();
        window.getContentPane().setLayout(layout);

        window.getContentPane().add(gameMenu, "Menu");
        window.getContentPane().add(gamePanel, "Game");
        window.getContentPane().add(gameScore, "Score");

        window.setVisible(true);
        showMenu();
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

    public void stopGame(boolean isWon) {
        this.setGameState(GameState.NOT_STARTED);
        if (!isWon) {
            window.getContentPane().setLayout(layout);
            showMenu();
        } else {
            showScore();
            // TODO: show custom panel
        }
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
