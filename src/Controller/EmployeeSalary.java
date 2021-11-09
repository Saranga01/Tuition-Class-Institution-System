package Controller;

import DBConnection.DbConnection;
import Model.PaySalary;
import TM.ClassFeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSalary {

    public boolean CheckSalary(String EId,String RYear,String RMonth) throws SQLException {
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Salary WHERE Employee_Id=? and R_year=? and R_Month=?");

        sql.setObject(1,EId);
        sql.setObject(2,RYear);
        sql.setObject(3,RMonth);

        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()){
            return true;
        }else{
            return false;
        }

    }

    public ObservableList<PaySalary> search1(String RYear,String RMonth) throws SQLException {
        boolean b=false;
        ObservableList<PaySalary> salary= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM Salary WHERE R_year=? and R_Month=?");
        sql.setObject(1,RYear);
        sql.setObject(2,RMonth);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            b=true;
            salary.add(
                    new PaySalary(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }
        if(b==true){
            return  salary;
        }else{
            return null;
        }
    }

    public boolean paySalary(PaySalary paySalary) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("INSERT INTO Salary VALUES (?,?,?,?,?,?,?,?)");

        sql.setObject(1,paySalary.getSalary_Id());
        sql.setObject(2,paySalary.getEmployee_Id());
        sql.setObject(3,paySalary.getSalary_Cost());
        sql.setObject(4,paySalary.getR_year());
        sql.setObject(5,paySalary.getR_Month());
        sql.setObject(6,paySalary.getDate());
        sql.setObject(7,paySalary.getTime());
        sql.setObject(8,paySalary.getStatus());

        return sql.executeUpdate()>0;


    }

    public ObservableList<PaySalary> getAllPayment() throws SQLException {


        ObservableList<PaySalary> obs= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Salary");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            obs.add(
                    new PaySalary(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    )
            );
        }

        return obs;

    }
    public int updatePayment(PaySalary paySalary) throws SQLException {


        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql2 = connection.prepareStatement("SELECT * FROM Salary WHERE Employee_Id=? and R_year=? and R_Month=?");

        sql2.setObject(1,paySalary.getEmployee_Id());
        sql2.setObject(2,paySalary.getR_year());
        sql2.setObject(3,paySalary.getR_Month());

        if (sql2.executeQuery().next()) {
            return -1;
        } else {


            PreparedStatement sql = connection.prepareStatement("UPDATE Salary SET Salary_Id =?,Employee_Id=?,Salary_Cost=?,R_year=?, R_Month=?,`Date`=?,`Time`=?,Stutus=? WHERE Salary_Id=?");
            sql.setObject(1, paySalary.getSalary_Id());
            sql.setObject(2, paySalary.getEmployee_Id());
            sql.setObject(3, paySalary.getSalary_Cost());
            sql.setObject(4, paySalary.getR_year());
            sql.setObject(5, paySalary.getR_Month());
            sql.setObject(6, paySalary.getDate());
            sql.setObject(7, paySalary.getTime());
            sql.setObject(8, paySalary.getStatus());
            sql.setObject(9, paySalary.getSalary_Id());

            return sql.executeUpdate();

        }
    }

}
