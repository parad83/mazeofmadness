package ui;

import javax.swing.JFrame;
import logic.GameState;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class GameController {
    private GameState gameState;
    private JFrame window;
    private GamePanel gamePanel;
    private GameMenu gameMenu;
    private CardLayout layout;

    public GameController(JFrame window) {
        this.window = window;
        this.gameState = GameState.NOT_STARTED;

        this.gamePanel = new GamePanel(this);
        this.gameMenu = new GameMenu(this);

        layout = new CardLayout();
        window.getContentPane().setLayout(layout);

        window.getContentPane().add(gameMenu, "Menu");
        window.getContentPane().add(gamePanel, "Game");

        window.setVisible(true);

        showMenu();

    }

    public void startGame() {
        if (gameState == GameState.STARTED) {
            return;
        }

        this.setGameState(GameState.STARTED);
        gamePanel.setupGame();
        showGame();
    }

    public void pauseGame() {
        this.setGameState(GameState.PAUSED);
        // TODO: pause logic
    }

    private void showMenu() {
        layout.show(window.getContentPane(), "Menu");
    }

    private void showGame() {
        layout.show(window.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState s) {
        this.gameState = s;
    }
}
