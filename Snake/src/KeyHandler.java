import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            setDirs(true, false, false, false);
        }
        if(code == KeyEvent.VK_S) {
            setDirs(false, true, false, false);

        }
        if(code == KeyEvent.VK_A) {
            setDirs(false, false, true, false);

        }
        if(code == KeyEvent.VK_D) {
            setDirs(false, false, false, true);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setDirs(boolean up, boolean down, boolean left, boolean right) {
        upPressed = up;
        downPressed = down;
        leftPressed = left;
        rightPressed = right;
    }
}
