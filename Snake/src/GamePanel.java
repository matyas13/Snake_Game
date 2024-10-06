import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final int tileSize = 50;
    private static final int screenRows = 18;
    private static final int screenCols = 18;
    private final int screenWidth = tileSize * screenRows;
    private final int screenHeight = tileSize * screenCols;

    Snake snake;
    KeyHandler keyH;

    public static int getScreenRows() {
        return screenRows;
    }

    public static int getScreenCols() {
        return screenCols;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(27, 32, 33));
        keyH = new KeyHandler();
        this.setFocusable(true);
        this.addKeyListener(keyH);

        this.setDoubleBuffered(true);

        snake = new Snake(keyH);
    }

    public void startGame() {
        double drawInterval = (double) 1000000000 / 10;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(true) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        snake.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        g.dispose();
    }
}
