package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Tm.CustomerTm;
import lk.ijse.db.DBConnection;
import lk.ijse.model.CustomerDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormCotroller {
    public AnchorPane rootsCus;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn<?,?> colCusId;
    public TableColumn<?,?> colCusName;
    public TableColumn<?,?> colCusAddress;
    public JFXButton btnSave;
    public JFXButton btnDelete;

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) rootsCus.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/main_form.fxml"))));
        stage.setTitle("Login form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String cusId = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();

        String sql = "INSERT INTO customer(cusId, name, address) VALUES(?, ?, ?)";
        try {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cusId);
        pstm.setString(2, name);
        pstm.setString(3, address);

        boolean isSaved = pstm.executeUpdate() > 0;
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Customer is Saved Successfully").show();
        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
    clearFields();
}

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String cusId = txtCustomerId.getText();
        String sql = "DELETE FROM customer WHERE cusId =?";
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, cusId);

            boolean isDeleted = pstm.executeUpdate() > 0;
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Customer is Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        clearFields();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String cusId = txtCustomerId.getText();
        String sql = "SELECT * FROM customer WHERE cusId =?";
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, cusId);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                txtCustomerName.setText(resultSet.getString("name"));
                txtCustomerAddress.setText(resultSet.getString("address"));
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
