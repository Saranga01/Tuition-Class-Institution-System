package Controller;

import DBConnection.DbConnection;
import Model.ClassFee;
import Model.Payments;
import TM.ClassFeeTM;
import TM.PaymentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class PaymentMangeClass implements PaymentMange{
    @Override
    public boolean Pay(Payments payment) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO Payment VALUES(?,?,?,?,?,?,?,?,?) ");
            sql.setObject(1, payment.getPay_Code());
            sql.setObject(2, payment.getTeach_Id());
            sql.setObject(3, payment.getClass_Id());
            sql.setObject(4, payment.getPay_Cost());
            sql.setObject(5, payment.getRelevent_Year());
            sql.setObject(6, payment.getRelevent_Month());
            sql.setObject(7, payment.getPay_Date());
            sql.setObject(8, payment.getPay_Time());
            sql.setObject(9, payment.getStatus());

            if(checkPayment(payment)){
                if(sql.executeUpdate()>0){
                    connection.commit();
                    return true;
                }else{
                    connection.rollback();
                    return false;
                }
            }else{
                connection.rollback();
                return false;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
       return  false;
    }
    public boolean checkPayment(Payments payment) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Payment WHERE Class_Id=? and  R_Year=? and R_Month=? ");
        sql.setObject(1,payment.getClass_Id());
        sql.setObject(2,payment.getRelevent_Year());
        sql.setObject(3,payment.getRelevent_Month());
        boolean x= sql.executeQuery().next();
        if(x==true){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public int deletePayment(String PayCode) {
        return 0;
    }

    @Override
    public ObservableList<PaymentTM> search(String ClassId) throws SQLException {
        boolean b=false;
        ObservableList<PaymentTM> payment= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM Payment WHERE Class_Id=?");
        sql.setObject(1,ClassId);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            b=true;
            payment.add(
                    new PaymentTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDate(7),
                            resultSet.getString(8),
                            resultSet.getString(9)

                    )
            );
        }
        if(b==true){
            return  payment;
        }else{
            return null;
        }

    }

    @Override
    public ArrayList<PaymentMange> All() throws SQLException {
        return null;
    }

    public Boolean checkStClass(String ClassId,String TechId) throws SQLException {

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT  * FROM Classes WHERE Class_Id=? and Teacher_Id=?"
        );
        sql.setObject(1, ClassId);
        sql.setObject(2, TechId);

        boolean x = sql.executeQuery().next();
        return x;
    }

    public Double getCost(String ClassId,String RYear,String RMonth) {

        Double cost = 0.0;

        PreparedStatement sql = null;
        try {

            sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Money WHERE Class_Id=? and Relevent_Year=? and Relevent_Month=?");
            sql.setObject(1,ClassId);
            sql.setObject(2,RYear);
            sql.setObject(3,RMonth);
            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                cost =cost+resultSet.getDouble(4);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cost;
    }

    public ObservableList<PaymentTM> search1(String ClassId,String RYear) throws SQLException {
        boolean b=false;
        ObservableList<PaymentTM> payment= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM Payment WHERE Class_Id=? and R_Year=?");
        sql.setObject(1,ClassId);
        sql.setObject(2,RYear);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            b=true;
            payment.add(
                    new PaymentTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDate(7),
                            resultSet.getString(8),
                            resultSet.getString(9)

                    )
            );
        }
        if(b==true){
            return  payment;
        }else{
            return null;
        }
    }
}
