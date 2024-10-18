package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class GameMenu extends JPanel {

    private JButton startButton;
    private JButton loadButton;
    private JButton button3;
    private JButton exitButton;
    private GameController gameController;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;

        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());

        startButton = new JButton("Begin game");
        loadButton = new JButton("Load game");
        button3 = new JButton("soething");
        exitButton = new JButton("Quit");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 2, 2, 2);

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
    }

    private void onStartGame() {
        System.out.println("Start button clicked");
        gameController.startGame();
    }

    private void onExitGame() {
        System.exit(0);
    }
}

