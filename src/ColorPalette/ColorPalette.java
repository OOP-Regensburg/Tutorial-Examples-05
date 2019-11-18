package ColorPalette;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

/**
 * Diese GraphicsApp-Anwendung stellt ein Gitternetz aus mehreren farbigen Rechtecken dar. Jedes Rechteck
 * verfügt über eine zu Beginn zufällig festgelegte Farbe. Im inneren des Rechteck wird eine Label mit aufsteigende
 * Nummer, beginnend bei "1" angezeigt. Die Rechtecke und Label werden in zwei Arrays gespeichert.
 */

public class ColorPalette extends GraphicsApp {

    /**
     * Wichtige Werte für die Konfiguration der Zeichenfläche und der darzustellenden Objekte werden
     * an zentraler Stelle durch Konstanten abgebildet. Möglichst viele Werte werden aus bereits vorhandenen
     * Konstanten berechnet. Dadurch müssen bei notwendigen Änderungen (z.B. der Größe der Zeichenfläche) nur eine
     * kleine Anzahl an Stellen des Codes verändert werden.
     */
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = SCREEN_WIDTH;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final Color LABEL_COLOR = Colors.WHITE;
    private static final int NUMBER_OF_FIELDS = 25;
    private static final int FIELD_SIZE = (int) (SCREEN_WIDTH / Math.sqrt(NUMBER_OF_FIELDS));

    /**
     * Arrays für die darzustellenden Rechtecke (fields) und die zugehörigen Labels. Die Arrays werden im Rahmen
     * dieser Anwendung parallel behandelt. D.h. in beiden Arrays ist die gleiche Anzahl an Elementen und das erste
     * "Label" beschreibt das erste "Rechteck".
     */
    private Rectangle[] fields;
    private Label[] labels;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initFieldsAndLabels();
    }

    /**
     * In dieser Methode werden die beiden Arrays initalisiert. Für die notwendige Angabe der Länge
     * wird der Wert aus der vorbereiteten Konstanten verwendet. Über eine einzige Schleife werden im Anschluss alle
     * Positionen beider Arrays mit konkreten Werten (Rectangle- und Label-Instanzen) belegt. Die Konstruktion dieser
     * Objekte ist in zwei zusätzliche Methoden (createField und createLabelForField) ausgelagert. Die notwendigen
     * Parameter, z.B. die Position (bezogen auf die Reihenfolge) des aktuellen Rechtecks, werden an die Methoden übergeben.
     *
     */
    private void initFieldsAndLabels() {
        fields = new Rectangle[NUMBER_OF_FIELDS];
        labels = new Label[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            fields[i] = createField(i);
            labels[i] = createLabelForField(fields[i], i + 1);
        }
    }

    /**
     * In dieser Methode wird ein zufällig eingefärbtes Rechteck erzeugt und als Ergebnis zurückgegeben. Auf Basis der
     * übergebenen Position und den als Instanzvariablen und Konstanzen festgehaltenen Werten wird die korrekte
     * x- bzw. y-Position berechnet.
     */
    private Rectangle createField(int position) {
        int squaresPerRow = SCREEN_WIDTH / FIELD_SIZE;
        float xPosition = (position % squaresPerRow) * FIELD_SIZE;
        float yPosition = (position / squaresPerRow) * FIELD_SIZE;
        Color randomColor = Colors.getRandomColor();
        Rectangle rect = new Rectangle(xPosition, yPosition, FIELD_SIZE, FIELD_SIZE, randomColor);
        // Entfernt den "Rand" des Rechtecks, in dem dessen Stärke auf 0 gesetzt wird
        rect.setBorderWeight(0);
        return rect;
    }

    /**
     * In dieser Methode wird ein Label erzeugt, das in der Mitte des übergebenen Rechtecks positioniert ist und als
     * Text die als zweiten Parameter übergebene Zahl beinhaltet. Das auf Bais dieser Werte erstellte Label wird als
     * Ergebnis zurückgegeben.
     */
    private Label createLabelForField(Rectangle field, int position) {
        float xPosition = field.getXPos() + field.getWidth() / 2;
        float yPosition = field.getYPos() + field.getHeight() / 2;
        Label label = new Label(xPosition, yPosition, "#" + position);
        label.setColor(LABEL_COLOR);
        return label;
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawFieldsAndLabels();
    }

    /**
     * Die Methode iteriert über alle Einträge der beiden Arrays - da die Arrays die gleiche Länge haben müssen,
     * kann eine einzige Schleife verwendet werden - und zeichnetjedes dort gespeicherte Objekt (Rectangle oder Label)
     * durch den Aufruf der draw-Methode.
     */
    public void drawFieldsAndLabels() {
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            fields[i].draw();
            labels[i].draw();
        }
    }

}