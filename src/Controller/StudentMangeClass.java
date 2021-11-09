package Controller;

import DBConnection.DbConnection;
import Model.*;
import TM.StudentClassesTM;
import TM.StudentTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentMangeClass implements StudentMange{
    @Override
    public int register(StudentReg studentReg) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO Students VALUES(?,?,?,?,?,?,?,?,?,?)");
            sql.setObject(1, studentReg.getStud_Id());
            sql.setObject(2, studentReg.getStud_Name());
            sql.setObject(3, studentReg.getMobile_Num());
            sql.setObject(4, studentReg.getEmail());
            sql.setObject(5, studentReg.getBirthday());
            sql.setObject(6, studentReg.getAddress());
            sql.setObject(7, studentReg.getGender());
            sql.setObject(8, studentReg.getTrustee_Name());
            sql.setObject(9, studentReg.getNic());
            sql.setObject(10, studentReg.getReg_Date());

            if (sql.executeUpdate() > 0) {
                if (RegClassStudent(studentReg.getClass_id(),studentReg.getStud_Id()) > 0) {
                    connection.commit();
                    return 1;
                } else {
                    connection.rollback();
                    return 0;
                }
            } else {
                connection.rollback();
                return 0;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return 0;
    }

    public int RegClassStudent(String Class_Id,String Stud_Id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("INSERT INTO StudentClass VALUES(?,?)");
        sql.setObject(1,Stud_Id);
        sql.setObject(2,Class_Id);

        return sql.executeUpdate();

    }

    @Override
    public int update(StudentReg studentReg) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("UPDATE Students SET Stud_Id=?,Stud_Name=?,Mobile_Num=?,Email=?,Birthday=?,Address=?,Gender=?,Trustee_Name=?,Nic=?,Reg_Date=?WHERE Stud_Id=?");
        sql.setObject(1, studentReg.getStud_Id());
        sql.setObject(2, studentReg.getStud_Name());
        sql.setObject(3, studentReg.getMobile_Num());
        sql.setObject(4, studentReg.getEmail());
        sql.setObject(5, studentReg.getBirthday());
        sql.setObject(6, studentReg.getAddress());
        sql.setObject(7, studentReg.getGender());
        sql.setObject(8, studentReg.getTrustee_Name());
        sql.setObject(9,studentReg.getNic());
        sql.setObject(10, studentReg.getReg_Date());
        sql.setObject(11,studentReg.getStud_Id());



        return sql.executeUpdate();
    }

    @Override
    public int delete(String studentId) {
        return 0;
    }

    @Override
    public StudentReg search(String studentId) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Students WHERE Stud_Id='" + studentId + "'");

        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()){
            StudentReg studentReg=new StudentReg(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getDate(10),
                    "Null"
            );
            return studentReg;
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<StudentTM> All() throws SQLException {

        ObservableList<StudentTM> StudentsAr=FXCollections.observableArrayList();
        JFXButton btn;
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Students");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){
            btn=new JFXButton("   View   ");
            StudentsAr.add(new StudentTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    btn
            ));
        }
        return StudentsAr;
    }

    public ArrayList<String> getCLassID() throws SQLException {
        ArrayList<String> ClassID = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Classes");
        ResultSet resultSet = sql.executeQuery();
        while (resultSet.next()) {
            ClassID.add(
                    resultSet.getString(1)

            );
        }
        return ClassID;

    }

    public ObservableList<StudentClassesTM> getStudentClasses(String StudentID) throws SQLException {

        ObservableList<StudentClassesTM> StudentClasses= FXCollections.observableArrayList();

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM StudentClass WHERE Stud_Id='" + StudentID + "'");

        ResultSet resultSet = sql.executeQuery();

        while (resultSet.next()){

            StudentClasses.addAll(
                    new StudentClassesTM(
                            resultSet.getString(2)
                    )
            );
        }
        return StudentClasses;

    }
    public int StudentAddNewClass(StudentAddNewClass addNewClass) throws SQLException {



        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql1 = connection.prepareStatement("SELECT  * FROM StudentClass WHERE Class_Id=? and Stud_Id=?");
        sql1.setObject(1,addNewClass.getClass_Id());
        sql1.setObject(2,addNewClass.getStud_Id());
        ResultSet resultSet = sql1.executeQuery();

        if(resultSet.next()){
            return 0;
        }else {

            PreparedStatement sql = connection.prepareStatement("INSERT INTO StudentClass VALUES(?,?)");
            sql.setObject(1, addNewClass.getStud_Id());
            sql.setObject(2, addNewClass.getClass_Id());
            return sql.executeUpdate();
        }
    }

}
