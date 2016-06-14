package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/loginForm.fxml"));
        primaryStage.setTitle("Welcome");

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            try {
                Database.closeProgram(primaryStage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}