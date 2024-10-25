package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GameScore extends JPanel {
    GameController gameController;

    JLabel winLabel;
    JLabel timeLabel;

    JButton exitButton;
    JButton restartButton;
    JButton nextNButton;

    public GameScore(GameController gameController, long milliseconds) {
        this.setBackground(Config.FOREGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        winLabel = new Label("You win!", 30);
        timeLabel = new Label("It took you " + formatTime(milliseconds) + " to finish the map" , 15);

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

    public void updateTime(long milliseconds) {
        timeLabel.setText("It took you " + formatTime(milliseconds) + " to finish the map");
        this.repaint();
    }

    public String formatTime(long milliseconds) {

        long hours = milliseconds / 3600000;
        long minutes = (milliseconds % 3600000) / 60000;
        long seconds = (milliseconds % 60000) / 1000;

        StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(" hours");
        }
        if (minutes > 0) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(minutes).append(" minutes");
        }
        if (seconds > 0) {
            if (result.length() > 0) {
                result.append(" and ");
            }
            result.append(seconds).append(" seconds");
        }

        return result.length() > 0 ? result.toString() : "0 seconds";
    }
}
