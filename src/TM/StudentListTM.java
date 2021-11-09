package TM;

public class StudentListTM {

    private String class_Id;
    private String stud_Id;
    private String stud_Name;
    private String status;

    public StudentListTM() {
    }

    public StudentListTM(String class_Id, String stud_Id, String stud_Name, String status) {
        this.setClass_Id(class_Id);
        this.stud_Id = stud_Id;
        this.stud_Name = stud_Name;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(String class_Id) {
        this.class_Id = class_Id;
    }
}
