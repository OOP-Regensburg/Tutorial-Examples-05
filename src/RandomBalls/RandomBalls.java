package RandomBalls;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Rectangle;

/**
 * In dieser GraphicsApp-Anwendung werden 100 zufällig platzierte Kreise animiert. Ausgehend von ihrer Startposition
 * bewegen sich je 25% der Kreise nach oben rechts, unten rechts, unten links und oben links. Die Kreise werden in einem
 * Arrays verwaltet.
 */

public class RandomBalls extends GraphicsApp {

    /**
     * Wichtige Werte für die Konfiguration der Zeichenfläche und der darzustellenden Objekte werden
     * an zentraler Stelle durch Konstanten abgebildet. Möglichst viele Werte werden aus bereits vorhandenen
     * Konstanten berechnet. Dadurch müssen bei notwendigen Änderungen (z.B. der Größe der Zeichenfläche) nur eine
     * kleine Anzahl an Stellen des Codes verändert werden.
     */
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final int NUMBER_OF_BALLS = 100;
    private static final int BALL_RADIUS = 15;
    private static final int BALL_SPEED = 2;

    /**
     * Array für die darzustellenden und zu bewegenden Bälle.
     */
    private Circle[] circles;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initCircles();
    }

    /**
     * Initalisierung/Erstellung der Zufallsbälle und deren Speicherung im vorher initalisierten Array.
     */
    private void initCircles() {
        circles = new Circle[NUMBER_OF_BALLS];
        /**
         * Die Schleife iteriert mit Hilfe der Zählervariable i über alle möglichen Positionen (Indices) des Arrays und
         * speichert in jeder Position ein neues, zufälliges Circle-Objekt.
         */
        for (int i = 0; i < circles.length; i++) {
            // Die eigentliche Konstruktion der Bälle ist in eine separate Methode ausgelagert
            circles[i] = createRandomCircle();
        }
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        updateAndDrawCircles();
    }

    /**
     * Diese Methode bewegt jeden den Bälle und zeichnen ihn anschließend auf der neuen Position ein.
     */
    private void updateAndDrawCircles() {
        // Wir iterieren über das gesamte Array ...
        for (int i = 0; i < circles.length; i++) {
            // ... und legen inital die für die Bewegung der ersten 25 % der Bälle zu verwendende Geschwindigkeit und
            // Richtung (unten rechts) fest.
            int xSpeed = BALL_SPEED;
            int ySpeed = BALL_SPEED;
            // Für die zweiten 25% der Bälle ...
            if (i >= circles.length * 0.25 && i < circles.length * 0.5) {
                // ... wird die Geschwindigkeit für eine Bewegung nach links oben angepasst.
                xSpeed = -BALL_SPEED;
                ySpeed = -BALL_SPEED;
            }
            // Für die dritten 25 % der Bälle ...
            if (i >= circles.length * 0.5 && i < circles.length * 0.75) {
                // ... wird die Geschwindigkeit für eine Bewegung nach rechts oben angepasst.
                xSpeed = BALL_SPEED;
                ySpeed = -BALL_SPEED;
            }
            // Für die letzten 25 % der Bälle ...
            if (i >= circles.length * 0.75) {
                // ... wird die Geschwindigkeit für eine Bewegung nach links unten angepasst.
                xSpeed = -BALL_SPEED;
                ySpeed = BALL_SPEED;
            }
            /**
             * Innerhalb der for-Schleife wird der "aktuelle" Ball bewegt und gezeichnet. Für die Bewegung wird der je
             * nach Position innerhalb des Arrays (Erstes, zweites, drittes oder viertes Viertel) angepasste Wert
             * verwendet.
             */
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

    /**
     * Die Methode berechnet mit Hilfe der Math.random()-Methode einen zufälligen Ganzzahl-Wert zwischen 0 und dem
     * als Parameter übergebenen oberen Limit (beide Werte sind inklusiv!). Der berechnete Wert wird als Ergebnis
     * zurückgegeben.
     */
    private int getRandomInt(int upperLimit) {
        return (int) (Math.random() * (upperLimit + 1));
    }

}
