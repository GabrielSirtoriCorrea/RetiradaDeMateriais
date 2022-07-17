package model;

import java.io.IOException;
import java.net.URL;

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
