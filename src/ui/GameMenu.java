package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameMenu extends JPanel {

    private Button startButton;
    private Button loadButton;
    private Button button3;
    private Button exitButton;
    private JLabel gameTitle = new JLabel();
    private GameController gameController;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;

        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());

        startButton = new Button("Begin game", 15);
        loadButton = new Button("Load game", 15);
        button3 = new Button("soething", 15);
        exitButton = new Button("Quit", 15);
        gameTitle = new JLabel("Maze of Maddness");
        gameTitle.setFont(Config.getFont(30));
        gameTitle.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 25, 5);

        this.add(gameTitle, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy++;
        gbc.gridy++;
        this.add(startButton, gbc);
        gbc.gridy++;
        this.add(loadButton, gbc);
        gbc.gridy++;
        this.add(button3, gbc);
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

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: loading maps
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
