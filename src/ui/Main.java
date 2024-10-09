package ui;

import java.awt.*;
import javax.swing.*;

public class Main {
    void demo() {
        JFrame frame = new JFrame ("component, container, helper example");
        // creates window
        // not visible yet
        JButton button = new JButton("OK"); // creates button
        frame.add(button, BorderLayout.SOUTH);
        // put component in frame: button in frame
        JPanel panel = new JPanel(); // creates another component
        frame.add(panel); // put panel in frame
        Color mauve = new Color(128, 100, 100); // creates helper object:
        // Color
        panel.setBackground( mauve ); // colors background panel
        // standard code for frames
        frame.setSize(400, 300); // size of window in pixels
        frame.setVisible(true); // make frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make close button (X) behave as expected
   }

    public static void main(String[] args) {
        new Main().demo();
    }
}
