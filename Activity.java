package sample;

public class Activity {
    /* <-------------------------------->  VARIABLES <--------------------------------> */

    private String name; //activity name
    private String supervisor; //activity supervisor
    private char casType; //C - creativity, A - action, S - service
    private boolean timeType; //0 - short term, 1 - long term
    private double hours; //number of hours
    private boolean[] activityTime = new boolean[7]; //activity time in the week - each field corresponds to a day

    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    /* <-------------------------------->  VARIABLES <--------------------------------> */


    //constructors
    public Activity(){
        name = "";
        supervisor = "";
        casType = 'C';
        timeType = false;
        hours = 0;
        activityTime = new boolean [] {false, false, false, false, false, false, false};
    }

    public Activity(String name, String supervisor, char casType, boolean timeType, double hours, boolean[] activityTime){
        this.name = name;
        this.supervisor = supervisor;
        this.casType = casType;
        this.timeType = timeType;
        this.hours = hours;
        this.activityTime = activityTime;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSupervisor(String supervisor){
        this.supervisor = supervisor;
    }

    public void setCasType(char casType) {
        this.casType = casType;
    }

    public void setTimeType(boolean timeType) {
        this.timeType = timeType;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setActivityTime(boolean[] activityTime){
        this.activityTime = activityTime;
    }


    //getters
    public String getName() {

        return name;
    }

    public String getSupervisor(){
        return supervisor;
    }
    public String getCasType() {
        if(casType == 'C') return "Creativity";
        if(casType == 'A') return "Action";
        else return "Service";
    }

    public String getTimeType() {
        String timeText="Short-term";
        if(timeType) timeText="Long-term";
        return timeText;
    }

    public String getHours() {
        return Double.toString(hours);
    }

    public String getActivityTime(){
        String aT = "";
        for(int i = 0; i<activityTime.length; i++) {
            if(activityTime[i]) aT += " " + days[i] + " ";
        }
        return aT;
    }

    //This method returns the time array in the form of 0s and 1s for convenience when reading the time from a text file
    public String getStringActivityTime(){
        String aT = "";
        for(int i = 0; i<activityTime.length; i++){
            if(activityTime[1]) aT += "1 ";
            else aT += "0 ";
        }
        return aT;
    }
}
