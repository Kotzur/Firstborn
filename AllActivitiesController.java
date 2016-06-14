package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AllActivitiesController extends MenuController implements Initializable{
     /* <-------------------------------->  VARIABLES <--------------------------------> */
    @FXML
     TableColumn<Activity, String> nameColumn;
    @FXML
    TableColumn<Activity, String> supervisorColumn;
    @FXML
    TableColumn<Activity, String> typeColumn;
    @FXML
    TableColumn<Activity, String> durationColumn;
    @FXML
    TableColumn<Activity, String> hoursColumn;
    @FXML
    TableColumn<Activity, String> timeColumn;

    @FXML
    TableView<Activity> activityTableView;
 /* <-------------------------------->  VARIABLES <--------------------------------> */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        supervisorColumn.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("casType"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeType"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("activityTime"));

        activityTableView.getItems().setAll(Database.activityList);
    }
}
