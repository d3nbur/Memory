package application.main;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.model.Board;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Memory");
        primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 838);

        new Board(new Controller(), borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
