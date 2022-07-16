package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;

public class ComponentsController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> tblColumnComponent;

    @FXML
    private TableColumn<?, ?> tblColumnStatus;

    @FXML
    private TableColumn<?, ?> tblColumnTime;

    @FXML
    private TableView<?> tblLoans;

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

    @FXML
    void btnNewComponent(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewComponent.fxml"), (Stage) pane.getScene().getWindow());
    }

}