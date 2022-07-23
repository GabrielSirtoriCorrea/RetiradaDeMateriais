package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    Alert alert;

    @FXML
    void btnAddComponent(ActionEvent event) {
        if(!txtComponent.getText().equals("") && !txtQtd.getText().equals("")){
            sqlConnection = new SQLConnection();

            sqlConnection.insertComponent(txtComponent.getText(), Integer.parseInt(txtQtd.getText()), 0);
            
            sqlConnection.close();
            App.changeScene(getClass().getResource("/view/Components.fxml"), (Stage) pane.getScene().getWindow());
        
        }else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO!");
            alert.setHeaderText("AVISO!");
            alert.setContentText("Preencha todos os campos!");
            alert.showAndWait();
        }
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