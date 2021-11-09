package Controller;

import Model.EmployeeReg;
import Model.TeacherReg;
import TM.AllEmployeeTM;
import TM.TeacherTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeMange {
    public boolean register(EmployeeReg employeeReg) throws SQLException;
    public int update(EmployeeReg employeeReg) throws SQLException;
    public int delete(String employeeId);
    public EmployeeReg search(String employeeId) throws SQLException;
    public ObservableList<AllEmployeeTM> All() throws SQLException;

}
