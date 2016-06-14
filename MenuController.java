package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;

public class MenuController {

 /* <-------------------------------->  VARIABLES <--------------------------------> */

    private Parent root;
    private Stage stage;

    //FXML Menu tags
    @FXML private MenuBar mainMenuBar;
    @FXML private MenuItem newAssignment;
    @FXML  MenuItem allAssignments;
    @FXML private MenuItem newActivity;
    @FXML  MenuItem allActivities;
    @FXML private MenuItem newSubject;
    @FXML  MenuItem allSubjects;

    public MenuController() {
    }




 /* <-------------------------------->  VARIABLES <--------------------------------> */


    //Reads clicks on the menu and swtiches the scenes
    public void handleMenuItem(ActionEvent event) throws IOException {


        if(event.getSource() == newAssignment){
            root = FXMLLoader.load(getClass().getResource("fxml/newAssignment.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("Create a New Assignment");
        }

        if(event.getSource() == allAssignments){
            root = FXMLLoader.load(getClass().getResource("fxml/allAssignments.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("All Assignments");

        }

        if(event.getSource() == newActivity){
            root = FXMLLoader.load(getClass().getResource("fxml/newActivity.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("Create a New Activity");
        }

        if(event.getSource() == allActivities){
            root = FXMLLoader.load(getClass().getResource("fxml/allActivities.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("All Activities");
        }

        if(event.getSource() == newSubject){
            root = FXMLLoader.load(getClass().getResource("fxml/newSubject.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("Create a New Subject");
        }

        if(event.getSource() == allSubjects){
            root = FXMLLoader.load(getClass().getResource("fxml/allSubjects.fxml"));
            stage = (Stage) mainMenuBar.getScene().getWindow();
            stage.setTitle("All Subjects");
        }


        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
