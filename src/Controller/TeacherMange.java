package Controller;

import Model.TeacherReg;
import TM.TeacherTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherMange {
    public int register(TeacherReg teacherReg) throws SQLException;
    public int update(TeacherReg teacherReg) throws SQLException;
    public int delete(String teacherId);
    public TeacherReg search(String teacherId) throws SQLException;
    public ArrayList<TeacherTM> All() throws SQLException;
}
