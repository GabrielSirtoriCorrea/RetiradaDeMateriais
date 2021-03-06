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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.App;
import model.Loan;
import model.SQLConnection;

public class HomeScreenController implements Initializable{

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Loan, String> tblColumnComponent = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnDay = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Loan, Integer> tblColumnQtd = new TableColumn<>();

    @FXML
    private TableColumn<Loan, String> tblColumnStatus = new TableColumn<>();

    @FXML
    private TableView<Loan> tblLoans;

    @FXML
    private TextField txtSearch;

    private SQLConnection sqlConnection;
    private ResultSet resultLoans, resultComponents;
    private Loan loan;
    private List<Loan> listLoans = new ArrayList<>();
    private ObservableList<Loan> obsLoans;
    private int selectedItem = -1;

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
    void tblClick(MouseEvent event) {
        selectedItem = tblLoans.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
    }

    @FXML
    void btnDevolution(ActionEvent event) {
        if(selectedItem != -1){
            loan = tblLoans.getItems().get(selectedItem);

            sqlConnection = new SQLConnection();

            try{
                sqlConnection.updateLoan("Id", loan.getid(), "status", false);
                sqlConnection.updateLoan("Id", loan.getid(), "devolutionDate", new Date(new java.util.Date().getTime()));
                resultComponents = sqlConnection.getComponent("component", loan.getComponent());
                System.out.println(loan.getQuantity());
                while(resultComponents.next()){
                    sqlConnection.updateComponent("Id", resultComponents.getInt("Id"), "qtdAvailable", resultComponents.getInt("qtdAvailable") + loan.getQuantity());
                    sqlConnection.updateComponent("Id", resultComponents.getInt("Id"), "qtdUnavailable", resultComponents.getInt("qtdUnavailable") - loan.getQuantity());

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sqlConnection.close();
            btnSearch(event);
        }
    }

    @FXML
    void btnSearch(ActionEvent event) {
        sqlConnection = new SQLConnection();
        
        resultLoans = sqlConnection.getLoan("status", true);
        listLoans.clear();
        
        try{
            while(resultLoans.next()){

                resultComponents = sqlConnection.getComponent("Id", resultLoans.getInt("componentId"));

                if(resultLoans.getString("name").toLowerCase().startsWith(txtSearch.getText().toLowerCase())||
                    resultComponents.getString("component").toLowerCase().startsWith(txtSearch.getText().toLowerCase())){
                    
                    loan = new Loan(resultLoans.getInt("Id"),
                    resultLoans.getString("name"),
                    resultLoans.getInt("componentId"),
                    resultLoans.getInt("quantity"),
                    resultLoans.getDate("loanDate"),
                    resultLoans.getDate("devolutionDate"),
                    resultLoans.getBoolean("status"));
    
                    listLoans.add(loan);
                }
            }

        obsLoans = FXCollections.observableArrayList(listLoans);
        tblLoans.setItems(obsLoans);

        sqlConnection.close();

        }catch(SQLException e){
            System.out.println("Erro ao pesquisar");
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            tblColumnComponent.setCellValueFactory(new PropertyValueFactory<Loan, String>("component"));
            tblColumnName.setCellValueFactory(new PropertyValueFactory<Loan, String>("name"));
            tblColumnDay.setCellValueFactory(new PropertyValueFactory<Loan, String>("loanDate"));
            tblColumnQtd.setCellValueFactory(new PropertyValueFactory<Loan, Integer>("quantity"));
            tblColumnStatus.setCellValueFactory(new PropertyValueFactory<Loan, String>("status"));
            
            sqlConnection = new SQLConnection();

            resultLoans = sqlConnection.getLoan("status", true);

            while(resultLoans.next()){
                loan = new Loan(resultLoans.getInt("Id"),
                resultLoans.getString("name"),
                resultLoans.getInt("componentId"),
                resultLoans.getInt("quantity"),
                resultLoans.getDate("loanDate"),
                resultLoans.getDate("devolutionDate"),
                resultLoans.getBoolean("status"));

                listLoans.add(loan);
            }

            obsLoans = FXCollections.observableArrayList(listLoans);
            tblLoans.setItems(obsLoans);

            sqlConnection.close();
        } catch (Exception e) {
            System.out.println("Falha ao selecionar dados");
            e.printStackTrace();
        }

    }

    

}