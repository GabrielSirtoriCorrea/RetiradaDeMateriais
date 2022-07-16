package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;

public class NewLoanController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> tblColumnStatus;

    @FXML
    private TableColumn<?, ?> tblColumnStatus1;

    @FXML
    private TableColumn<?, ?> tblColumnTime;

    @FXML
    private TableColumn<?, ?> tblColumnTime1;

    @FXML
    private TableView<?> tblLoans;

    @FXML
    private TableView<?> tblLoans1;

    @FXML
    void btnBack(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());

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