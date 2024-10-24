package ui;

import javax.swing.JButton;

public class Button extends JButton {
    public Button(String label, int size) {
        super(label);
        setFont(Config.getFont(size));
    }
}
