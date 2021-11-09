package Controller;

import DBConnection.DbConnection;
import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

public class DashBoardController {
    public AnchorPane RootNavigation;
    public StackPane rootDashBord;
    public AnchorPane RootCenter;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane SettingPane;
    public AnchorPane ScrollAnchorPane;
    public Label fulltext;
    public AnchorPane headNavigation;
    public JFXButton btnHelpOnAction;
    public JFXButton btnSettingOnAction;
    public JFXButton btnReportsOnAction;
    public JFXButton btnEmployeeOnAction;
    public JFXButton btnAttendenceOnAction;
    public JFXButton btnPaymentOnAction;
    public JFXButton btnFeesOnAction;
    public JFXButton btnTeacherOnAction;
    public JFXButton btnStudentsOnAction;
    public JFXButton btnClassOnAction;
    public Pane topNavigation;
    public Pane NavigationSmall;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public AnchorPane popupPane1;
    public Label lblPopupText1;
    public AnchorPane Popup2pane1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    String themeName;


    public void initialize() {

        try {
            PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Theme");
            ResultSet resultSet = sql.executeQuery();

            if(resultSet.next()){
                themeName=resultSet.getString(1);
            }

            if(themeName.equals("naughtyBlue")){
                  theme("-fx-background-color: #130f40 ","-fx-background-color: #30336b ");


            }else if(themeName.equals("belize")){
                theme("-fx-background-color:  #574b90 ","-fx-background-color: #303952");

            }else if(themeName.equals("greenSea")){
                theme("-fx-background-color:   #006266","-fx-background-color: #0a3d62 ");
            }else{
                theme("-fx-background-color:    #2d3436 ","-fx-background-color:  #2c3e50 ");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        SettingPane.setVisible(false);
        StackPane stackPane;
        LoadTimeAndDate();
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/HomeForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void theme(String btnColor,String NavigationSmallColor){
        btnClassOnAction.setStyle(btnColor);
        btnStudentsOnAction.setStyle(btnColor);
        btnTeacherOnAction.setStyle(btnColor);
        btnFeesOnAction.setStyle(btnColor);
        btnPaymentOnAction.setStyle(btnColor);
        btnAttendenceOnAction.setStyle(btnColor);
        btnReportsOnAction.setStyle(btnColor);
        btnEmployeeOnAction.setStyle(btnColor);
        btnSettingOnAction.setStyle(btnColor);
        btnHelpOnAction.setStyle(btnColor);
        RootNavigation.setStyle(btnColor);
        NavigationSmall.setStyle(NavigationSmallColor);
        topNavigation.setStyle(btnColor);
        headNavigation.setStyle(btnColor);


    }

    public void LoadTimeAndDate(){
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
        Timeline time=new Timeline(new KeyFrame(Duration.ZERO,e->{
            LocalTime CurrentTime=LocalTime.now();
            if(CurrentTime.getHour()<10) {
                if(CurrentTime.getSecond()<10){
                    lblTime.setText("0" + CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" +"0"+ CurrentTime.getSecond());
                }else {
                    lblTime.setText("0" + CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" + CurrentTime.getSecond());
                }
            }else{
                if(CurrentTime.getSecond()<10){
                    lblTime.setText(CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" +"0"+ CurrentTime.getSecond());
                }else{
                    lblTime.setText(CurrentTime.getHour() + ":" + CurrentTime.getMinute() + ":" + CurrentTime.getSecond());
                }

            }
        }),

                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();


    }

    public void btnClassOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/ClassForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();


    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/HomeForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();

    }

    public void btnStudentsOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/StudentForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }

    public void btnTeacherOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/TeacherForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }


    public void btnFeesOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/ClassFeesForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/LogInForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) this.rootDashBord.getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setTitle("Home");
        new FadeIn(load).play();
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/PaymentForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();

    }

    public void btnAttendenceOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/AttendenceForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/EmployeeForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }

    public void btnReportsOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/ReportForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }

    public void btnSettingOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        this.RootCenter.getChildren().addAll(SettingPane);
        SettingPane.setVisible(true);

        new ZoomIn(SettingPane).play();
    }

    public void btnHelpOnAction(ActionEvent actionEvent) {
        RootCenter.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/HelpForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootCenter.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootCenter).play();
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        popupPane.setVisible(false);

    }

    public void btn(ActionEvent actionEvent) {
        RootNavigation.setStyle("-fx-background-color: blue;");

    }

    public void btnConcrete(ActionEvent actionEvent) {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement sql = connection.prepareStatement("Delete FROM Theme");
            sql.executeUpdate();

            PreparedStatement sql1 = connection.prepareStatement("INSERT INTO Theme VALUES(?)");
            sql1.setObject(1,"naughtyBlue");
            sql1.executeUpdate();
            theme("-fx-background-color: #130f40 ","-fx-background-color: #30336b ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void btnBelize(ActionEvent actionEvent) {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement sql = connection.prepareStatement("Delete FROM Theme");
            sql.executeUpdate();

            PreparedStatement sql1 = connection.prepareStatement("INSERT INTO Theme VALUES(?)");
            sql1.setObject(1,"belize");
            sql1.executeUpdate();
            theme("-fx-background-color:  #574b90 ","-fx-background-color:  #303952 ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnGreenSea(ActionEvent actionEvent) {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement sql = connection.prepareStatement("Delete FROM Theme");
            sql.executeUpdate();

            PreparedStatement sql1 = connection.prepareStatement("INSERT INTO Theme VALUES(?)");
            sql1.setObject(1,"greenSea");
            sql1.executeUpdate();
            theme("-fx-background-color:   #006266 ","-fx-background-color: #0a3d62 ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnDark(ActionEvent actionEvent) {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            PreparedStatement sql = connection.prepareStatement("Delete FROM Theme");
            sql.executeUpdate();

            PreparedStatement sql1 = connection.prepareStatement("INSERT INTO Theme VALUES(?)");
            sql1.setObject(1,"beaDark");
            sql1.executeUpdate();
            theme("-fx-background-color:    #2d3436 ","-fx-background-color:  #2c3e50 ");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void successPopup(){
        popupPane.setVisible(true);
        Popup2pane.setStyle("-fx-background-color:  #32ff7e");
        SuccessImg.setVisible(true);
        wrongImg.setVisible(false);
        // new SlideInLeft(popupPane).play();
    }
    public void wrongPopup(){
        Popup2pane.setStyle("-fx-background-color: #ff4d4d");
        popupPane.setVisible(true);
        SuccessImg.setVisible(false);
        wrongImg.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }


    public void btnDeleteAllStudents(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, all students will be removed from the system.Do you want to delete all students ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                int delete_from_students = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Students").executeUpdate();

                if(delete_from_students<1){
                        wrongPopup();
                        lblPopupText.setText("Sorry Can,t Delete All Students..!");
                }else{
                    successPopup();
                    lblPopupText.setText("Students Delete Successfully..!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    public void btnDeleteAllTeachers(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, all teachers will be removed from the system.Do you Want to delete all Teachers ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                int delete_from_teachers = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Teachers").executeUpdate();

                if(delete_from_teachers<1){
                    wrongPopup();
                    lblPopupText.setText("Sorry Can,t Delete All Teaches..!");
                }else{
                    successPopup();
                    lblPopupText.setText("Teachers Delete Successfully..!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void btnDeleteAllEmployees(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, all employees will be removed from the system.Do you Want to delete all employees ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                int delete_from_employee = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee").executeUpdate();

                if(delete_from_employee<1){
                    wrongPopup();
                    lblPopupText.setText("Sorry Can,t Delete All Employees..!");
                }else{
                    successPopup();
                    lblPopupText.setText("Teachers Delete Employees..!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void btnDeleteAllClasses(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, all classes will be removed from the system.Do you Want to delete all classes ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                int delete_from_classes = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Classes").executeUpdate();

                if(delete_from_classes<1){
                    wrongPopup();
                    lblPopupText.setText("Sorry Can,t Delete All Classes..!");
                }else{
                    successPopup();
                    lblPopupText.setText("Teachers Delete Classes.!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void btnFullReset(ActionEvent actionEvent) {

        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, all Details will be removed from the system.Do you Want to Reset ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            try {
                int delete_from_classes = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Classes").executeUpdate();
                int delete_from_Students = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Students").executeUpdate();
                int delete_from_Teachers = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Teachers").executeUpdate();
                int delete_from_Employee = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Employee").executeUpdate();
                int delete_from_Payments = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Payment").executeUpdate();
                int delete_from_Salary = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Salary").executeUpdate();
                int delete_from_Money= DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Money").executeUpdate();
                int delete_from_SpecialNote = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM SpecalNotice").executeUpdate();
                int delete_from_Attendence = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Attendence").executeUpdate();

                Connection connection=DbConnection.getInstance().getConnection();

                PreparedStatement sql = connection.prepareStatement("Delete FROM Theme");
                sql.executeUpdate();
                PreparedStatement sql1 = connection.prepareStatement("INSERT INTO Theme VALUES(?)");
                sql1.setObject(1,"beaDark");
                sql1.executeUpdate();

                theme("-fx-background-color:    #2d3436 ","-fx-background-color:  #2c3e50 ");
                    successPopup();
                    lblPopupText.setText("Reset Successfully.!");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
