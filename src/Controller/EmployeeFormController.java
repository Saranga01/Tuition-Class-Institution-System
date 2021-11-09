package Controller;

import DBConnection.DbConnection;
import Model.EmployeeReg;
import Model.PaySalary;
import Model.TeacherReg;
import TM.AllEmployeeTM;
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
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public JFXTextField txtEAddress;
    public JFXTextField txtEJobTitle;
    public JFXTextField txtEEmail;
    public JFXTextField txtEName;
    public JFXTextField txtEMobile;
    public JFXTextField txtENic;
    public JFXComboBox comboEGender;


    public AnchorPane RootUpdateDelete;
    public AnchorPane paneEId;
    public Label lblGenerateEID;
    public AnchorPane popupPane;
    public AnchorPane Popup2pane;
    public ImageView SuccessImg;
    public ImageView wrongImg;
    public Label lblPopupText;
    public TableView tblAllEmployee;
    public TableColumn colmEmployeeId;
    public TableColumn colmEmployeeName;
    public TableColumn colmEmployeeAddress;
    public TableColumn colmBtn;
    public Label lblERegDate;
    public Label lbElGender;
    public Label lblEMobile;
    public Label lblEAddress;
    public Label lblEEmail;
    public Label lblENic;
    public Label lblEName;
    public Label lblEJob;
    public Label lblEId;
    public TextField txtSearchEId;
    public javafx.scene.control.ScrollPane ScrollPane;
    public AnchorPane paneUpdateDetails;
    public JFXTextField txtUpdateEName;
    public JFXTextField txtUpdateEEmail;
    public JFXTextField txtUpdateEAddress;
    public JFXTextField txtUpdateENic;
    public JFXTextField txtUpdateEMobile;
    public JFXComboBox comboGenderUpdate;
    public JFXTextField txtUpdateEJobTitle;
    public AnchorPane popupPane1;
    public Label lblPopupText1;
    public AnchorPane Popup2pane1;
    public ImageView wrongImg1;
    public ImageView successImg1;
    public AnchorPane paneSalaryAdd;
    public JFXTextField txtSalaryEmployeeId;
    public JFXComboBox combSalaryMonth;
    public JFXTextField txtSalaryReleventYear;
    public AnchorPane panePayForm;
    public Label lblSalaryID;
    public Label lblEmployeeId;
    public Label lblSalaryMonth;
    public Label lblSalaryCost;
    public Label lblSalaryYear;
    public AnchorPane SubBackAnchorPane;
    public JFXTextField txtSalaryCost;
    public AnchorPane PaneAllPayment;
    public TableView tblAllPayments;
    public TableColumn colmSalaryId;
    public TableColumn colmSalaryEmployeeId;
    public TableColumn colmSalaryCost;
    public TableColumn colmSalaryRYear;
    public TableColumn colmSalaryRMonth;
    public TableColumn colmSalaryPayDate;
    public TableColumn colmSalaryPayTime;
    public TableColumn colmSalaryStatus;
    public AnchorPane paneUpdatePayment;
    public ComboBox comboMonth1;
    public ComboBox comboYear1;
    public AnchorPane paneCheckMY;
    public ComboBox comboMonth;
    public ComboBox comboYear;
    public AnchorPane paneDownColor;
    public AnchorPane paneDownErrorMsg;
    public Label lblDownErrorMsg;

    Date date=new Date();
    String gender;
    LocalTime CurrentTime=LocalTime.now();
    String Time;
    String year1;
    String month1;
    int PIndex=-1;

    int year = Calendar.getInstance().get(Calendar.YEAR);
    String month;

    Pattern EName=Pattern.compile("^[A-z. ]{3,70}$");
    Pattern EAddress=Pattern.compile("^[A-z0-9/,. ]{4,150}$");
    Pattern EEmail=Pattern.compile("^[A-z0-9]{3,}[@][a-z]{2,}[.][a-z]{2,5}$");
    Pattern ENic=Pattern.compile("^[0-9]{6,20}[Vv]?$");
    Pattern EMobile=Pattern.compile("^[0-9]{9,10}$");
    Pattern Ids = Pattern.compile("^[A-z]{3,4}[/][0-9]{1,}$");
    Pattern Year = Pattern.compile("^[0-9]{4,}$");
    Pattern Price = Pattern.compile("^[1-9][0-9.]{1,}$");

    public void initialize(){


        txtSalaryReleventYear.setText(String.valueOf(year));
        PaneAllPayment.setVisible(false);

        comboEGender.getItems().addAll("male","female");

        comboEGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            gender= (String) newValue;
        });
        comboGenderUpdate.getItems().addAll("male","female");

        comboGenderUpdate.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            gender= (String) newValue;
        });


        combSalaryMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month= (String) newValue;
        });

        comboMonth1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month1= (String) newValue;
        });
        comboYear1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            year1= String.valueOf(newValue);
        });

        comboMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            month1= (String) newValue;
        });
        comboYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            year1= String.valueOf(newValue);
        });

        tblAllPayments.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            PIndex= (int) newValue;
        });

        colmSalaryId .setStyle("-fx-alignment: CENTER;");
        colmSalaryEmployeeId.setStyle("-fx-alignment: CENTER;");
        colmSalaryCost.setStyle("-fx-alignment: CENTER;");
        colmSalaryRYear.setStyle("-fx-alignment: CENTER;");
        colmSalaryRMonth.setStyle("-fx-alignment: CENTER;");
        colmSalaryPayDate.setStyle("-fx-alignment: CENTER;");
        colmSalaryPayTime.setStyle("-fx-alignment: CENTER;");
        colmSalaryStatus.setStyle("-fx-alignment: CENTER;");


        colmSalaryId.setCellValueFactory(new PropertyValueFactory<>("salary_Id"));
        colmSalaryEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employee_Id"));
        colmSalaryCost.setCellValueFactory(new PropertyValueFactory<>("salary_Cost"));
        colmSalaryRYear.setCellValueFactory(new PropertyValueFactory<>("r_year"));
        colmSalaryRMonth.setCellValueFactory(new PropertyValueFactory<>("r_Month"));
        colmSalaryPayDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmSalaryPayTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colmSalaryStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        suggestEmployeeFrom();
    }

    public void suggestEmployeeFrom(){
        try {
            TextFields.bindAutoCompletion(txtSearchEId,new Suggest().getEmployeeId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        popupPane.setVisible(false);
        popupPane1.setVisible(false);
        tblAllPayments.getSelectionModel().clearSelection();
        setBottomErrorMsgClose();

        if(mouseEvent.getTarget().equals(tblAllEmployee)){
            tblAllEmployee.setVisible(true);
        }else{
            tblAllEmployee.setVisible(false);
        }

        if(mouseEvent.getTarget().equals(panePayForm)) {
            panePayForm.setVisible(true);
        }

        if(mouseEvent.getTarget().equals(paneSalaryAdd)){
            paneSalaryAdd.setVisible(true);
        }else{
            paneSalaryAdd.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(PaneAllPayment)){
            PaneAllPayment.setVisible(true);
        }else{
            PaneAllPayment.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(paneCheckMY)){
            paneCheckMY.setVisible(true);
        }else{
            paneCheckMY.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(paneUpdatePayment)){
            paneUpdatePayment.setVisible(true);
        }else{
            paneUpdatePayment.setVisible(false);

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


    public void btnGenerateIdOnMouseClicked(MouseEvent mouseEvent) throws SQLException {
        paneEId.setVisible(true);
        ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Employee_Id From `Employee` ORDER BY Employee_Id DESC LIMIT 1").executeQuery();
        if(sql.next()){
            int tempId=Integer.parseInt(sql.getString(1).split("/")[1]);
            tempId=tempId+1;
            if(tempId<10){
                lblGenerateEID.setText("EMP/00"+tempId);
            }else if(tempId<100){
                lblGenerateEID.setText("EMP/0"+tempId);
            }else {
                lblGenerateEID.setText("O"+tempId);
            }

        }else{
            lblGenerateEID.setText("EMP/001");
        }

    }

    public void textClear(){
        paneEId.setVisible(false);
        txtEName.clear();
        comboEGender.getSelectionModel().clearSelection();
        txtEMobile.clear();
        txtEEmail.clear();
        txtEAddress.clear();
        txtENic.clear();
        txtEJobTitle.clear();
    }

    public void btnRegisterOnMouseClicked(MouseEvent mouseEvent) {
       popupPane.setVisible(false);
       popupPane1.setVisible(false);

        if(     paneEId.isVisible()&
                new Validation().patternValidation(EName,txtEName)&
                new Validation().patternValidation(EMobile,txtEMobile)&
                new Validation().patternValidation(EEmail,txtEEmail)&
                new Validation().patternValidation(ENic,txtENic)&
                new Validation().patternValidation(EAddress,txtEAddress)&
                new Validation().patternValidation(EAddress,txtEJobTitle)&
                comboEGender.getSelectionModel().getSelectedIndex()>=0
        ) {

            EmployeeReg employeeReg = new EmployeeReg(
                    lblGenerateEID.getText(),
                    txtEName.getText(),
                    gender,
                    Integer.parseInt(txtEMobile.getText()),
                    txtEEmail.getText(),
                    txtEAddress.getText(),
                    txtENic.getText(),
                    date,
                    txtEJobTitle.getText()

            );

            try {
                if (new EmployeeManageClass().register(employeeReg)) {
                    successPopup();
                    lblPopupText.setText("Employee Register Successfully");
                    textClear();
                } else {
                    wrongPopup();
                    lblPopupText.setText("Wrong...!Please Try Again");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongPopup();
            lblPopupText.setText("Something Wrong...! please Check Details");
        }

    }


    ObservableList<AllEmployeeTM> allEmployeeTM;

    public void btnViewAllOnMouseClicked(MouseEvent mouseEvent) {
        tblAllEmployee.setVisible(true);

        colmEmployeeId .setStyle("-fx-alignment: CENTER;");
        colmEmployeeName .setStyle("-fx-alignment: CENTER;");
        colmEmployeeAddress.setStyle("-fx-alignment: CENTER;");
        colmBtn.setStyle("-fx-alignment: CENTER;");


        colmEmployeeId.setCellValueFactory(new PropertyValueFactory<>("E_Id"));
        colmEmployeeName.setCellValueFactory(new PropertyValueFactory<>("E_Name"));
        colmEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("E_Address"));
        colmBtn.setCellValueFactory(new PropertyValueFactory<>("btn"));

        try {
            allEmployeeTM=new EmployeeManageClass().All();
            tblAllEmployee.setItems(allEmployeeTM);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void menuitemUpdateEmployeePane(ActionEvent actionEvent) {
        paneUpdateDetails.setVisible(true);
        txtUpdateEName.setText(lblEName.getText());
        txtUpdateEEmail.setText(lblEEmail.getText());
        txtUpdateEAddress.setText(lblEAddress.getText());
        txtUpdateENic.setText(lblENic.getText());
        txtUpdateEMobile.setText(lblEMobile.getText());
        txtUpdateEJobTitle.setText(lblEJob.getText());

        boolean b=true;
        comboGenderUpdate.getSelectionModel().selectFirst();
        while (b){
            if(comboGenderUpdate.getSelectionModel().getSelectedItem().equals(lbElGender.getText())){
                comboGenderUpdate.getSelectionModel().select(comboGenderUpdate.getSelectionModel().getSelectedItem());
                b=false;
            }else{
                comboGenderUpdate.getSelectionModel().selectNext();
            }
        }


    }

    public void menuitemRemoveEmployeePane(ActionEvent actionEvent) {
    }

    public void menuitemPaymentEmployeePane(ActionEvent actionEvent) {
        paneSalaryAdd.setVisible(true);
        paneUpdateDetails.setVisible(false);
        txtSalaryEmployeeId.setText(lblEId.getText());
        txtSalaryEmployeeId.setDisable(true);
        combSalaryMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

    }

    public void btnSearchEDetailsOnMouseCLicked(MouseEvent mouseEvent) {
        search();
    }

    public void search(){
        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        paneUpdatePayment.setVisible(false);
        try {

            if (new EmployeeManageClass().search(txtSearchEId.getText()) == null) {

                wrongUpdatePopup();
                lblPopupText1.setText("Invalid Entered..!");

            } else {
                ScrollPane.setVvalue(0.7);
                RootUpdateDelete.setVisible(false);
                paneSalaryAdd.setVisible(false);
                popupPane1.setVisible(false);
                popupPane.setVisible(false);
                PaneAllPayment.setVisible(false);


                EmployeeReg employeeReg = new EmployeeManageClass().search(txtSearchEId.getText());

                lblEId.setText(employeeReg.getE_Id());
                lblEAddress.setText(employeeReg.getAddress());
                lblEEmail.setText(String.valueOf(employeeReg.getEmail()));
                lblEJob.setText(employeeReg.getJob_Title());
                lblEMobile.setText(String.valueOf(employeeReg.getMobile_Num()));
                lblEName.setText(employeeReg.getE_Name());
                lblENic.setText(employeeReg.getNic());
                lblERegDate.setText(String.valueOf(employeeReg.getReg_Date()));
                lbElGender.setText(String.valueOf(employeeReg.getGender()));

                if (RootUpdateDelete.isVisible() == false) {
                    RootUpdateDelete.setVisible(true);
                } else {

                }

            }
            } catch(SQLException throwables){
                throwables.printStackTrace();
            }

    }

    public void btnFinalUpdateOnAction(ActionEvent actionEvent) throws ParseException {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);

        if(
                new Validation().patternValidation(EName,txtUpdateEName)&
                new Validation().patternValidation(EMobile,txtUpdateEMobile)&
                new Validation().patternValidation(EEmail,txtUpdateEEmail)&
                new Validation().patternValidation(ENic,txtUpdateENic)&
                new Validation().patternValidation(EAddress,txtUpdateEAddress)&
                new Validation().patternValidation(EAddress,txtUpdateEJobTitle)
        ) {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(lblERegDate.getText());

            EmployeeReg employeeReg = new EmployeeReg(
                    txtSearchEId.getText(),
                    txtUpdateEName.getText(),
                    gender,
                    Integer.parseInt(txtUpdateEMobile.getText()),
                    txtUpdateEEmail.getText(),
                    txtUpdateEAddress.getText(),
                    txtUpdateENic.getText(),
                    date1,
                    txtUpdateEJobTitle.getText()

            );

            try {
                if (new EmployeeManageClass().update(employeeReg) > 0) {
                    search();
                    successUpdatePopup();
                    lblPopupText1.setText("Employee Details Update Successfully");
                    paneUpdateDetails.setVisible(false);


                } else {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Update Fail..! Please try Again");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            wrongUpdatePopup();
            lblPopupText1.setText("Something Wrong...! please Check Details");
        }

    }

    public void UpdateDetailsPaneCloseMouseOnClicked(MouseEvent mouseEvent) {
        paneUpdateDetails.setVisible(false);
        paneSalaryAdd.setVisible(false);
    }

    public void btnSalaryAddOnMouseClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);

        if(     new Validation().patternValidation(Ids,txtSalaryEmployeeId)&
                new Validation().patternValidation(Year,txtSalaryReleventYear)&
                combSalaryMonth.getSelectionModel().getSelectedIndex()>=0&
                new Validation().patternValidation(Price,txtSalaryCost)

        ) {

            try {

                if (new EmployeeSalary().CheckSalary(txtSalaryEmployeeId.getText(), txtSalaryReleventYear.getText(), month)) {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Already Paid this Month...!");
                } else {
                    paneUpdateDetails.setVisible(false);
                    panePayForm.setVisible(true);
                    SubBackAnchorPane.setOpacity(0.5);
                    SubBackAnchorPane.setDisable(true);
                    ResultSet sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT Salary_Id From `Salary` ORDER BY Salary_Id DESC LIMIT 1").executeQuery();
                    if (sql.next()) {
                        int tempId = Integer.parseInt(sql.getString(1).split("/")[1]);
                        tempId = tempId + 1;
                        if (tempId < 10) {
                            lblSalaryID.setText("SLR/00" + tempId);
                        } else if (tempId < 100) {
                            lblSalaryID.setText("SLR/0" + tempId);
                        } else {
                            lblSalaryID.setText("O" + tempId);
                        }

                    } else {
                        lblSalaryID.setText("SLR/001");
                    }

                    lblEmployeeId.setText(txtSalaryEmployeeId.getText());
                    lblSalaryYear.setText(txtSalaryReleventYear.getText());
                    lblSalaryMonth.setText(month);
                    lblSalaryCost.setText(txtSalaryCost.getText());

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Something Wrong...! please Check Details");
        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        if(CurrentTime.getMinute()<10){
            Time=(CurrentTime.getHour()+":0"+CurrentTime.getMinute());
        }else{
            Time=(CurrentTime.getHour()+":"+CurrentTime.getMinute());
        }
        PaySalary paySalary=new PaySalary(
                lblSalaryID.getText(),
                lblEmployeeId.getText(),
                Double.parseDouble(lblSalaryCost.getText()),
                lblSalaryYear.getText(),
                lblSalaryMonth.getText(),
                date,
                Time,
                "Paid"
        );

        try {
            if(new EmployeeSalary().paySalary(paySalary)){
                successUpdatePopup();
                lblPopupText1.setText("Payment Successfully");
                panePayForm.setVisible(false);
                SubBackAnchorPane.setDisable(false);
                SubBackAnchorPane.setOpacity(1);
            }else{
                wrongUpdatePopup();
                lblPopupText1.setText("Wrong...!please Check Details");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void payFromCloseMouseOnClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);
        popupPane.setVisible(false);
        SubBackAnchorPane.setOpacity(1);
        SubBackAnchorPane.setDisable(false);
        panePayForm.setVisible(false);
        paneSalaryAdd.setVisible(true);
    }

    ObservableList<PaySalary> Salary;

    public void btnViewAllPaymentsOnMouseClicked(MouseEvent mouseEvent) {
        loadTbl();
        paneSalaryAdd.setVisible(false);
        panePayForm.setVisible(false);
        paneUpdatePayment.setVisible(false);

    }

    public void loadTbl(){
        RootUpdateDelete.setVisible(false);
        paneUpdateDetails.setVisible(false);
        PaneAllPayment.setVisible(true);
        try {
            Salary = new EmployeeSalary().getAllPayment();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblAllPayments.setItems(Salary);
    }

    public void MenuItemUpdatePayment(ActionEvent actionEvent) {
        paneUpdatePayment.setVisible(false);
        setBottomErrorMsgClose();
        if(PIndex==-1){
            setBottomErrorMsg();
            lblDownErrorMsg.setText("Please select row and try again..!");
        }else {
            paneUpdatePayment.setVisible(true);
            paneCheckMY.setVisible(false);


            comboMonth1.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            comboYear1.getItems().addAll(year, year - 1, year - 2, year - 3, year - 4, year - 5);
        }
    }

    public void MenuItemCotagoryMonth(ActionEvent actionEvent) {
        comboMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        comboYear.getItems().addAll(year, year - 1, year - 2, year - 3, year - 4, year - 5);
        paneUpdatePayment.setVisible(false);
        paneCheckMY.setVisible(true);
    }

    public void MenuItemRemovePayment(ActionEvent actionEvent) {

    }

    public void btnFinalUpdatePaymentOnMouseClicked(MouseEvent mouseEvent) {
        popupPane1.setVisible(false);
        setBottomErrorMsgClose();
        if(comboMonth1.getSelectionModel().getSelectedIndex()>=0&comboYear1.getSelectionModel().getSelectedIndex()>=0) {
            PaySalary paySalary = new PaySalary(
                    Salary.get(PIndex).getSalary_Id(),
                    Salary.get(PIndex).getEmployee_Id(),
                    Salary.get(PIndex).getSalary_Cost(),
                    String.valueOf(year1),
                    month1,
                    Salary.get(PIndex).getDate(),
                    Salary.get(PIndex).getTime(),
                    Salary.get(PIndex).getStatus()
            );
            try {
                if (new EmployeeSalary().updatePayment(paySalary) == 1) {
                    loadTbl();
                    successUpdatePopup();

                    lblPopupText1.setText("Payment Update Successfully ");
                   // paneUpdatePayment.setVisible(false);


                } else if (new EmployeeSalary().updatePayment(paySalary) == -1) {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Can't Update...!Already Paid this month");
                } else {
                    wrongUpdatePopup();
                    lblPopupText1.setText("Can't Update...Please Check details...!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Something Wrong...Please Check details...!");
        }

    }

    public void btnCheckMYOnMouseClicked(MouseEvent mouseEvent) {

        setBottomErrorMsgClose();
        popupPane1.setVisible(false);
        if(comboMonth.getSelectionModel().getSelectedIndex()>=0 & comboYear.getSelectionModel().getSelectedIndex()>=0) {
            try {
                if (new EmployeeSalary().search1(year1, month1) == null) {
                    setBottomErrorMsg();
                    lblDownErrorMsg.setText("Invalid month..!");
                } else {
                    tblAllPayments.setItems(new EmployeeSalary().search1(year1, month1));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            wrongUpdatePopup();
            lblPopupText1.setText("Something Wrong..Please Check details...!");
        }

    }
}
