package Controller;

import DBConnection.DbConnection;
import Model.*;
import TM.ClassTM;
import TM.ScheduleTM;
import TM.StudentTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassMangeClass implements ClassMange {

    @Override
    public boolean register(ClassReg Class) {
        Connection connection = null;
        connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO Classes VALUES(?,?,?,?,?,?,?,?,?)");
            sql.setObject(1, Class.getClassId());
            sql.setObject(2, Class.getTeacherId());
            sql.setObject(3, Class.getSubjectName());
            sql.setObject(4, Class.getClassFee());
            sql.setObject(5, Class.getGrade());
            sql.setObject(6, Class.getTitle());
            sql.setObject(7, Class.getClassType());
            sql.setObject(8, Class.getShortDesc());
            sql.setObject(9, Class.getRegDate());

            if (sql.executeUpdate() > 0) {
                if (setSchedule(Class.getSchedule())) {
                    connection.commit();
                    return true;
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
        }finally {
            try {

                connection.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }


    public boolean setSchedule(ArrayList<ScheduleTM> schedule) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();


        for (ScheduleTM Schedule:schedule
             ) {
            PreparedStatement sql = connection.prepareStatement("INSERT INTO Shedule VALUES(?,?,?,?,?)");
            sql.setObject(1,Schedule.getClassId());
            sql.setObject(2,Schedule.getScheduleType());
            sql.setObject(3,Schedule.getDate());
            sql.setObject(4,Schedule.getDay());
            sql.setObject(5,Schedule.getTime());

            if(sql.executeUpdate()>0){
                
            }else{
                return false;
            }
        }
        return true;
    }

    static String classId;
    static String date;
    static String day;
    static String time;

    public int updateSchedule(ScheduleTM Schedule,GetDT DT) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql=connection.prepareStatement("UPDATE Shedule SET Class_Id=?,Shedule_Type=?,`Date`=?,`Day`=?,`Time`=? WHERE Class_Id=? and `Date`=? and `Day`=? and `Time`=?");
        sql.setObject(1,Schedule.getClassId());
        sql.setObject(2,Schedule.getScheduleType());
        sql.setObject(3,Schedule.getDate());
        sql.setObject(4,Schedule.getDay());
        sql.setObject(5,Schedule.getTime());
        sql.setObject(6,Schedule.getClassId());
        sql.setObject(7,DT.getDate());
        sql.setObject(8,DT.getDay());
        sql.setObject(9,DT.getTime());

        return sql.executeUpdate();

    }



    @Override
    public int update(ClassReg Class) throws SQLException {


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql=connection.prepareStatement("UPDATE Classes SET Class_Id=?,Teacher_Id=?,Subject_Name=?,Class_Fee=?,Gread=?,Title=?,Class_Type=?,Short_Desc=?,Reg_Date=? WHERE  Class_Id=?");
        sql.setObject(1,Class.getClassId());
        sql.setObject(2,Class.getTeacherId());
        sql.setObject(3,Class.getSubjectName());
        sql.setObject(4,Class.getClassFee());
        sql.setObject(5,Class.getGrade());
        sql.setObject(6,Class.getTitle());
        sql.setObject(7,Class.getClassType());
        sql.setObject(8,Class.getShortDesc());
        sql.setObject(9,Class.getRegDate());
        sql.setObject(10,Class.getClassId());
        return sql.executeUpdate();

    }

    @Override
    public int delete(String ClassId) throws SQLException {

        int delete_from_students = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Classes WHERE Class_Id='"+ClassId+"'").executeUpdate();
        return delete_from_students;
    }

    @Override
    public ClassReg search(String ClassId) throws SQLException {
        ArrayList<ScheduleTM> schedule = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Shedule WHERE Class_Id='" + ClassId + "'");
        ResultSet resultSet1 = sql.executeQuery();
        while (resultSet1.next()) {
            schedule.add(
                    new ScheduleTM(resultSet1.getString(1), resultSet1.getString(2), resultSet1.getString(3), resultSet1.getString(4), resultSet1.getString(5)));

        }


        PreparedStatement sql1 = connection.prepareStatement("SELECT * FROM Classes WHERE Class_Id='" + ClassId + "'");

        ResultSet resultSet = sql1.executeQuery();
        if(resultSet.next()){
            ClassReg classReg=new ClassReg(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getDate(9),
                    schedule

            );
            return classReg;
        }else{
            return null;
        }


    }

    @Override
    public ArrayList<ClassTM> All() throws SQLException {
        ArrayList<ClassTM> ClassAr = new ArrayList<>();
        JFXButton btn;
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Classes");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()) {
            btn = new JFXButton("   View   ");
            ClassAr.add(new ClassTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    btn
            ));
        }
        return ClassAr;
    }

    public ObservableList<StudentTM> allStudents(String ClassId) throws SQLException {

        ObservableList<StudentTM> studentTM= FXCollections.observableArrayList();

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("SELECT * FROM StudentClass WHERE Class_Id='" + ClassId + "'");

        ResultSet resultSet = sql.executeQuery();
        boolean b=false;
        while (resultSet.next()){
            b=true;
            PreparedStatement sql1 = connection.prepareStatement("SELECT * FROM Students WHERE Stud_Id='" + resultSet.getString(1) + "'");
            ResultSet resultSet1 = sql1.executeQuery();
            JFXButton button=new JFXButton();
            if(resultSet1.next()){
                studentTM.add(
                        new StudentTM(
                                resultSet1.getString(1),
                                resultSet1.getString(2),
                                button
                        )
                );
            }
        }
        if(b){
            return studentTM;
        }else{
            return null;
        }

    }
}
