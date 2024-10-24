package ui;

import java.awt.*;
import javax.swing.*;
import utils.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setSize(new Dimension(1280, 720));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setTitle("Maze of Madness");

        GameController gameController = new GameController(window);

        window.setVisible(true); 
    }
}
