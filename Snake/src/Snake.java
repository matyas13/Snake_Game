import enums.SnakeDirections;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class Snake {

    private ArrayList<Point> snakeBody;
    SnakeDirections currDirection;
    KeyHandler keyH;

    private Point apple;
    private boolean appleEaten;
    private int appleSize = GamePanel.getTileSize() * 2 / 3;
    Random random;

    public Snake(KeyHandler keyH) {
        snakeBody = new ArrayList<>();

        initSnake();
        currDirection = SnakeDirections.RIGHT;
        this.keyH = keyH;

        appleEaten = false;
        random = new Random();
        apple = new Point();
        generateApplePos();
    }

    public void generateApplePos() {
        do {
            apple.x = random.nextInt(GamePanel.getScreenCols());
            apple.y = random.nextInt(GamePanel.getScreenRows());
        }while(snakeBody.contains(apple));
    }

    public void initSnake() {
        for(int i = 7; i >= 0; i--) {
            snakeBody.add(new Point(i, 0));
        }
    }

    public void update() {

        if(appleEaten) {
            generateApplePos();
            appleEaten = false;
        }

        int x = 0, y = 0;

        if(keyH.isUpPressed()) {
            if(currDirection != SnakeDirections.DOWN) {
                currDirection = SnakeDirections.UP;
            }
        }
        if(keyH.isDownPressed()) {
            if(currDirection != SnakeDirections.UP) {
                currDirection = SnakeDirections.DOWN;
            }
        }
        if(keyH.isLeftPressed()) {
            if(currDirection != SnakeDirections.RIGHT) {
                currDirection = SnakeDirections.LEFT;
            }
        }
        if(keyH.isRightPressed()) {
            if(currDirection != SnakeDirections.LEFT) {
                currDirection = SnakeDirections.RIGHT;
            }
        }

        if(currDirection == SnakeDirections.UP) {
            x = 0;
            y = -1;
        }
        if(currDirection == SnakeDirections.DOWN) {
            x = 0;
            y = 1;
        }
        if(currDirection == SnakeDirections.LEFT) {
            x = -1;
            y = 0;
        }
        if(currDirection == SnakeDirections.RIGHT) {
            x = 1;
            y = 0;
        }

        Point tmp = new Point(snakeBody.getFirst().x + x, snakeBody.getFirst().y + y);

        if(checkCollision(tmp)) {
            System.out.println("Game Over");
            exit(0);
        }

        if(tmp.x == -1) {
            tmp.x = GamePanel.getScreenCols() - 1;
        }
        if(tmp.x == GamePanel.getScreenCols()) {
            tmp.x = 0;
        }
        if(tmp.y == -1) {
            tmp.y = GamePanel.getScreenRows() - 1;
        }
        if(tmp.y == GamePanel.getScreenRows()) {
            tmp.y = 0;
        }


        snakeBody.addFirst(new Point(tmp.x, tmp.y));
        if(tmp.equals(apple)) {
            appleEaten = true;
        } else {
            snakeBody.removeLast();
        }
        keyH.setDirs(false, false, false, false);
    }

    public boolean checkCollision(Point p) {
        return snakeBody.contains(p);
    }

    public void draw(Graphics g) {

        g.setColor(Color.red);
        g.fillOval(apple.x * GamePanel.getTileSize() + (GamePanel.getTileSize() - appleSize) / 2,
                apple.y * GamePanel.getTileSize() + (GamePanel.getTileSize() - appleSize) / 2,
                appleSize,
                appleSize);

        g.setColor(Color.green);

        g.fillRect(snakeBody.getFirst().x * GamePanel.getTileSize(),
                snakeBody.getFirst().y * GamePanel.getTileSize(),
                GamePanel.getTileSize(),
                GamePanel.getTileSize());

        g.setColor(Color.GREEN);
        for(int i = 1; i < snakeBody.size(); i++) {
            g.fillRect(snakeBody.get(i).x * GamePanel.getTileSize(),
                    snakeBody.get(i).y * GamePanel.getTileSize(),
                    GamePanel.getTileSize(),
                    GamePanel.getTileSize());
        }
    }
}
