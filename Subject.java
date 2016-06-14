package sample;

public class Subject {
    private String name; //class name
    private String teacher; //class teacher
    private String room; //class room
    private boolean[][] classTime = new boolean[5][4]; //periods when the class is [days][periods]
    private String[][] classTimeInWords =
            {{"Monday 1-2", "Monday 3-4", "Monday 5-6", "Monday 7-8"},
                    {"Tuesday 1-2", "Tuesday 3-4", "Tuesday 5-6", "Tuesday 7-8"},
                    {"Wednesday 1-2", "Wednesday 3-4", "Wednesday 5-6", "Wednesday 7-8"},
                    {"Thursday 1-2", "Thursday 3-4", "Thursday 5-6", "Thursday 7-8"},
                    {"Friday 1-2", "Friday 3-4", "Friday 5-6", "Friday 7-8"}};

    //constructors
    public Subject() {
        setName("No Subject Selected");
    }

    public Subject(String n, String r, String t, boolean cT[][]) {
        setName(n);
        setRoom(r);
        setTeacher(t);
        setClassTime(cT);
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassTime(boolean[][] classTime) {
        this.classTime = classTime;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getClassTime() {
        String periods = "";
        for (int i = 0; i < classTime.length; i++) {
            for (int j = 0; j < classTime[0].length; j++) {
                if (classTime[i][j]) {
                    periods += classTimeInWords[i][j] + " || ";
                }
            }
        }

        return periods;
    }

    //Returns a string of 1s and 0s (true and false) so that it's easier to read it
    public String getStringClassTime(){
        String time = "";
        for(int i = 0; i < classTime.length; i++){
            for(int j = 0; j < classTime[0].length; j++){
                if(classTime[i][j]) time += "1 ";
                else time += "0 ";
            }
        }
        return time;
    }
}