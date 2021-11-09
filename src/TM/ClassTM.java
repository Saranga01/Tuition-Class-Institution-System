package TM;

import javafx.scene.control.Button;

public class ClassTM {
    private String classId;
    private String TeacherId;
    private String subject;
    private Button btn;

    public ClassTM(String classId, String teacherId, String subject, Button btn) {
        this.classId = classId;
        TeacherId = teacherId;
        this.subject = subject;
        this.btn = btn;
    }

    public ClassTM() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
