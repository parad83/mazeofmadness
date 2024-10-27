package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the game's main menu.
 */
public class GameMenu extends JPanel {
    private Button startButton;
    private Button exitButton;
    private JLabel gameTitle = new JLabel();
    private GameController gameController;

    /**
     * Class constructor.
     * @param gameController    Game controller object.
     */
    public GameMenu(GameController gameController) {
        this.gameController = gameController;

        this.setBackground(Config.FOREGROUND_COLOR);
        this.setLayout(new GridBagLayout());

        startButton = new Button("Begin game", 15);
        exitButton = new Button("Quit", 15);
        gameTitle = new JLabel("Maze of Madness");
        gameTitle.setFont(Config.getFont(30));
        gameTitle.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 25, 5);

        this.add(gameTitle, gbc);
        gbc.gridy++;
        gbc.gridy++;
        this.add(startButton, gbc);
        gbc.gridy++;
        this.add(exitButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStartGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExitGame();
            }
        });
    }

    private void onStartGame() {
        gameController.startGame();
    }

    private void onExitGame() {
        System.exit(0);
    }
}
