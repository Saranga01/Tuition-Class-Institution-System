package Controller;

import DBConnection.DbConnection;
import Model.Attendence;
import TM.AttendenceTM;
import TM.StudentListTM;
import TM.TodayClassTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class AttendenceManageClass {

    public ObservableList<TodayClassTM>  getTodayClass(String Date,String Day) throws SQLException {


        ObservableList<TodayClassTM> TodayCls= FXCollections.observableArrayList();

        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Shedule WHERE `Date`='" + Date + "'or `Day`='" + Day + "'");
        ResultSet resultSet = sql.executeQuery();
        while (resultSet.next()){
            TodayCls.add(
                    new TodayClassTM(
                            resultSet.getString(1),
                            resultSet.getString(5)
                    )
            );
        }

        return TodayCls;

    }


    public boolean setStudentList(String ClassId) throws SQLException {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime CurrentTime = LocalTime.now();
        String Time;
        if (CurrentTime.getMinute() < 10) {
            Time = (CurrentTime.getHour() + ":0" + CurrentTime.getMinute());
        } else {
            Time = (CurrentTime.getHour() + ":" + CurrentTime.getMinute());
        }


        ObservableList<StudentListTM> studentList = FXCollections.observableArrayList();

        Connection connection = DbConnection.getInstance().getConnection();


            PreparedStatement sql = connection.prepareStatement("SELECT * FROM StudentClass WHERE Class_Id='" + ClassId + "'");

            ResultSet resultSet = sql.executeQuery();
            dup:
            while (resultSet.next()) {
                PreparedStatement sql2 = connection.prepareStatement("SELECT * FROM Students WHERE Stud_Id='" + resultSet.getString(1) + "'");
                ResultSet resultSet1 = sql2.executeQuery();
                resultSet1.next();
                PreparedStatement sql5 = connection.prepareStatement("SELECT * FROM Attendence WHERE Class_Id='" + resultSet.getString(2) + "' and Stud_Id='" + resultSet.getString(1) + "' and '" + f.format(date) + "'");
                ResultSet resultSet6 = sql5.executeQuery();
                if (resultSet6.next()) {
                    continue dup;
                } else {
                    PreparedStatement sql3 = connection.prepareStatement("INSERT INTO Attendence VALUES (?,?,?,?,?,?)");
                    sql3.setObject(1, resultSet.getString(2));
                    sql3.setObject(2, resultSet.getString(1));
                    sql3.setObject(3, resultSet1.getString(2));
                    sql3.setObject(4, date);
                    sql3.setObject(5, Time);
                    sql3.setObject(6, "Absent");

                    sql3.executeUpdate();
                }
            }
                return true;
    }

    public ObservableList<StudentListTM> studentListObs(String ClassId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        ObservableList<StudentListTM> studentListObs=FXCollections.observableArrayList();
        PreparedStatement sql4 = connection.prepareStatement("SELECT * FROM Attendence WHERE Class_Id=? and `Date`=?");
        sql4.setObject(1,ClassId);
        sql4.setObject(2,f.format(date));
        ResultSet resultSet2 = sql4.executeQuery();

        while (resultSet2.next()){

            studentListObs.add(
                    new StudentListTM(
                            resultSet2.getString(1),
                            resultSet2.getString(2),
                            resultSet2.getString(3),
                            resultSet2.getString(6)
                    )
            );

        }
        return  studentListObs;
    }


    public boolean updateAttendence(Attendence attend) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("UPDATE Attendence SET Stutus=? WHERE Class_Id=? and Stud_Id=? and `Date`=?");
        sql.setObject(1,attend.getStatus());
        sql.setObject(2,attend.getClass_id());
        sql.setObject(3,attend.getStud_Id());
        sql.setObject(4,attend.getDate());

        return sql.executeUpdate()>0;

    }

    public ObservableList <StudentListTM> studentSearch(String Stud_Id,String Class_Id,String date) throws SQLException {


        ObservableList<StudentListTM> studentListSearch=FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  Attendence  WHERE CONCAT(Stud_Id) LIKE '%" + Stud_Id + "%' and Class_Id= '"+Class_Id+"' and `Date`='"+date+"' ");
        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){

            studentListSearch.add(
                    new StudentListTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(6)
                    )
            );
        }
        return studentListSearch;

    }

    public ObservableList<AttendenceTM> getAttendence(String class_Id,String date) throws SQLException {

        ObservableList<AttendenceTM> attendence=FXCollections.observableArrayList();
        PreparedStatement sql = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Attendence WHERE Class_Id='" + class_Id + "' and `Date`='" + date + "'");
        ResultSet resultSet = sql.executeQuery();

        boolean b=false;
        while (resultSet.next()){
            b=true;
            attendence.add(
                    new AttendenceTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        if(b==true) {
            return attendence;
        }else{
            return null;
        }


    }


}
















