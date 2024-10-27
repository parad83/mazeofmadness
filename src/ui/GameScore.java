package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Reprents the game score menu.
 */
public class GameScore extends JPanel {
    GameController gameController;

    JLabel winLabel;
    JLabel timeLabel;

    JButton exitButton;
    JButton restartButton;
    JButton nextNButton;

    /**
     * Class constructor.
     * @param gameController    Game controller object.
     * @param milliseconds      Number of milliseconds that it took to complete the level.
     */
    public GameScore(GameController gameController, long milliseconds) {
        this.setBackground(Config.FOREGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        winLabel = new Label("You win!", 30);
        timeLabel = new Label(
            "It took you " + (float) milliseconds / 1000.0 + " to finish the map", 15);

        exitButton = new Button("exit", 14);
        restartButton = new Button("restart", 14);
        nextNButton = new Button("next game", 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 25, 5);

        restartButton.addActionListener(e -> {
            gameController.restartGame();
            
        });
        exitButton.addActionListener(e -> {
            gameController.exitGame();
        });
        nextNButton.addActionListener(e -> {
            gameController.startGame();
        });

        this.add(winLabel, gbc);
        gbc.gridy++;
        this.add(timeLabel, gbc);
        gbc.gridy++;
        gbc.gridy++;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 1.0; // Allow buttons to expand equally
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(25, 5, 25, 5);
        this.add(restartButton, gbc);
        gbc.gridx++;
        this.add(exitButton, gbc);
        gbc.gridx++;
        this.add(nextNButton, gbc);
    }

    /**
     * Updates the time of the score menu.
     * @param milliseconds  Time in milliseconds.
     */
    public void updateTime(long milliseconds) {
        timeLabel.setText(
            "It took you " + (float) milliseconds / 1000.0 + " seconds to finish the map");
        this.repaint();
    }
}
