package Controller;

import Model.StudentReg;
import TM.StudentTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface StudentMange {
    public int register(StudentReg studentReg) throws SQLException;
    public int update(StudentReg studentReg) throws SQLException;
    public int delete(String studentId);
    public StudentReg search(String studentId) throws SQLException;
    public ObservableList<StudentTM> All() throws SQLException;

}
