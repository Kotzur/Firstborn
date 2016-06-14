package sample;

import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {
    //Variables

    public static ArrayList<Subject> subjectList = new ArrayList<>();
    public static ArrayList<Assignment> assignmentList = new ArrayList<>();
    public static ArrayList<Activity> activityList = new ArrayList<>();
    public static String login = "";

    //This method takes care of loading and validating Strings into Assignment objects If there's a problem it returns a false value
    public static boolean loadAssignment(String [] assParts){

        //temporary Assignment variables
        String name = "";
        Subject subject = new Subject();
        char type;
        boolean priority;
        boolean complete;

        boolean everythingSmooth = true; //variable, indicating if something's wrong


        //Setting the name and making sure it's under 99 char and doesn't contain '#' (My delimeter)
        if(legalString(assParts[0], 99)) name = assParts[0];
        else{
            AlertBox.display("The name is incorrect. It can be up to 99 characters and cannot contain '#' symbol", "Name Error");
            everythingSmooth = false;
        }

        //Matches the subject and gives an error if not found (for some weird reason)
        String subjectName = assParts[1];
        int i = 0;
        boolean found = false;
        while(i < subjectList.size() && !found){
            if(subjectList.get(i).getName().equals(subjectName)) {
                subject = subjectList.get(i);
                found = true;
            }
            i++;
        }
        if(!found){
            AlertBox.display("The subject cannot be found", "Subject missing");
            everythingSmooth = false;
        }

        //Checks the type and makes sure it's either of the four
        type = assParts[2].charAt(0);
        if(!(type == 'H' || type == 'P' || type == 'T')) type = 'O';

        //Checks the priority
        priority = !assParts[3].equals("Normal");

        //Checks if complete
        complete = assParts[4].equals("Complete");

        //If everything went cool, a new assignment is added
        if(everythingSmooth) assignmentList.add(new Assignment(name, subject, type, priority, complete));

        //Returns the everythingSmooth variable to change the panel or display an error
        return everythingSmooth;
    }

    public static boolean loadActivity(String [] actParts){
        boolean everythingSmooth = true; //variable to check if everything is running smoothly

        //Temporary activity variables
        String name = "";
        String supervisor = "";
        char casType;
        boolean timeType = false;
        double hours = 0;
        boolean [] activityTime = {false, false, false, false, false, false, false };

        //name under 99 char, not containing '#'
        if(legalString(actParts[0], 99)) name = actParts[0];
        else{
            AlertBox.display("The name is incorrect. It can be up to 99 characters and cannot contain '#' symbol", "Name Error");
            everythingSmooth = false;
        }

        //supervisor under 99 char, not containing symbols other than letters, dot or dash
        if(legalString(actParts[1], 99)) supervisor = actParts[1];
        else{
            everythingSmooth = false;
            AlertBox.display("The supervisor is incorrect. Make sure your suupervisor only has letters, numers '-' or '.'", "Supervisor Error");
        }

        //casType 'C', 'A' or 'S'
        casType = actParts[2].charAt(0);

        //timeType short - false, long - true
        timeType = actParts[3].equals("Long-term");

        //hours
        try{
            hours = Double.parseDouble(actParts[4]);
        }
        catch(NumberFormatException e){
            everythingSmooth = false;
            AlertBox.display("Only numbers are accepted in the Hours box", "Hours Error");
        }

        //activity time read from the 'binary' output
        String [] times = actParts[5].split(" ");
        for(int i = 0; i < times.length; i++){
            activityTime[i] = !times[i].equals("0");
        }

        //Create an Activity object
        if(everythingSmooth) activityList.add(new Activity(name, supervisor, casType, timeType, hours, activityTime));

        return everythingSmooth;
    }

    public static boolean loadSubject(String [] subParts){
        //variable to check if everything is running smoothly
        boolean everythingSmooth = true;

        //temp variables
        String name = "";
        String teacher = "";
        String room = "";
        boolean [][] classTime = new boolean[5][4];

        //name checked if legal
        if(legalString(subParts[0], 99)) name = subParts[0];
        else{
            everythingSmooth = false;
            AlertBox.display("The name is incorrect. It can be up to 99 characters and contain letters, numbers or symbols '.', '-'","Name Error");
        }

        //teacher checked if legal
        if(legalString(subParts[1], 99)) teacher = subParts[1];
        else{
            everythingSmooth = false;
            AlertBox.display("The teacher is incorrect. The name can be up to 99 characters and contain letters, numbers or symbols '.', '-'","Teacher Error");
        }

        //room check if legal
        if(legalString(subParts[2], 5)) room = subParts[2];
        else{
            everythingSmooth = false;
            AlertBox.display("The room number is incorrect. It can be up to 5 characters and contain letters, numbers or symbols '.', '-'","Name Error");
        }

        //class time read and check
        //First the string from StringClassTime is gotten with split and sorted into the classTime array by incrementing j when i+1 divisable by 4
        String [] times = subParts[3].split(" ");
        int x = 0;
        for(int i = 0; i < classTime.length; i++){
            for(int j = 0; j < classTime[0].length; j++){
                if (times[x].equals("1")){
                    classTime[i][j] = true;
                }
                else if(times[x].equals("0")){
                    classTime[i][j] = false;
                }
                x++;
            }
        }

        //check if any classes are overlapping
        for (Subject otherSub : subjectList) {
            String[] temp = otherSub.getStringClassTime().split(" ");
            for (int i = 0; i < classTime.length; i++) {
                if (temp[i].equals("1") && times[i].equals("1")) {
                    everythingSmooth = false;
                    AlertBox.display("Your classes are overlaping. Change the time.", "Class Time Error");
                }
            }
        }

        if(everythingSmooth) subjectList.add(new Subject(name, teacher, room, classTime));

        return everythingSmooth;
    }

    /*method used to validate inputed strings. The program accepts names under 'max' characters containing letters, numbers, spaces
    dashes, and dots */
    private static boolean legalString(String word, int max){
        if(word.length() > max) return false;
        char [] chars = word.toCharArray();
        for(char c : chars){
            if(!(Character.isLetterOrDigit(c) || (c == '-') || (c == '.') || c == ' ')) return false;
        }
        return true;
    }

    public static void closeProgram(Stage stage) throws IOException {
        Path path;

        //Save the subjects
        path = Paths.get("files", login, "subjects.txt").toAbsolutePath();
        BufferedWriter writer = Files.newBufferedWriter(path);

        for (Subject subject : subjectList) {
            writer.write(subject.getName() + "#" + subject.getTeacher() + "#" + subject.getRoom() + "#" + subject.getStringClassTime());
            writer.newLine();
        }
        writer.close();

        //Save the assignments
        path = Paths.get("files", login, "assignments.txt").toAbsolutePath();
        writer = Files.newBufferedWriter(path);

        for (Assignment assignment : assignmentList) {
            writer.write(assignment.getName() + "#" + assignment.getSubject() + "#" + assignment.getType() + "#" + assignment.getPriority() + "#" + assignment.getComplete());
            writer.newLine();
        }
        writer.close();

        //Save the activities
        path = Paths.get("files", login, "activities.txt").toAbsolutePath();
        writer = Files.newBufferedWriter(path);

        for (Activity activity : activityList) {
            writer.write(activity.getName() + "#" + activity.getSupervisor() + "#" + activity.getCasType() + "#" + activity.getTimeType() + "#" + activity.getHours() + "#" + activity.getStringActivityTime());
            writer.newLine();
        }
        writer.close();

        stage.close();
    }
}
