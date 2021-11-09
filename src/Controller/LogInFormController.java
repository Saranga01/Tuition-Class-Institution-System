package Controller;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormController {
    public AnchorPane Root1;
    public Label Root2;
    public StackPane RootLoginPage;
    public JFXButton btnLogInOnAction;
    public JFXTextField txtUserID;
    public JFXPasswordField txtPassword;
    public Label lblInvalidPassword;
    public Label lblInvalidUserId;


    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
       String userId = "User01";
        String password = "Uni/321@@";
        if (txtUserID.getText().equals(userId)) {
            if (txtPassword.getText().equals(password)) {
                Parent load = FXMLLoader.load(getClass().getResource("../View/DashboardForm.fxml"));
                Scene scene = new Scene(load);
                Stage stage = (Stage) this.RootLoginPage.getScene().getWindow();

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                stage.setTitle("Home");
                new FadeIn(load).play();

           } else {
               txtPassword.setStyle("-jfx-unfocus-color: red");
               txtUserID.setStyle("-jfx-unfocus-color: #bfb7b7");

            }
        }else {

            txtUserID.setStyle("-jfx-unfocus-color: red");
        }
    }
}
