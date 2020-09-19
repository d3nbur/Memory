package application.model;

import application.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.geometry.Pos.CENTER;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class Card extends StackPane {
    private final Rectangle rectangle;
    private final EventHandler<? super MouseEvent> mouseClickEventHandler;
    private Color color;

    public Card(Color color, Controller controller, final Board board) {
        this.color = color;
        rectangle = new Rectangle(200, 200);
        rectangle.setFill(WHITE);
        rectangle.setStroke(BLACK);

        setAlignment(CENTER);
        getChildren().addAll(rectangle);

        setOnMouseClicked(event -> {
            rectangle.setFill(color);

            if (!controller.shouldCheck()) {
                controller.setFirstCard(this);
                controller.setShouldCheck(true);
            } else {
                if (!controller.getFirstCard().equals(this)) {
                    board.disable(true);
                    // wait 0,5 second
                    final Timeline tl = new Timeline(new KeyFrame(Duration.millis(500), e -> {
                        controller.setSecondCard(this);
                        controller.check();
                        controller.setShouldCheck(false);

                        board.disable(false);
                    }));
                    tl.setCycleCount(1);
                    tl.play();
                }
            }
        });
        mouseClickEventHandler = getOnMouseClicked();
    }

    protected void activate() {
        setOnMouseClicked(mouseClickEventHandler);
    }

    private void deactivate() {
        setOnMouseClicked(null);
    }

    public Color getColor() {
        return color;
    }

    public void reset() {
        this.rectangle.setFill(WHITE);
    }

    public Rectangle getRectangleBorder() {
        return rectangle;
    }

    protected void updateColor(final Color color) {
        this.color = color;
    }

    public void setFound() {
        deactivate();
    }

}