package carnet.controleur.page;

import carnet.controleur.Observateur;
import carnet.exceptions.ProblemePage;
import carnet.model.Carnet;
import carnet.model.ModeAjouterPage;
import carnet.model.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ControleurNav implements Observateur {

    private Carnet carnet;

    private Stage stage;

    private Scene scene;

    private HashMap<String, Scene> scenes;


    public ControleurNav(Carnet carnet, BorderPane mainPane, Stage stage) {
        this.carnet = carnet;
        this.stage = stage;
        this.carnet.ajouterObservateur(this);

        this.scenes = new HashMap<>();

        initPages();

        carnet.notifierObservateurs();
    }

    private void initPages() {
        try {
            loadPage("Accueil", "/fxml/PageAccueil.fxml", new ControleurPageAccueil(carnet));
            loadPage("TextPhoto", "/fxml/PageTextPhoto.fxml", new ControleurPageTextPhoto(carnet));
            loadPage("TextPhotoMap", "/fxml/PageTextPhotoMap.fxml", new ControleurPageTextPhotoMap(carnet));
            loadPage("ModeAjouterPage", "/fxml/PageAddPage.fxml", new ControleurModeAjouterPage(carnet));
            loadPage("ModeVignette", "/fxml/ModeVignette.fxml", new ControleurModeVignette(carnet));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage(String name, String fxmlPath, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(iC -> controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scenes.put(name, scene);
    }

    @Override
    public void reagir() {
        if (carnet.getPageCourante().estModeVignette()) {
            stage.setScene(scenes.get("ModeVignette"));
        } else if (carnet.getPageCourante().estModeAjouterPage()) {
            stage.setScene(scenes.get("ModeAjouterPage"));
        } else if (carnet.getPageCourante().estTextPhoto()){
            stage.setScene(scenes.get("TextPhoto"));
        } else if (carnet.getPageCourante().estTextPhotoMap()){
            stage.setScene(scenes.get("TextPhotoMap"));
        } else {
            stage.setScene(scenes.get("Accueil"));
        }
    }
}