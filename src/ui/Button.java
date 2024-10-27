package ui;

import javax.swing.JButton;

/**
 * Represents a button.
 */
public class Button extends JButton {
    /**
     * Class constructor.
     * @param label Text on the button.
     * @param size  Size of the button.
     */
    public Button(String label, int size) {
        super(label);
        setFont(Config.getFont(size));
    }
}
