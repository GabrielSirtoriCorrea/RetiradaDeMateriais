package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ComponentsController {

    @FXML
    private TableColumn<?, ?> tblColumnComponent;

    @FXML
    private TableColumn<?, ?> tblColumnStatus;

    @FXML
    private TableColumn<?, ?> tblColumnTime;

    @FXML
    private TableView<?> tblLoans;

    @FXML
    void btnLoan(ActionEvent event) {

    }

}