package Controller;

import DBConnection.DbConnection;
import Model.ClassFee;
import Model.StudentReg;
import TM.ClassHomeTM;
import TM.TodayClassTM;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class HomeFormController {
    final DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
    public AnchorPane RootMain;
    public BarChart barChart;
    public AnchorPane RootPieChart;
    public JFXListView HomeSpecialNoticeList;
    public PieChart PieChartPayments;
    public PieChart PieChartStudentGender;
    public Label lblSCount;
    public Label lblTCount;
    public Label lblCCount;
    public TableView<ClassHomeTM> tblHomeClassDate;
    public TableColumn colmClassId;
    public TableColumn colmTeacherId;
    public TableColumn ColmTime;
    public JFXButton btnTomorrow;
    public JFXButton btnToday;
    public JFXButton btnYesterday;
    public JFXTextArea txtSpecialNotice;
    public JFXTextField txtFClassId;
    public JFXTextField txtFStudId;
    public JFXComboBox combFMonth;
    public JFXTextField txtFReleventYear;
    public AnchorPane panePayForm;
    public Label lblFeeID;
    public Label lblFClassId;
    public Label lblFStudId;
    public Label lblFMonth;
    public Label lblFCost;
    public AnchorPane panePayClassFeeback;
    public Label lblNotification;
    public TextField txtSearchStudentIdHome;
    public AnchorPane PaneUpdateDelete;
    public Label lblSId;
    public Label lblSGender;
    public Label lblSTName;
    public Label lblSName;
    public Label lblSNic;
    public Label lblSEmail;
    public Label lblSAddress;
    public Label lblSMobile;
    public Label lblSBirthday;
    public Label lblSRegDate;
    String month;
    LocalTime CurrentTime=LocalTime.now();
    String Time;


    int year = Calendar.getInstance().get(Calendar.YEAR);
    Pattern Ids = Pattern.compile("^[A-z]{3,4}[/][0-9]{1,}$");
    Pattern Year = Pattern.compile("^[0-9]{4,}$");
    Pattern Co = Pattern.compile("^[0-9]{1,3}$");


    public void initialize() throws SQLException {
        CategoryAxis xAxis=new CategoryAxis();
        NumberAxis yAxis=new NumberAxis();
        setNote();
        suggestFeeFrom();
        txtFReleventYear.setText(String.valueOf(year));


        combFMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

        combFMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
        });
        try {

            if(new HomeMangeClass().getStudentCount()<10){
                lblSCount.setText("0"+new HomeMangeClass().getStudentCount());
            }else{
                lblSCount.setText(String.valueOf(new HomeMangeClass().getStudentCount()));
            }

            if(new HomeMangeClass().getTeacherCount()<10){
                lblTCount.setText("0"+new HomeMangeClass().getTeacherCount());
            }else{
                lblTCount.setText(String.valueOf(new HomeMangeClass().getTeacherCount()));
            }

            if(new HomeMangeClass().getClassCount()<10){
                lblCCount.setText("0"+new HomeMangeClass().getClassCount());
            }else{
                lblCCount.setText(String.valueOf(new HomeMangeClass().getClassCount()));
            }


            int sReg=new HomeMangeClass().thisMonthSReg();
            int tReg=new HomeMangeClass().getThisMonthTReg();
            int cReg=new HomeMangeClass().getThisMonthCReg();
            int eReg=new HomeMangeClass().getThisMonthEReg();

            XYChart.Series data=new XYChart.Series();
            XYChart.Series data1=new XYChart.Series();
            XYChart.Series data2=new XYChart.Series();
            XYChart.Series data3=new XYChart.Series();


            data.getData().add(new XYChart.Data("Student",sReg));
            data1.getData().add(new XYChart.Data("Teachers",tReg));
            data2.getData().add(new XYChart.Data("Classes",cReg));
            data3.getData().add(new XYChart.Data("Employees",eReg));

            barChart.getData().addAll(data,data1,data2,data3);

            barChart= new BarChart(xAxis,yAxis);
            barChart.setTitle("This month Registration");

            ArrayList<Integer> GenderAndPaid=new HomeMangeClass().getGenderCAndThisMPaid("male");
            ObservableList<PieChart.Data> pie= FXCollections.observableArrayList(
                    new PieChart.Data("Paid",GenderAndPaid.get(2)),
                    new PieChart.Data("Not Paid",GenderAndPaid.get(3))

            );

            PieChartPayments.setData(pie);
            ObservableList<PieChart.Data> pie1= FXCollections.observableArrayList(
                    new PieChart.Data("Male",GenderAndPaid.get(0)),
                    new PieChart.Data("Female",GenderAndPaid.get(1))

            );

            PieChartStudentGender.setData(pie1);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        colmClassId.setStyle("-fx-alignment: CENTER;");
        colmTeacherId.setStyle("-fx-alignment: CENTER;");
        ColmTime.setStyle("-fx-alignment: CENTER;");
        colmClassId.setCellValueFactory(new PropertyValueFactory<>("ClassId"));
        colmTeacherId.setCellValueFactory(new PropertyValueFactory<>("TeacherId"));
        ColmTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        loadTodayTbl();


    }
    Date date=new Date();
    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);
    LocalDate tomorrow = today.plusDays(1);

    public void btnYestaday(MouseEvent mouseEvent) {
        try {
            tblHomeClassDate.setItems(new HomeMangeClass().getTodayClass(String.valueOf(yesterday),getDay("pre")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void suggestFeeFrom(){
        try {
            TextFields.bindAutoCompletion(txtFStudId,new Suggest().getStudId());
            TextFields.bindAutoCompletion(txtFClassId,new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtSearchStudentIdHome,new Suggest().getStudId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnToday(MouseEvent mouseEvent) {
       loadTodayTbl();
    }

    public void loadTodayTbl(){
        try {
            tblHomeClassDate.setItems(new HomeMangeClass().getTodayClass(String.valueOf(f.format(date)),String.valueOf(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US))));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void btnTomorrow(MouseEvent mouseEvent) {
        try {
            tblHomeClassDate.setItems(new HomeMangeClass().getTodayClass(String.valueOf(tomorrow),getDay("pos")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String getDay(String day){
        String[] Day = new String[7];
        Day[0] = "Sunday";
        Day[1] = "Monday";
        Day[2] = "Tuesday";
        Day[3] = "Wednesday";
        Day[4] = "Thursday";
        Day[5] = "Friday";
        Day[6] = "Saturday";

        if(day.equals("pre")) {

            for (int i = 0; i < Day.length; i++) {
                if (String.valueOf(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)).equals(Day[i])) {
                    if (dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US).equals(Day[0])) {
                        return Day[i + 6];
                    } else {
                        return Day[i - 1];
                    }
                }
            }
            return "null";

        }else{
            for (int i = 0; i < Day.length; i++) {
                if (String.valueOf(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)).equals(Day[i])) {
                    if(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US).equals(Day[6])){
                        return Day[0];
                    }else{
                        return Day[i + 1];
                    }

                }
            }
            return "null";
        }
    }

    public void btnNoteClear(ActionEvent actionEvent) throws SQLException {
        txtSpecialNotice.clear();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("DELETE FROM SpecalNotice");
        sql.executeUpdate();
        setNote();

    }


    public void btnSave(MouseEvent mouseEvent) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM SpecalNotice WHERE SpecalNoteId='001'").executeQuery();

        if(resultSet.next()){

            PreparedStatement sql = connection.prepareStatement("UPDATE SpecalNotice SET SpecalNot='"+txtSpecialNotice.getText()+"' WHERE SpecalNoteId='"+resultSet.getString(1)+"'");
            sql.executeUpdate();

        }else{
            PreparedStatement sql = connection.prepareStatement("INSERT INTO SpecalNotice VALUES (?,?)");
            sql.setObject(1,"001");
            sql.setObject(2,txtSpecialNotice.getText());

            sql.executeUpdate();
        }


    }

    public void setNote() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM specalnotice WHERE SpecalNoteId='001'").executeQuery();
        if(resultSet.next()){
            txtSpecialNotice.setText(" " +resultSet.getString(2));

        }
    }

    public void payment2BtnOnAction(ActionEvent actionEvent) {

        RootMain.getChildren().clear();
        StackPane stackPane;
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("../View/PaymentForm.fxml"));
        try {
            stackPane=fxmlLoader.load();
            this.RootMain.getChildren().addAll(stackPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ZoomIn(RootMain).play();

    }

    public void btnAddFeeOnAction(ActionEvent actionEvent) {
        lblNotification.setVisible(false);


        if(     new Validation().patternValidation(Ids,txtFClassId)&
                new Validation().patternValidation(Ids,txtFStudId)&
                new Validation().patternValidation(Year,txtFReleventYear)&
                combFMonth.getSelectionModel().getSelectedIndex()>=0


        ) {
            try {

                if(new ClassFeeManageClass().checkStClass(txtFStudId.getText(),txtFClassId.getText())){
                    panePayForm.setVisible(true);
                    panePayClassFeeback.setVisible(false);
                    new ZoomIn(panePayForm).play();
                    ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Fee_Id From `ClassFee` ORDER BY Fee_Id DESC LIMIT 1").executeQuery();
                    if(sql.next()){
                        int tempId=Integer.parseInt(sql.getString(1).split("/")[1]);
                        tempId=tempId+1;
                        if(tempId<10){
                            lblFeeID.setText("CFEE/00"+tempId);
                        }else if(tempId<100){
                            lblFeeID.setText("CFEE/0"+tempId);
                        }else {
                            lblFeeID.setText("O"+tempId);
                        }

                    }else{
                        lblFeeID.setText("CFEE/001");
                    }

                    lblFClassId.setText(txtFClassId.getText());
                    lblFStudId.setText(txtFStudId.getText());
                    lblFMonth.setText(month);
                    lblFCost.setText(String.valueOf(new ClassFeeManageClass().getCost(txtFClassId.getText())));
                }else{
                    lblNotification.setVisible(true);
                    lblNotification.setText("Invalid Entered..!");
                    lblNotification.setStyle("-fx-text-fill: red");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            lblNotification.setVisible(true);
            lblNotification.setText("Something Wrong Check Details..!");
            lblNotification.setStyle("-fx-text-fill: red");
        }

    }

    public void btnPayOnAction(ActionEvent actionEvent) {
         lblNotification.setVisible(false);
        Date date= new Date();
        if(CurrentTime.getMinute()<10){
            Time=(CurrentTime.getHour()+":0"+CurrentTime.getMinute());
        }else{
            Time=(CurrentTime.getHour()+":"+CurrentTime.getMinute());
        }

        ClassFee classFee=new ClassFee(
                lblFeeID.getText(),
                lblFStudId.getText(),
                lblFClassId.getText(),
                Double.parseDouble(lblFCost.getText()),
                date,
                Time,
                txtFReleventYear.getText(),
                lblFMonth.getText(),
                "Paid",
                30
        );
        try {
            if(new ClassFeeManageClass().PayFee(classFee)){
                lblNotification.setVisible(true);
                lblNotification.setText("Successfully Payment");
                lblNotification.setStyle("-fx-text-fill: #5bae34");
                panePayForm.setVisible(false);
                panePayClassFeeback.setOpacity(1);

            }else{
                lblNotification.setVisible(true);
                lblNotification.setText("Wrong Try Again");
                lblNotification.setStyle("-fx-text-fill: red");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void PayClassFeebtnOnAction(ActionEvent actionEvent) {
        panePayClassFeeback.setVisible(true);
    }

    public void MainOnClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(panePayForm)){
            panePayForm.setVisible(true);
        }else{
            panePayForm.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(panePayClassFeeback)){
            panePayClassFeeback.setVisible(true);
        }else{
            panePayClassFeeback.setVisible(false);
        }
        lblNotification.setVisible(false);
        if(mouseEvent.getTarget().equals(PaneUpdateDelete)){
            PaneUpdateDelete.setVisible(true);
        }else{
            PaneUpdateDelete.setVisible(false);
        }

    }

    public void SearchSIdOnAction(ActionEvent actionEvent) {

        try {
            StudentReg studentReg = new StudentMangeClass().search(txtSearchStudentIdHome.getText());
            if (txtSearchStudentIdHome.getText().isEmpty()) {
                lblNotification.setVisible(true);
                lblNotification.setText("Empty Text Field");
                lblNotification.setStyle("-fx-text-fill: red");
            } else {

                if (studentReg == null) {
                    lblNotification.setVisible(true);
                    lblNotification.setText("Invalid Enter");
                    lblNotification.setStyle("-fx-text-fill: red");
                } else {

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
}
