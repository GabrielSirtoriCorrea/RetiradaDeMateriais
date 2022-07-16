package controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;

public class HomeScreenController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> tblColumnComponent;

    @FXML
    private TableColumn<?, ?> tblColumnDay;

    @FXML
    private TableColumn<?, ?> tblColumnName;

    @FXML
    private TableColumn<?, ?> tblColumnStatus;

    @FXML
    private TableColumn<?, ?> tblColumnTime;

    @FXML
    private TableView<?> tblLoans;

    @FXML
    void btnComponents(ActionEvent event) {

    }

    @FXML
    void btnHistory(ActionEvent event) {

    }

    @FXML
    void btnLoan(ActionEvent event) {
        App.changeScene(getClass().getResource("/view/NewLoan.fxml"), (Stage) pane.getScene().getWindow());
    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    

}