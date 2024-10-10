package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import models.*;

public class GamePanel extends JPanel implements KeyListener {
    private Player player;

    public static final int SCREEN_WIDTH = 1000;
    public static int SCREEN_HEIGHT = 600;

    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;
    private Thread gameLoop;

    public GamePanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        player = new Player(200, 100, 20, 10);
        this.add(player);
    
        addKeyListener(this);
        setFocusable(true);

        startGameLoop();

        JButton but = new JButton("test butt", null);
        but.addActionListener(new exampleActionListener());
        but.setBounds(50, 50, 100, 50);
        this.add(but);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                goRight = true;
                break;
            case KeyEvent.VK_A:
                goLeft = true;
                break;
            case KeyEvent.VK_W:
                goUp = true;
                break;
            case KeyEvent.VK_S:
                goDown = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                goRight = false;
                break;
            case KeyEvent.VK_A:
                goLeft = false;
                break;
            case KeyEvent.VK_W:
                goUp = false;
                break;
            case KeyEvent.VK_S:
                goDown = false;
                break;
            default:
                break;
        }        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void startGameLoop() {
        gameLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (goLeft) {
                        player.moveLeft();
                    } 
                    if (goRight) {
                        player.moveRight();
                    }
                    if (goUp) {
                        player.moveUp();
                    } 
                    if (goDown) {
                        player.moveDown();
                    }

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        gameLoop.start(); // Start the game loop thread
    }
}

class exampleActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("button pressed " + e.getID());

    }
}
