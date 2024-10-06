import javax.swing.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.setResizable(false);

        gamePanel = new GamePanel();
        this.add(gamePanel);

        this.setLocation(400, 100);
        this.pack();
        this.setVisible(true);
    }

    public void startGame() {
        gamePanel.startGame();
    }
}
