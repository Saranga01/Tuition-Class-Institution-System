package Controller;

import Model.ClassFee;
import TM.ClassFeeTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeeManage {
    public boolean PayFee(ClassFee classFee) throws SQLException;
    public int updateFee(ClassFee ClassFee) throws SQLException;
    public int delete(String ClassFeeId);
    public ObservableList<ClassFeeTM> search(String ClassFeeId) throws SQLException;
    public ArrayList<ClassFee> All() throws SQLException;

}
