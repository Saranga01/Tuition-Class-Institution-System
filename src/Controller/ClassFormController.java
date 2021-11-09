package Controller;

import DBConnection.DbConnection;
import Model.*;
import TM.ClassTM;
import TM.DayScheduleTM;
import TM.ScheduleTM;
import TM.StudentTM;
import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class ClassFormController {

    public AnchorPane RootUpdateDelete;
    public JFXComboBox comboScheduleType;
    public JFXTextArea txtCDesc;
    public JFXComboBox comboCGrade;
    public JFXComboBox comboCTeacherName;
    public JFXComboBox comboCType;
    public JFXTextField txtCTitle;
    public JFXTextField txtCFee;
    public JFXTextField txtCSubjectName;
    public AnchorPane paneClassId;
    public Label lblGenerateCID;
    public AnchorPane PaneScheduleMonthly;
    public AnchorPane PaneScheduleWeekly;
    public JFXComboBox comboSelectDay;
    public TableView<DayScheduleTM> tblDaySchedule;
    public TableColumn colmDay;
    public TableColumn colmTime;
    public JFXTextField txtDayScheduleTime;
    public ComboBox comboAmPm;
    public TableColumn colmScheduleDate;
    public TableColumn colmScheduleTime;
    public JFXDatePicker txtSelectDate;
    public JFXTextField txtDateScheduleTime;
    public TableView<DayScheduleTM> tblScheduleDate;
    public ComboBox comboAmPm1;
    public AnchorPane paneScheduleEdit;
    public Label lblClassDesc;
    public Label lblClassFee;
    public Label lblSubject;
    public Label lblClassTitle;
    public Label lblTeacherName;
    public Label lblGrade;
    public Label lblClassType;
    public Label lblClassId;
    public AnchorPane paneUpdateDelete;
    public TextField txtSearchClassId;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public TableColumn coloTime;
    public TableColumn colmDateAndDay;
    public TableView<DayScheduleTM> tblViewSchedule;
    public AnchorPane paneClassUpdateDetails;
    public JFXTextField txtClassTitle;
    public JFXTextField txtClassSubject;
    public JFXTextField txtClassFee;
    public JFXTextField txtClassShortDesc;
    public JFXComboBox comboClassType;
    public JFXComboBox comboClassTeacherName;
    public JFXComboBox comboClassGrade;
    public Label lblRegDate;
    public AnchorPane popupPane1;
    public AnchorPane Popup2pane1;
    public ImageView SuccessImg1;
    public ImageView wrongImg1;
    public Label lblPopupText1;
    public MenuItem menuitemAddNewSchedulePane;
    public TableView<ClassTM> tblCLasses;
    public TableColumn colmClassId;
    public TableColumn colmTeachId;
    public TableColumn colmSubject;
    public TableColumn colmBtnView;
    public AnchorPane PaneUpdateScheduleWeekly;
    public JFXTextField txtUpdateDayScheduleTime;
    public JFXComboBox comboUpdateSelectDay;
    public ComboBox comboUpdateAmPm;
    public AnchorPane PaneUpdateScheduleMonthly;
    public JFXTextField txtUpdateDateScheduleTime1;
    public ComboBox comboMonthlyUpdateAmPm;
    public JFXDatePicker txtDateUpdateSelectDate;
    public javafx.scene.control.ScrollPane ScrollPane;
    public TextField txtSearchClassAllID;
    public TableView<StudentTM> tblStudents;
    public TableColumn colmStudId;
    public TableColumn colmStudentName;
    public Label lblResult;
    public AnchorPane paneAllStud;
    String ScheduleType;
    String TeacherName;
    String Grade;
    String ClassType;
    String Day;
    String AmPm = "am";
    String AmPm1 = "am";
    Date date = new Date();
    ArrayList TeachersId;
    int i;
    Pattern CTitle = Pattern.compile("^[A-z.0-9/ ]{3,70}$");
    Pattern CPrice = Pattern.compile("^[1-9][0-9.]{1,}$");
    Pattern ScheduleTime = Pattern.compile("^[0-9]{2}[.][0-9]{2}$");




    {
        try {
            TeachersId = new TeacherManageClass().getTeachersId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void initialize() {
        paneClassId.setVisible(false);
        PaneScheduleMonthly.setVisible(false);
        PaneScheduleWeekly.setVisible(false);
        paneUpdateDelete.setVisible(false);
        paneScheduleEdit.setVisible(false);
        popupPane.setVisible(false);
        paneClassUpdateDetails.setVisible(false);
        popupPane1.setVisible(false);
        tblCLasses.setVisible(false);


        try {
            loadCombo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        comboScheduleType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Monthly")) {
                PaneScheduleMonthly.setVisible(true);
                PaneScheduleWeekly.setVisible(false);
            } else {
                PaneScheduleMonthly.setVisible(false);
                PaneScheduleWeekly.setVisible(true);
            }
            ScheduleType = (String) newValue;
        });
        comboSelectDay.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Day = (String) newValue;
        });
        comboAmPm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            AmPm = (String) newValue;
        });
        comboAmPm1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            AmPm1 = (String) newValue;
        });
        comboCTeacherName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TeacherName = (String) newValue;
        });
        comboCGrade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Grade = (String) newValue;
        });
        comboCType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ClassType = (String) newValue;
        });
        tblViewSchedule.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ClassReg classReg = new ClassMangeClass().search(lblClassId.getText());
                ArrayList<ScheduleTM> scheduleTM = classReg.getSchedule();
                i = (int) newValue;
                for (ScheduleTM Schedule : scheduleTM
                ) {
                    if (Schedule.getScheduleType().equals("Monthly")) {
                        if (i >= 0) {
                            PaneUpdateScheduleWeekly.setVisible(false);
                            PaneUpdateScheduleMonthly.setVisible(true);
                            new SlideInDown(PaneUpdateScheduleMonthly).play();
                            txtDateUpdateSelectDate.setValue(LocalDate.parse(schedule.get(i).getDay()));
                            txtUpdateDateScheduleTime1.setText(schedule.get(i).getTime());

                        } else {
                            return;
                        }
                    } else {
                        if (i >= 0) {
                            PaneUpdateScheduleMonthly.setVisible(false);
                            PaneUpdateScheduleWeekly.setVisible(true);
                            new SlideInDown(PaneUpdateScheduleWeekly).play();
                            comboUpdateSelectDay.setValue((schedule.get(i).getDay()));
                            txtUpdateDayScheduleTime.setText(schedule.get(i).getTime());
                        } else {
                            return;
                        }
                    }
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });


        //Set Day Schedule table Data
        colmDay.setStyle("-fx-alignment: CENTER;");
        colmTime.setStyle("-fx-alignment: CENTER;");
        colmDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colmTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        //Set Date Schedule table Data
        colmScheduleDate.setStyle("-fx-alignment: CENTER;");
        colmScheduleTime.setStyle("-fx-alignment: CENTER;");
        colmScheduleDate.setCellValueFactory(new PropertyValueFactory<>("day"));
        colmScheduleTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        //Set View Schedule table Data
        colmDateAndDay.setStyle("-fx-alignment: CENTER;");
        coloTime.setStyle("-fx-alignment: CENTER;");
        colmDateAndDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        coloTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        //Set AllClasses table Data
        colmClassId.setStyle("-fx-alignment: CENTER;");
        colmTeachId.setStyle("-fx-alignment: CENTER;");
        colmSubject.setStyle("-fx-alignment: CENTER;");
        colmBtnView.setStyle("-fx-alignment: CENTER;");
        colmClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colmTeachId.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colmSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colmBtnView.setCellValueFactory(new PropertyValueFactory<>("btn"));

        suggestClassId();

    }

    public void suggestClassId() {
        try {
            TextFields.bindAutoCompletion(txtSearchClassId, new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtSearchClassAllID, new StudentMangeClass().getCLassID());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadCombo() throws SQLException {

        comboCTeacherName.getItems().addAll(TeachersId);

        comboCGrade.getItems().addAll(
                "Grade 1",
                "Grade 2",
                "Grade 3",
                "Grade 4",
                "Grade 5",
                "Grade 6",
                "Grade 7",
                "Grade 8",
                "Grade 9",
                "Grade 10",
                "O/L",
                "A/L ",
                "Other"
        );
        comboCType.getItems().addAll("Theory Class", "Revision Class", "Practical Class");
        comboScheduleType.getItems().addAll("Monthly", "Weekly");
        comboSelectDay.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        comboUpdateSelectDay.getItems().addAll("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        comboAmPm.getItems().addAll("am", "pm");
        comboAmPm.getSelectionModel().selectFirst();
        comboAmPm1.getItems().addAll("am", "pm");
        comboAmPm1.getSelectionModel().selectFirst();

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
        SuccessImg1.setVisible(true);
        wrongImg1.setVisible(false);
        // new SlideInLeft(popupPane).play();
    }

    public void wrongUpdatePopup() {
        Popup2pane1.setStyle("-fx-background-color: #ff4d4d");
        popupPane1.setVisible(true);
        SuccessImg1.setVisible(false);
        wrongImg1.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        popupPane.setVisible(false);
        popupPane1.setVisible(false);

        if (mouseEvent.getTarget().equals(paneClassUpdateDetails)) {
            paneClassUpdateDetails.setVisible(true);
        } else {
            paneClassUpdateDetails.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(paneScheduleEdit)) {
            paneScheduleEdit.setVisible(true);
        } else {
            paneScheduleEdit.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(tblCLasses)) {
            tblCLasses.setVisible(true);
        } else {
            tblCLasses.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(PaneUpdateScheduleMonthly)) {
            PaneUpdateScheduleMonthly.setVisible(true);
            paneScheduleEdit.setVisible(true);
        } else {
            PaneUpdateScheduleMonthly.setVisible(false);
        }
        if (mouseEvent.getTarget().equals(PaneUpdateScheduleWeekly)) {
            PaneUpdateScheduleWeekly.setVisible(true);
            paneScheduleEdit.setVisible(true);
        } else {
            PaneUpdateScheduleWeekly.setVisible(false);
        }

        if (mouseEvent.getTarget().equals(paneAllStud)) {
            paneAllStud.setVisible(true);
        } else {
            paneAllStud.setVisible(false);
            txtSearchClassAllID.clear();
        }

    }

    public void clearRegForm() {
        paneClassId.setVisible(false);
        comboCTeacherName.getSelectionModel().clearSelection();
        comboCGrade.getSelectionModel().clearSelection();
        comboCType.getSelectionModel().clearSelection();
        txtCTitle.clear();
        txtCSubjectName.clear();
        txtCDesc.clear();
        txtCFee.clear();
        PaneScheduleWeekly.setVisible(false);
        PaneScheduleMonthly.setVisible(false);
    }

    public void btnGenerateClassIdOnAction(ActionEvent actionEvent) throws SQLException {
        paneClassId.setVisible(true);
        ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Class_Id From `Classes` ORDER BY Class_Id DESC LIMIT 1").executeQuery();
        if (sql.next()) {
            int tempId = Integer.parseInt(sql.getString(1).split("/")[1]);
            tempId = tempId + 1;
            if (tempId < 10) {
                lblGenerateCID.setText("CLS/00" + tempId);
            } else if (tempId < 100) {
                lblGenerateCID.setText("CLS/0" + tempId);
            } else {
                lblGenerateCID.setText("O" + tempId);
            }

        } else {
            lblGenerateCID.setText("CLS/001");
        }
    }


    public void btnScheduleCloseOnClicked(MouseEvent mouseEvent) {
        PaneScheduleMonthly.setVisible(false);
        PaneScheduleWeekly.setVisible(false);
    }


    ObservableList<DayScheduleTM> observableList = FXCollections.observableArrayList();

    public void btnDayScheduleOnMouseClicked(MouseEvent mouseEvent) {
        if (comboSelectDay.getSelectionModel().getSelectedIndex() >= 0 &
                new Validation().patternValidation(ScheduleTime, txtDayScheduleTime)) {
            observableList.add(
                    new DayScheduleTM(Day, txtDayScheduleTime.getText() + AmPm1)
            );
            tblDaySchedule.setItems(observableList);
        } else {
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }

    }


    ObservableList<DayScheduleTM> observableList1 = FXCollections.observableArrayList();

    public void btnSetScheduleDateOnMouseClicked(MouseEvent mouseEvent) {

        if (txtSelectDate.getValue() == null) {
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        } else {

            if (new Validation().patternValidation(ScheduleTime, txtDateScheduleTime)
            ) {
                observableList1.add(
                        new DayScheduleTM(String.valueOf(txtSelectDate.getValue()), txtDateScheduleTime.getText() + AmPm));
                tblScheduleDate.setItems(observableList1);
            } else {
                wrongPopup();
                lblPopupText.setText("Something Wrong...! please Check Details");
            }
        }

    }


    public void btnClassRegisterOnMouseClicked(MouseEvent mouseEvent) {

        if (paneClassId.isVisible() &
                comboCTeacherName.getSelectionModel().getSelectedIndex() >= 0 &
                comboCGrade.getSelectionModel().getSelectedIndex() >= 0 &
                comboCType.getSelectionModel().getSelectedIndex() >= 0 &
                new Validation().patternValidation(CTitle, txtCTitle) &
                new Validation().patternValidation(CPrice, txtCFee) &
                new Validation().patternValidation(CTitle, txtCSubjectName)

        ) {
            if (comboScheduleType.getSelectionModel().getSelectedItem().equals("Weekly")) {
                if (observableList.size() > 0
                ) {
                    classReg();
                    suggestClassId();
                } else {
                    wrongPopup();
                    lblPopupText.setText("Something Wrong...! please Check Details");

                }

            } else {
                if (observableList1.size() > 0
                ) {
                    classReg();
                } else {

                    wrongPopup();
                    lblPopupText.setText("Something Wrong...! please Check Details");
                }

            }

        } else {
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }

    }

    public void classReg() {
        ArrayList<ScheduleTM> scheduleAr = new ArrayList<>();
        if (ScheduleType.equals("Monthly")) {
            for (DayScheduleTM schedule : observableList1
            ) {
                scheduleAr.add(
                        new ScheduleTM(lblGenerateCID.getText(), ScheduleType, String.valueOf(schedule.getDay()), "Null", schedule.getTime())
                );
            }
        } else {
            for (DayScheduleTM schedule : observableList
            ) {
                scheduleAr.add(
                        new ScheduleTM(lblGenerateCID.getText(), ScheduleType, "Null", schedule.getDay(), schedule.getTime())
                );
            }
        }
        ClassReg classReg = new ClassReg(
                lblGenerateCID.getText(),
                TeacherName,
                txtCSubjectName.getText(),
                Double.parseDouble(txtCFee.getText()),
                Grade,
                txtCTitle.getText(),
                ClassType,
                txtCDesc.getText(),
                date,
                scheduleAr
        );

        if (new ClassMangeClass().register(classReg)) {
            lblPopupText.setText("Class Registration Successfully...!");
            scheduleAr.clear();
            successPopup();
            clearRegForm();

        } else {
            wrongPopup();
            lblPopupText.setText("Wrong...! Please Try Again");
        }
    }


    ObservableList<DayScheduleTM> schedule;

    public void btnSearchOnMouseClicked(MouseEvent mouseEvent) {
        search();


    }

    public void search() {

        ScrollPane.setVvalue(0.7);
        paneScheduleEdit.setVisible(false);
        paneClassUpdateDetails.setVisible(false);
        PaneUpdateScheduleWeekly.setVisible(false);
        PaneUpdateScheduleMonthly.setVisible(false);


        try {
            if(new ClassMangeClass().search(txtSearchClassId.getText())==null){
                wrongUpdatePopup();
                lblPopupText1.setText("Invalid Entered..!");
                ScrollPane.setVvalue(0);
            }else{

                ClassReg classReg = new ClassMangeClass().search(txtSearchClassId.getText());

                lblClassTitle.setText(classReg.getTitle());
                lblClassType.setText("(" + classReg.getClassType() + ")");
                lblClassId.setText(classReg.getClassId());
                lblTeacherName.setText(classReg.getTeacherId());
                lblGrade.setText(classReg.getGrade());
                lblSubject.setText(classReg.getSubjectName());
                lblClassFee.setText(String.valueOf(classReg.getClassFee()));
                lblClassDesc.setText(classReg.getShortDesc());
                lblRegDate.setText(String.valueOf(classReg.getRegDate()));

                ArrayList<ScheduleTM> scheduleAr = classReg.getSchedule();
                schedule = FXCollections.observableArrayList();

                for (ScheduleTM Schedule : scheduleAr

                ) {

                    if (Schedule.getDate().equals("Null")) {
                        schedule.add(
                                new DayScheduleTM(Schedule.getDay(), Schedule.getTime())

                        );
                    } else {
                        schedule.add(
                                new DayScheduleTM(Schedule.getDate(), Schedule.getTime())
                        );
                    }

                }
                paneUpdateDelete.setVisible(true);
                tblViewSchedule.setItems(schedule);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnFinalClassUpdateOnAction(ActionEvent actionEvent) throws ParseException {
        if (
                new Validation().patternValidation(CTitle, txtClassTitle) &
                new Validation().patternValidation(CPrice, txtClassFee) &
                new Validation().patternValidation(CTitle, txtClassSubject)

        ) {
            Update();
            search();

        } else {
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }

    }

    public void Update() throws ParseException {
        String sDate1 = lblRegDate.getText();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

        ArrayList arrayList = new ArrayList();
        ClassReg classReg = new ClassReg(
                lblClassId.getText(),
                String.valueOf(comboClassTeacherName.getSelectionModel().getSelectedItem()),
                txtClassSubject.getText(),
                Double.parseDouble(txtClassFee.getText()),
                String.valueOf(comboClassGrade.getSelectionModel().getSelectedItem()),
                txtClassTitle.getText(),
                String.valueOf(comboClassType.getSelectionModel().getSelectedItem()),
                txtClassShortDesc.getText(),
                date1,
                arrayList

        );

        try {

            if (new ClassMangeClass().update(classReg) > 0) {
                lblPopupText1.setText("Successfully Update Class Details");
                successUpdatePopup();
                search();
                paneClassUpdateDetails.setVisible(false);
            } else {
                wrongUpdatePopup();
                lblPopupText1.setText("Wrong...! Please try Again");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }

    public void UpdateClassDetailsPaneCloseMouseOnClicked(MouseEvent mouseEvent) {
        paneClassUpdateDetails.setVisible(false);
        paneScheduleEdit.setVisible(false);
    }

    public void menuitemUpdateClassPane(ActionEvent actionEvent) {
        paneScheduleEdit.setVisible(false);
        comboClassTeacherName.getItems().clear();
        comboClassType.getItems().clear();
        comboClassGrade.getItems().clear();
        paneScheduleEdit.setVisible(false);
        comboClassTeacherName.getItems().addAll(TeachersId);

        comboClassGrade.getItems().addAll(
                "Grade 1",
                "Grade 2",
                "Grade 3",
                "Grade 4",
                "Grade 5",
                "Grade 6",
                "Grade 7",
                "Grade 8",
                "Grade 9",
                "Grade 10",
                "O/L",
                "A/L ",
                "Other"
        );
        comboClassType.getItems().addAll("Theory Class", "Revision Class", "Practical Class");
        boolean b = true;
        while (b) {
            if (lblTeacherName.getText().equals(comboClassTeacherName.getSelectionModel().getSelectedItem())) {
                comboClassTeacherName.getSelectionModel().select(comboClassTeacherName.getSelectionModel().getSelectedIndex());
                b = false;

            } else {
                comboClassTeacherName.getSelectionModel().selectNext();
            }

            b = true;
            if (lblGrade.getText().equals(comboClassGrade.getSelectionModel().getSelectedItem())) {
                comboClassGrade.getSelectionModel().select(comboClassGrade.getSelectionModel().getSelectedIndex());
                b = false;
            } else {
                comboClassGrade.getSelectionModel().selectNext();
            }
            b = true;

            if (lblClassType.getText().equals("(" + comboClassType.getSelectionModel().getSelectedItem() + ")")) {
                comboClassType.getSelectionModel().select(comboClassType.getSelectionModel().getSelectedIndex());

                b = false;
            } else {
                comboClassType.getSelectionModel().selectNext();
            }

        }
        paneClassUpdateDetails.setVisible(true);
        txtClassSubject.setText(lblSubject.getText());
        txtClassTitle.setText(lblClassTitle.getText());
        txtClassFee.setText(lblClassFee.getText());
        txtClassShortDesc.setText(lblClassDesc.getText());

    }

    public void menuitemRemoveClassPane(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.WARNING,"If you click yes, this student will be removed from the system.Do you Want to delete this student ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            try {

                if(new ClassMangeClass().delete(lblClassId.getText())<1){
                    wrongUpdatePopup();
                    lblPopupText1.setText("Sorry Can,t Delete Student..!");
                }else{
                    successUpdatePopup();
                    lblPopupText1.setText("Student Delete Successfully..!");
                    paneUpdateDelete.setVisible(false);
                    paneScheduleEdit.setVisible(false);
                    paneClassUpdateDetails.setVisible(false);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }


    public void menuitemViewSchedulePane(ActionEvent actionEvent) {
        paneScheduleEdit.setVisible(true);
        paneClassUpdateDetails.setVisible(false);
        tblViewSchedule.getSelectionModel().clearSelection();
    }

    public void menuitemUpdateSchedulePane(ActionEvent actionEvent) {
    }

    public void menuitemRemoveSchedulePane(ActionEvent actionEvent) {
    }

    public void menuitemAddNewSchedulePane(ActionEvent actionEvent) {
    }


    public void btnViewAllClassesOnMouseClicked(MouseEvent mouseEvent) {
        tblCLasses.setVisible(true);
        try {
            ArrayList<ClassTM> classTM = new ClassMangeClass().All();

            ObservableList<ClassTM> obs = FXCollections.observableArrayList();
            for (ClassTM Class : classTM
            ) {
                obs.add(new ClassTM(
                        Class.getClassId(), Class.getTeacherId(), Class.getSubject(), Class.getBtn()
                ));
            }
            tblCLasses.setItems(obs);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void btnFinalMonthlyScheduleUpdateOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
        GetDT getDT = new GetDT(schedule.get(i).getDay(), "null", schedule.get(i).getTime());

        ScheduleTM scheduleTM = new ScheduleTM(lblClassId.getText(), "Monthly", String.valueOf(txtDateUpdateSelectDate.getValue()), "Null", txtUpdateDateScheduleTime1.getText());


        if(txtUpdateDateScheduleTime1.getText().equals("")){
            wrongUpdatePopup();
            lblPopupText1.setText("Empty Text Field..!");
        }else {
            if (new ClassMangeClass().updateSchedule(scheduleTM, getDT) > 0) {
                successUpdatePopup();
                lblPopupText1.setText("Successfully Update Schedule");
                btnSearchOnMouseClicked(mouseEvent);
                paneScheduleEdit.setVisible(true);
            } else {
                wrongUpdatePopup();
                lblPopupText1.setText("Wrong...! Please try Again");
            }
        }

    }

    public void btnFinalWeeklyScheduleUpdateOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
        GetDT getDT = new GetDT("null", schedule.get(i).getDay(), schedule.get(i).getTime());
        ScheduleTM scheduleTM = new ScheduleTM(lblClassId.getText(), "Weekly", "Null", String.valueOf(comboUpdateSelectDay.getValue()), txtUpdateDayScheduleTime.getText());

        if(txtUpdateDayScheduleTime.getText().equals("")){
            wrongUpdatePopup();
            lblPopupText1.setText("Empty Text Field..!");
        }else {
            if (new ClassMangeClass().updateSchedule(scheduleTM, getDT) > 0) {
                successUpdatePopup();
                lblPopupText1.setText("Successfully Update Schedule");
                btnSearchOnMouseClicked(mouseEvent);
                paneScheduleEdit.setVisible(true);

            } else {
                wrongUpdatePopup();
                lblPopupText1.setText("Wrong...! Please try Again");
            }
        }
    }

    public void btnSelectUpdateScheduleOnMouseClicked(MouseEvent mouseEvent) {

    }

    public void btnSearchAll(MouseEvent mouseEvent) {
                popupPane.setVisible(false);

                try {
                    ObservableList<StudentTM> studentTM = new ClassMangeClass().allStudents(txtSearchClassAllID.getText());
                    if (studentTM == null) {
                        wrongPopup();
                        lblPopupText.setText("Invalid Entered..!");

                    } else {
                        lblResult.setText("Search Result :" +studentTM.size());
                        paneAllStud.setVisible(true);
                        tblStudents.setItems(studentTM);
                        colmStudId.setStyle("-fx-alignment: CENTER;");
                        colmStudentName.setStyle("-fx-alignment: CENTER;");
                        colmStudId.setCellValueFactory(new PropertyValueFactory<>("stud_Id"));
                        colmStudentName.setCellValueFactory(new PropertyValueFactory<>("stud_Name"));
                    }

            } catch(
            SQLException throwables)

            {
                throwables.printStackTrace();
            }
    }

}
