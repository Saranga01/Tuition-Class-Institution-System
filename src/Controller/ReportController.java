package Controller;

import TM.IncomeReportTM;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.Calendar;

public class ReportController {

    public javafx.scene.control.ScrollPane ScrollPane;
    public AnchorPane ScrollAnchorPane;
    public TextField txtSearchId;
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
    public JFXComboBox combYear;
    public JFXComboBox comboMonth;
    public TableView<IncomeReportTM> tblMonthlyIncome;
    public TableColumn colmClassId;
    public TableColumn colmMonth;
    public TableColumn colmProfit;
    public AnchorPane paneSelectMY;
    public AnchorPane paneMonthlyTbl;
    int year = Calendar.getInstance().get(Calendar.YEAR);

    int  Year;
    String Month;

    public void initialize(){


        comboMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

        combYear.getItems().addAll(year,year-1,year-2,year-3,year-4,year-5);

        comboMonth.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Month= (String) newValue;
        });

        combYear.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           Year= (int) newValue;
        });

        colmMonth.setStyle("-fx-alignment: CENTER;");
        colmClassId.setStyle("-fx-alignment: CENTER;");
        colmProfit.setStyle("-fx-alignment: CENTER;");

        colmClassId.setCellValueFactory(new PropertyValueFactory<>("ClassId"));
        colmMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        colmProfit.setCellValueFactory(new PropertyValueFactory<>("Profit"));
    }

    public void RootMainAnchorPaneOnClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getTarget().equals(paneSelectMY)){
            paneSelectMY.setVisible(true);
        }else{
            paneSelectMY.setVisible(false);
        }
        if(mouseEvent.getTarget().equals(paneMonthlyTbl)){
            paneMonthlyTbl.setVisible(true);
        }else{
            paneMonthlyTbl.setVisible(false);
        }

    }


    ObservableList<IncomeReportTM> incomeMonthly;

    {
        try {
            incomeMonthly = new ReportManageClass().monthlyIncome(Month,Year);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnViewOnAction(ActionEvent actionEvent) {
        paneMonthlyTbl.setVisible(true);

        try {
            if( new ReportManageClass().monthlyIncome(Month,Year)==null){
                System.out.println("wrong");
            }else {
                tblMonthlyIncome.setItems(new ReportManageClass().monthlyIncome(Month,Year));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void btnIMPOnAction(ActionEvent actionEvent) {
        paneSelectMY.setVisible(true);
    }

    public void MIncomePrintReport(ActionEvent actionEvent) {

        JasperDesign load = null;
        try {
            load = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/MonthlyProfits.jrxml"));
            JasperReport CompileReport = JasperCompileManager.compileReport(load);
            ObservableList<IncomeReportTM> items = tblMonthlyIncome.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(CompileReport, null, new JRBeanArrayDataSource(items.toArray()));
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }





    }
}
