package Controller;

import DBConnection.DbConnection;
import Model.ClassFee;
import TM.ClassFeeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.DoubleBinaryOperator;

public class ClassFeeManageClass implements FeeManage {

    @Override
    public boolean PayFee(ClassFee classFee) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
            try {
                connection.setAutoCommit(false);
                PreparedStatement sql = connection.prepareStatement("INSERT INTO ClassFee VALUES(?,?,?,?,?,?,?,?,?) ");
                sql.setObject(1, classFee.getFee_Id());
                sql.setObject(2, classFee.getStud_Id());
                sql.setObject(3, classFee.getClass_Id());
                sql.setObject(4, classFee.getFe_eCost());
                sql.setObject(5, classFee.getPay_Date());
                sql.setObject(6, classFee.getPay_Time());
                sql.setObject(7, classFee.getRelevent_Year());
                sql.setObject(8, classFee.getRelevent_Month());
                sql.setObject(9, classFee.getStatus());

                if (checkClassFee(classFee)) {
                    if (setMoney(classFee)) {
                        if (sql.executeUpdate() > 0) {
                            connection.commit();
                            return true;
                        } else {
                            return false;
                        }

                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return false;

    }


    public boolean setMoney(ClassFee classFee) throws SQLException {
        Double InstituteProfit;
        Double ClassProfit;

        InstituteProfit=classFee.getFe_eCost()/100*classFee.getCommiss();
        ClassProfit=classFee.getFe_eCost()-InstituteProfit;

        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("INSERT INTO Money VALUES(?,?,?,?,?) ");

        sql.setObject(1,classFee.getClass_Id());
        sql.setObject(2,classFee.getRelevent_Year());
        sql.setObject(3,classFee.getRelevent_Month());
        sql.setObject(4,ClassProfit);
        sql.setObject(5,InstituteProfit);

        if(sql.executeUpdate()>0){
                return true;

        }else{
            return false;
        }

    }

    String RYear;
    String RMonth;
    public boolean updateMoney(ClassFee classFee) throws SQLException {


        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql1=connection.prepareStatement("SELECT * FROM ClassFee WHERE Fee_Id='"+classFee.getFee_Id()+"'");

        ResultSet resultSet = sql1.executeQuery();

        if(resultSet.next()) {
            RYear = resultSet.getString(7);
            RMonth = resultSet.getString(8);
        }


        try {
            PreparedStatement sql = connection.prepareStatement("UPDATE Money SET Relevent_Year=?,Relevent_Month=? WHERE Class_Id=? and Relevent_Year=? and Relevent_Month=?");

            sql.setObject(1,classFee.getRelevent_Year());
            sql.setObject(2,classFee.getRelevent_Month());
            sql.setObject(3,classFee.getClass_Id());
            sql.setObject(4,RYear);
            sql.setObject(5,RMonth);

            return sql.executeUpdate()>0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean checkClassFee(ClassFee classFee) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM ClassFee WHERE Stud_Id=? and Class_Id=? and Relevent_Year=? and Relevent_Month=? ");
            sql.setObject(1,classFee.getStud_Id());
            sql.setObject(2,classFee.getClass_Id());
            sql.setObject(3,classFee.getRelevent_Year());
            sql.setObject(4,classFee.getRelevent_Month());
            boolean x= sql.executeQuery().next();
            if(x==true){
                return false;
            }else{
                return true;
            }

    }

    @Override
    public int updateFee(ClassFee ClassFee) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement sql2 = connection.prepareStatement("SELECT * FROM ClassFee WHERE Class_Id=? and Stud_Id=? and Relevent_Year=? and Relevent_Month=?");
        sql2.setObject(1,ClassFee.getClass_Id());
        sql2.setObject(2,ClassFee.getStud_Id());
        sql2.setObject(3,ClassFee.getRelevent_Year());
        sql2.setObject(4,ClassFee.getRelevent_Month());

        boolean b = sql2.executeQuery().next();
        if(b){
            return -1;
        }else {
            try {
                connection.setAutoCommit(false);
                PreparedStatement sql = connection.prepareStatement("UPDATE ClassFee SET Fee_Id=?,Stud_Id=?,Class_Id=?,Fee_Cost=?,Pay_Date=?,Pay_Time=?,Relevent_Year=?,Relevent_Month=?,Stutus=? WHERE Fee_Id=?");
                sql.setObject(1, ClassFee.getFee_Id());
                sql.setObject(2, ClassFee.getStud_Id());
                sql.setObject(3, ClassFee.getClass_Id());
                sql.setObject(4, ClassFee.getFe_eCost());
                sql.setObject(5, ClassFee.getPay_Date());
                sql.setObject(6, ClassFee.getPay_Time());
                sql.setObject(7, ClassFee.getRelevent_Year());
                sql.setObject(8, ClassFee.getRelevent_Month());
                sql.setObject(9, ClassFee.getStatus());
                sql.setObject(10, ClassFee.getFee_Id());

                if (updateMoney(ClassFee)) {
                    if (sql.executeUpdate() > 0) {
                        connection.commit();
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
            return 0;
        }
    }

    @Override
    public int delete(String ClassFeeId) {
        return 0;
    }

    @Override
    public ObservableList<ClassFeeTM> search(String ClassFeeId) throws SQLException {
     boolean b=false;
        ObservableList<ClassFeeTM> classFee= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM ClassFee WHERE Class_Id=?");
        sql.setObject(1,ClassFeeId);

        ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()) {
                b=true;
                classFee.add(
                        new ClassFeeTM(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getDate(5),
                                resultSet.getString(6),
                                resultSet.getDouble(4),
                                resultSet.getString(9)

                        )
                );
            }
            if(b==true){
                return  classFee;
            }else{
                return null;
            }

    }

    public ObservableList<ClassFeeTM> search1(String ClassFeeId,String RMonth,String RYear) throws SQLException {
        boolean b=false;
        ObservableList<ClassFeeTM> classFee= FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT *FROM ClassFee WHERE Class_Id=? and Relevent_Year=? and Relevent_Month=?");
        sql.setObject(1,ClassFeeId);
        sql.setObject(2,RYear);
        sql.setObject(3,RMonth);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            b=true;
            classFee.add(
                    new ClassFeeTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getDate(5),
                            resultSet.getString(6),
                            resultSet.getDouble(4),
                            resultSet.getString(9)

                    )
            );
        }
        if(b==true){
            return  classFee;
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<ClassFee> All() throws SQLException {
        return null;
    }

    public Boolean checkStClass(String StudId,String ClassId) throws SQLException {

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT  * FROM StudentClass WHERE Stud_Id=? and Class_Id=?"
        );
        sql.setObject(1, StudId);
        sql.setObject(2, ClassId);

        boolean x = sql.executeQuery().next();
        return x;
    }
    public Double getCost(String classId) throws SQLException {

        Double cost=0.0;

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Classes WHERE Class_Id='" + classId + "'");
        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
          cost= resultSet.getDouble(4);
        }

        return cost;
    }

    public int classCount(String ClassId) throws SQLException {
        int count=0;

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM StudentClass WHERE Class_Id=?");
        sql.setObject(1,ClassId);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            count++;
        }

        System.out.println(count);
        return count;
    }

    public int ClassFeeCount(String ClassID,String RYear,String RMonth) throws SQLException {
        int count=0;

        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM ClassFee WHERE Class_Id=? and Relevent_Year=? and Relevent_Month=? ");
        sql.setObject(1,ClassID);
        sql.setObject(2,RYear);
        sql.setObject(3,RMonth);

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            count++;
        }
        System.out.println(count);
        return count;

    }
}
