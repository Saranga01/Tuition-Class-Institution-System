package Controller;

import DBConnection.DbConnection;
import Model.ClassFee;
import TM.ClassFeeTM;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class ClassFeesFormController {
    public javafx.scene.chart.PieChart PieChart;
    public JFXTextField txtFClassId;
    public JFXTextField txtFStudId;
    public JFXComboBox combFMonth;
    public AnchorPane panePayForm;
    public Label lblFeeID;
    public Label lblFClassId;
    public Label lblFStudId;
    public Label lblFMonth;
    public Label lblFCost;
    public Label lblCommiss;
    public AnchorPane panePayClassFeeback;
    public JFXTextField txtFReleventYear;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public AnchorPane PaneCommissChange;
    public TextField txtCommisChange;
    public AnchorPane pieChartPane;
    public TextField txtSearchClassId;
    public AnchorPane paneTable;
    public TableView<ClassFeeTM> tblClassFees;
    public TableColumn ColmSId;
    public TableColumn colmRMonth;
    public TableColumn colmPDate;
    public TableColumn colmPTime;
    public TableColumn colmCost;
    public TableColumn colmStatus;
    public Label lblClassName;
    public AnchorPane paneCheckMY;
    public ComboBox comboMonth;
    public ComboBox comboYear;
    public TableColumn colmRYear;
    public JFXComboBox combFMonth1;
    public JFXTextField txtUpdateRYearName;
    public AnchorPane paneUpdateDetails;
    public ComboBox comboMonth1;
    public ComboBox comboYear1;
    public Button btnSearch;
    public TableColumn colmFeeCode;
    public AnchorPane popupPane1;
    public Label lblPopupText1;
    public AnchorPane Popup2pane1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    public AnchorPane paneDownColor;
    public AnchorPane paneDownErrorMsg;
    public Label lblDownErrorMsg;
    public javafx.scene.control.ScrollPane ScrollPane;
    String month;
    int RYear;
    LocalTime CurrentTime=LocalTime.now();
    String Time;
    int paid;
    int notPaid;
    Date date =new Date();
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int tblIndex=-1;

    Pattern Ids = Pattern.compile("^[A-z]{3,4}[/][0-9]{1,}$");
    Pattern Year = Pattern.compile("^[0-9]{4,}$");
    Pattern Co = Pattern.compile("^[0-9]{1,3}$");




    public void initialize(){
        popupPane.setVisible(false);
        txtFReleventYear.setText(String.valueOf(year));

        //set month combo box
        combFMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

        combFMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
            try {
                loadPieChart();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        comboMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        comboYear.getItems().addAll(year,year-1,year-2,year-3,year-4,year-5);
        comboMonth1.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
        comboYear1.getItems().addAll(year,year-1,year-2,year-3,year-4,year-5);

        comboMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
        });
        comboYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            RYear= (int) newValue;
        });

        comboMonth1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
        });
        comboYear1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            RYear= (int) newValue;
        });

        tblClassFees.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tblIndex= (int) newValue;
        });


        colmFeeCode.setStyle("-fx-alignment: CENTER;");
        ColmSId.setStyle("-fx-alignment: CENTER;");
        colmRYear.setStyle("-fx-alignment: CENTER;");
        colmRMonth.setStyle("-fx-alignment: CENTER;");
        colmPDate.setStyle("-fx-alignment: CENTER;");
        colmPTime.setStyle("-fx-alignment: CENTER;");
        colmCost.setStyle("-fx-alignment: CENTER;");
        colmStatus.setStyle("-fx-alignment: CENTER;");

        colmFeeCode.setCellValueFactory(new PropertyValueFactory<>("FeeCode"));
        ColmSId.setCellValueFactory(new PropertyValueFactory<>("stud_Id"));
        colmRYear.setCellValueFactory(new PropertyValueFactory<>("relevent_Year"));
        colmRMonth.setCellValueFactory(new PropertyValueFactory<>("relevent_Month"));
        colmPDate.setCellValueFactory(new PropertyValueFactory<>("pay_Date"));
        colmPTime.setCellValueFactory(new PropertyValueFactory<>("pay_Time"));
        colmCost.setCellValueFactory(new PropertyValueFactory<>("fe_eCost"));
        colmStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        suggestFeeFrom();
    }

    public void suggestFeeFrom(){
        try {
            TextFields.bindAutoCompletion(txtSearchClassId,new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtFStudId,new Suggest().getStudId());
            TextFields.bindAutoCompletion(txtFClassId,new StudentMangeClass().getCLassID());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setBottomErrorMsg(){
        paneDownErrorMsg.setVisible(true);
        paneDownColor.setVisible(true);

    }
    public void setBottomErrorMsgClose(){
        paneDownErrorMsg.setVisible(false);
        paneDownColor.setVisible(false);
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getTarget().equals(panePayForm)){
            panePayForm.setVisible(true);

        }else{
            panePayForm.setVisible(false);
            panePayClassFeeback.setOpacity(1);
        }
        if(mouseEvent.getTarget().equals(paneCheckMY)){
            paneCheckMY.setVisible(true);

        }else{
            btnSearch.setDisable(false);
           paneCheckMY.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(paneUpdateDetails)){
            paneUpdateDetails.setVisible(true);

        }else{
            paneUpdateDetails.setVisible(false);
        }
        popupPane.setVisible(false);
        popupPane1.setVisible(false);
        PaneCommissChange.setVisible(false);
        setBottomErrorMsgClose();
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
        Popup2pane.setOpacity(0.18);
        popupPane.setVisible(true);
        SuccessImg.setVisible(false);
        wrongImg.setVisible(true);
        //new SlideInLeft(popupPane).play();
    }
   public void successUpdatePopup(){
        popupPane1.setVisible(true);
        Popup2pane1.setStyle("-fx-background-color:  #32ff7e");
        Popup2pane.setOpacity(0.18);
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
    public void btnAddFeeOnAction(ActionEvent actionEvent) {

        if(     new Validation().patternValidation(Ids,txtFClassId)&
                new Validation().patternValidation(Ids,txtFStudId)&
                new Validation().patternValidation(Year,txtFReleventYear)&
                combFMonth.getSelectionModel().getSelectedIndex()>=0


        ) {
            try {

                if(new ClassFeeManageClass().checkStClass(txtFStudId.getText(),txtFClassId.getText())){
                    panePayForm.setVisible(true);
                    panePayClassFeeback.setOpacity(0.5);
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
                    wrongPopup();
                    lblPopupText.setText("Invalid Entered...!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }


    }

    public void loadPieChart() throws SQLException {
        pieChartPane.setVisible(true);
        paid=new ClassFeeManageClass().ClassFeeCount(txtFClassId.getText(),txtFReleventYear.getText(),month);
        notPaid=new ClassFeeManageClass().classCount(txtFClassId.getText())-paid;
        ObservableList<PieChart.Data> pie1= FXCollections.observableArrayList(
                new PieChart.Data("Paid",paid),
                new PieChart.Data("Not Paid",notPaid)

        );
        PieChart.setData(pie1);
    }

    public void btnPayOnAction(ActionEvent actionEvent) throws SQLException {

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
                Integer.parseInt(lblCommiss.getText())
        );
            if(new ClassFeeManageClass().PayFee(classFee)){
                successPopup();
                lblPopupText.setText("Payment is made successfully");
                panePayForm.setVisible(false);
                loadPieChart();


            }else{
                wrongPopup();
                lblPopupText.setText("This student has paid for the relevant month...!");
            }


    }

    public void btnCommissChangeOnAction(ActionEvent actionEvent) {
        if(Co.matcher(txtCommisChange.getText()).matches()) {
            lblCommiss.setText(txtCommisChange.getText());
        }else{
            wrongPopup();
            lblPopupText.setText("Invalid Value..!");
        }
    }

    public void btnChangeOnAction(ActionEvent actionEvent) {
        PaneCommissChange.setVisible(true);


    }

    public void menuItemUpdateFees(ActionEvent actionEvent) {
        setBottomErrorMsgClose();
        if(tblIndex==-1){
            setBottomErrorMsg();
            lblDownErrorMsg.setText("Please select row and try again..!");
        }else{
            paneUpdateDetails.setVisible(true);
            paneCheckMY.setVisible(false);
        }

    }

    public void menuItemRemoveFees(ActionEvent actionEvent) {

    }

    public void menuItemCotagary(ActionEvent actionEvent) {
        paneCheckMY.setVisible(true);
        paneUpdateDetails.setVisible(false);
        btnSearch.setDisable(true);


    }

    public void menuItemClear(ActionEvent actionEvent) {
    }

    ObservableList<ClassFeeTM> ClassFeeTm;
    public void btnSearchOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
            setTable();


    }

    public void setTable() throws SQLException {

        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Classes WHERE Class_Id='" + txtSearchClassId.getText() + "'");
        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()) {
            lblClassName.setText(resultSet.getString(6));
        }

        try {
            ClassFeeTm=new ClassFeeManageClass().search(txtSearchClassId.getText());
            if(ClassFeeTm==null){
                paneTable.setVisible(false);
                wrongUpdatePopup();
                lblPopupText1.setText("Invalid Entered...!");

            }else{
                tblClassFees.setItems(ClassFeeTm);
                paneTable.setVisible(true);
                ScrollPane.setVvalue(0.9);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void btnCheckMYOnMouseClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);
        if(comboMonth.getSelectionModel().getSelectedIndex()>=0 & comboYear.getSelectionModel().getSelectedIndex()>=0){
            try {
                ClassFeeTm=new ClassFeeManageClass().search1(txtSearchClassId.getText(),month, String.valueOf(RYear));
                if(ClassFeeTm==null){
                    setBottomErrorMsg();
                    lblDownErrorMsg.setText("Invalid Month..!");
                }else{
                    tblClassFees.setItems(ClassFeeTm);
                    paneTable.setVisible(true);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Something Wrong...! please Check Details");
        }


    }

    public void btnFinalUpdateOnMouseClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);

        if(comboMonth1.getSelectionModel().getSelectedIndex()>=0&comboYear1.getSelectionModel().getSelectedIndex()>=0) {
            ClassFee classFee = new ClassFee(
                    ClassFeeTm.get(tblIndex).getFeeCode(),
                    ClassFeeTm.get(tblIndex).getStud_Id(),
                    txtSearchClassId.getText(),
                    ClassFeeTm.get(tblIndex).getFe_eCost(),
                    ClassFeeTm.get(tblIndex).getPay_Date(),
                    ClassFeeTm.get(tblIndex).getPay_Time(),
                    String.valueOf(RYear),
                    month,
                    ClassFeeTm.get(tblIndex).getStatus(),
                    30

            );

            try {
                if (new ClassFeeManageClass().updateFee(classFee) > 0) {
                    successUpdatePopup();
                    lblPopupText1.setText("Class Fee Update Successfully");
                    setTable();
                    paneUpdateDetails.setVisible(false);
                } else if (new ClassFeeManageClass().updateFee(classFee) == -1) {
                    wrongUpdatePopup();
                    lblPopupText1.setText("This student has paid for the relevant month...!");
                } else {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Wrong...! please try Again");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Can't update...! please Check Details");
        }

    }
}
