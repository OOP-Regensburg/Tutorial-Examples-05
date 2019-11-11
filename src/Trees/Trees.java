package Trees;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

/**
 * Diese GraphicsApp stellt eine Szene aus 10 wachsenden Blumen auf einer Wiese dar. Die Positionen (Wurzeln) der Blumen
 * werden als Point-Instanzen in einem Array gespeichert. Ausgehend von dieser Position aus werden in jedem Frame die
 * eigentlichen Blumen, bestehend aus verschiedenen graphischen Elementen, gezeichnet. Die Größe der Blumen wird aus einer
 * Variable ausgelesen, die mit jedem Aufruf der draw-Methode leicht inkrementiert wird.
 */

public class Trees extends GraphicsApp {

    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;
    private static final Color BACKGROUND_COLOR = Colors.BLUE;
    private static final Color GROUND_COLOR = Colors.GREEN;
    private static final Color FLOWER_COLOR = Colors.GREEN;
    private static final Color BLOSSOM_COLOR = Colors.RED;
    private static final Color INNER_BLOSSOM_COLOR = Colors.YELLOW;
    private static final float DEFAULT_FLOWER_SIZE = 20;
    private static final int DEFAULT_FLOWER_WEIGHT = 5;
    private static final int FLOWER_RADIUS = 15;
    private static final float FLOWER_GROW_SPEED = 0.25f;
    private static final int NUMBER_OF_FLOWERS = 10;
    private static final int FLOWER_MARGIN = SCREEN_WIDTH / (NUMBER_OF_FLOWERS + 1);

    private Rectangle ground;
    private float currentFlowerSize = DEFAULT_FLOWER_SIZE;
    private Point[] flowerPositions;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initGround();
        initFlowerPositions();
    }

    private void initGround() {
        int xPositionGround = 0;
        int yPositionGround = (int) (SCREEN_HEIGHT * 0.75);
        ground = new Rectangle(xPositionGround, yPositionGround, SCREEN_WIDTH, SCREEN_HEIGHT - yPositionGround, GROUND_COLOR);
    }

    private void initFlowerPositions() {
        flowerPositions = new Point[NUMBER_OF_FLOWERS];
        int xPosition = FLOWER_MARGIN;
        int yPosition = (int) (SCREEN_HEIGHT * 0.75);
        for (int i = 0; i < flowerPositions.length; i++) {
            flowerPositions[i] = new Point(xPosition, yPosition);
            xPosition += FLOWER_MARGIN;
        }
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawScene();
        currentFlowerSize += FLOWER_GROW_SPEED;
    }

    private void drawScene() {
        for (int i = 0; i < flowerPositions.length; i++) {
            drawFlowerAt(flowerPositions[i]);
        }
        ground.draw();
    }

    private void drawFlowerAt(Point position) {
        Line line = new Line(position.getXPos(), position.getYPos(), position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_COLOR, DEFAULT_FLOWER_WEIGHT);
        line.draw();
        Circle blossom = new Circle(position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_RADIUS, BLOSSOM_COLOR);
        blossom.draw();
        Circle innerBlossom = new Circle(position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_RADIUS/2, INNER_BLOSSOM_COLOR);
        innerBlossom.draw();
    }

}
