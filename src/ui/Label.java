package ui;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * Label that uses a custom font and color.
 */
public class Label extends JLabel {
    /**
     * Class constructor.
     * @param label Text of the label.
     * @param size  Font size.
     */
    public Label(String label, int size) {
        super(label);
        setFont(Config.getFont(size));
        setForeground(Color.white);
        setBackground(null);
    }
}
