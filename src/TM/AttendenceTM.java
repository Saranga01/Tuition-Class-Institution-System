package TM;

import java.util.Date;

public class AttendenceTM {
    private String class_Id;
    private String stud_Id;
    private String stud_Name;
    private Date date;
    private String time;
    private String Status;

    public AttendenceTM() {
    }

    public AttendenceTM(String class_Id, String stud_Id, String stud_Name, Date date, String time, String status) {
        this.class_Id = class_Id;
        this.stud_Id = stud_Id;
        this.stud_Name = stud_Name;
        this.date = date;
        this.time = time;
        Status = status;
    }

    public String getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(String class_Id) {
        this.class_Id = class_Id;
    }

    public String getStud_Id() {
        return stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        this.stud_Id = stud_Id;
    }

    public String getStud_Name() {
        return stud_Name;
    }

    public void setStud_Name(String stud_Name) {
        this.stud_Name = stud_Name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
