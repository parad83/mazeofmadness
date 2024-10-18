package ui;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setSize(new Dimension(1280, 720));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setTitle("Maze of Madness");
        // window.setLayout(new GridBagLayout());
        
        GameController gameController = new GameController(window);

        window.setVisible(true); 
    }
}
