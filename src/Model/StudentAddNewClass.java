package Model;

public class StudentAddNewClass {
    private String Stud_Id;
    private String Class_Id;

    public StudentAddNewClass() {
    }

    public StudentAddNewClass(String stud_Id, String class_Id) {
        Stud_Id = stud_Id;
        Class_Id = class_Id;
    }

    public String getStud_Id() {
        return Stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        Stud_Id = stud_Id;
    }

    public String getClass_Id() {
        return Class_Id;
    }

    public void setClass_Id(String class_Id) {
        Class_Id = class_Id;
    }
}
