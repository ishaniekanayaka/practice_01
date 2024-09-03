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
import lk.ijse.Tm.ItemTm;
import lk.ijse.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemFormController {
    public AnchorPane rootItem;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<ItemTm> tblItems;
    public TableColumn<?,?> colCode;
    public TableColumn<?,?> colDescription;
    public TableColumn<?,?> colQTYOnHand;
    public TableColumn<?,?> colUnitPrice;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) rootItem.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/main_form.fxml"))));
        stage.setTitle("Login form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        int  unitPrice = Integer.parseInt(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQtyOnHand.getText());

        String sql = "INSERT INTO item(code, description, unitPrice, qty) VALUES(?, ?, ?,?)";
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,code);
            pstm.setString(2, description);
            pstm.setInt(3, unitPrice);
            pstm.setInt(4, qty);

            boolean isSaved = pstm.executeUpdate() > 0;
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item is Saved Successfully").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String code = txtCode.getText();
        String sql = "DELETE FROM item WHERE code=?";
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, code);

            boolean isDeleted = pstm.executeUpdate() > 0;
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Item is Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String code = txtCode.getText();
        String sql = "SELECT * FROM item WHERE code=?";
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, code);
            java.sql.ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                txtDescription.setText(resultSet.getString("description"));
                txtUnitPrice.setText(String.valueOf(resultSet.getInt("unitPrice")));
                txtQtyOnHand.setText(String.valueOf(resultSet.getInt("qty")));
            } else {
                new Alert(Alert.AlertType.ERROR, "Item not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
