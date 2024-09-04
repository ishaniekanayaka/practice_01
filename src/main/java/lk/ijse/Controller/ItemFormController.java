package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Tm.CustomerTm;
import lk.ijse.Tm.ItemTm;
import lk.ijse.db.DBConnection;
import lk.ijse.model.CustomerDTO;
import lk.ijse.model.ItemDTO;
import lk.ijse.repo.CustomerRepo;
import lk.ijse.repo.ItemRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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


    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemList = ItemRepo.getAll();

                for( ItemDTO item : itemList) {
                ItemTm tm = new ItemTm(
                        item.getCode(),
                        item.getDescription(),
                        item.getUnitPrice(),
                        item.getQty()
                );
                obList.add(tm);
            }
            tblItems.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty"));

    }

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

        ItemDTO item = new ItemDTO(code, description, unitPrice, qty);

        try {

            boolean isSaved = ItemRepo.save(item);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item is Saved Successfully").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Item is Saved Unsuccessfully").show();
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
        String description = txtDescription.getText();
        int  unitPrice = Integer.parseInt(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQtyOnHand.getText());

        ItemDTO item = new ItemDTO(code, description, unitPrice, qty);
        try {


            boolean isDeleted = ItemRepo.delete(item);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Item is Deleted Successfully").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Item is Deleted Unsuccessfully").show();

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
