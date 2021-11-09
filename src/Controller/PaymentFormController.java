package Controller;

import DBConnection.DbConnection;
import Model.ClassFee;
import Model.Payments;
import TM.PaymentTM;
import animatefx.animation.ZoomIn;
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
import java.time.LocalTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class PaymentFormController {
    public javafx.scene.control.ScrollPane ScrollPane;
    public AnchorPane paneTable;
    public TableColumn colmPayCode;
    public TableColumn ColmTId;
    public TableColumn colmRYear;
    public TableColumn colmRMonth;
    public TableColumn colmPDate;
    public TableColumn colmPTime;
    public TableColumn colmCost;
    public TableColumn colmStatus;
    public Label lblClassName;
    public TextField txtSearchClassId;
    public Button btnSearch;
    public PieChart pieChart;
    public AnchorPane panePayForm;
    public Label lblPYear;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public AnchorPane paneCheckMY;
    public ComboBox comboYear;
    public AnchorPane paneUpdateDetails;
    public AnchorPane popupPane1;
    public Label lblPopupText1;
    public AnchorPane Popup2pane1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    public AnchorPane paneDownColor;
    public AnchorPane paneDownErrorMsg;
    public Label lblDownErrorMsg;
    public Label lblPCost;
    public Label lblPMonth;
    public Label lblPTechId;
    public Label lblPClassId;
    public Label lblPayID;
    public TableView<PaymentTM> tblClassPayment;
    public JFXTextField txtPReleventYear;
    public JFXComboBox combPMonth;
    public JFXTextField txtPClassId;
    public AnchorPane panePayClassPayBack;
    public JFXTextField txtPTeachId;

    String setYear;
    String Time;
    String month;
    LocalTime CurrentTime=LocalTime.now();
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int tblIndex=-1;

    Pattern Ids = Pattern.compile("^[A-z]{3,4}[/][0-9]{1,}$");
    Pattern Year = Pattern.compile("^[0-9]{4,}$");
    Pattern Co = Pattern.compile("^[0-9]{1,3}$");
    Pattern CPrice = Pattern.compile("^[1-9][0-9.]{1,}$");

    public void initialize(){
        txtPReleventYear.setText(String.valueOf(year));
        //set month combo box
        combPMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

        combPMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
        });

        colmPayCode.setStyle("-fx-alignment: CENTER;");
        ColmTId.setStyle("-fx-alignment: CENTER;");
        colmRYear.setStyle("-fx-alignment: CENTER;");
        colmRMonth.setStyle("-fx-alignment: CENTER;");
        colmPDate.setStyle("-fx-alignment: CENTER;");
        colmPTime.setStyle("-fx-alignment: CENTER;");
        colmCost.setStyle("-fx-alignment: CENTER;");
        colmStatus.setStyle("-fx-alignment: CENTER;");

        colmPayCode.setCellValueFactory(new PropertyValueFactory<>("Pay_Code"));
        ColmTId.setCellValueFactory(new PropertyValueFactory<>("Teach_Id"));
        colmRYear.setCellValueFactory(new PropertyValueFactory<>("relevent_Year"));
        colmRMonth.setCellValueFactory(new PropertyValueFactory<>("relevent_Month"));
        colmPDate.setCellValueFactory(new PropertyValueFactory<>("pay_Date"));
        colmPTime.setCellValueFactory(new PropertyValueFactory<>("pay_Time"));
        colmCost.setCellValueFactory(new PropertyValueFactory<>("pay_Cost"));
        colmStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));


        tblClassPayment.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tblIndex= (int) newValue;
        });

        loadPieChart();
        suggestPaymentFrom();
    }

    public void suggestPaymentFrom(){
        try {
            TextFields.bindAutoCompletion(txtSearchClassId,new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtPClassId,new StudentMangeClass().getCLassID());
            TextFields.bindAutoCompletion(txtPTeachId,new TeacherManageClass().getTeachersId());
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

    public void loadPieChart(){
        ObservableList<PieChart.Data> pie1= FXCollections.observableArrayList(
                new PieChart.Data("Paid",25),
                new PieChart.Data("Not Paid",60)

        );
        pieChart.setData(pie1);
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(panePayForm)){
            panePayForm.setVisible(true);

        }else{
            panePayForm.setVisible(false);
            panePayClassPayBack.setOpacity(1);
        }
        if(mouseEvent.getTarget().equals(paneCheckMY)){
            paneCheckMY.setVisible(true);

        }else{
            btnSearch.setDisable(false);
            paneCheckMY.setVisible(false);
        }
        popupPane.setVisible(false);
        popupPane1.setVisible(false);
        setBottomErrorMsgClose();
        setBottomErrorMsgClose();
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        popupPane.setVisible(false);
        popupPane1.setVisible(false);

            if (lblPCost.getText().equals("0.0")) {
                wrongPopup();
                lblPopupText.setText("Can't Pay..! Invalid Pay Month");
            } else {
                Date date = new Date();
                if (CurrentTime.getMinute() < 10) {
                    Time = (CurrentTime.getHour() + ":0" + CurrentTime.getMinute());
                } else {
                    Time = (CurrentTime.getHour() + ":" + CurrentTime.getMinute());
                }

                Payments payment = new Payments(
                        lblPayID.getText(),
                        lblPTechId.getText(),
                        lblPClassId.getText(),
                        Double.parseDouble(lblPCost.getText()),
                        txtPReleventYear.getText(),
                        lblPMonth.getText(),
                        date,
                        Time,
                        "Paid"
                );

                try {
                    if (new PaymentMangeClass().Pay(payment)) {
                        successPopup();
                        lblPopupText.setText("Payment is made successfully");
                        panePayForm.setVisible(false);
                        panePayClassPayBack.setOpacity(1);
                        loadPieChart();
                        paneTable.setVisible(false);

                    } else {
                        wrongPopup();
                        lblPopupText.setText("Has paid for the relevant month...!");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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


    public void btnCheckYOnMouseClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);
        setBottomErrorMsgClose();

        if(comboYear.getSelectionModel().getSelectedIndex()>=0){
            try {
                paymentObs=new PaymentMangeClass().search1(txtSearchClassId.getText(),setYear);
                if(paymentObs==null){
                    setBottomErrorMsg();
                    lblDownErrorMsg.setText("Invalid Fees..!");
                }else{
                    tblClassPayment.setItems(paymentObs);
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

    public void menuItemAllClear(ActionEvent actionEvent) {
    }

    public void btnSearchOnMouseClicked(MouseEvent mouseEvent) {
        popupPane.setVisible(false);
        popupPane1.setVisible(false);
        setBottomErrorMsgClose();
        try {
            SearchBtnClicked();
            ScrollPane.setVvalue(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ObservableList<PaymentTM> paymentObs;
    public void SearchBtnClicked() throws SQLException {
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Classes WHERE Class_Id='" + txtSearchClassId.getText() + "'");
        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()) {
            lblClassName.setText(resultSet.getString(6));
        }

        try {
            paymentObs=new PaymentMangeClass().search(txtSearchClassId.getText());
            if(paymentObs==null){
                paneTable.setVisible(false);
                wrongUpdatePopup();
                lblPopupText1.setText("Invalid Entered...!");

            }else{
                tblClassPayment.setItems(paymentObs);
                paneTable.setVisible(true);
                ScrollPane.setVvalue(0.9);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void menuItemRemovePayment(ActionEvent actionEvent) {
    }

    public void menuItemCategoryPayment(ActionEvent actionEvent) {
        setBottomErrorMsgClose();
        comboYear.getSelectionModel().clearSelection();
        comboYear.getItems().addAll(year,year-1,year-2,year-3,year-4,year-5);
        comboYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setYear= String.valueOf(newValue);
        });
        paneCheckMY.setVisible(true);
    }

    public void AddPaymentOnAction(ActionEvent actionEvent) {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);

        if (new Validation().patternValidation(Ids, txtPClassId) &
                new Validation().patternValidation(Ids, txtPTeachId) &
                new Validation().patternValidation(Year, txtPReleventYear) &
                combPMonth.getSelectionModel().getSelectedIndex() >= 0


        ) {
            try {

                if (new PaymentMangeClass().checkStClass(txtPClassId.getText(), txtPTeachId.getText())) {
                    panePayForm.setVisible(true);
                    panePayClassPayBack.setOpacity(0.5);
                    new ZoomIn(panePayForm).play();
                    ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Payment_Code From `Payment` ORDER BY Payment_Code  DESC LIMIT 1").executeQuery();
                    if (sql.next()) {
                        int tempId = Integer.parseInt(sql.getString(1).split("/")[1]);
                        tempId = tempId + 1;
                        if (tempId < 10) {
                            lblPayID.setText("PMT/00" + tempId);
                        } else if (tempId < 100) {
                            lblPayID.setText("PMT/0" + tempId);
                        } else {
                            lblPayID.setText("O" + tempId);
                        }

                    } else {
                        lblPayID.setText("PMT/001");
                    }

                    lblPClassId.setText(txtPClassId.getText());
                    lblPTechId.setText(txtPTeachId.getText());
                    lblPYear.setText(txtPReleventYear.getText());
                    lblPMonth.setText(month);
                    lblPCost.setText(String.valueOf(new PaymentMangeClass().getCost(txtPClassId.getText(), txtPReleventYear.getText(), month)));
                } else {
                    wrongPopup();
                    lblPopupText.setText("Invalid Entered...!");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }
    }
}
