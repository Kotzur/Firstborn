package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class UsersController {
    /* <-------------------------------->  VARIABLES <--------------------------------> */
    @FXML
    Button loginButton;
    @FXML
    Button newUserButton;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;


    @FXML Button createNewUserButton;
    @FXML Button goBackButton;
    @FXML TextField newLoginField;
    @FXML TextField newPasswordField;
    @FXML TextField repeatField;

    String login;
    Path path;

    /* <-------------------------------->  VARIABLES <--------------------------------> */


    public void handleButtonClick(ActionEvent event) throws IOException {
        //----- Finding/Creating the users file -----
        path = Paths.get("files", "users.txt").toAbsolutePath();

        //Creates the directory 'files' if it doesn't exist yet
        try{
            Files.createDirectory(path.getParent());
        }catch(FileAlreadyExistsException ignored){}

        //Creates the file "users.txt" if it doesn't exist yet
        try{
            Files.createFile(path);
        }catch(FileAlreadyExistsException ignored){}

        BufferedReader userReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        //----- Finding/Creating the password file -----
        path = Paths.get("files", "password.txt").toAbsolutePath();

        //Creates the file "password.txt" if it doesn't exist yet
        try{
            Files.createFile(path);
        }catch (FileAlreadyExistsException ignored){}

        BufferedReader passReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

        //What happens when the login button is clicked
        if (event.getSource() == loginButton) {
            login = encrypt(loginField.getText());

            //a while loop, which searches through the users file in search for the entered login. When found, loop ends
            String line;
            boolean found = false;
            int lineNumber = -1;
            while (((line = userReader.readLine()) != null) && !found) {
                lineNumber++;
                if (line.equals(login)) found = true;
            }
            userReader.close();

            /*
            if the login is not found an error message is displayed
            if it is found, the password from the corresponding line from the password file is read and compared to the entered password
            if correct, the files are loaded
            if not, an error message is displayed
            */
            if (!found) {
                AlertBox.display("Such a user doesn't exist.\nTry again or create a new user.", "ERROR");
            } else {
                String password = passwordField.getText();
                String decryptedPassword = "";

                for (int i = 0; i <= lineNumber; i++) {
                    if (i != lineNumber) passReader.readLine();
                    else {
                        decryptedPassword = decrypt(passReader.readLine());
                    }
                }

                //Passes an encrypted login and loads files
                if (decryptedPassword.equals(password)){
                    Database.login = login;
                    loadFiles();
                }
                else {
                    AlertBox.display("Your login or password are incorrect.\nTry again or create a new user.", "ERROR");
                }
            }
        }

        //newUserButton clicked - Move on to the registration formula
        if(event.getSource() == newUserButton){
            Parent root = FXMLLoader.load(getClass().getResource("fxml/newUser.fxml"));
            Stage stage = (Stage) newUserButton.getScene().getWindow();
            stage.setTitle("Create New User");
            switchScenes(root, stage);
        }

        //goBackButton clicked - go back to the Log In screen
        if(event.getSource() == goBackButton){
            Parent root = FXMLLoader.load(getClass().getResource("fxml/loginForm.fxml"));
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            switchScenes(root, stage);
        }

        //When Create New User button is clicked
        if(event.getSource() == createNewUserButton){
            //Reads the fields
            login = newLoginField.getText();
            String password = newPasswordField.getText();
            String repeat = repeatField.getText();

            //Login cannot be bigger than 99 characters and password has to be 8-99 characterxs
            if((login.length() < 100) && (password.length() > 7) && (password.length() < 100)){

                //A while loop, which searches for the login in the users file
                String line = null;
                boolean found = false;
                int lineNumber = -1;
                while (((line = userReader.readLine()) != null) && !found) {
                    lineNumber++;
                    if (line.equals(encrypt(login))) found = true;
                }
                userReader.close();

                if(!found) {
                    if (password.equals(repeat)) {
                        //save encrypted login
                        path = Paths.get("files", "users.txt").toAbsolutePath();
                        BufferedWriter loginWriter = Files.newBufferedWriter(path);
                        loginWriter.write(encrypt(login));
                        loginWriter.newLine();

                        //saved encrypted password
                        path = Paths.get("files", "password.txt").toAbsolutePath();
                        BufferedWriter passwordWriter = Files.newBufferedWriter(path);
                        passwordWriter.write(encrypt(password));
                        passwordWriter.newLine();

                        loginWriter.close();
                        passwordWriter.close();

                        //Create the user's directory (under his encrypted login) and add the subjects file
                        path = Paths.get("files", encrypt(login), "subjects.txt").toAbsolutePath();
                        Files.createDirectory(path.getParent());
                        Files.createFile(path);

                        //Add the assignments file
                        path = Paths.get("files", encrypt(login), "assignments.txt").toAbsolutePath();
                        Files.createFile(path);

                        //Add the activities file
                        path = Paths.get("files", encrypt(login), "activities.txt").toAbsolutePath();
                        Files.createFile(path);

                        Parent root = FXMLLoader.load(getClass().getResource("fxml/loginForm.fxml"));
                        Stage stage = (Stage) createNewUserButton.getScene().getWindow();
                        stage.setTitle("Welcome");
                        switchScenes(root, stage);
                    } else AlertBox.display("Your passwords don't match", "Wrong password");
                } else AlertBox.display("This user exsists already. Try a different login","Wrong login");
            }
            else AlertBox.display("Something's wrong. Remember that your login cannot be bigger than 99 characters and password has to be 8-99 characterxs","Wrong input");
        }
    }

    //encryption method, shifting even characters by 4 right and odd characters by 4 left
    private String encrypt(String text) {
        char letter;
        String encryptedText = "";
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (letter % 2 == 0) letter += 4;
            else letter -= 4;

            encryptedText += letter;
        }

        return encryptedText;
    }

    //decryption method, doing the reverse
    private String decrypt(String text) {
        char letter;
        String decryptedText = "";
        for (int i = 0; i < text.length(); i++) {
            letter = text.charAt(i);
            if (letter % 2 == 0) letter -= 4;
            else letter += 4;

            decryptedText += letter;
        }

        return decryptedText;
    }


    /*Receives an encrypted login to know which folder to choose the files from
      This method reads files in the user's folder. In the files, each line is a new element of the type in the text name.
      As the method reads the lines, it splits the text with the # delimeter, puts them in an array and sends them to the database
      to formulate the objects with the load-Object-(String [] arrayWithParts) method.
    */
    private void loadFiles() throws IOException {
        path = Paths.get("files", login, "subjects.txt").toAbsolutePath();
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String line;
        boolean fine = true;

        line = null;
        String [] subParts;
        while((line = reader.readLine()) != null){
            subParts = line.split("#");
            if(!(Database.loadSubject(subParts))) fine = false;
        }
        reader.close();

        path = Paths.get("files", login, "assignments.txt").toAbsolutePath();
        reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        line = null;
        String[] assParts;
        while((line = reader.readLine()) != null){
            assParts = line.split("#");
            Database.loadAssignment(assParts);
        }
        reader.close();

        path = Paths.get("files", login, "activities.txt").toAbsolutePath();
        reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        line = null;
        String [] actParts;
        while((line = reader.readLine()) != null){
            actParts = line.split("#");
            Database.loadActivity(actParts);
        }
        reader.close();

        if(!fine) AlertBox.display("Not all of your Subjects, Assignments or Activities were created", "Something went wrong");

        Parent root = FXMLLoader.load(getClass().getResource("fxml/start.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setTitle("Welcome");
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void switchScenes(Parent root, Stage stage){
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
