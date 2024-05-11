package carnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import carnet.model.Carnet;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Carnet carnet = new Carnet();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PageAccueil.fxml"));        stage.setTitle("Carnet de notes");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }
}
