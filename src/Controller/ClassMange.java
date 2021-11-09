package Controller;

import Model.ClassReg;
import TM.ClassTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassMange {
    public boolean register(ClassReg Class) throws SQLException;
    public int update(ClassReg Class) throws SQLException;
    public int delete(String ClassId) throws SQLException;
    public ClassReg search(String ClassId) throws SQLException;
    public ArrayList<ClassTM> All() throws SQLException;
}
