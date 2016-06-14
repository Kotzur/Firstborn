package sample;

public class Assignment {
    private String name; //assigement name
    private Subject subject; //for what subject is it
    private char type; //H - homework, T - test, P - project, O - other
    private boolean priority; //0 - low, 1 - high
    private boolean complete; //0 - not done, 1 - finished


    //constructors
    public Assignment(){
        name = "";
        subject = new Subject();
        type = '0';
        priority = false;
        complete = false;
    }

    public Assignment(String name, Subject subject, char type, boolean priority, boolean complete) {
        this.name = name;
        this.subject = subject;
        this.type = type;
        this.priority = priority;
        this.complete = complete;
    }


    //Getters and Setters. All getters return strings for convenience when initialising tables

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }



    public String getName() {
        return name;
    }

    public String getSubject() {
        if(subject!=null){
            return subject.getName();
        }
        return "No subject selected";
    }

    public String getType() {
        String typeString = "";
        switch(type){
            case 'H': typeString = "Homework"; break;
            case 'T': typeString = "Test"; break;
            case 'P': typeString = "Project"; break;
            case 'O': typeString = "Other"; break;
        }
        return typeString;
    }

    public String getPriority() {
        if(priority) return "High";
        else return "Normal";
    }

    public String getComplete() {
        if(complete) return "Complete";
        else return "Not Complete";
    }
}
