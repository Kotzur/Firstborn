package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;

public class StartController {
    /* <-------------------------------->  VARIABLES <--------------------------------> */
    @FXML Button enterButton;
    Parent root;
    Stage stage;

    /* <-------------------------------->  VARIABLES <--------------------------------> */

    public void handleButtonClick(ActionEvent event) throws IOException {
        if(event.getSource() == enterButton){
            stage = (Stage) enterButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("fxml/allAssignments.fxml"));
        }
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
