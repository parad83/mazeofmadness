package ui;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import logic.GameState;
import models.TileBuilder;

/**
 * Controls main logic of the UI and game state.
 */
public class GameController {
    GameState gameState;
    JFrame window;

    GamePanel gamePanel;
    GameMenu gameMenu;
    GameScore gameScore;
    CardLayout layout;

    TileBuilder tileBuilder;

    /**
     * Class constructor.
     * @param window    Main window of the application.
     */
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

    /**
     * Resets the current game.
     */
    public void resetCurrGame() {
        gamePanel.restart();
    }

    /**
     * Restarts the game using the same map; called from the score menu.
     */
    public void restartGame() {
        this.setGameState(GameState.STARTED);
        gamePanel.restart();
        gamePanel.startGameLoop();
        window.getContentPane().setLayout(layout);
        showGame();
    }

    public boolean isGameStarted() {
        return (this.gameState == GameState.STARTED);
    }

    /**
     * Starts the game and shows it.
     */
    public void startGame() {
        if (gameState == GameState.STARTED) {
            return;
        }

        this.setGameState(GameState.STARTED);
        gamePanel.setupGame();
        showGame();
    }

    /**
     * Exits to the main menu.
     */
    public void exitGame() {
        this.setGameState(GameState.NOT_STARTED);
        window.getContentPane().setLayout(layout);
        showMenu();
    }

    /**
     * Called when the game is won to update state and shows score menu.
     */
    public void gameWon() {
        this.setGameState(GameState.NOT_STARTED);
        window.getContentPane().setLayout(layout);
        gameScore.updateTime(gamePanel.getElapsedTime());
        showScore();
    }

    /**
     * Shows the score menu.
     */
    private void showScore() {
        layout.show(window.getContentPane(), "Score");
        window.getContentPane().setLayout(new GridBagLayout());
        gameScore.requestFocusInWindow();
    }

    /**
     * Shows the main menu.
     */
    private void showMenu() {
        layout.show(window.getContentPane(), "Menu");
    }

    /**
     * Shows the game window.
     */
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
