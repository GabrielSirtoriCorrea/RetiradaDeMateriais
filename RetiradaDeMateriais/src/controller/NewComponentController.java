package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;
import model.SQLConnection;

public class NewComponentController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtComponent;

    @FXML
    private TextField txtQtd;

    SQLConnection sqlConnection;

    @FXML
    void btnAddComponent(ActionEvent event) {
        sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");

        sqlConnection.insertComponent(txtComponent.getText(), Integer.parseInt(txtQtd.getText()), 0);
    }

    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());

    }

    @FXML
    void btnComponents(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnHistory(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/History.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }

}