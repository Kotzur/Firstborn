package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class NewItemController extends MenuController implements Initializable {
    /* <-------------------------------->  ASSIGNMENT VARIABLES <--------------------------------> */
    @FXML TextField assignmentNameField;
    @FXML RadioButton homeworkRadio;
    @FXML RadioButton testRadio;
    @FXML RadioButton projectRadio;
    @FXML RadioButton otherRadio;
    @FXML ChoiceBox<String> classBox;
    @FXML ChoiceBox<String> priorityBox;
    @FXML RadioButton yesRadio;
    @FXML Button addNewAssignmentButton;
    /* <-------------------------------->  ASSIGNMENT VARIABLES <--------------------------------> */

    /* <-------------------------------->  ACTIVITY VARIABLES <--------------------------------> */
    @FXML Button newActivityButton;
    @FXML TextField activityNameField;
    @FXML TextField activitySupervisorField;
    @FXML TextField activityHoursField;
    @FXML RadioButton creativityRadio;
    @FXML RadioButton actionRadio;
    @FXML RadioButton serviceRadio;
    @FXML RadioButton shortRadio;
    @FXML RadioButton longRadio;
    @FXML CheckBox monBox;
    @FXML CheckBox tueBox;
    @FXML CheckBox wedBox;
    @FXML CheckBox thurBox;
    @FXML CheckBox friBox;
    @FXML CheckBox satBox;
    @FXML CheckBox sunBox;
       /* <-------------------------------->  ACTIVITY VARIABLES <--------------------------------> */

    /* <-------------------------------->  SUBJECT VARIABLES <--------------------------------> */
    @FXML Button createSubjectButton;
    @FXML TextField subjectNameField;
    @FXML TextField subjectTeacherField;
    @FXML TextField subjectRoomField;

    //Monday
    @FXML CheckBox monBox12;
    @FXML CheckBox monBox34;
    @FXML CheckBox monBox56;
    @FXML CheckBox monBox78;

    //Tuesday
    @FXML CheckBox tueBox12;
    @FXML CheckBox tueBox34;
    @FXML CheckBox tueBox56;
    @FXML CheckBox tueBox78;

    //Wednesday
    @FXML CheckBox wedBox12;
    @FXML CheckBox wedBox34;
    @FXML CheckBox wedBox56;
    @FXML CheckBox wedBox78;

    //Thursday
    @FXML CheckBox thurBox12;
    @FXML CheckBox thurBox34;
    @FXML CheckBox thurBox56;
    @FXML CheckBox thurBox78;

    //Friday
    @FXML CheckBox friBox12;
    @FXML CheckBox friBox34;
    @FXML CheckBox friBox56;
    @FXML CheckBox friBox78;

    private boolean[][] classTime = new boolean[5][4];

     /* <-------------------------------->  SUBJECT VARIABLES <--------------------------------> */



    public void handleButtonClick(ActionEvent event){

        //What happens when you click new assignment button
        if(event.getSource() == addNewAssignmentButton){
            String [] assParts = new String[5];

            //Name read
            assParts[0] = assignmentNameField.getText();

            //Subject read
            assParts[1] = classBox.getValue();

            //Type read
            if(homeworkRadio.isSelected()) assParts[2] = "H";
            else if(testRadio.isSelected()) assParts[2] = "T";
            else if(projectRadio.isSelected()) assParts[2] = "P";
            else if(otherRadio.isSelected()) assParts[2] = "O";

            //Priority read
            assParts[3] = priorityBox.getValue();

            //Complete read
            if(yesRadio.isSelected()) assParts[4] = "Complete";
            else assParts[4] = "Not Complete";

            /*If the load assignments method runs smoothly and there are no errors, imitate clicking the "All Assignments"
              button from the menu. Basically move on to the 'All Assignments' panel
             */
            if(Database.loadAssignment(assParts)){
                allAssignments.fire();
            }
            else{
                AlertBox.display("Something went wrong. The Assignment wasn't added.", "Error");
            }
        }

        //What happens when you click new activity button
        if(event.getSource()==newActivityButton){
            //Temporary variables
            boolean activityTime[] = {false, false, false, false, false, false, false};
            String [] actParts = new String[6];

            //Name read
            actParts[0] = activityNameField.getText();

            //Supervisor read
            actParts[1] = activitySupervisorField.getText();

            //CAS type read
            if(creativityRadio.isSelected()) actParts[2] = "C";
            else if(actionRadio.isSelected()) actParts[2] = "A";
            else actParts[2] = "S";

            //Time type
            if(!shortRadio.isSelected()) actParts[3] = "Long-term";
            else actParts[3] = "Short-term";

            //Hours read
            actParts[4] = activityHoursField.getText();

            //Activity time read
            if(monBox.isSelected()) activityTime[0]=true;
            if(tueBox.isSelected()) activityTime[1]=true;
            if(wedBox.isSelected()) activityTime[2]=true;
            if(thurBox.isSelected()) activityTime[3]=true;
            if(friBox.isSelected()) activityTime[4]=true;
            if(satBox.isSelected()) activityTime[5]=true;
            if(sunBox.isSelected()) activityTime[6]=true;

            String aT = "";
            for(int i = 0; i<activityTime.length; i++){
                if(activityTime[i]) aT += "1 ";
                else aT += "0 ";
            }
            actParts[5] = aT;

            //Creating the new object
            if(Database.loadActivity(actParts))
                allActivities.fire();
            else
                AlertBox.display("Something went wrong. The Activity wasn't created", "Error");
        }

        //what happens when you click new subject button
        if(event.getSource() == createSubjectButton) {
            //Temp array
            String [] subParts = new String[4];

            //Read name
            subParts[0] = subjectNameField.getText();

            //read teacher
            subParts[1] = subjectTeacherField.getText();

            //read room
            subParts[2] = subjectRoomField.getText();

            //read class time

            //Monday
            if(monBox12.isSelected()) classTime[0][0] = true;
            if(monBox34.isSelected()) classTime[0][1] = true;
            if(monBox56.isSelected()) classTime[0][2] = true;
            if(monBox78.isSelected()) classTime[0][3] = true;
            //Tuesday
            if(tueBox12.isSelected()) classTime[1][0] = true;
            if(tueBox34.isSelected()) classTime[1][1] = true;
            if(tueBox56.isSelected()) classTime[1][2] = true;
            if(tueBox78.isSelected()) classTime[1][3] = true;
            //Wednesday
            if(wedBox12.isSelected()) classTime[2][0] = true;
            if(wedBox34.isSelected()) classTime[2][1] = true;
            if(wedBox56.isSelected()) classTime[2][2] = true;
            if(wedBox78.isSelected()) classTime[2][3] = true;
            //Thursday
            if(thurBox12.isSelected()) classTime[3][0] = true;
            if(thurBox34.isSelected()) classTime[3][1] = true;
            if(thurBox56.isSelected()) classTime[3][2] = true;
            if(thurBox78.isSelected()) classTime[3][3] = true;
            //Friday
            if(friBox12.isSelected()) classTime[4][0] = true;
            if(friBox34.isSelected()) classTime[4][1] = true;
            if(friBox56.isSelected()) classTime[4][2] = true;
            if(friBox78.isSelected()) classTime[4][3] = true;

            String time = "";
            for (boolean[] x : classTime) {
                for (int j = 0; j < classTime[0].length; j++) {
                    if (x[j]) time += "1 ";
                    else time += "0 ";
                }
            }
            subParts[3] = time;

            if(Database.loadSubject(subParts)) allSubjects.fire();
            else AlertBox.display("Something went wrong. The Subject wasn't created. Try again", "Error");

        }

    }

    //Populates the Subject choice box with subjects if the subject list is not empty
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            try {
                if (addNewAssignmentButton.isVisible()) {
                    try {
                        if (!(Database.subjectList.isEmpty())) {
                            for (int i = 0; i < Database.subjectList.size(); i++) {
                                classBox.getItems().add(Database.subjectList.get(i).getName());
                            }
                        }
                    } catch (NullPointerException e) {
                        AlertBox.display("There are no Subjects available. Please, create a Subject first.", "No Subjects");
                    }
                }
            }catch(NullPointerException ignored){}
         }
    }
