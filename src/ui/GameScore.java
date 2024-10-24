package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameScore extends JPanel {
    private GameController gameController;
    private JLabel gameTitle = new JLabel();

    public GameScore(GameController gameController) {
        this.gameController = gameController;
    }
}
