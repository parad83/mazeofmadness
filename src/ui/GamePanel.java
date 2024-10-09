package ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import models.*;

public class GamePanel extends JPanel implements KeyListener {
    private Player player;

    public static final int SCREEN_WIDTH = 1000;
    public static int SCREEN_HEIGHT = 600;

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_D: 
            player.moveRight();
            break;
            case KeyEvent.VK_A:
            player.moveLeft();
            break;
            case KeyEvent.VK_W:
            player.moveUp();
            break;
            case KeyEvent.VK_S:
            player.moveDown();
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // switch(e.getKeyCode()) {
        //     case KeyEvent.VK_D: 
        //     player.moveRight();
        //     break;
        //     case KeyEvent.VK_A:
        //     player.moveLeft();
        //     break;
        //     case KeyEvent.VK_W:
        //     player.moveUp();
        //     break;
        //     case KeyEvent.VK_S:
        //     player.moveDown();
        //     break;
        // }
        System.out.println("key realsed " + e.getKeyCode());
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("key typed " + e.getID());
    }


    public GamePanel() {
        this.setLayout(null);

        player = new Player(200, 100, 20, 10);
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    

        addKeyListener(this);
        setFocusable(true);

        this.add(player);


        JButton but = new JButton("test butt", null);

        but.addActionListener(new exampleActionListener());
        but.setBounds(50, 50, 100, 50);


        this.add(but);
    }
}

class exampleActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("button pressed " + e.getID());

    }
}
