package Controller;

import DBConnection.DbConnection;
import TM.ClassHomeTM;
import TM.TodayClassTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class HomeMangeClass {


    public int getStudentCount() throws SQLException {

        int sCount = 0;
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Students");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            sCount++;
        }
        return sCount;
    }

    public int getTeacherCount() throws SQLException {

        int TCount=0;
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Teachers");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            TCount++;
        }
        return TCount;
    }

    public int getClassCount() throws SQLException {

        int CCount=0;
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Classes");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            CCount++;
        }
        return CCount;
    }

    public int thisMonthSReg() throws SQLException {
        int sRegCount = 0;
        String month;


        int monthValue= new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        if(monthValue<10){
            month="0"+monthValue;
        }else{
            month= String.valueOf(monthValue);
        }

        Connection connection = DbConnection.getInstance().getConnection();

        ResultSet resultSet = connection.prepareStatement("SELECT * FROM Students").executeQuery();


        while (resultSet.next()){
            String date=resultSet.getString(10);
            String s = date.split("-")[1];
            if(month.equals(s)){
                sRegCount++;
            }else{
            }

        }
        return sRegCount;
    }
    public int getThisMonthTReg() throws SQLException {
        int tRegCount=0;
        String month;
        int monthValue= new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        if(monthValue<10){
            month="0"+monthValue;
        }else{
            month= String.valueOf(monthValue);
        }
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet1 = connection.prepareStatement("SELECT * FROM Teachers").executeQuery();
        b:
        while (resultSet1.next()){
            String date1=resultSet1.getString(9);
            String s = date1.split("-")[1];
            if(month.equals(s)){
                tRegCount++;
            }else {
                continue b;
            }
        }
        return tRegCount;
    }
    public int getThisMonthCReg() throws SQLException {
        int cRegCount=0;
        String month;
        int monthValue= new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        if(monthValue<10){
            month="0"+monthValue;
        }else{
            month= String.valueOf(monthValue);
        }
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet2 = connection.prepareStatement("SELECT * FROM Classes").executeQuery();
        c:
        while (resultSet2.next()){
            String date1=resultSet2.getString(9);
            String s = date1.split("-")[1];
            if(month.equals(s)){
                cRegCount++;
            }else {
                continue c;
            }
        }
        return cRegCount;

    }
    public int getThisMonthEReg() throws SQLException {
        int eRegCount=0;
        String month;
        int monthValue= new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        if(monthValue<10){
            month="0"+monthValue;
        }else{
            month= String.valueOf(monthValue);
        }
        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet3 = connection.prepareStatement("SELECT * FROM Employee").executeQuery();
        d:
        while (resultSet3.next()){
            String date1=resultSet3.getString(8);
            String s = date1.split("-")[1];
            if(month.equals(s)){
                eRegCount++;
            }else {
                continue d;
            }
        }
        return eRegCount;

    }

    public ArrayList<Integer> getGenderCAndThisMPaid(String male) throws SQLException {
        String month;
        int monthValue = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        if (monthValue < 10) {
            month = "0" + monthValue;
        } else {
            month = String.valueOf(monthValue);
        }
            ArrayList<Integer> GAndPaidCount = new ArrayList<>();

            int maleCount = 0;

            int PaidCount = 0;


            Connection connection = DbConnection.getInstance().getConnection();

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM  Students WHERE Gender='" + male + "'").executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(7).equals("male")) {
                    maleCount++;
                } else {

                }
            }

            ResultSet resultSet1 = connection.prepareStatement("SELECT * FROM Payment").executeQuery();
            int teacherCount=new HomeMangeClass().getTeacherCount();
            int studCount=new HomeMangeClass().getStudentCount();

            d:
            while (resultSet1.next()) {
                String date1 = resultSet1.getString(7);
                String s = date1.split("-")[1];
                if (month.equals(s)) {
                    PaidCount++;
                } else {

                }
            }

            int notPaid = teacherCount-PaidCount;
            int femaleCount = studCount-maleCount;
            GAndPaidCount.add(maleCount);
            GAndPaidCount.add(femaleCount);
            GAndPaidCount.add(PaidCount);
            GAndPaidCount.add(notPaid);

            return GAndPaidCount;
        }

    public ObservableList<ClassHomeTM> getTodayClass(String Date, String Day) throws SQLException {


        ObservableList<ClassHomeTM> TodayCls= FXCollections.observableArrayList();

        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Shedule WHERE `Date`='" + Date + "'or `Day`='" + Day + "'");
        ResultSet resultSet = sql.executeQuery();
        while (resultSet.next()){
            ResultSet resultSet1 = connection.prepareStatement("SELECT * FROM Classes WHERE Class_Id='" + resultSet.getString(1) + "'").executeQuery();
          if(resultSet1.next()) {
              TodayCls.add(
                      new ClassHomeTM(
                              resultSet.getString(1),
                              resultSet1.getString(2),
                              resultSet.getString(5)
                      )
              );
          }
        }

        return TodayCls;

    }


}
