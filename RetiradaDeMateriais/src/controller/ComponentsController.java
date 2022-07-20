package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;
import model.Component;
import model.SQLConnection;

public class ComponentsController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Component, String> tblColumnComponent = new TableColumn<>();

    @FXML
    private TableColumn<Component, Integer> tblColumnUnavailable = new TableColumn<>();

    @FXML
    private TableColumn<Component, Integer> tblColumnAvailable = new TableColumn<>();

    @FXML
    private TableView<Component> tblComponents;

    
    private List<Component> listComponents = new ArrayList<>();
    private SQLConnection sqlConnection;
    private ResultSet result;
    private ObservableList<Component> obsComponents;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnComponent.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnAvailable.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));
        tblColumnUnavailable.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdUnavailable"));

        sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");
        
        result = sqlConnection.getComponent();

        try {
            while(result.next()){
                listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));
            }
        } catch (SQLException e) {
            System.out.println("Falha ao selecionar dados do banco");
            e.printStackTrace();
        }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblComponents.setItems(obsComponents);

        sqlConnection.close();
    }

}