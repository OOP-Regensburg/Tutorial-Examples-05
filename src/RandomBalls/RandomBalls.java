package RandomBalls;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

/**
 * In dieser GraphicsApp-Anwendung werden 100 zufällig platzierte Kreise animiert. Ausgehend von ihrer Startposition
 * bewegen sich je 25% der Kreise nach oben rechts, unten rechts, unten links und oben links. Die Kreise werden in einem
 * Arrays verwaltet.
 */

public class RandomBalls extends GraphicsApp {

    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final int NUMBER_OF_BALLS = 100;
    private static final int BALL_RADIUS = 15;
    private static final int BALL_SPEED = 2;
    private Circle[] circles;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initCircles();
    }

    private void initCircles() {
        circles = new Circle[NUMBER_OF_BALLS];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = createRandomCircle();
        }
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        updateAndDrawCircles();
    }

    private void updateAndDrawCircles() {
        for (int i = 0; i < circles.length; i++) {
            int xSpeed = BALL_SPEED;
            int ySpeed = BALL_SPEED;
            if (i >= circles.length * 0.25 && i < circles.length * 0.5) {
                xSpeed = -BALL_SPEED;
                ySpeed = -BALL_SPEED;
            }
            if (i >= circles.length * 0.5 && i < circles.length * 0.75) {
                xSpeed = BALL_SPEED;
                ySpeed = -BALL_SPEED;
            }
            if (i >= circles.length * 0.75) {
                xSpeed = -BALL_SPEED;
                ySpeed = BALL_SPEED;
            }
            circles[i].move(xSpeed, ySpeed);
            circles[i].draw();
        }
    }

    private Circle createRandomCircle() {
        int xPosition = getRandomInt(SCREEN_WIDTH);
        int yPosition = getRandomInt(SCREEN_HEIGHT);
        Color ballColor = Colors.getRandomColor();
        return new Circle(xPosition, yPosition, BALL_RADIUS, ballColor);
    }

    private int getRandomInt(int upperLimit) {
        return (int) (Math.random() * (upperLimit + 1));
    }

}
