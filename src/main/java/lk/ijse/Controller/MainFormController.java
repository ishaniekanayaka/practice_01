package lk.ijse.Controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public ImageView imgCustomer;
    public Label lblDescription;
    public ImageView imgItem;
    public AnchorPane root;



    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/item_form.fxml"))));
        stage.setTitle("Login form");
        stage.centerOnScreen();
        stage.show();

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        /*Stage stage = new Stage();*/
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/customer_form.fxml"))));
        stage.setTitle("Login form");
        stage.centerOnScreen();
        stage.show();
    }
}
