package TM;

import javafx.scene.control.Button;

import java.awt.*;

public class StudentTM {
    private String stud_Id;
    private String stud_Name;
    private Button btn;

    public StudentTM() {
    }

    public StudentTM(String stud_Id, String stud_Name, Button btn) {
        this.stud_Id = stud_Id;
        this.stud_Name = stud_Name;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
