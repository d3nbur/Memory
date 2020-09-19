package application.model;

import application.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.*;

import static application.controller.Controller.CHECK;
import static javafx.scene.paint.Color.*;

public class Board implements Observer {

    private final Card[][] boardCards = new Card[4][4];
    private final Pane pane = new Pane();
    private final Controller controller;
    private final BorderPane borderPane;

    public Board(final Controller controller, final BorderPane borderPane) {
        this.controller = controller;
        this.borderPane = borderPane;

        controller.addObserver(this);

        addTop();
        addCenter();
    }

    private void addTop() {
        final HBox hBox = new HBox();
        hBox.setPadding(new Insets(5));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        final Button button = new Button("Neues Spiel");
        button.setOnAction(event -> {
            final List<Color> shuffledCardColors = getShuffledCardColors();
            int index = 0;
            for (final Card[] cards : boardCards) {
                for (final Card card : cards) {
                    card.reset();
                    card.getRectangleBorder().setFill(WHITE);
                    card.activate();
                    card.updateColor(shuffledCardColors.get(index));
                    index++;
                }
            }
        });

        hBox.getChildren().add(button);
        borderPane.setTop(hBox);
    }

    private void addCenter() {
        final List<Color> shuffledCardColors = getShuffledCardColors();
        int index = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final Card card = new Card(shuffledCardColors.get(index), controller, this);
                index++;
                card.setTranslateX(j * 200);
                card.setTranslateY(i * 200);
                pane.getChildren().add(card);
                boardCards[j][i] = card;
            }
        }
        borderPane.setCenter(pane);
    }

    private List<Color> getShuffledCardColors() {
        final List<Color> cardColors = Arrays.asList(
                YELLOW,
                YELLOW,
                BLUE,
                BLUE,
                GREEN,
                GREEN,
                RED,
                RED,
                ORANGE,
                ORANGE,
                PINK,
                PINK,
                BLACK,
                BLACK,
                DARKMAGENTA,
                DARKMAGENTA
        );
        Collections.shuffle(cardColors);
        return cardColors;
    }

    protected void disable(final boolean disable) {
        for (final Card[] cards : boardCards) {
            for (final Card card : cards) {
                card.setDisable(disable);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals(CHECK)) {
            if (controller.getFirstCard().getColor().equals(controller.getSecondCard().getColor())
                    && !controller.getFirstCard().equals(controller.getSecondCard())) {
                controller.getFirstCard().setFound();
                controller.getSecondCard().setFound();
            } else {
                controller.getFirstCard().reset();
                controller.getSecondCard().reset();
            }
        }
    }
}
