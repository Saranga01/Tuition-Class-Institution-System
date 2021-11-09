package Controller;

import DBConnection.DbConnection;
import Model.TeacherReg;
import TM.TeacherTM;
import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherManageClass implements TeacherMange {


    @Override
    public int register(TeacherReg teacherReg) throws SQLException {


        Connection connection= DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("INSERT INTO Teachers VALUES(?,?,?,?,?,?,?,?,?)");
        sql.setObject(1, teacherReg.getTeacherID());
        sql.setObject(2, teacherReg.getTeacherName());
        sql.setObject(3, teacherReg.getTeacherMobile());
        sql.setObject(4, teacherReg.getTeacherEmail());
        sql.setObject(5, teacherReg.getTeacherBirthday());
        sql.setObject(6, teacherReg.getTeacherSchoolName());
        sql.setObject(7, teacherReg.getTeacherNic());
        sql.setObject(8, teacherReg.getTeacherAddress());
        sql.setObject(9, teacherReg.getRegDate());

        int i=sql.executeUpdate();
        return i;
    }

    @Override
    public int update(TeacherReg teacherReg) throws SQLException {

        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("UPDATE Teachers SET Teacher_Id=?,Teacher_Name=?,Mobile_Num=?,Email=?,Birthday=?,School_Name=?,Nic=?,Address=?,Reg_Date=? WHERE Teacher_Id=?");
        sql.setObject(1, teacherReg.getTeacherID());
        sql.setObject(2, teacherReg.getTeacherName());
        sql.setObject(3, teacherReg.getTeacherMobile());
        sql.setObject(4, teacherReg.getTeacherEmail());
        sql.setObject(5, teacherReg.getTeacherBirthday());
        sql.setObject(6, teacherReg.getTeacherSchoolName());
        sql.setObject(7, teacherReg.getTeacherNic());
        sql.setObject(8, teacherReg.getTeacherAddress());
        sql.setObject(9, teacherReg.getRegDate());
        sql.setObject(10,teacherReg.getTeacherID());

        return sql.executeUpdate();

    }

    @Override
    public int delete(String teacherId) {
        return 0;
    }

    @Override
    public TeacherReg search(String teacherId) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Teachers WHERE Teacher_Id='" + teacherId + "'");

        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()){
           TeacherReg teacherReg=new TeacherReg(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getInt(3),
                   resultSet.getString(4),
                   resultSet.getString(6),
                   resultSet.getString(7),
                   resultSet.getString(8),
                   resultSet.getString(5),
                   resultSet.getDate(9)
           );
           return teacherReg;
       }

        return null;
    }

    @Override
    public ArrayList<TeacherTM> All() throws SQLException {
        ArrayList<TeacherTM> TeacherAr=new ArrayList<>();
        JFXButton btn;
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Teachers");

        ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()){
                btn=new JFXButton("   View   ");
                TeacherAr.add(new TeacherTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        btn
                ));
            }
            return TeacherAr;
        }
        public ArrayList<String> getTeachersId() throws SQLException {
            ArrayList<String> TeacherAr=new ArrayList<>();
            Connection connection= DbConnection.getInstance().getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT Teacher_Id FROM Teachers");

            ResultSet resultSet = sql.executeQuery();

            while (resultSet.next()){
                TeacherAr.add(
                                resultSet.getString(1)
                );
            }
            return TeacherAr;
        }

    }

