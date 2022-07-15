package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlfile = new FXMLLoader(getClass().getResource("/view/History.fxml"));
        Parent root = fxmlfile.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
