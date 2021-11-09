package Controller;

import Model.ClassFee;
import Model.Payments;
import TM.ClassFeeTM;
import TM.PaymentTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentMange {
    public boolean Pay(Payments payment) throws SQLException;
    public int deletePayment(String PayCode);
    public ObservableList<PaymentTM> search(String PayCode) throws SQLException;
    public ArrayList<PaymentMange> All() throws SQLException;
}
