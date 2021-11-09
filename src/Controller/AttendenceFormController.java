package Controller;

import Model.Attendence;
import TM.AttendenceTM;
import TM.StudentListTM;
import TM.TodayClassTM;
import animatefx.animation.*;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class AttendenceFormController {
    final DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
    public TableView<TodayClassTM> tblTodayClasses;
    public TableColumn colmClassID;
    public TableColumn colmClassTime;
    public AnchorPane paneClassStudentTbl;
    public TableView<StudentListTM> tblStudentList;
    public TableColumn colmStudId;
    public TableColumn ColmStudName;
    public TableColumn colmStatus;
    public AnchorPane paneToday;
    public AnchorPane paneSaveNotification;
    public TextField txtStudentID;
    public TableView<AttendenceTM> tblAttendence;
    public TableColumn colmAClassId;
    public TableColumn colmAStudentId;
    public TableColumn colmAStudentName;
    public TableColumn colmADate;
    public TableColumn colmATime;
    public TableColumn colmAStatus;
    public JFXDatePicker txtADate;
    public JFXTextField txtAClassId;
    public ScrollPane scrollPane;
    public AnchorPane paneDownColor;
    public AnchorPane paneDownErrorMsg;
    public Label lblDownErrorMsg;
    public AnchorPane paneDownColor1;
    public AnchorPane paneDownErrorMsg1;
    public Label lblDownErrorMsg1;

    int ClassIndex;
    int CStudentListIndex=-1;





    ObservableList<TodayClassTM> todayCls;
    public void initialize() {

        tblAttendence.setVisible(false);
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        try {
        todayCls=new AttendenceManageClass().getTodayClass(String.valueOf(f.format(date)),String.valueOf(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)));
            tblTodayClasses.setItems(todayCls);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colmClassID.setStyle("-fx-alignment: CENTER;");
        colmClassTime.setStyle("-fx-alignment: CENTER;");
        colmClassID.setCellValueFactory(new PropertyValueFactory<>("ClassId"));
        colmClassTime.setCellValueFactory(new PropertyValueFactory<>("Time"));


        colmStudId.setStyle("-fx-alignment: CENTER;");
        colmStatus.setStyle("-fx-alignment: CENTER;");
        ColmStudName.setStyle("-fx-alignment: CENTER;");
        colmStudId.setCellValueFactory(new PropertyValueFactory<>("stud_Id"));
        ColmStudName.setCellValueFactory(new PropertyValueFactory<>("stud_Name"));
        colmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblTodayClasses.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            ClassIndex = (int) newValue;
            paneSaveNotification.setVisible(true);
            paneClassStudentTbl.setVisible(false);
            new SlideInDown(paneSaveNotification).play();
            tblAttendence.setVisible(false);
            setBottomErrorMsgClose();
            setBottomErrorMsgClose1();

        });
        tblStudentList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            CStudentListIndex = (int) newValue;
            setBottomErrorMsgClose();
            setBottomErrorMsgClose1();

        });

        //set Attendence table

        colmAClassId.setStyle("-fx-alignment: CENTER;");
        colmAStudentId.setStyle("-fx-alignment: CENTER;");
        colmAStudentName.setStyle("-fx-alignment: CENTER;");
        colmADate.setStyle("-fx-alignment: CENTER;");
        colmATime.setStyle("-fx-alignment: CENTER;");
        colmAStatus.setStyle("-fx-alignment: CENTER;");

        colmAClassId.setCellValueFactory(new PropertyValueFactory<>("class_Id"));
        colmAStudentId.setCellValueFactory(new PropertyValueFactory<>("stud_Id"));
        colmAStudentName.setCellValueFactory(new PropertyValueFactory<>("stud_Name"));
        colmADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmATime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colmAStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        suggestPaymentFrom();

    }


    public void suggestPaymentFrom(){
        try {
            TextFields.bindAutoCompletion(txtAClassId,new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtStudentID,new Suggest().getStudId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public AttendenceFormController() throws ParseException {
    }
    public void setBottomErrorMsg(){
        paneDownErrorMsg.setVisible(true);
        paneDownColor.setVisible(true);

    }
    public void setBottomErrorMsgClose(){
        paneDownErrorMsg.setVisible(false);
        paneDownColor.setVisible(false);
    }
    public void setBottomErrorMsg1(){
        paneDownErrorMsg1.setVisible(true);
        paneDownColor1.setVisible(true);

    }
    public void setBottomErrorMsgClose1(){
        paneDownErrorMsg1.setVisible(false);
        paneDownColor1.setVisible(false);
    }


    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {

        if(mouseEvent.getTarget().equals(paneClassStudentTbl)){
            paneClassStudentTbl.setVisible(true);
        }else{
            paneClassStudentTbl.setVisible(false);
            paneToday.setOpacity(1);
            paneToday.setDisable(false);

        }
        if(mouseEvent.getTarget().equals(paneSaveNotification)){
            paneSaveNotification.setVisible(true);
        }else{
                new Pulse(paneSaveNotification).play();
        }

        if(mouseEvent.getTarget().equals(tblAttendence)){
            tblAttendence.setVisible(true);
        }else{
            tblAttendence.setVisible(false);
        }
        setBottomErrorMsgClose();
        setBottomErrorMsgClose1();

    }
    ObservableList<StudentListTM> studentListTM;
   public void btnOk(ActionEvent actionEvent) {
       try {
           new AttendenceManageClass().setStudentList(todayCls.get(ClassIndex).getClassId());


       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       try {
           studentListTM=new AttendenceManageClass().studentListObs(todayCls.get(ClassIndex).getClassId());
           paneClassStudentTbl.setVisible(true);
           paneToday.setOpacity(0.3);
           if(ClassIndex==-1){

           }else {
               tblStudentList.setItems(studentListTM);
               paneSaveNotification.setVisible(false);


           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }

   }

       public void btnCancel(ActionEvent actionEvent) {
        paneSaveNotification.setVisible(false);

    }
    Date date=new Date();
    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    public void btnPresentOnMouseClicked(MouseEvent mouseEvent) throws ParseException {
        setBottomErrorMsgClose();
        setBottomErrorMsgClose1();
        if(CStudentListIndex==-1){
            setBottomErrorMsg1();
            lblDownErrorMsg1.setText("Please Select Row..!");
        }else {

            Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse(f.format(date));
            Attendence attendence = new Attendence(
                    todayCls.get(ClassIndex).getClassId(),
                    studentListTM.get(CStudentListIndex).getStud_Id(),
                    date5,
                    "Present"
            );

            try {
                if (new AttendenceManageClass().updateAttendence(attendence)) {
                    studentListTM = new AttendenceManageClass().studentListObs(todayCls.get(ClassIndex).getClassId());
                    tblStudentList.setItems(studentListTM);
                } else {
                    System.out.println("wrong");

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void btnAbsentOnMouseClicked(MouseEvent mouseEvent) throws ParseException {
        setBottomErrorMsgClose();
        setBottomErrorMsgClose1();
        if (CStudentListIndex == -1) {
            setBottomErrorMsg1();
            lblDownErrorMsg1.setText("Please Select Row..!");
        } else {
            Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse(f.format(date));
            Attendence attendence = new Attendence(
                    todayCls.get(ClassIndex).getClassId(),
                    studentListTM.get(CStudentListIndex).getStud_Id(),
                    date5,
                    "Absent"
            );


            try {
                if (new AttendenceManageClass().updateAttendence(attendence)) {
                    studentListTM = new AttendenceManageClass().studentListObs(todayCls.get(ClassIndex).getClassId());
                    tblStudentList.setItems(studentListTM);
                } else {
                    System.out.println("wrong");

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void txtStudSearchOnKeyReleased(KeyEvent keyEvent) {

        try {

            studentListTM=new AttendenceManageClass().studentSearch(txtStudentID.getText(),todayCls.get(ClassIndex).getClassId(),f.format(date));
            tblStudentList.setItems(studentListTM);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public void btnCheckOnMouseClicked(MouseEvent mouseEvent) {
        setBottomErrorMsgClose();
        setBottomErrorMsgClose1();
        if(txtADate.getValue()==null){
            setBottomErrorMsg();
            lblDownErrorMsg.setText("Empty Text Field..!");
        }else {
            if (txtAClassId.getText().equals("")) {

                setBottomErrorMsg();
                lblDownErrorMsg.setText("Empty Text Field..!");

            } else {
                try {

                    ObservableList<AttendenceTM> attendence = new AttendenceManageClass().getAttendence(txtAClassId.getText(), String.valueOf(txtADate.getValue()));
                    if (attendence == null) {
                        setBottomErrorMsg();
                        lblDownErrorMsg.setText("Invalid Entered..!");
                    } else {
                        tblAttendence.setVisible(true);
                        tblAttendence.setItems(attendence);
                        scrollPane.setVvalue(1);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
