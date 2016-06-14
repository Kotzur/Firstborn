package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
    Based on Bucky's alert box tutorial
    Creates an alert box and pops it up. Blocks other input until it has been dealt with
     */
public class AlertBox{
    public static void display(String message, String title){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);

            Label label = new Label(message);
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox();
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        }
}
