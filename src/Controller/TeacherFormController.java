package Controller;

import DBConnection.DbConnection;
import Model.TeacherReg;
import TM.TeacherTM;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class TeacherFormController  {
    public JFXTextField txtTAddress;
    public JFXTextField txtTSchoolName;
    public JFXTextField txtTMobileNumber;
    public JFXTextField txtTTeacherName;
    public JFXTextField txtEmail;
    public JFXTextField txtTNic;
    public JFXDatePicker txtTBirthday;
    public Label lblGenerateTID;
    public AnchorPane popupPane;
    public Label lblPopupText;
    public AnchorPane Popup2pane;
    public AnchorPane paneTeacherId;
    public TextField txtSearchId;
    public Label lblTId;
    public Label lblTSchoolName;
    public Label lblTName;
    public Label lblTNic;
    public Label lblTEmail;
    public Label lblTAddress;
    public Label lblTMobile;
    public Label lblTRegDate;
    public Label lblTBirthday;
    public AnchorPane paneMoreOption;
    public AnchorPane paneSearchDetails;
    public AnchorPane ScrollAnchorPane;
    public AnchorPane paneUpdateDetails;
    public JFXTextField txtUpdateTName;
    public JFXTextField txtUpdateTSchoolName;
    public JFXTextField txtUpdateTEmail;
    public JFXTextField txtUpdateTAddress;
    public JFXTextField txtUpdateTNic;
    public JFXDatePicker txtUpdateTBirthday;
    public JFXTextField txtUpdateTMobile;
    public AnchorPane popupPane1;
    public AnchorPane Popup2pane1;
    public Label lblPopupText1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public TableView<TeacherTM> tblTeacher;
    public TableColumn colmTeachId;
    public TableColumn colmTeachName;
    public TableColumn colmBtnView;
    public AnchorPane AnchorPaneDelete;
    public javafx.scene.control.ScrollPane ScrollPane;

    Pattern TName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern TAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern TEmail=Pattern.compile("^[A-z0-9]{3,}[@][a-z]{2,}[.][a-z]{2,5}$");
    Pattern TNic=Pattern.compile("^[0-9]{6,20}[Vv]?$");
    Pattern TMobile=Pattern.compile("^[0-9]{9,10}$");

    Date date=new Date();


    public void initialize(){
        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        paneTeacherId.setVisible(false);
        paneUpdateDetails.setVisible(false);
        paneSearchDetails.setVisible(false);
        tblTeacher.setVisible(false);

        try {
            setAllTeachersTable(new TeacherManageClass().All());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //set All Teachers Table colm
       colmTeachId .setStyle("-fx-alignment: CENTER;");
       colmTeachName .setStyle("-fx-alignment: CENTER;");
       colmBtnView.setStyle("-fx-alignment: CENTER;");


        colmTeachId.setCellValueFactory(new PropertyValueFactory<>("TID"));
        colmTeachName.setCellValueFactory(new PropertyValueFactory<>("TName"));
        colmBtnView.setCellValueFactory(new PropertyValueFactory<>("btnView"));

        suggestTeachId();


    }


    public void btnGenerateTeacherIDOnAction(ActionEvent actionEvent) throws SQLException {
        paneTeacherId.setVisible(true);
        ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Teacher_Id From `Teachers` ORDER BY Teacher_Id DESC LIMIT 1").executeQuery();
        if(sql.next()){
            int tempId=Integer.parseInt(sql.getString(1).split("/")[1]);
            tempId=tempId+1;
            if(tempId<10){
                lblGenerateTID.setText("TCR/00"+tempId);
            }else if(tempId<100){
                lblGenerateTID.setText("TCR/0"+tempId);
            }else {
                lblGenerateTID.setText("O"+tempId);
            }

        }else{
            lblGenerateTID.setText("TCR/001");
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
    public void successUpdatePopup(){
        popupPane1.setVisible(true);
        Popup2pane1.setStyle("-fx-background-color:  #32ff7e");
        successImg1.setVisible(true);
        wrongImg1.setVisible(false);
        // new SlideInLeft(popupPane).play();
    }

    public void wrongUpdatePopup(){
        Popup2pane1.setStyle("-fx-background-color: #ff4d4d");
        popupPane1.setVisible(true);
        successImg1.setVisible(false);
        wrongImg1.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }

    public void suggestTeachId(){
        try {
            TextFields.bindAutoCompletion(txtSearchId,new TeacherManageClass().getTeachersId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {

        if(mouseEvent.getTarget().equals(paneUpdateDetails)){
            paneUpdateDetails.setVisible(true);
        }else{
            paneUpdateDetails.setVisible(false);
            tblTeacher.setVisible(false);
        }

        popupPane.setVisible(false);
        popupPane1.setVisible(false);

    }


    public void ClearRegText(){
        txtTTeacherName.clear();
        txtEmail.clear();
        txtTAddress.clear();
        txtTNic.clear();
        txtTMobileNumber.clear();
        txtTSchoolName.clear();
    }
    public void btnTeacherRegisterOnAction(ActionEvent actionEvent) throws SQLException {
        if(     paneTeacherId.isVisible()&
                new Validation().patternValidation(TName,txtTTeacherName)&
                new Validation().patternValidation(TMobile,txtTMobileNumber)&
                new Validation().patternValidation(TEmail,txtEmail)&
                new Validation().patternValidation(TNic,txtTNic)&
                new Validation().patternValidation(TAddress,txtTAddress)

        ){
             regTeacher();
             
        }else{
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }
    }

    public void regTeacher(){
        TeacherReg teacherReg=new TeacherReg(
                lblGenerateTID.getText(),
                txtTTeacherName.getText(),
                Integer.parseInt(txtTMobileNumber.getText()),
                txtEmail.getText(),
                txtTSchoolName.getText(),
                txtTNic.getText(),
                txtTAddress.getText(),
                String.valueOf(txtTBirthday.getValue()),
                date
        );

        try {
            if(new TeacherManageClass().register(teacherReg)==1){
                lblPopupText.setText("Teacher registration is successful...!");
                successPopup();
                paneTeacherId.setVisible(false);
                ClearRegText();
                setAllTeachersTable(new TeacherManageClass().All());

            }else{
                lblPopupText.setText("Teacher registration is wrong...!");
                wrongPopup();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void btnSearchTeacherIdOnAction(ActionEvent actionEvent) {
       search();

    }

    public void search(){

        ScrollPane.setVvalue(0.7);
        paneUpdateDetails.setVisible(false);


        try {
            if (new TeacherManageClass().search(txtSearchId.getText()) == null) {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Invalid Entered...!");

            } else {
                TeacherReg teacherReg = new TeacherManageClass().search(txtSearchId.getText());

                lblTId.setText(teacherReg.getTeacherID());
                lblTName.setText(teacherReg.getTeacherName());
                lblTMobile.setText(String.valueOf(teacherReg.getTeacherMobile()));
                lblTAddress.setText(teacherReg.getTeacherAddress());
                lblTNic.setText(teacherReg.getTeacherNic());
                lblTSchoolName.setText(teacherReg.getTeacherSchoolName());
                lblTBirthday.setText(teacherReg.getTeacherBirthday());
                lblTEmail.setText(teacherReg.getTeacherEmail());
                lblTRegDate.setText(String.valueOf(teacherReg.getRegDate()));

            }
            } catch(SQLException throwables){
                throwables.printStackTrace();
            }

            if (paneSearchDetails.isVisible() == false) {
                paneSearchDetails.setVisible(true);

            } else {

            }

    }

    public void btnMoreOnAction(ActionEvent actionEvent) {
        if(paneMoreOption.isVisible()==false){
            paneMoreOption.setVisible(true);
        }else {
            paneMoreOption.setVisible(false);
        }


    }

    public void btnFinalUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        if(
                new Validation().patternValidation(TName,txtUpdateTName)&
                new Validation().patternValidation(TMobile,txtUpdateTMobile)&
                new Validation().patternValidation(TEmail,txtUpdateTEmail)&
                new Validation().patternValidation(TNic,txtUpdateTNic)&
                new Validation().patternValidation(TAddress,txtUpdateTAddress)

        ){
           Update();
           search();
        }
    }

    public void Update(){
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement sql= null;
        try {
            sql = connection.prepareStatement("SELECT * FROM Teachers WHERE Teacher_Id=?");
            sql.setObject(1,lblTId.getText());
            ResultSet resultSet = sql.executeQuery();

            if(resultSet.next()) {
                java.util.Date date = resultSet.getDate(9);
                TeacherReg teacherReg = new TeacherReg(
                        lblTId.getText(),
                        txtUpdateTName.getText(),
                        Integer.parseInt(txtUpdateTMobile.getText()),
                        txtUpdateTEmail.getText(),
                        txtUpdateTSchoolName.getText(),
                        txtUpdateTNic.getText(),
                        txtUpdateTAddress.getText(),
                        String.valueOf(txtUpdateTBirthday.getValue()),
                        date


                );
                if (new TeacherManageClass().update(teacherReg) == 1) {
                    lblPopupText1.setText("Successfully update teacher details");
                    successUpdatePopup();
                    paneUpdateDetails.setVisible(false);
                    setAllTeachersTable(new TeacherManageClass().All());

                } else {
                    lblPopupText1.setText("Update is wrong.please try again..!");
                    wrongUpdatePopup();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void UpdateDetailsPaneCloseMouseOnClicked(MouseEvent mouseEvent) {
        paneUpdateDetails.setVisible(false);
    }


    public void btnViewAllTeachersOnMouseClicked(MouseEvent mouseEvent) {
        tblTeacher.setVisible(true);



    }
    public void setAllTeachersTable(ArrayList<TeacherTM> teachers){

        ObservableList<TeacherTM> observableList= FXCollections.observableArrayList();

        teachers.forEach(e->{
            observableList.add(new TeacherTM(e.getTID(),e.getTName(),e.getBtnView()));

                });
        tblTeacher.setItems(observableList);
    }


    public void btnDeleteTeacherOnMouseClicked(MouseEvent mouseEvent) throws IOException {

    }

    public void menuitemupdateTeacherpane(ActionEvent actionEvent) {
        paneUpdateDetails.setVisible(true);
        txtUpdateTName.setText(lblTName.getText());
        txtUpdateTSchoolName.setText(lblTSchoolName.getText());
        txtUpdateTAddress.setText(lblTAddress.getText());
        txtUpdateTNic.setText(lblTNic.getText());
        txtUpdateTEmail.setText(lblTEmail.getText());
        txtUpdateTMobile.setText(lblTMobile.getText());
        txtUpdateTBirthday.setValue(LocalDate.parse(lblTBirthday.getText()));
    }

    public void menuitemRemoveTeacherPane(ActionEvent actionEvent) {
    }

    public void btnDeleteTeacher(ActionEvent actionEvent) {
    }
}

