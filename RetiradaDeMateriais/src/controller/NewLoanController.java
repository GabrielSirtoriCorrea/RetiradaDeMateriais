package controller;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;
import model.Component;
import model.SQLConnection;

public class NewLoanController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Component> tblAvailableComponents;

    @FXML
    private TableColumn<Component, String> tblColumnAvailableComponents;

    @FXML
    private TableColumn<Component, Integer> tblColumnAvailableQtd;

    @FXML
    private TableColumn<Component, String> tblColumnLoanComponents;

    @FXML
    private TableColumn<Component, Integer> tblColumnLoanQtd;

    @FXML
    private TableView<Component> tblLoanComponents;
    
    @FXML
    private TextField txtQtd;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtName;

    private List<Component> listComponents = new ArrayList<>();
    private List<Component> listLoanComponents = new ArrayList<>();
    private SQLConnection sqlConnection;
    private ResultSet result;
    private ObservableList<Component> obsComponents;
    private int selectedItem;
    private Component component;
    private Alert alert;

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
    void btnMakeLoan(ActionEvent event) {
        if(!listLoanComponents.isEmpty() && !txtName.getText().equals("")){
            sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");

            for(int c = 0; c<listLoanComponents.size(); c++){
                try {
                    System.out.println("ADICIONANDO COMPONENTES");
                    sqlConnection.insertLoan(txtName.getText(), listLoanComponents.get(c).getId(), Integer.parseInt(txtQtd.getText()),  new Date(new java.util.Date().getTime()), true);
                    result = sqlConnection.getComponent("Id", listLoanComponents.get(c).getId());
                    System.out.println("ATUALIZANDO COMPONENTES");
                    while(result.next()){
                        sqlConnection.updateComponent("Id", listLoanComponents.get(c).getId(), "qtdAvailable", result.getInt("qtdAvailable") - Integer.parseInt(txtQtd.getText()));
                        sqlConnection.updateComponent("Id", listLoanComponents.get(c).getId(), "qtdUnavailable", result.getInt("qtdUnavailable") + Integer.parseInt(txtQtd.getText()));

                    }
                    
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.println("EMPRESTIMO REALIZADO");
            sqlConnection.close();
            App.changeScene(getClass().getResource("/view/HomeScreen.fxml"), (Stage) pane.getScene().getWindow());

        }else{
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("AVISO!");
            alert.setHeaderText("AVISO!");
            alert.setContentText("Preencha todos os campos e selecione os componentes desejados!");
            alert.showAndWait();
        }

    }

    @FXML
    void btnAddComponent(ActionEvent event) {
        component = tblAvailableComponents.getItems().get(selectedItem);

        listLoanComponents.add(new Component(component.getId(), component.getComponent(), Integer.parseInt(txtQtd.getText()), 0));

        obsComponents = FXCollections.observableArrayList(listLoanComponents);
        tblLoanComponents.setItems(obsComponents);

    }

    @FXML
    void tblClick(MouseEvent event) {
        selectedItem = tblAvailableComponents.getSelectionModel().getSelectedIndex();
    }

    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");
        
        result = sqlConnection.getComponent();
        listComponents.clear();
        try{
            while(result.next()){
                if(result.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){
                    listComponents.add(new Component(result.getInt("id"), result.getString("component"),result.getInt("qtdAvailable"), result.getInt("qtdUnavailable")));

                }
            }

        obsComponents = FXCollections.observableArrayList(listComponents);
        tblAvailableComponents.setItems(obsComponents);

        sqlConnection.close();

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblColumnAvailableComponents.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnAvailableQtd.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));

        tblColumnLoanComponents.setCellValueFactory(new PropertyValueFactory<Component, String>("component"));
        tblColumnLoanQtd.setCellValueFactory(new PropertyValueFactory<Component, Integer>("qtdAvailable"));


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
        tblAvailableComponents.setItems(obsComponents);

        sqlConnection.close();
        
    }

}