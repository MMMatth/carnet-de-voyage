package carnet;

import carnet.controleur.ControleurPageAccueil;
import carnet.model.Page;
import carnet.model.PageAccueil;
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
        carnet.addPage(new PageAccueil("Titre", "Auteur", null, null, "Participant 1", "Participant 2"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PageAccueil.fxml"));
        loader.setControllerFactory(iC -> new ControleurPageAccueil(carnet));
        Parent root = loader.load();

        stage.setTitle("Carnet de notes");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }
}
