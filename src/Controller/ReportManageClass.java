package Controller;

import DBConnection.DbConnection;
import TM.IncomeReportTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportManageClass {


    public ObservableList<IncomeReportTM> monthlyIncome(String Month,int year) throws SQLException {
        ObservableList<IncomeReportTM> incomeReportTM = FXCollections.observableArrayList();
        Connection connection=DbConnection.getInstance().getConnection();

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement(" Select * ,sum(Institute_Profit) as Institute_Profit from Money Where Relevent_Year='"+year+"' and Relevent_Month='"+Month+"' group by Class_Id ");


        ResultSet resultSet = sql.executeQuery();

        boolean b = false;

        while (resultSet.next()) {
            b = true;
            incomeReportTM.add(
                    new IncomeReportTM(
                            resultSet.getString(1),
                            resultSet.getString(3),
                            new BigDecimal(resultSet.getDouble(6))
                    )
            );
        }
        if (b) {
            return incomeReportTM;
        } else {
           return null;
        }
    }
}
