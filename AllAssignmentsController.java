package sample;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
public class AllAssignmentsController extends MenuController implements Initializable {

    @FXML private TableView<Assignment> assignmentsTableView;
    @FXML private TableColumn<Assignment, String> nameColumn;
    @FXML private TableColumn<Assignment, String> subjectColumn;
    @FXML private TableColumn<Assignment, String> typeColumn;
    @FXML private TableColumn<Assignment, String> priorityColumn;
    @FXML private TableColumn<Assignment, String> completeColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        completeColumn.setCellValueFactory(new PropertyValueFactory<>("complete"));


        assignmentsTableView.getItems().setAll(Database.assignmentList);
    }
}
