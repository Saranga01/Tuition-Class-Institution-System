package TM;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class AllEmployeeTM {
    private String E_Id;
    private String E_Name;
    private String E_Address;
    private JFXButton btn;

    public AllEmployeeTM() {
    }

    public AllEmployeeTM(String e_Id, String e_Name, String e_Address, JFXButton btn) {
        E_Id = e_Id;
        E_Name = e_Name;
        E_Address = e_Address;
        this.btn = btn;
    }

    public String getE_Id() {
        return E_Id;
    }

    public void setE_Id(String e_Id) {
        E_Id = e_Id;
    }

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        E_Name = e_Name;
    }

    public String getE_Address() {
        return E_Address;
    }

    public void setE_Address(String e_Address) {
        E_Address = e_Address;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
