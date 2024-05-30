package carnet;

import carnet.controleur.page.ControleurNav;
import carnet.exceptions.SaveNotWork;
import carnet.model.*;
import carnet.outils.JsonManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Carnet carnet;
    private JsonManager jsonManager;
    private String path;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        carnet = new Carnet();
        jsonManager = new JsonManager(carnet.getGestionnairePage());
        path = "/tmp/carnet.json";

        try {
            jsonManager.load(path);
        } catch (Exception e) {
            System.out.println("Impossible de charger le fichier de sauvegarde, un nouveau carnet sera créé à la fermeture de l'application.");
        }


        ControleurNav controleurNav = new ControleurNav(carnet, stage);
        controleurNav.initPages();

        stage.setTitle("Carnet de voyages");

        stage.setMinHeight(600);
        stage.setMinWidth(800);

        carnet.notifierObservateurs();

        stage.show();

    }

    @Override
    public void stop() {
        try {
            jsonManager.save(path);
        } catch (SaveNotWork ex) {
            throw new RuntimeException(ex);
        }
    }
}
