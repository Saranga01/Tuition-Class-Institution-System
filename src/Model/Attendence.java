package Model;

import java.util.Date;

public class Attendence {
    private String class_id;
    private String stud_Id;
    private Date date;
    private String status;

    public Attendence() {
    }

    public Attendence(String class_id, String stud_Id, Date date, String status) {
        this.class_id = class_id;
        this.stud_Id = stud_Id;
        this.date = date;
        this.status = status;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getStud_Id() {
        return stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        this.stud_Id = stud_Id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
