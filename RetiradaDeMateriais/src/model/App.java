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

        SQLConnection sqlConnection = new SQLConnection("src/model/RetiradaDeMateriais.db");
        System.out.println("CONECTADO");
        //sqlConnection.insertLoan("Wellington", 12, 10, new Date(new java.util.Date().getTime()), true);
        sqlConnection.insertComponent("Arduino", 12, 10);
        System.out.println("DADOS INSERIDOS");

        ResultSet result = sqlConnection.getComponent("component", "Arduino");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        /*while(result.next()){
            System.out.println("loanId: " + result.getInt("Id"));
            System.out.println("name: " + result.getString("name"));
            System.out.println("Data: " + dateFormat.format(result.getDate("loanDate")));
        }*/

        while(result.next()){
            System.out.println("ComponentId: " + result.getInt("Id"));
            System.out.println("Component: " + result.getString("component"));
            System.out.println("Available: " + result.getInt("qtdAvailable"));
            System.out.println("Unavailable: " + result.getInt("qtdUnavailable"));
        }


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
