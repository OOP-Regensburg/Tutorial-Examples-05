package ColorPalette;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

/**
 * Diese GraphicsApp-Anwendung stellt ein Gitternetz aus mehreren farbigen Rechtecken dar. Jedes Rechteck
 * verfügt über eine zu Beginn zufällig festgelegte Farbe. Im inneren des Rechteck wird eine Label mit aufsteigende
 * Nummer, beginnend bei "1" angezeigt. Die Rechtecke und Label werden in zwei Arrays gespeichert.
 */

public class ColorPalette extends GraphicsApp {

    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final Color LABEL_COLOR = Colors.WHITE;
    private static final int NUMBER_OF_FIELDS = 25;
    private static final int FIELD_SIZE = (int) (SCREEN_WIDTH / Math.sqrt(NUMBER_OF_FIELDS));

    private Rectangle[] fields;
    private Label[] labels;

    @Override
    public void initialize() {
        setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        initFieldsAndLabels();
        initFieldsAndLabels();
    }

    private void initFieldsAndLabels() {
        fields = new Rectangle[NUMBER_OF_FIELDS];
        labels = new Label[NUMBER_OF_FIELDS];
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            fields[i] = createField(i);
            labels[i] = createLabelForField(fields[i], i + 1);
        }
    }

    private Rectangle createField(int position) {
        int squaresPerRow = SCREEN_WIDTH / FIELD_SIZE;
        float xPosition = (position % squaresPerRow) * FIELD_SIZE;
        float yPosition = (position / squaresPerRow) * FIELD_SIZE;
        Color randomColor = Colors.getRandomColor();
        Rectangle rect = new Rectangle(xPosition, yPosition, FIELD_SIZE, FIELD_SIZE, randomColor);
        rect.setBorderWeight(0);
        return rect;
    }

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

    public void drawFieldsAndLabels() {
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            fields[i].draw();
            labels[i].draw();
        }
    }

}