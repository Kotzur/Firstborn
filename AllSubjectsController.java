package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AllSubjectsController extends MenuController implements Initializable {

     /* <-------------------------------->  VARIABLES <--------------------------------> */
    @FXML TableView<Subject> subjectTableView;
    @FXML TableColumn<Subject, String> nameColumn;
    @FXML TableColumn<Subject, String> teacherColumn;
    @FXML TableColumn<Subject, String> roomColumn;
    @FXML TableColumn<Subject, String> timeColumn;
     /* <-------------------------------->  VARIABLES <--------------------------------> */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("teacher"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("room"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("classTime"));

        subjectTableView.getItems().setAll(Database.subjectList);

    }
}
