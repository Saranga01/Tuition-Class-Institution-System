package Controller;

import DBConnection.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Suggest {

    public ObservableList<String> getStudId() throws SQLException {

        ObservableList<String> studId= FXCollections.observableArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Students").executeQuery();

        while (resultSet.next()){
            studId.add(
                    resultSet.getString(1)
            );
        }

        return studId;
    }
    public ObservableList<String> getEmployeeId() throws SQLException {
        ObservableList<String> EId= FXCollections.observableArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Employee").executeQuery();

        while (resultSet.next()){
            EId.add(
                    resultSet.getString(1)
            );
        }

        return EId;

    }

}
