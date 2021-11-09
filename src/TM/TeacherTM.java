package TM;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;

public class TeacherTM extends RecursiveTreeObject<TeacherTM> {
    private String TID;
    private String TName;
    private JFXButton btnView;

    public TeacherTM(String TID, String TName, Button btnView) {
        this.TID = TID;
        this.TName = TName;
        this.btnView = (JFXButton) btnView;
    }

    public TeacherTM() {
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public Button getBtnView() {
        return btnView;
    }

    public void setBtnView(Button btnView) {
        this.btnView = (JFXButton) btnView;
    }
}
