package logic;

import java.awt.event.*;

import ui.GamePanel;

public class KeyHandler implements KeyListener {
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;

    public void reset() {
        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            default:
                break;
        }        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
