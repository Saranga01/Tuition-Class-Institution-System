package Controller;

import DBConnection.DbConnection;
import Model.EmployeeReg;
import Model.TeacherReg;
import TM.AllEmployeeTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManageClass implements EmployeeMange {


    @Override
    public boolean register(EmployeeReg employeeReg) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)");

        sql.setObject(1,employeeReg.getE_Id());
        sql.setObject(2,employeeReg.getE_Name());
        sql.setObject(3,employeeReg.getGender());
        sql.setObject(4,employeeReg.getMobile_Num());
        sql.setObject(5,employeeReg.getEmail());
        sql.setObject(6,employeeReg.getAddress());
        sql.setObject(7,employeeReg.getNic());
        sql.setObject(8,employeeReg.getReg_Date());
        sql.setObject(9,employeeReg.getJob_Title());

        if(sql.executeUpdate()>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public int update(EmployeeReg employeeReg) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement sql = connection.prepareStatement("UPDATE Employee SET Employee_Id=?,Employee_Name=?,Gender=?,Mobile_Num=?, Email=?,Address=?,Nic=?,Reg_Date=?,Job_Title=? WHERE Employee_Id=?");

        sql.setObject(1, employeeReg.getE_Id());
        sql.setObject(2, employeeReg.getE_Name());
        sql.setObject(3, employeeReg.getGender());
        sql.setObject(4, employeeReg.getMobile_Num());
        sql.setObject(5, employeeReg.getEmail());
        sql.setObject(6, employeeReg.getAddress());
        sql.setObject(7, employeeReg.getNic());
        sql.setObject(8, employeeReg.getReg_Date());
        sql.setObject(9, employeeReg.getJob_Title());
        sql.setObject(10, employeeReg.getE_Id());

        return sql.executeUpdate();

    }

    @Override
    public int delete(String employeeId) {
        return 0;
    }

    @Override
    public EmployeeReg search(String employeeId) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        PreparedStatement sql = connection.prepareStatement("SELECT * FROM Employee WHERE Employee_Id='" + employeeId + "'");

        ResultSet resultSet = sql.executeQuery();
        if(resultSet.next()){
            EmployeeReg employeeReg=new EmployeeReg(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDate(8),
                    resultSet.getString(9)
            );
            return employeeReg;
        }else {
            return null;
        }
    }



    @Override
    public ObservableList<AllEmployeeTM> All() throws SQLException {


        ObservableList<AllEmployeeTM> employeeTm= FXCollections.observableArrayList();


        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();

        while (resultSet.next()){
            JFXButton btn=new JFXButton(" View ");
            employeeTm.add(
                    new AllEmployeeTM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(6),
                            btn

                    )
            );
        }
        return employeeTm;
    }
}
