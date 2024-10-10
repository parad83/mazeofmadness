package logic;

import java.awt.event.*;

public class KeyHandler implements KeyListener {
    public boolean goLeft;
    public boolean goRight;
    public boolean goUp;
    public boolean goDown;

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
}
