package ui;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Maze of Madness");

        // in main game
        // window.setSize(400, 300); 
        
   
        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);


        window.pack();
        window.setVisible(true); 
    }
}
