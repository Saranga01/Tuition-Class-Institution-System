package Controller;

import DBConnection.DbConnection;
import Model.*;
import TM.StudentClassesTM;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class StudentFormController {
    public Label lblGenerateSID;
    public AnchorPane paneStudId;
    public JFXDatePicker txtSBirthday;
    public JFXComboBox combSClass;
    public JFXTextField txtSEmail;
    public JFXTextField txtSTName;
    public JFXTextField txtSName;
    public JFXComboBox combSGender;
    public JFXTextField txtSNic;
    public JFXTextField txtSAddress;
    public JFXTextField txtSMobile;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public AnchorPane PanViewClasses;
    public Label lblSRegDate;
    public Label lblSBirthday;
    public Label lblSMobile;
    public Label lblSAddress;
    public Label lblSEmail;
    public Label lblSNic;
    public Label lblSName;
    public Label lblSTName;
    public Label lblSGender;
    public Label lblSId;
    public AnchorPane PaneUpdateDelete;
    public TextField txtSearchStudentId;
    public AnchorPane AnchorPaneUp;
    public AnchorPane paneUpdateDetails;
    public JFXTextField txtUpdateSName;
    public JFXTextField txtUpdateSTName;
    public JFXTextField txtUpdateSEmail;
    public JFXTextField txtUpdateSAddress;
    public JFXTextField txtUpdateSNic;
    public JFXDatePicker txtUpdateSBirthday;
    public JFXTextField txtUpdateSMobile;
    public JFXComboBox comboUpdateSGender;
    public Label lblPopupText1;
    public ImageView wrongImg1;
    public ImageView SuccessImg1;
    public AnchorPane Popup2pane1;
    public AnchorPane popupPane1;
    public ImageView SuccessImg2;
    public ImageView wrongImg2;
    public TableView<StudentClassesTM> tblStudentClasses;
    public TableColumn colmClassID;
    public JFXComboBox comboAddNewClass;
    public AnchorPane paneAddClass;
    public AnchorPane paneViewCLassDetails;
    public Label lblClassId;
    public Label lblClassType;
    public Label lblGrade;
    public Label lblTeacherName;
    public Label lblClassTitle;
    public Label lblSubject;
    public Label lblClassFee;
    public Label lblClassDesc;
    public Label lblRegDate;
    public AnchorPane DownAnchorPane;
    public AnchorPane RootMainAnchorPane;
    public TableView tblAllStudent;
    public TableColumn colmStudId;
    public TableColumn colmStudName;
    public TableColumn colmMoreDetails;
    public javafx.scene.control.ScrollPane ScrollPane;
    Date date = new Date();
    String comboSGenderTxt;
    String comboClassIdText;
    String comboUpdateGender;
    String comboAddClass;
    String selectClassId;
    int i;
    Pattern SName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern SAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern SEmail=Pattern.compile("^[A-z0-9]{3,}[@][a-z]{2,}[.][a-z]{2,5}$");
    Pattern SNic=Pattern.compile("^[0-9]{6,20}[Vv]?$");
    Pattern SMobile=Pattern.compile("^[0-9]{9,10}$");

    public void initialize() {
        paneUpdateDetails.setVisible(false);
        popupPane.setVisible(false);
        popupPane1.setVisible(false);
        PaneUpdateDelete.setVisible(false);
        PanViewClasses.setVisible(false);
        paneViewCLassDetails.setVisible(false);
        tblAllStudent.setVisible(false);
        combSGender.getItems().addAll("male", "female");
        comboUpdateSGender.getItems().addAll("male", "female");
        combSGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            comboSGenderTxt = (String) newValue;
        });

        combSClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            comboClassIdText = (String) newValue;
        });

        comboUpdateSGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            comboUpdateGender = (String) newValue;
        });
        comboAddNewClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            comboAddClass = (String) newValue;
        });
        tblStudentClasses.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            i = (int) newValue;
        });
        suggestStud();


        //Set StudentClasses Table Data
        colmClassID.setCellValueFactory(new PropertyValueFactory<>("ClassId"));
        colmClassID.setStyle("-fx-alignment: CENTER;");

        //Set AllStudents Table Data
        colmStudId.setCellValueFactory(new PropertyValueFactory<>("stud_Id"));
        colmStudName.setCellValueFactory(new PropertyValueFactory<>("stud_Name"));
        colmMoreDetails.setCellValueFactory(new PropertyValueFactory<>("btn"));

        colmStudId.setStyle("-fx-alignment: CENTER;");
        colmStudName.setStyle("-fx-alignment: CENTER;");
        colmMoreDetails.setStyle("-fx-alignment: CENTER;");
        setClassID();
    }

    public void suggestStud(){
        try {
            TextFields.bindAutoCompletion(txtSearchStudentId, new Suggest().getStudId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        popupPane.setVisible(false);
        popupPane1.setVisible(false);

        if (mouseEvent.getTarget().equals(paneUpdateDetails)) {
            paneUpdateDetails.setVisible(true);
        } else {
            paneUpdateDetails.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(paneAddClass)) {
            paneAddClass.setVisible(true);
        } else {
            paneAddClass.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(tblAllStudent)) {
            tblAllStudent.setVisible(true);
        } else {
            tblAllStudent.setVisible(false);
        }

    }

    public void successPopup() {
        popupPane.setVisible(true);
        Popup2pane.setStyle("-fx-background-color:  #32ff7e");
        SuccessImg.setVisible(true);
        wrongImg.setVisible(false);
        // new SlideInLeft(popupPane).play();
    }

    public void wrongPopup() {
        Popup2pane.setStyle("-fx-background-color: #ff4d4d");
        popupPane.setVisible(true);
        SuccessImg.setVisible(false);
        wrongImg.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }

    public void successUpdatePopup() {
        popupPane1.setVisible(true);
        Popup2pane1.setStyle("-fx-background-color:  #32ff7e");
        SuccessImg2.setVisible(true);
        wrongImg2.setVisible(false);
        // new SlideInLeft(popupPane).play();
    }

    public void wrongUpdatePopup() {
        Popup2pane1.setStyle("-fx-background-color: #ff4d4d");
        popupPane1.setVisible(true);
        wrongImg2.setVisible(false);
        wrongImg2.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }


    public void setClassID() {
        try {
            ArrayList<String> ClassID = new StudentMangeClass().getCLassID();
            combSClass.getItems().addAll(ClassID);
            comboAddNewClass.getItems().addAll(ClassID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void textClear(){
        txtSMobile.clear();
        txtSEmail.clear();
        txtSAddress.clear();
        txtSNic.clear();
        txtSName.clear();
        txtSTName.clear();
        combSClass.getSelectionModel().clearSelection();
        combSGender.getSelectionModel().clearSelection();
        paneStudId.setVisible(false);
    }

    public void btnSRegister(MouseEvent mouseEvent) throws SQLException {

        if(     paneStudId.isVisible()&
                new Validation().patternValidation(SName,txtSName)&
                new Validation().patternValidation(SName,txtSTName)&
                new Validation().patternValidation(SNic,txtSNic)&
                new Validation().patternValidation(SAddress,txtSAddress)&
                new Validation().patternValidation(SEmail,txtSEmail)&
                new Validation().patternValidation(SMobile,txtSMobile)&
                (combSGender.getSelectionModel().getSelectedIndex()>=0)&
                (combSClass.getSelectionModel().getSelectedIndex()>=0)

       ){
           StudentReg studentReg = new StudentReg(
                   lblGenerateSID.getText(),
                   txtSName.getText(),
                   Integer.parseInt(txtSMobile.getText()),
                   txtSEmail.getText(),
                   String.valueOf(txtSBirthday.getValue()),
                   txtSAddress.getText(),
                   comboSGenderTxt,
                   txtSTName.getText(),
                   txtSNic.getText(),
                   date,
                   comboClassIdText
           );


           if (new StudentMangeClass().register(studentReg) > 0) {

               successPopup();
               lblPopupText.setText("Student Registration Successfully");
               textClear();
               suggestStud();
           } else {
               wrongPopup();
               lblPopupText.setText("Wrong...! Please Try Again");

           }

       }else{
           wrongPopup();
           lblPopupText.setText("Something Wrong...! please Check Details");
       }

    }

    public void btnGenerateStudentIdOnMouseClicked(MouseEvent mouseEvent) {

        paneStudId.setVisible(true);
        ResultSet sql = null;
        try {
            sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Stud_Id From `Students` ORDER BY Stud_Id DESC LIMIT 1").executeQuery();
            if (sql.next()) {
                int tempId = Integer.parseInt(sql.getString(1).split("/")[1]);
                tempId = tempId + 1;
                if (tempId < 10) {
                    lblGenerateSID.setText("STUD/00" + tempId);
                } else if (tempId < 100) {
                    lblGenerateSID.setText("STUD/0" + tempId);
                } else {
                    lblGenerateSID.setText("O" + tempId);
                }

            } else {
                lblGenerateSID.setText("STUD/001");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void btnSearchSIdOnMouseClicked(MouseEvent mouseEvent) {
        search();
        paneAddClass.setVisible(false);
        paneUpdateDetails.setVisible(false);
        PanViewClasses.setVisible(false);
    }

    public void search() {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        try {
            StudentReg studentReg = new StudentMangeClass().search(txtSearchStudentId.getText());
            if (txtSearchStudentId.getText().isEmpty()) {
                wrongUpdatePopup();
                lblPopupText1.setText("Empty Text Field..!");
            } else {

                if (studentReg == null) {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Invalid Entered..!");
                } else {
                    ScrollPane.setVvalue(0.7);
                    lblSName.setText(studentReg.getStud_Name());
                    lblSGender.setText(studentReg.getGender());
                    lblSId.setText(studentReg.getStud_Id());
                    lblSAddress.setText(studentReg.getAddress());
                    lblSEmail.setText(studentReg.getEmail());
                    lblSMobile.setText(String.valueOf(studentReg.getMobile_Num()));
                    lblSNic.setText(studentReg.getNic());
                    lblSTName.setText(studentReg.getTrustee_Name());
                    lblSBirthday.setText(String.valueOf(studentReg.getBirthday()));
                    lblSRegDate.setText(String.valueOf(studentReg.getReg_Date()));
                    PaneUpdateDelete.setVisible(true);

                }
            }
            } catch(SQLException throwables){
                throwables.printStackTrace();
            }

    }

    public void AnchorPaneUpOnMouseClicked(MouseEvent mouseEvent) {
        PaneUpdateDelete.setVisible(false);
        paneViewCLassDetails.setVisible(false);
        PanViewClasses.setVisible(false);
        txtSearchStudentId.clear();
        DownAnchorPane.setDisable(false);
        DownAnchorPane.setOpacity(1);
    }

    public void btnFinalUpdateOnAction(ActionEvent actionEvent) throws SQLException {

        if(
                new Validation().patternValidation(SName,txtUpdateSName)&
                new Validation().patternValidation(SName,txtUpdateSTName)&
                new Validation().patternValidation(SNic,txtUpdateSNic)&
                new Validation().patternValidation(SAddress,txtUpdateSAddress)&
                new Validation().patternValidation(SEmail,txtUpdateSEmail)&
                new Validation().patternValidation(SMobile,txtUpdateSMobile)

        ) {


            String sDate1 = lblSRegDate.getText();
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                StudentReg studentReg = new StudentReg(
                        lblSId.getText(),
                        txtUpdateSName.getText(),
                        Integer.parseInt(txtUpdateSMobile.getText()),
                        txtUpdateSEmail.getText(),
                        String.valueOf(txtUpdateSBirthday.getValue()),
                        txtUpdateSAddress.getText(),
                        comboUpdateGender,
                        txtUpdateSTName.getText(),
                        txtUpdateSNic.getText(),
                        date1,
                        "null"
                );
                if (new StudentMangeClass().update(studentReg) > 0) {
                    search();
                    successUpdatePopup();
                    lblPopupText1.setText("Student Details Update Successfully");
                } else {
                    wrongUpdatePopup();
                    ScrollPane.setVvalue(0.8);
                    lblPopupText1.setText("Wrong...! Please Try Again");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public void UpdateDetailsPaneCloseMouseOnClicked(MouseEvent mouseEvent) {
        paneUpdateDetails.setVisible(false);

    }

    public void MenuItemUpdateStudentOnAction(ActionEvent actionEvent) {
        txtUpdateSName.setText(lblSName.getText());
        txtUpdateSTName.setText(lblSTName.getText());
        txtUpdateSNic.setText(lblSNic.getText());
        txtUpdateSEmail.setText(lblSEmail.getText());
        txtUpdateSAddress.setText(lblSAddress.getText());
        txtUpdateSMobile.setText(lblSMobile.getText());
        txtUpdateSBirthday.setValue(LocalDate.parse(lblSBirthday.getText()));


        comboUpdateSGender.getSelectionModel().selectFirst();
        if (lblSGender.getText().equals(comboUpdateSGender.getSelectionModel().getSelectedItem())) {
            comboUpdateSGender.getSelectionModel().select(0);

        } else {
            comboUpdateSGender.getSelectionModel().select(1);
        }
        paneUpdateDetails.setVisible(true);
    }

    ObservableList<StudentClassesTM> Class = null;

    public void MenuitemViewCLassesOnAction(ActionEvent actionEvent) {
        try {
            Class = new StudentMangeClass().getStudentClasses(lblSId.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblStudentClasses.setItems(Class);
        PanViewClasses.setVisible(true);
        paneUpdateDetails.setVisible(false);


    }

    public void btnAddClassOnAction(ActionEvent actionEvent) {
        paneAddClass.setVisible(true);
    }

    public void btnRemoveClassOnAction(ActionEvent actionEvent) {
    }

    public void btnViewClassOnAction(ActionEvent actionEvent) {
         popupPane1.setVisible(false);
        if(tblStudentClasses.getSelectionModel().getSelectedIndex()>=0) {
            selectClassId = Class.get(i).getClassId();
            try {
                ClassReg classReg = new ClassMangeClass().search(selectClassId);
                lblClassTitle.setText(classReg.getTitle());
                lblClassType.setText(classReg.getClassType());
                lblClassDesc.setText(classReg.getShortDesc());
                lblClassFee.setText(String.valueOf(classReg.getClassFee()));
                lblClassId.setText(classReg.getClassId());
                lblTeacherName.setText(classReg.getTeacherId());
                lblSubject.setText(classReg.getSubjectName());
                lblRegDate.setText(String.valueOf(classReg.getRegDate()));
                lblGrade.setText(classReg.getGrade());
                paneViewCLassDetails.setVisible(true);
                new ZoomIn(paneViewCLassDetails).play();
                paneAddClass.setVisible(false);
                DownAnchorPane.setDisable(true);
                DownAnchorPane.setOpacity(0.5);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Please Select Row");
        }

    }

    public void btnAddNewClassOnAction(ActionEvent actionEvent) {


        if(comboAddNewClass.getSelectionModel().getSelectedIndex()<0){
            wrongUpdatePopup();
            lblPopupText1.setText("please Select Class");
        }else {
            StudentAddNewClass studentAddNewClass = new StudentAddNewClass(
                    lblSId.getText(),
                    comboAddClass

            );
            try {
                if (new StudentMangeClass().StudentAddNewClass(studentAddNewClass) > 0) {
                    successUpdatePopup();
                    lblPopupText1.setText("Successfully Added New Class to This Student");
                    tblStudentClasses.setItems(new StudentMangeClass().getStudentClasses(lblSId.getText()));
                    tblStudentClasses.refresh();
                    paneAddClass.setVisible(false);

                } else {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Wrong..! Please Try Again");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void ViewClassDetailsPaneCloseOnMouseClicked(MouseEvent mouseEvent) {
        paneViewCLassDetails.setVisible(false);
        DownAnchorPane.setDisable(false);
        DownAnchorPane.setOpacity(1);
    }

   public void DownAnchorPaneOnMouseClicked(MouseEvent mouseEvent) {
       /*if (mouseEvent.getTarget().equals(DownAnchorPane)) {
           paneViewCLassDetails.setVisible(true);
           PanViewClasses.setVisible(true);

       } else {
           paneViewCLassDetails.setVisible(false);
       }*/
   }

    public void btnAllStudentsOnMouseClicked(MouseEvent mouseEvent) {
        try {
            tblAllStudent.setItems(new StudentMangeClass().All());
            tblAllStudent.setVisible(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
