package project;

import javafx.application.Application;
import javafx.stage.Stage;
import project.controller.MainController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MainController(primaryStage);
    }
}
