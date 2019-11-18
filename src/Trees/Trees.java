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
    /**
     * Wichtige Werte für die Konfiguration der Zeichenfläche und der darzustellenden Objekte werden
     * an zentraler Stelle durch Konstanten abgebildet. Möglichst viele Werte werden aus bereits vorhandenen
     * Konstanten berechnet. Dadurch müssen bei notwendigen Änderungen (z.B. der Größe der Zeichenfläche) nur eine
     * kleine Anzahl an Stellen des Codes verändert werden.
     */
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

    /**
     * Variablen für den "Boden", die Positionen der Blumen sowie deren aktuelle Höhe.
     */
    private Rectangle ground;
    private Point[] flowerPositions;
    private float currentFlowerSize = DEFAULT_FLOWER_SIZE;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initGround();
        initFlowerPositions();
    }

    /**
     * Initalisiert den Boden (die Wiese) auf der die Blumen wachsen. Der Boden wird als Rechteck am unteren Bildschirm-
     * rand dargestellt und durch ein Objekt der Rectangle-Klasse dargestellt.
     */
    private void initGround() {
        int xPositionGround = 0;
        int yPositionGround = (int) (SCREEN_HEIGHT * 0.75);
        ground = new Rectangle(xPositionGround, yPositionGround, SCREEN_WIDTH, SCREEN_HEIGHT - yPositionGround, GROUND_COLOR);
    }

    /**
     * Initalisiert die Position bzw. den Ursprung der einzelnen Blumen in Form von Positon-Objekten, die eine x- und eine
     * y-Koordinante speichern können. Die Positionen werden in einem Array (flowerPositions) gespeichert. Die Erzeugung
     * und Speicherung erfolgt mit Hilfe eines Arrays. Dabei wird die y-Position mit jeder Iteration um einen bestimmten
     * Wert erhöht um einen gleichmäßigen Abstand zwischen den Pflanzen zu erreichen.
     */
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
        /**
         * Nach jedem Zeichenvorgang wird die Größe der Blumen für den nächsten Aufruf der draw-Methode erhöht: Dadurch
         * wird das "Wachsen" der Blumen realisiert.
         */
        currentFlowerSize += FLOWER_GROW_SPEED;
    }

    /**
     * In dieser ausgelagerten Methode werden zuerst alle Blumen über eine Schleife gezeichnet. Anschließend wird der
     * Boden eingezeichnet. Die Reihenfolge der Zeichenbefehle bestimmt die z-Position der Objekte auf der Zeichen-
     * fläche. Später gezeichnete Objekte überlagern bei Überschneidung solche Elemente, die vorher gezeichnet wurden.
     */
    private void drawScene() {
        for (int i = 0; i < flowerPositions.length; i++) {
            // Die aus dem Array ausgelesene Position wird an eine Methode übergeben, die auf Basis dieser Information
            // eine einzelne Blume zeichnet.
            drawFlowerAt(flowerPositions[i]);
        }
        ground.draw();
    }

    private void drawFlowerAt(Point position) {
        // Zeichnet den Stängel der Blume als Linie von deren Position bis zur aktuellen Wuchshöhe
        Line line = new Line(position.getXPos(), position.getYPos(), position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_COLOR, DEFAULT_FLOWER_WEIGHT);
        line.draw();
        // Zeichnet die äußere Blüte am Ende des gezeichneten Stängels
        Circle blossom = new Circle(position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_RADIUS, BLOSSOM_COLOR);
        blossom.draw();
        // Zeichnet die innere, kleinere Blüte über die so eben gezeichnete äußere Blüte
        Circle innerBlossom = new Circle(position.getXPos(), position.getYPos() - currentFlowerSize, FLOWER_RADIUS/2, INNER_BLOSSOM_COLOR);
        innerBlossom.draw();
    }

}
