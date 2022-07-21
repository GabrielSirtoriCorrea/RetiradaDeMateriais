package model;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    Stage primaryStage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        changeScene(getClass().getResource("/view/HomeScreen.fxml"), primaryStage);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SQLConnection sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");
        System.out.println("CONECTADO");
        //sqlConnection.insertLoan("Arnaldo", 2, 10, new Date(new java.util.Date().getTime()), true);
        //sqlConnection.insertComponent("Arduino", 10, 10);
        //sqlConnection.insertLoan("Carlao", 1, 10, new Date(new java.util.Date().getTime()), true);
        //sqlConnection.insertComponent("Esp", 10, 10);
        System.out.println("DADOS INSERIDOS");

        ResultSet result = sqlConnection.getLoan();

        while(result.next()){
            System.out.println("loanId: " + result.getInt("Id"));
            System.out.println("name: " + result.getString("name"));
            System.out.println("ComponentId: " + result.getInt("componentId"));
            System.out.println("Data: " + dateFormat.format(result.getDate("loanDate")));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");

        result = sqlConnection.getComponent();

        while(result.next()){
            System.out.println("ComponentId: " + result.getInt("Id"));
            System.out.println("Component: " + result.getString("component"));
            System.out.println("Available: " + result.getInt("qtdAvailable"));
            System.out.println("Unavailable: " + result.getInt("qtdUnavailable"));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");

        //sqlConnection.updateLoan("Id", 2, "status", false);
        //sqlConnection.updateComponent("Id", 1, "component", "Raspberry");

        System.out.println("DADOS ATUALIZADOS");

        /*result = sqlConnection.getLoan();

        while(result.next()){
            System.out.println("loanId: " + result.getInt("Id"));
            System.out.println("name: " + result.getString("name"));
            System.out.println("ComponentId: " + result.getInt("componentId"));
            System.out.println("Data: " + dateFormat.format(result.getDate("loanDate")));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");

        result = sqlConnection.getComponent();

        while(result.next()){
            System.out.println("ComponentId: " + result.getInt("Id"));
            System.out.println("Component: " + result.getString("component"));
            System.out.println("Available: " + result.getInt("qtdAvailable"));
            System.out.println("Unavailable: " + result.getInt("qtdUnavailable"));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");

        sqlConnection.deleteLoan("name", "Carlao");
        sqlConnection.deleteComponent("component", "Esp");

        System.out.println("DADOS DELETADOS");

        result = sqlConnection.getLoan();

        while(result.next()){
            System.out.println("loanId: " + result.getInt("Id"));
            System.out.println("name: " + result.getString("name"));
            System.out.println("ComponentId: " + result.getInt("componentId"));
            System.out.println("Data: " + dateFormat.format(result.getDate("loanDate")));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");

        result = sqlConnection.getComponent();

        while(result.next()){
            System.out.println("ComponentId: " + result.getInt("Id"));
            System.out.println("Component: " + result.getString("component"));
            System.out.println("Available: " + result.getInt("qtdAvailable"));
            System.out.println("Unavailable: " + result.getInt("qtdUnavailable"));
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-==-=-==-=-=--=-=--=-=");*/

        sqlConnection.close();
        System.out.println("DESCONECTADO");
        
    }

    public static void changeScene(URL path, Stage stage){
        try {
            Parent root = FXMLLoader.load(path);
            //Stage stage = new Stage();
            Scene scene = new Scene(root, 720, 1280); 
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
