package ui;

import javax.swing.JLabel;
import java.awt.Color;

public class Label extends JLabel {
    public Label(String label, int size) {
        super(label);
        setFont(Config.getFont(size));
        setForeground(Color.white);
        setBackground(null);
    }
}
